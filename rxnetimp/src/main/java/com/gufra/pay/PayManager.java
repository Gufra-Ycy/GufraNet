package com.gufra.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

/**
 * @author yinchaoyin
 * 支付管理
 * */
public class PayManager {

    private static String PAY_STATUS_SUCCESS = "9000";
    private static final String TAG = "gufra.PayManager";
    private static PayTask payTask = null;
    private static Map<String,String>payResult;
    private static PayCallback payCallback;
    private static Activity mActivity;

    public static PayManager getInstance(Activity activity){
        mActivity = activity;
        return PayManagerHolder.payManager;
    }
    private static class PayManagerHolder{
        private static PayManager payManager = new PayManager();
    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            AliPayResult payResult = new AliPayResult((Map<String,String>)msg.obj);
            String status = payResult.getResultStatus();
            String result = payResult.getResult();
            String memo = payResult.getMemo();
            if (PAY_STATUS_SUCCESS.equals(status)){
                payCallback.paySuccess(result);
            }else {
                payCallback.payFailed(status,memo);
            }
        }
    };

    public void pay(final String order, PayCallback callback){
        payCallback = callback;
        payTask = new PayTask(mActivity);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                payResult = payTask.payV2(order,true);
                Log.d(TAG,"pay result"+payResult.toString());
                Message message = new Message();
                message.obj = payResult;
                handler.sendMessage(message);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(runnable);
        payThread.start();
    }
}

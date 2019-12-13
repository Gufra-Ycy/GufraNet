package com.gufra.net.rxnetimp;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

public class NetManager {

    private static final NetManager ourInstance = new NetManager();

    private static String TAG = "gufra.NetManager";
    IResultCallback resultCallback = null;
    RequestQueue requestQueue = null;
    private static Context mContext = null;
    public static NetManager getInstance(Context context) {
        mContext = context;
        return ourInstance;
    }

    private NetManager() {

    }
    /**初始化queue*/
    private void init(){
        requestQueue = Volley.newRequestQueue(mContext);

    }

    public void post2Volley(String url, Map<String,Object> params, final IResultCallback callback){
        Log.d(TAG,"post2Volley");
        init();
        StringRequest request =new StringRequest(POST, url, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.successed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.failed(error.toString());
            }
        });
        request.setTag(TAG);
        requestQueue.add(request);
    }

    public void get2Volley(String url, Map<String,Object> params, final IResultCallback callback){
        Log.d(TAG,"get2Volley");
        init();
        StringRequest request =new StringRequest(GET, url, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.successed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.failed(error.toString());
            }
        });
        request.setTag(TAG);
        requestQueue.add(request);
    }

    /**结束请求*/
    public void onStop(){
        if (requestQueue != null){
            requestQueue.cancelAll(TAG);
        }
    }
}

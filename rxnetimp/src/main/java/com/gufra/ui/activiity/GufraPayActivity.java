package com.gufra.ui.activiity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gufra.net.rxnetimp.R;
import com.gufra.pay.PayCallback;
import com.gufra.pay.PayManager;

/**
 * @author yinchaoyin
 * 支付activity
 * */

public class GufraPayActivity extends AppCompatActivity {

    Button mBtnAli;
    TextView mTextPayInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gufra_pay);
        initView();

        mBtnAli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayManager.getInstance(GufraPayActivity.this).pay(System.currentTimeMillis() + "", new PayCallback() {
                    @Override
                    public void paySuccess(String orderid) {
                        Toast.makeText(GufraPayActivity.this,"支付成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void payFailed(String code, String msg) {
                        Toast.makeText(GufraPayActivity.this,"支付失败"+code+msg,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    public void initView(){
        mTextPayInfo = (TextView)findViewById(R.id.txt_info_pay);
        mBtnAli = (Button)findViewById(R.id.btn_ali_pay);
    }
}

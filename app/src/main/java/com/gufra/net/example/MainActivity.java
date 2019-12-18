package com.gufra.net.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gufra.net.base.utils.FileManager;
import com.gufra.net.rxnetimp.IResultCallback;
import com.gufra.net.rxnetimp.NetManager;
import com.gufra.ui.activiity.TabActivity;
import com.gufra.ui.view.FloatActionView;
import com.gufra.ui.view.VerifyCodeView;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {

    String url = "https://api.lovelive.tools/api/SweetNothings/Serialization/:serializationType";

    TextView txtResult;
    VerifyCodeView codeView;
    FloatActionView floatView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = (TextView)findViewById(R.id.txt_result);
        codeView = (VerifyCodeView)findViewById(R.id.view_code);
        codeView.setOnClickReFresh(true);

        floatView = (FloatActionView)findViewById(R.id.view_float);
        floatView.bringToFront();
        FileManager.writeFile(this,"gufra.txt","This is gufra's Test!!!");
    }


    /**存储*/
    public void OnQ(View view) {
        txtResult.setText(FileManager.readFile(this,"gufra.txt"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        NetManager.getInstance(this).onStop();
    }


    public void OnTab(View view) {
        startActivity(new Intent(MainActivity.this, TabActivity.class));
    }
}

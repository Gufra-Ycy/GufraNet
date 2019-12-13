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

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {

    String url = "https://api.lovelive.tools/api/SweetNothings/Serialization/:serializationType";

    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = (TextView)findViewById(R.id.txt_result);
        FileManager.writeFile(this,"gufra.txt","This is gufra's Test!!!");
    }


    /**网络请求*/
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

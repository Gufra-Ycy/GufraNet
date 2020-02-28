package com.gufra.net.base.log;

import android.util.Log;

public class GufraLog {

    private boolean isDebug = false;

    public void setDebug(boolean debug){
        isDebug = debug;
    }

    public void i(String tag, String msg){
        Log.i(tag,msg);
    }
    public void d(String tag, String msg){
        Log.d(tag,msg);
    }
    public void w(String tag, String msg){
        Log.w(tag,msg);
    }
    public void e(String tag, String msg){
        Log.e(tag,msg);
    }
}

package com.gufra.net.base.utils;

import android.content.Context;
import android.provider.Settings;

public class AppUtils {

    /**获取AndroidID*/
    public static String getAndroidId(Context context){
        String androidId = "";
        try {
            androidId = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
        }catch (Exception e){
            androidId = "";
        }
        return androidId;
    }
}

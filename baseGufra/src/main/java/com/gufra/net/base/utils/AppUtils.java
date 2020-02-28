package com.gufra.net.base.utils;

import android.content.Context;
import android.provider.Settings;
/**
 *
 * */
public class AppUtils {
    private static Context mContext;
    private AppUtils(){
    };
    public static AppUtils getInstance(Context context) {
        mContext = context;
        //第一次调用getInstance方法时才加载SingletonHolder并初始化sInstance
        return AppUtilsHolder.sInstance;
    }
    //静态内部类
    private static class AppUtilsHolder {
        private static final AppUtils sInstance = new AppUtils();
    }

    /**获取AndroidID*/
    public  String getAndroidId(){
        String androidId = "";
        try {
            androidId = Settings.Secure.getString(mContext.getContentResolver(),Settings.Secure.ANDROID_ID);
        }catch (Exception e){
            androidId = "";
        }
        return androidId;
    }

    /**获取资源id*/
    public int getResId(String name, String defType){
        return mContext.getResources().getIdentifier(name,defType,mContext.getPackageName());
    }
}

package com.gufra.net.base.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * 签名加密工具
 */
public class SignUtils {

    /**BASE64
     * 编码
     */
    public static String encodeWord(String s){
        try {
            return Base64.encodeToString(s.getBytes("utf-8"),Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return s;
        }
    }
    /**BASE64
     * 解密
     */
    public static String decodeWord(String s){
        try {
            return new String(Base64.decode(s,Base64.NO_WRAP),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }


}

package com.gufra.net.rxnetimp;

import java.util.Map;

public interface IRequestCallback {

    void post(String url, Map<String,Object> params, IResultCallback callback);
    void get(String url, Map<String,Object> params, IResultCallback callback);
}

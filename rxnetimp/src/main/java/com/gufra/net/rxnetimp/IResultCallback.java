package com.gufra.net.rxnetimp;

public interface IResultCallback {
    void successed(String request);
    void failed(String message);
}

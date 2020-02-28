package com.gufra.pay;

public interface PayCallback {
    void paySuccess(String orderid);
    void payFailed(String code, String msg);
}

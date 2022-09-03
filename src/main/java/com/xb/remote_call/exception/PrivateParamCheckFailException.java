package com.xb.remote_call.exception;

/**
 * @Author KAJ
 * @Date 2022/9/2  11:33
 * @Description TODO
 **/
public class PrivateParamCheckFailException extends RemoteCallException {

    public PrivateParamCheckFailException(String attributeName, String cause) {
        this.attributeName = attributeName;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return "private params {" + attributeName + "} checked fail, cause by: " + cause;
    }
}

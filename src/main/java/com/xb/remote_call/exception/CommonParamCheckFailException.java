package com.xb.remote_call.exception;

/**
 * @Author KAJ
 * @Date 2022/9/2  11:33
 * @Description TODO
 **/
public class CommonParamCheckFailException extends RemoteCallException {

    public CommonParamCheckFailException(String attributeName, String cause) {
        this.attributeName = attributeName;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return "common params {" + attributeName + "} checked fail, cause by: " + cause;
    }
}

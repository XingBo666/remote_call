package com.xb.remote_call.exception;

/**
 * @Author KAJ
 * @Date 2022/9/3  7:20
 * @Description 缺少必要的参数异常
 **/
public class NecessaryParamLackException extends RemoteCallException {

    public NecessaryParamLackException(String attributeName, String cause) {
        this.attributeName = attributeName;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return attributeName + " exception, cause" + cause;
    }
}

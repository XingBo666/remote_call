package com.xb.remote_call.core;

import com.xb.remote_call.exception.RemoteCallException;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @Author KAJ
 * @Date 2022/9/4  8:41
 * @Description 提供简单Get请求
 **/
public class SimpleGetRequest extends RemoteCallCore {


    public <T> T get(String url, Class<T> tClass) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, RemoteCallException {
        this.REQUEST_METHOD = "GET";
        this.REQUEST_URL = url;
        T t = sendMessage(tClass);

        return t;
    }


    @Override
    public void encryption() {


    }

    @Override
    public boolean checkPrivateParams() {

        return true;
    }
}

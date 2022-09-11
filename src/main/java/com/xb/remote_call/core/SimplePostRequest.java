package com.xb.remote_call.core;

import com.xb.remote_call.exception.RemoteCallException;
import com.xb.remote_call.helper.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author KAJ
 * @Date 2022/9/5  13:05
 * @Description 简单post请求
 **/
public class SimplePostRequest extends RemoteCallCore {

    Logger logger = LoggerFactory.getLogger("com.xb.remote_call.core");

    public <T> T post(String url, Object params, Class<T> tClass) {

        this.REQUEST_METHOD = "POST";
        this.REQUEST_URL = url;

        try {
            this.postParams = ObjectHelper.getObjParamsMap(params, true);
        } catch (Exception e) {
            logger.error("解析对象失败");
            e.printStackTrace();
        }

        try {
            T t = this.sendMessage(tClass);
            return t;
        } catch (Exception e) {
            logger.info("发送请求失败，失败原因：");
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void encryption() {

    }

    @Override
    public boolean checkPrivateParams() throws RemoteCallException {
        return true;
    }
}

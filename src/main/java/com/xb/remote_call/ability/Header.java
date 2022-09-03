package com.xb.remote_call.ability;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author KAJ
 * @Date 2022/9/2  14:01
 * @Description 设置请求头的能力
 * 可实现此类，获得修改请求头的能力
 **/
public interface Header {

    Map<String, String> headers = new HashMap<>();

    public void setHeader(String key, String value);

    public void clearHeader(String key, String value);

    public void removeHeader(String key, String value);
}

package com.xb.remote_call.core;


import com.alibaba.fastjson.JSON;
import com.xb.remote_call.ability.Encryption;
import com.xb.remote_call.ability.Header;
import com.xb.remote_call.annotation.JsonIgnore;
import com.xb.remote_call.exception.CommonParamCheckFailException;
import com.xb.remote_call.exception.RemoteCallException;
import com.xb.remote_call.helper.HttpHelper;
import com.xb.remote_call.helper.ObjectHelper;
import com.xb.remote_call.helper.RequestHelper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

/**
 * @Author KAJ
 * @Date 2022/9/2  11:10
 * @Description 远程调用的过程拆分
 * 1. 设置请求路径、
 * 2. 设置请求头信息
 * 3. 拼接Get参数
 * 4. 设置Post参数
 * 5. 参数校验
 * 6. 使用自定义加密规则
 * 7. 发送请求并接收返回结果
 * 8. 填充各个结果集
 **/
public abstract class RemoteCallCore implements Header, Encryption {

    /**
     * 配置项
     */

    /* 是否序列化空白的字符串 */
    protected boolean serializeBlankChar = true;


    /**
     * 请求头
     */
    protected Map<String, String> headers;

    protected Map<String, Object> getParams;

    protected Map<String, Object> postParams;

    /** 定义公共的一些参数 **/

    /**
     * 请求路径
     **/
    @JsonIgnore
    protected String REQUEST_URL;

    /**
     * 内容类型
     */
    @JsonIgnore
    protected String CONTENT_TYPE = "application/json;charset=utf-8";

    /**
     * 请求类型
     */
    @JsonIgnore
    protected String REQUEST_METHOD;


    public <T> T sendMessage(Class<T> tClass) throws RemoteCallException, IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        /* 校验请求头等信息 */
        this.checkCommonParams();
        this.checkPrivateParams();
        /* 拼接请求路径 */
        String requestUrl = RequestHelper.splicingRequestUrl(REQUEST_URL, getParams);

        //  发送请求
        String resultStr = HttpHelper.remoteInvoking(requestUrl, JSON.toJSONString(postParams), CONTENT_TYPE, REQUEST_METHOD, headers);

        T parse = ObjectHelper.parse(resultStr, tClass);

        return parse;
    }

    /**
     * @Author KAJ
     * @Date 2022/9/2 13:21
     * @Description //校验公共的参数
     */
    public boolean checkCommonParams() throws RemoteCallException {
        if (StringUtils.isBlank(REQUEST_URL)) {
            throw new CommonParamCheckFailException("REQUEST_URL", "request url should be not null!");
        }

        if (StringUtils.isBlank(CONTENT_TYPE)) {
            throw new CommonParamCheckFailException("CONTENT_TYPE", "contentType should be not null!");
        }

        if (StringUtils.isBlank(REQUEST_METHOD)) {
            throw new CommonParamCheckFailException("REQUEST_METHOD", "request method should be not null");
        }

        return true;
    }

    /* 校验私有的参数，需要自己实现 */
    public abstract boolean checkPrivateParams() throws RemoteCallException;

    /**
     * @Author KAJ
     * @Date 2022/9/2 14:07
     * @Description //设置Get请求参数,以对象的形式进行设置
     */
    public void setGetParams(Object params) {
        try {
            this.getParams = ObjectHelper.getObjParamsMap(params, serializeBlankChar);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setGetParams(Map<String, Object> map) {
        this.getParams = map;
    }

    public void clearGetParams() {
        this.getParams.clear();
    }

    /**
     * @Author KAJ
     * @Date 2022/9/2 17:35
     * @Description //设置Post参数，是application/json格式的
     */
    public void setPostParams(Object params) {
        try {
            this.postParams = ObjectHelper.getObjParamsMap(params, serializeBlankChar);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setPostParams(Map<String, Object> map) {
        this.postParams = map;
    }

    public void clearPostParams() {
        this.postParams.clear();
    }


    /* 设置是否序列化空白字符串的能力 */
    public void setSerializeBlankChar(boolean serializeBlankChar) {
        this.serializeBlankChar = serializeBlankChar;
    }


    @Override
    public void setHeader(String key, String value) {
        headers.put(key, value);
    }

    @Override
    public void clearHeader(String key, String value) {
        headers.clear();
    }

    @Override
    public void removeHeader(String key, String value) {
        headers.remove(key);
    }


}

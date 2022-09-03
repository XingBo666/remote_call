package com.xb.remote_call.helper;

import com.xb.remote_call.exception.NecessaryParamLackException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author KAJ
 * @Date 2022/9/2  17:50
 * @Description TODO
 **/
public class RequestHelper {

    /* 根据请求地址和参数拼接url */
    public static String splicingRequestUrl(String baseRequestUrl, Map<String, Object> getParams) {
        if (getParams == null || getParams.isEmpty()) {
            return baseRequestUrl;
        }

        Set<String> getParamsKeySet = getParams.keySet();

        StringBuilder sb = new StringBuilder();

        sb.append(baseRequestUrl);
        sb.append("?");

        boolean firstFlag = true;

        for (String s : getParamsKeySet) {
            if (firstFlag) {
                sb.append(s);
                sb.append("=");
                sb.append(getParams.get(s));

                firstFlag = false;
            } else {
                sb.append("&");
                sb.append(s);
                sb.append("=");
                sb.append(String.valueOf(getParams.get(s)));
            }
        }

        return sb.toString();
    }

    public static String splicingRequestUrl(String baseRequestUrl, Map<String, Object> getParams, Map<String, Object> extraParams) throws NecessaryParamLackException {
        if ((getParams == null || getParams.isEmpty()) && (extraParams != null && !extraParams.isEmpty())) {
            throw new NecessaryParamLackException("getParams", " is empty, but extraParams is not empty, please check your input");
        }
        StringBuilder sb = new StringBuilder();

        sb.append(splicingRequestUrl(baseRequestUrl, getParams));

        if (extraParams == null || extraParams.size() == 0) {
            //  无需拼接特定参数，直接调用两个参数的方法
            return sb.toString();
        }

        Set<String> extraParamsSet = extraParams.keySet();

        for (String paramName : extraParamsSet) {
            sb.append("&");
            sb.append(paramName);
            sb.append("=");
            sb.append(String.valueOf(extraParams.get(paramName)));
        }

        return sb.toString();
    }

    public static void main(String[] args) throws NecessaryParamLackException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 6);


        Map<String, Object> extraParams = new HashMap<>();
        extraParams.put("hello", "world");

        String result = RequestHelper.splicingRequestUrl("http://baidu.com", map, null);

        System.out.println(result);
    }
}

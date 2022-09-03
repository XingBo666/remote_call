package com.xb.remote_call.helper;

import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @Author KAJ
 * @Date 2022/9/3  7:50
 * @Description 发起请求工具类
 **/
public class HttpHelper {

    public static final String DEFAULT_CONTENT_TYPE = "application/json;charset=utf-8";

    public static final String DEFAULT_REQUEST_METHOD = "POST";

    public static String remoteInvoking(String requestUrl, String data, String contentType, String requestMethod) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {

        String result = "";

        /* 设置默认参数 */
        contentType = StringUtils.isBlank(contentType) ? DEFAULT_CONTENT_TYPE : contentType;
        requestMethod = StringUtils.isBlank(requestMethod) ? DEFAULT_REQUEST_METHOD : requestMethod.toUpperCase();
        data = StringUtils.isBlank(data) ? "{}" : data;

        URL url = new URL(requestUrl);
        HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();

        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
        TrustManager[] tm = {new MyX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();


        /* post请求 */
        httpURLConnection.setSSLSocketFactory(ssf);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);

        /* 使用Post请求时， 必须禁用缓存 */
        httpURLConnection.setRequestMethod(requestMethod);
        httpURLConnection.setUseCaches(false);

        httpURLConnection.setInstanceFollowRedirects(true);

        /* 设置内容类型 */
        httpURLConnection.setRequestProperty("Content-Type", contentType);

        httpURLConnection.connect();

        /* 通过输入流输入 */
        if ("POST".equals(requestMethod)) {
            DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.write(data.getBytes("utf-8"));
            dos.flush();
            dos.close();
        }


        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream())
        );

        String b = "";
        StringBuilder sb = new StringBuilder();
        while ((b = bufferedReader.readLine()) != null) {
            sb.append(b);
        }

        result = sb.toString();

        bufferedReader.close();

        return result;
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        String url = "https://api.savevip.cn/pdd/boutique?pageNo=1";

        String s = remoteInvoking(url, null, null, "GET");

        System.out.println(s);
    }

}

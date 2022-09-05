package com.xb.remote_call;

import com.alibaba.fastjson.JSONObject;
import com.xb.remote_call.ability.SerializeChildren;
import com.xb.remote_call.core.SimpleGetRequest;
import com.xb.remote_call.exception.RemoteCallException;
import com.xb.remote_call.helper.ObjectHelper;
import com.xb.remote_call.test.SaveVipResponse;
import com.xb.remote_call.test.Son;
import com.xb.remote_call.test.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class RemoteCallApplicationTests {

    private ObjectHelper objectHelper = new ObjectHelper();

    @Test
    void contextLoads() {
        try {
            Son son = new Son("123");
            User user = new User("zhangsan", null, 1);
            user.setSon(son);
            Map<String, Object> map = objectHelper.getObjParamsMap(user, true);

            Set<String> set = map.keySet();
            for (String key : set) {
                System.out.println("key: " + key + ", value: " + map.get(key));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void test02() {
        Son son = new Son("hello");

        System.out.println(son instanceof SerializeChildren);
    }

    @Test
    void test03() throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, RemoteCallException {
        SimpleGetRequest simpleGetRequest = new SimpleGetRequest();
        SaveVipResponse response = simpleGetRequest.get("https://api.savevip.cn/pdd/boutique?pageNo=1", SaveVipResponse.class);

        System.out.println(response.toString());
    }

}

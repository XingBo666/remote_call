package com.xb.remote_call;

import com.xb.remote_call.ability.SerializeChildren;
import com.xb.remote_call.helper.ObjectHelper;
import com.xb.remote_call.test.Son;
import com.xb.remote_call.test.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}

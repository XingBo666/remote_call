package com.xb.remote_call.helper;

import com.alibaba.fastjson.JSONObject;
import com.xb.remote_call.ability.SerializeChildren;
import com.xb.remote_call.annotation.JsonIgnore;
import com.xb.remote_call.test.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author KAJ
 * @Date 2022/9/2  14:09
 * @Description 对象工具类
 **/
public class ObjectHelper {

    /**
     * @Author KAJ
     * @Date 2022/9/2 14:13
     * @Description //使用反射，获取对象的所有属性
     */
    public static Map<String, Object> getObjParamsMap(Object instance, boolean serializeBlankChar) throws IllegalAccessException {

        /* 创建一个用来存储结果集的map */
        Map<String, Object> result = new HashMap<>();

        Class<?> instanceClass = instance.getClass();

        Field[] declaredFields = instanceClass.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            String name = declaredField.getName();

            // 是否忽略此字段
            boolean ignoreField = ignoreField(declaredField);

            /* 如果是忽略字段，那么不序列化此类 */
            if (!ignoreField) {
                Object fieldValue = declaredField.get(instance);

                /* 如果属性值不存在，那么给他赋空字符串 */
                if (fieldValue == null) {
                    fieldValue = "";
                } else if (fieldValue instanceof String && StringUtils.isBlank((String) fieldValue)) {
                    fieldValue = "";
                } else if (fieldValue instanceof SerializeChildren) {
                    //  如果该属性实现了此接口，那么表明这是一个父类，继续序列化它的属性
                    fieldValue = getObjParamsMap(fieldValue, serializeBlankChar);
                }

                if ("".equals(fieldValue) && !serializeBlankChar) {
                    continue;
                }
                result.put(name, fieldValue);
            } else {
                continue;
            }
        }
        return result;
    }


    /**
     * @Author KAJ
     * @Date 2022/9/2 14:20
     * @Description //检查是否忽略此字段
     * @Version 1.0 只要value属性是true  就忽略
     */
    public static boolean ignoreField(Field field) {
        JsonIgnore jsonIgnore = field.getAnnotation(JsonIgnore.class);

        if (jsonIgnore == null) {
            return false;
        }

        return jsonIgnore.value();
    }


    /**
     * @Author KAJ
     * @Date 2022/9/3 17:15
     * @Description //解析为对象,如果对象里包含对象，那么继续解析
     * @Version 1.0 只实现类中固定的类
     */
    public static <T> T parse(String jsonStr, Class<T> tClass) {
        T t = JSONObject.parseObject(jsonStr, tClass);

        return t;
    }
}



package com.xb.remote_call.test;

import com.xb.remote_call.ability.SerializeChildren;

/**
 * @Author KAJ
 * @Date 2022/9/2  16:36
 * @Description TODO
 **/
public class User {

    private String username;

    private String password;

    private Integer sex;

    private Son son;

    public void setSon(Son son) {
        this.son = son;
    }

    public User(String username, String password, Integer sex) {
        this.username = username;
        this.password = password;
        this.sex = sex;
    }
}


package com.xb.remote_call.test;

import com.xb.remote_call.ability.SerializeChildren;

public class Son implements SerializeChildren {
    private String age;

    public Son(String age) {
        this.age = age;
    }
}
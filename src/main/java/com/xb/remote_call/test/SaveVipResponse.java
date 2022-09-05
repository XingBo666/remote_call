package com.xb.remote_call.test;

import java.util.List;

/**
 * @Author KAJ
 * @Date 2022/9/4  9:17
 * @Description TODO
 **/
public class SaveVipResponse {

    private Integer code;

    private List<PddResponse> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}

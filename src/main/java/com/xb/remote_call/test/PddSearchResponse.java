package com.xb.remote_call.test;

import java.util.List;

/**
 * @Author KAJ
 * @Date 2022/9/5  13:22
 * @Description TODO
 **/
public class PddSearchResponse extends BaseResult {

    private List<PddResponse> data;


    @Override
    public List<PddResponse> getData() {
        return data;
    }

    public void setData(List<PddResponse> data) {
        this.data = data;
    }


}

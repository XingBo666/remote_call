package com.xb.remote_call.test;

/**
 * @Author KAJ
 * @Date 2022/9/5  13:16
 * @Description TODO
 **/
public class PddRequest {

    public PddRequest(Integer pageNo, Integer pageSize, String keyword) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.keyword = keyword;
    }

    private Integer pageNo;

    private Integer pageSize;

    private String keyword;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}

package com.xb.remote_call.test;

/**
 * @Author KAJ
 * @Date 2022/9/4  9:17
 * @Description TODO
 **/
public class PddResponse {

    private String cover;

    private Integer amount;

    private String nickName;

    private Integer price;

    private Integer oldPrice;

    private String sign;

    private String title;

    private Integer promotionRate;


    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Integer oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(Integer promotionRate) {
        this.promotionRate = promotionRate;
    }
}

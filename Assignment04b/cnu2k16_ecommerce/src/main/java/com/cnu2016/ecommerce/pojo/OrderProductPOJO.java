package com.cnu2016.ecommerce.pojo;

/**
 * Created by vipulj on 11/07/16.
 */
public class OrderProductPOJO {
    private Integer product_id;
    private Integer qty;

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }
}

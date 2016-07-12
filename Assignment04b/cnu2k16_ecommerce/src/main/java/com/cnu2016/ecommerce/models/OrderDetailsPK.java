package com.cnu2016.ecommerce.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by vipulj on 09/07/16.
 */
@Embeddable
public class OrderDetailsPK implements Serializable{
    private Integer orderId;
    private Integer productId;

    public OrderDetailsPK(Integer orderId, Integer productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderDetailsPK() {}


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

}

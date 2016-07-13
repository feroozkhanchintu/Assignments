package com.cnu2016.ecommerce.pojo;

/**
 * Created by vipulj on 10/07/16.
 */
public enum OrderEnum {
    INPROCESS("IN PROCESS"),
    SHIPPED("SHIPPED"),
    CANCELLED("CANCELLED"),
    ON_HOLD("ON HOLD");

    private String status;

    OrderEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
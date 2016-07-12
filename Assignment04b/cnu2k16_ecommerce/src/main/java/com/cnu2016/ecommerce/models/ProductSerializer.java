package com.cnu2016.ecommerce.models;

/**
 * Created by vipulj on 08/07/16.
 */
public class ProductSerializer {
    private int id;
    
    public ProductSerializer(Product product) {
        this.id = product.getProductID();
        this.code = product.getProductCode();
        this.description = product.getProductDescription();
    }

    public ProductSerializer(int id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String code;
    private String description;

    public ProductSerializer(){}
}

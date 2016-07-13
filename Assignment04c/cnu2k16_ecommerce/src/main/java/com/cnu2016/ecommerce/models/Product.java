package com.cnu2016.ecommerce.models;

/**
 * Created by vipulj on 07/07/16.
 */

import com.cnu2016.ecommerce.pojo.ProductSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="PRODUCT_ID")
    private Integer productID;
    @Column(name="PRODUCT_DESCRIPTION")
    private String productDescription;
    @Column(name="COST_PRICE")
    private Double costPrice;
    @Column(name="SELLING_PRICE")
    private Double sellingPrice;
    @Column(name="QUANTITY_IN_STOCK")
    private Integer quantityInStock;
    @Column(name="PRODUCT_CODE")
    private String productCode;
    @Column(name="PRODUCT_NAME")
    private String productName;
    @Column(name="IS_AVAILABLE")
    private Boolean isAvailable;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    protected Product() {}

    public Product(int productID, String productDescription, Double costPrice,
                   Double sellingPrice, Integer quantityInStock,
                   String productCode, String productName,
                   Boolean isAvailable) {
        this.productID = productID;
        this.productDescription = productDescription;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.quantityInStock = quantityInStock;
        this.productCode = productCode;
        this.productName = productName;
        this.isAvailable = isAvailable;
    }

    public Product(ProductSerializer productSerializer) {
        this.productID = productSerializer.getId();
        this.productDescription = productSerializer.getDescription();
        this.productCode = productSerializer.getCode();
        this.quantityInStock = productSerializer.getQty();
        this.isAvailable = Boolean.TRUE;
    }

}
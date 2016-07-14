package com.cnu2016.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by vipulj on 08/07/16.
 */
@Entity
@Table(name="ORDER_DETAILS")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ORDER_DETAILS_ID")
    private Integer orderDetailsId;

    @Column(name="ORDER_ID")
    private Integer orderId;

    @Column(name="PRODUCT_ID")
    private Integer productId;

    @Column(name="QUANTITY_ORDERED")
    public Integer quantityOrdered;

    @Column(name="COST_PRICE")
    public Double costPrice;

    @Column(name="SELLING_PRICE")
    public Double sellingPrice;

    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    @JsonBackReference
    private Orders orders;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    @JsonBackReference
    private Product products;

    protected OrderDetails() {}

    public OrderDetails(Integer orderId, Integer productId, Integer quantityOrdered, Double costPrice, Double sellingPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantityOrdered = quantityOrdered;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
    }

    public OrderDetails(Integer orderDetailsId, Integer orderId,
                        Integer productId, Integer quantityOrdered, Double costPrice, Double sellingPrice) {
        this.orderDetailsId = orderDetailsId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantityOrdered = quantityOrdered;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
    }


    public OrderDetails(Integer orderId, Integer productId,
                        Integer quantityOrdered, Double costPrice, Double sellingPrice, Orders orders, Product products) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantityOrdered = quantityOrdered;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.orders = orders;
        this.products = products;
    }

    public OrderDetails(Integer orderDetailsId, Integer orderId, Integer productId,
                        Integer quantityOrdered, Double costPrice, Double sellingPrice, Orders orders, Product products) {
        this.orderDetailsId = orderDetailsId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantityOrdered = quantityOrdered;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.orders = orders;
        this.products = products;
    }

    public Integer getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Integer orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
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

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }


}

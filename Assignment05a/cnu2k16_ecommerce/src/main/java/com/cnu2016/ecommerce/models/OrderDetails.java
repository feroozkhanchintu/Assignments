package com.cnu2016.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by vipulj on 08/07/16.
 */
@Entity
@Table(name="ORDER_DETAILS")
public class OrderDetails {

    @EmbeddedId
    private OrderDetailsPK orderDetailsPk;

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
    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product products;

    protected OrderDetails() {}

    public OrderDetails(OrderDetailsPK orderDetailsPk, Integer quantityOrdered, Double costPrice,
                        Double sellingPrice, Orders orders, Product products) {
        this.orderDetailsPk = orderDetailsPk;
        this.quantityOrdered = quantityOrdered;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.orders = orders;
        this.products = products;
    }

    public OrderDetails(OrderDetailsPK orderDetailsPk, Integer quantityOrdered) {
        this.orderDetailsPk = orderDetailsPk;
        this.quantityOrdered = quantityOrdered;
    }


    public OrderDetailsPK getOrderDetailsPk() {
        return orderDetailsPk;
    }

    public void setOrderDetailsPk(OrderDetailsPK orderDetailsPk) {
        this.orderDetailsPk = orderDetailsPk;
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

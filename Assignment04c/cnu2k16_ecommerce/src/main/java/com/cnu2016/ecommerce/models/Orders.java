package com.cnu2016.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by vipulj on 08/07/16.
 */
@Entity
@Table(name="ORDERS")
public class Orders {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ORDER_ID")
    private Integer orderId;

    @Column(name="ORDER_DATE")
    private Date orderDate;

    @Column(name="STATUS")
    private String status;

    @Column(name="DELETED")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User userDetails;

    @OneToMany(mappedBy = "orders")
    @ElementCollection(targetClass=OrderDetails.class)
    @JsonManagedReference
    private Set<OrderDetails> orderDetails;

    public Orders(Integer orderId, Integer userId, Date orderDate, String status, User userDetails, boolean deleted) {
        this.orderId = orderId;
        this.userDetails = userDetails;
        this.orderDate = orderDate;
        this.status = status;
        this.deleted = deleted;
    }

    public Orders(){}

    public Orders(Date date, String status) {
        this.orderDate = date;
        this.status = status;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public User getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(User userDetails) {
        this.userDetails = userDetails;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}

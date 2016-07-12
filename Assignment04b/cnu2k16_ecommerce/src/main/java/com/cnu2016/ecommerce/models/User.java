package com.cnu2016.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vipulj on 08/07/16.
 */
@Entity
@Table(name="USER_DETAILS")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="USER_ID")
    private Integer userId;

    @Column(name="COMPANY_NAME")
    private String companyName;

    @Column(name="CONTACT_FIRST_NAME")
    private String contactFirstName;

    @Column(name="CONTACT_LAST_NAME")
    private String contactLastName;

    @Column(name="PHONE")
    private String phone;

    @Column(name="ADDRESS_LINE")
    private String address;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="POSTAL_CODE")
    private String postalCode;

    @Column(name="COUNTRY")
    private String country;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    protected User() {}

    public User(Integer userId, String companyName, String contactFirstName, String contactLastName,
                String phone, String address, String state, String postalCode, String country) {
        this.userId = userId;
        this.companyName = companyName;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.phone = phone;
        this.address = address;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public User(String companyName, String address) {
        this.companyName = companyName;
        this.address = address;
    }

    public User(String companyName) {
        this.companyName = companyName;
    }

}
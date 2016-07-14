package com.cnu2016.ecommerce.models;

import javax.persistence.*;

/**
 * Created by vipulj on 13/07/16.
 */
@Entity
@Table(name="FEEDBACK")
public class Feedback {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="FEEDBACK_ID")
    private Integer id;

    private String message;

    public Feedback(String message) {
        this.message = message;
    }

    public Feedback() {}

}

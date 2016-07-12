package com.cnu2016.ecommerce.controller;

import com.cnu2016.ecommerce.models.OrderDetails;
import com.cnu2016.ecommerce.models.OrderDetailsPK;
import com.cnu2016.ecommerce.models.Orders;
import com.cnu2016.ecommerce.models.User;
import com.cnu2016.ecommerce.pojo.OrderEnum;
import com.cnu2016.ecommerce.pojo.OrderProductPOJO;
import com.cnu2016.ecommerce.pojo.ProductSerializer;
import com.cnu2016.ecommerce.repository.OrderDetailsRepository;
import com.cnu2016.ecommerce.repository.OrdersRepository;
import com.cnu2016.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by vipulj on 09/07/16.
 */
@RestController
public class OrderController {
    @Autowired
    OrdersRepository ordersRepository;

    @RequestMapping(value="/api/orders/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> getOrdersById(@PathVariable Integer id) {
        return ResponseEntity.ok(ordersRepository.findOne(id));
    }

    @RequestMapping(value="/api/orders", method=RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody ProductSerializer body) {
        Orders orders = ordersRepository.save(new Orders(new Date(), OrderEnum.INPROCESS.getStatus()));
        return ResponseEntity.status(HttpStatus.CREATED).body(orders.getOrderId());
    }

}

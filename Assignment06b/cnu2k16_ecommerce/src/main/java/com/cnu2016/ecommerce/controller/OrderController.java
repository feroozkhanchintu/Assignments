package com.cnu2016.ecommerce.controller;

import com.cnu2016.ecommerce.models.Orders;
import com.cnu2016.ecommerce.pojo.OrderEnum;
import com.cnu2016.ecommerce.pojo.ProductSerializer;
import com.cnu2016.ecommerce.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vipulj on 09/07/16.
 */
@RestController
public class OrderController {
    @Autowired
    OrdersRepository ordersRepository;

    @RequestMapping(value="/api/orders/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> getOrdersById(@PathVariable Integer id) {
        Orders orders = ordersRepository.findByOrderIdAndDeleted(id, Boolean.FALSE);
        if (orders == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Id does not exist!!");
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("id", orders.getOrderId());
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @RequestMapping(value="/api/orders", method=RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody ProductSerializer body) {
        Orders orders = ordersRepository.save(new Orders(new Date(), OrderEnum.INPROCESS.getStatus()));
        Map<String, Integer> map = new HashMap<>();
        map.put("id", orders.getOrderId());
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @RequestMapping(value="/api/orders/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteOrder(@PathVariable Integer id) {
        Orders orders = ordersRepository.findByOrderIdAndDeleted(id, Boolean.FALSE);
        if (orders == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Id does not exist!!");
        }
        orders.setDeleted(Boolean.TRUE);
        orders = ordersRepository.save(orders);
        Map<String, Integer> map = new HashMap<>();
        map.put("id", orders.getOrderId());
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }


}

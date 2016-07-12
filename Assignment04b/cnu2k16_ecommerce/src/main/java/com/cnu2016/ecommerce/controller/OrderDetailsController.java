package com.cnu2016.ecommerce.controller;

import com.cnu2016.ecommerce.models.OrderDetails;
import com.cnu2016.ecommerce.models.OrderDetailsPK;
import com.cnu2016.ecommerce.models.Orders;
import com.cnu2016.ecommerce.models.Product;
import com.cnu2016.ecommerce.pojo.OrderProductPOJO;
import com.cnu2016.ecommerce.repository.OrderDetailsRepository;
import com.cnu2016.ecommerce.repository.OrdersRepository;
import com.cnu2016.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by vipulj on 09/07/16.
 */
public class OrderDetailsController {
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value="/api/order_details/{id1}/{id2}", method= RequestMethod.GET)
    public ResponseEntity<?> getOrdersById(@PathVariable Integer id1, @PathVariable Integer id2) {
        OrderDetailsPK orderDetailsPK = new OrderDetailsPK(id1, id2);
        return ResponseEntity.ok(orderDetailsRepository.findOne(orderDetailsPK));
    }

//    @RequestMapping(value="/api/orders/{orderId}/orderLineItem/", method=RequestMethod.POST)
//    public ResponseEntity<?> addProductIntoOrder(@PathVariable Integer orderId, @RequestBody OrderProductPOJO body) {
//        Orders orders = ordersRepository.findOne(orderId);
//        if (orders == null) {
//
//        }
//        Product product = productRepository.findOne(body.getProduct_id());
//        if (product == null) {
//
//        }
//        OrderDetailsPK orderDetailsPK = new OrderDetailsPK(body.getProduct_id(), body.getQty());
//        OrderDetails orderDetails = orderDetailsRepository.findOne(orderDetailsPK);
//        OrderDetails newOrderDetails;
//        if (orderDetails == null) {
//            newOrderDetails = new OrderDetails(orderDetailsPK, body.getQty());
//        } else {
//
//        }
//        Orders orders = orderDetailsRepository.save();
//        return ResponseEntity.status(HttpStatus.CREATED).body(orders);
//    }

}

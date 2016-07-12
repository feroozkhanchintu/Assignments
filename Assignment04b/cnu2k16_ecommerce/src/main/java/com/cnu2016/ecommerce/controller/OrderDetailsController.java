package com.cnu2016.ecommerce.controller;

import com.cnu2016.ecommerce.models.OrderDetails;
import com.cnu2016.ecommerce.models.OrderDetailsPK;
import com.cnu2016.ecommerce.models.Orders;
import com.cnu2016.ecommerce.models.Product;
import com.cnu2016.ecommerce.pojo.OrderEnum;
import com.cnu2016.ecommerce.pojo.OrderProductPOJO;
import com.cnu2016.ecommerce.repository.OrderDetailsRepository;
import com.cnu2016.ecommerce.repository.OrdersRepository;
import com.cnu2016.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by vipulj on 09/07/16.
 */
@RestController
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

    @RequestMapping(value="/api/order/{orderId}/orderLineItem/", method=RequestMethod.POST)
    public ResponseEntity<?> addProductIntoOrder(@PathVariable Integer orderId, @RequestBody OrderProductPOJO body) {
        Orders orders = ordersRepository.findOne(orderId);
        if (orders == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Such Order Created!!");
        }
        Product product = productRepository.findOne(body.getProduct_id());
        if (product == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Such Product Found!!");
        }

        if (orders.getStatus() == OrderEnum.SHIPPED.getStatus()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already Shipped!!");
        }
        OrderDetailsPK orderDetailsPK = new OrderDetailsPK(orders.getOrderId(), product.getProductID());
        OrderDetails orderDetails = orderDetailsRepository.findOne(orderDetailsPK);
        OrderDetails newOrderDetails;
        if (orderDetails == null) {
            newOrderDetails = new OrderDetails(orderDetailsPK, body.getQty(),
                    product.getCostPrice(), product.getSellingPrice(), orders, product);
        } else {
            System.out.println("imhere");
            newOrderDetails = new OrderDetails(orderDetailsPK, orderDetails.getQuantityOrdered() + body.getQty(),
                    product.getCostPrice(), product.getSellingPrice());
        }
        OrderDetails orderDetails1 = orderDetailsRepository.save(newOrderDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(orders);
    }

}

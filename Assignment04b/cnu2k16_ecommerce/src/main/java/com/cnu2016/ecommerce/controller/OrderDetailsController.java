package com.cnu2016.ecommerce.controller;

import com.cnu2016.ecommerce.models.*;
import com.cnu2016.ecommerce.pojo.CheckoutPOJO;
import com.cnu2016.ecommerce.pojo.OrderEnum;
import com.cnu2016.ecommerce.pojo.OrderProductPOJO;
import com.cnu2016.ecommerce.repository.OrderDetailsRepository;
import com.cnu2016.ecommerce.repository.OrdersRepository;
import com.cnu2016.ecommerce.repository.ProductRepository;
import com.cnu2016.ecommerce.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by vipulj on 09/07/16.
 */
@RestController
public class OrderDetailsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(com.cnu2016.ecommerce.controller.OrderDetailsController.class);

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/api/orders/{orderId}/orderLineItem", method = RequestMethod.POST)
    public ResponseEntity<?> addProductIntoOrder(@PathVariable Integer orderId, @RequestBody OrderProductPOJO body) {
        Orders orders = ordersRepository.findByOrderIdAndDeleted(orderId, Boolean.FALSE);
        if (body == null || body.getQty() == null || body.getProduct_id() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Entry into add product to item!!");
        }
        if (orders == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Such Order Created!!");
        }
        Product product = productRepository.findOne(body.getProduct_id());
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Such Product Found!!");
        }
        if (orders.getStatus().equals(OrderEnum.SHIPPED.getStatus())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already Shipped!!");
        }
        OrderDetailsPK orderDetailsPK = new OrderDetailsPK(orders.getOrderId(), product.getProductID());
        OrderDetails orderDetails = orderDetailsRepository.findOne(orderDetailsPK);
        OrderDetails newOrderDetails;
        if (orderDetails == null) {
            newOrderDetails = new OrderDetails(orderDetailsPK, body.getQty(),
                    product.getCostPrice(), product.getSellingPrice(), orders, product);
        } else {
            newOrderDetails = new OrderDetails(orderDetailsPK, orderDetails.getQuantityOrdered() + body.getQty(),
                    product.getCostPrice(), product.getSellingPrice());
        }
        OrderDetails orderDetails1 = orderDetailsRepository.save(newOrderDetails);
//        product.setQuantityInStock(product.getQuantityInStock() - body.getQty());
//        productRepository.save(product);
        Map<String, Integer> map = new HashMap<>();
        map.put("id", orders.getOrderId());
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @RequestMapping(value = "/api/orders/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> checkout(@PathVariable Integer id, @RequestBody CheckoutPOJO body) {
        Orders orders = ordersRepository.findByOrderIdAndDeleted(id, Boolean.FALSE);
        if (body == null || body.getAddress() == null || body.getStatus() == null || body.getUser_name() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incomplete data!!");
        }
        User user = userRepository.findDistinctUserByCompanyName(body.getUser_name());
        if (orders == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Such Order Created!!");
        }
        if (orders.getStatus().equals(OrderEnum.SHIPPED.toString())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already shipped!!");
        }
        Set<OrderDetails> orderDetailsSet = orders.getOrderDetails();
        for (OrderDetails orderDetails : orderDetailsSet) {
            Product product = orderDetails.getProducts();
            if (orderDetails.getQuantityOrdered() > product.getQuantityInStock()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Items more than inside the inventory!!");
            }
        }
        for (OrderDetails orderDetails : orderDetailsSet) {
            Product product = orderDetails.getProducts();
            product.setQuantityInStock(product.getQuantityInStock() - orderDetails.getQuantityOrdered());
            productRepository.save(product);
        }
        if (user == null) {
            User user1 = userRepository.save(new User(body.getUser_name(), body.getAddress()));
            orders.setStatus(OrderEnum.SHIPPED.getStatus());
            orders.setUserDetails(user1);
            Orders orders1 = ordersRepository.save(orders);
        } else {
            user.setAddress(body.getAddress());
            userRepository.save(user);
            orders.setStatus(OrderEnum.SHIPPED.getStatus());
            orders.setUserDetails(user);
            Orders orders1 = ordersRepository.save(orders);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("id", orders.getOrderId());
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @RequestMapping(value = "/api/health", method = RequestMethod.GET)
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}

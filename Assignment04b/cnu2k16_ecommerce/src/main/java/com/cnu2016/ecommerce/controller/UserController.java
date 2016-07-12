package com.cnu2016.ecommerce.controller;

import com.cnu2016.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vipulj on 09/07/16.
 */
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/api/users/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userRepository.findOne(id));
    }
}

package com.cnu2016.ecommerce.controller;

/**
 * Created by vipulj on 13/07/16.
 */

import com.cnu2016.ecommerce.models.Feedback;
import com.cnu2016.ecommerce.repository.FeedbackRepository;
import com.cnu2016.ecommerce.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedbackController {
    @Autowired
    FeedbackRepository feedbackRepository;

    @RequestMapping(value="/api/contactus", method= RequestMethod.POST)
    public ResponseEntity<?> getOrdersById(@RequestBody String message) {
//        if (message.equals(null)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty message given!!");
//        }
        feedbackRepository.save(new Feedback(message));
        return ResponseEntity.status(HttpStatus.OK).body("Message Added");
    }

}

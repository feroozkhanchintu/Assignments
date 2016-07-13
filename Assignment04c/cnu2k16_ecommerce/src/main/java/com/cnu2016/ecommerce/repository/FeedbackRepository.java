package com.cnu2016.ecommerce.repository;

import com.cnu2016.ecommerce.models.Feedback;
import com.cnu2016.ecommerce.models.OrderDetails;
import com.cnu2016.ecommerce.models.OrderDetailsPK;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vipulj on 13/07/16.
 */
public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {
}

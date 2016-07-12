package com.cnu2016.ecommerce.repository;

import com.cnu2016.ecommerce.models.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by vipulj on 08/07/16.
 */
public interface OrdersRepository extends CrudRepository<Orders, Integer> {

}

package com.cnu2016.ecommerce.repository;

/**
 * Created by vipulj on 07/07/16.
 */

import com.cnu2016.ecommerce.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findByProductIDAndIsAvailable(Integer productId, Boolean available);
    List<Product> findByIsAvailable(Boolean available);
}
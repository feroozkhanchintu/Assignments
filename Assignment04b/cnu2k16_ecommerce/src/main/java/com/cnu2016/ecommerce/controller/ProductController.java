package com.cnu2016.ecommerce.controller;

/**
 * Created by vipulj on 07/07/16.
 */

import com.cnu2016.ecommerce.models.Product;
import com.cnu2016.ecommerce.pojo.ProductSerializer;
import com.cnu2016.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController{

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/api/products", method = RequestMethod.GET)
    @ResponseStatus(value= HttpStatus.OK)
    public List<ProductSerializer> getProducts()
    {
        List<ProductSerializer> target = new ArrayList<>();
        for (Product product : productRepository.findByIsAvailable(true)) {
            target.add(new ProductSerializer(product));
        }
        return target;
    }


    @RequestMapping(value="/api/products/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> getProductsById(@PathVariable Integer id) {
        Product product = productRepository.findByProductIDAndIsAvailable(id, true);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ProductSerializer(product));
        } else {
            Map error = new HashMap();
            error.put("detail" , "Not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @RequestMapping(value="/api/products", method=RequestMethod.POST)
    public ResponseEntity<?> insertProductsPost(@RequestBody ProductSerializer body) {
        Product product = productRepository.save(new Product(body));
        return ResponseEntity.status(HttpStatus.CREATED ).body(new ProductSerializer(product));
    }

    @RequestMapping(value="/api/products/{pk}", method=RequestMethod.PUT)
    public ResponseEntity<?> insertProductsPut(@RequestBody ProductSerializer body, @PathVariable Integer pk) {
//        if (body.getCode() == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
//        }
        Product product = productRepository.findByProductIDAndIsAvailable(pk, true);
        body.setId(pk);
        if (product != null) {
            productRepository.save(new Product(body));
            return ResponseEntity.status(HttpStatus.OK).body(body);
        } else {
            Map error = new HashMap();
            error.put("detail" , "Not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }


    @RequestMapping(value="/api/products/{pk}", method=RequestMethod.PATCH)
    public ResponseEntity<?> insertProductsPatch(@RequestBody ProductSerializer body, @PathVariable Integer pk) {
        Product product = productRepository.findByProductIDAndIsAvailable(pk, true);
        body.setId(pk);
        if (product != null) {
            if (body.getCode() != null) {
                product.setProductCode(body.getCode());
            }
            if (body.getDescription() != null) {
                product.setProductDescription(body.getDescription());
            }
            if (body.getQty() != null) {
                product.setQuantityInStock(body.getQty());
            }
            productRepository.save(product);
            return ResponseEntity.status(HttpStatus.OK).body(new ProductSerializer(product));
        } else {
            Map error = new HashMap();
            error.put("detail" , "Not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }


    @RequestMapping(value="/api/products/{pk}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable Integer pk) {
        Product product = productRepository.findByProductIDAndIsAvailable(pk, true);
        if (product == null) {
            Map error = new HashMap();
            error.put("detail" , "Not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } else {
            product.setIsAvailable(Boolean.FALSE);
            productRepository.save(product);
            return ResponseEntity.status(HttpStatus.OK).body("");
        }
    }

}
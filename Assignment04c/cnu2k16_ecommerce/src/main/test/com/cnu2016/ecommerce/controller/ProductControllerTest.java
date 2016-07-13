package com.cnu2016.ecommerce.controller;

import com.cnu2016.ecommerce.MainApplication;
import com.cnu2016.ecommerce.models.Product;
import com.cnu2016.ecommerce.repository.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by vipulj on 13/07/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ProductControllerTest {

    @Autowired
    ProductRepository productRepository;

    @Value("${local.server.port}")
    int port;

    Product product;

    @Before
    public void setUp() throws Exception {
        product = new Product("");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getProducts() throws Exception {

    }

    @Test
    public void getProductsById() throws Exception {

    }

    @Test
    public void insertProductsPost() throws Exception {

    }

    @Test
    public void insertProductsPut() throws Exception {

    }

    @Test
    public void insertProductsPatch() throws Exception {

    }

    @Test
    public void deleteProduct() throws Exception {

    }

}
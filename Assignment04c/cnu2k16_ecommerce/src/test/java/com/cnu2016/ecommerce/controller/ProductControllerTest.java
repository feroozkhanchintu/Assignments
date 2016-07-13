package com.cnu2016.ecommerce.controller;

import com.cnu2016.ecommerce.MainApplication;
import com.cnu2016.ecommerce.repository.ProductRepository;
import org.junit.Before;
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

    @Before
    public void setUp() {
        productRepository
        // 8
        repository.deleteAll();
        repository.save(Arrays.asList(mickey, minnie, pluto));

        // 9
        RestAssured.port = port;
    }


}
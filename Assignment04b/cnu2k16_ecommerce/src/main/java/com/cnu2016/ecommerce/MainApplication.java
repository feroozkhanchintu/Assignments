package com.cnu2016.ecommerce;

import com.cnu2016.ecommerce.models.Product;
import com.cnu2016.ecommerce.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by vipulj on 07/07/16.
 */
@SpringBootApplication
public class MainApplication {
    private static final Logger log = LoggerFactory.getLogger(com.cnu2016.ecommerce.MainApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(com.cnu2016.ecommerce.MainApplication.class);
    }

//    @Bean
//    public CommandLineRunner demo(ProductRepository repository) {
//        return (args) -> {
//            // save a couple of customers
////            repository.save(new Product(111, "Bauer", 0.0, 0.0, 0, "hello_", "product_code", 1));
//            // fetch all customers
//            log.info("Product found with findAll():");
//            log.info("-------------------------------");
//            for (Product product : repository.findAll()) {
//                System.out.println(product.toString());
//            }
//            log.info("");
//        };
//    }
}



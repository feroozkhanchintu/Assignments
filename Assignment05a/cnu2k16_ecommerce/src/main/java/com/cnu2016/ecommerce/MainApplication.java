package com.cnu2016.ecommerce;

import com.cnu2016.ecommerce.interceptor.LogInterceptor;
import com.cnu2016.ecommerce.models.Product;
import com.cnu2016.ecommerce.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by vipulj on 07/07/16.
 */
@SpringBootApplication
public class MainApplication extends WebMvcConfigurerAdapter {
    private static final Logger log = LoggerFactory.getLogger(com.cnu2016.ecommerce.MainApplication.class);

    @Autowired
    private LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
    }


    public static void main(String[] args) {
        SpringApplication.run(com.cnu2016.ecommerce.MainApplication.class);
    }

}



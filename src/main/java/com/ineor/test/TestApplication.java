package com.ineor.test;

import com.ineor.test.jsonEntity.*;
import com.ineor.test.logic.BusinessLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TestApplication {

    private static final Logger log = LoggerFactory.getLogger(TestApplication.class);
    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {

            BusinessLogic.doJob(restTemplate.getForObject("http://jsonvat.com/", Header.class));

        };
    }
}
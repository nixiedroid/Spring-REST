package com.nixiedroid.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

//Annotation @SpringBootApplication
// is alias for
//@Component
//@ComponentScan
//@EnableAutoConfiguration

@SpringBootApplication
@EnableFeignClients
@Configuration
@ConfigurationPropertiesScan //Autowire properties from different sources. Currently, application.properties
public class SpringRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestApplication.class, args);
    }

}

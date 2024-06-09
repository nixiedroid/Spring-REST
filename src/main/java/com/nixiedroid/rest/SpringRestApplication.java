package com.nixiedroid.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.regex.Pattern;

//Annitation @SpringBootApplication
// is alias for
//@Component
//@ComponentScan
//@EnableAutoConfiguration

@SpringBootApplication
@ConfigurationPropertiesScan //Autowire properties from different sources. Currently, application.properties
public class SpringRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestApplication.class, args);
    }

}

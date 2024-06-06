package com.nixiedroid.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Annitation @SpringBootApplication
// is alias for
//@Component
//@ComponentScan
//@EnableAutoConfiguration

@SpringBootApplication
public class SpringRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestApplication.class, args);
    }

}

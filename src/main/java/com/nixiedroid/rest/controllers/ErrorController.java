package com.nixiedroid.rest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/error")
public class ErrorController {
    @RequestMapping
    public String error(){
        return "Unknown error occurred. No extra information available";
    }
}

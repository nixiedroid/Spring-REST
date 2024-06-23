package com.nixiedroid.rest.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;


public class ErrorController {
    @RequestMapping(method = {GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE})
    public String error() {
        return "Unknown error occurred. No extra information available";
    }

    @ExceptionHandler
    public String exception() {
        return "Unknown error occurred. No extra information available";
    }
}

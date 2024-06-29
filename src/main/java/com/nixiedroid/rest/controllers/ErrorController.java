package com.nixiedroid.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> AnyException() {
        return ResponseEntity
                .internalServerError()
                .body("Bad Request");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> exception() {
        return ResponseEntity
                .badRequest()
                .body("Bad Request");
    }

}

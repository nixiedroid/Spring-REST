package com.nixiedroid.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class RootController {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    public RootController(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    @GetMapping("/")
    public ResponseEntity<List<String>> getEndpoints() {
        return new ResponseEntity<>(
                requestMappingHandlerMapping.getHandlerMethods().keySet()
                        .stream().map(RequestMappingInfo::toString)
                        .collect(Collectors.toList()), HttpStatus.OK);
    }
}

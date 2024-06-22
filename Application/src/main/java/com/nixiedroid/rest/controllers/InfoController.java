package com.nixiedroid.rest.controllers;

import com.nixiedroid.rest.models.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//REST API
@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {
    /**
     * Info properties accessor <br>
     * {@link org.springframework.beans.factory.annotation.Autowired}
     */
    private final Info info;

    @Value("${spring.application.name:UNDEFINED_VARIABLE}")
    String appName;

    @GetMapping("/name")
    String getName(){
        return info.getName();
    }

    @GetMapping("/appName")
    String getAppName(){
        return appName;
    }

    @GetMapping("/version")
    String getVersion(){
        return info.getVersion();
    }
}

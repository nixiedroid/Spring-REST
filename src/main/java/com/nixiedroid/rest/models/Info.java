package com.nixiedroid.rest.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "info")
@Getter
@Setter

public class Info {
    private String version;
    private String name;
}

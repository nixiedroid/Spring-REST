package com.nixiedroid.rest.weather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "owm.secrets")
@Getter
@Setter
@AllArgsConstructor
public class OWMSecrets
{
    public String TOKEN;
}

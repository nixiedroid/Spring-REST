package com.nixiedroid.rest.weather.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GeocodingResponce{
   private double lat;
   private double lon;
    private String country;
}



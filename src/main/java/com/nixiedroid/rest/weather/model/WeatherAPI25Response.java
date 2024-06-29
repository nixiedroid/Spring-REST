package com.nixiedroid.rest.weather.model;

import java.util.Set;

public record WeatherAPI25Response(
        Coord coord,
        Set<Weather> weather,
        String base,
        WeatherState main,
        int visibility,
        Wind wind,
        Clouds clouds,
        long dt,
        Sys sys,
        long timezone,
        int id,
        String name,
        int cod
) {
}

record Coord(
        double lat,
        double lon
) { }

record Weather(
        int id,
        String main,
        String description,
        String icon
) {}

record WeatherState(
        double temp,
        double feels_like,
        double temp_min,
        double temp_max,
        double pressure,
        double humidity,
        double sea_level,
        double grnd_level
) { }

record Wind(
        double speed,
        int deg,
        double gust
){}

record Clouds(
        int all
) {}

record Sys(
        int type,
        int id,
        String country,
        long sunrise,
        long sunset
){}



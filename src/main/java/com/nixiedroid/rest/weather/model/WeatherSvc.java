package com.nixiedroid.rest.weather.model;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class WeatherSvc implements Function<WeatherAPI25Response,SimpleWeather> {
    String  getWeatherName(WeatherAPI25Response weather){
        return weather.weather().stream().findFirst().map(Weather::description).orElse("");
    }

    public  SimpleWeather fromWeather(WeatherAPI25Response resp){
        return new SimpleWeather(
                resp.name(),
                getWeatherName(resp));
    }

    @Override
    public SimpleWeather apply(WeatherAPI25Response simpleWeather) {
        return fromWeather(simpleWeather);
    }
}

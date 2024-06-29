package com.nixiedroid.rest.controllers;

import com.nixiedroid.rest.weather.model.GeocodingResponce;
import com.nixiedroid.rest.weather.model.WeatherAPI25Response;
import com.nixiedroid.rest.weather.model.WeatherSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import com.nixiedroid.rest.weather.model.SimpleWeather;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class WeatherController {

    @Value("${owm.secrets.TOKEN}")
    public String TOKEN;

    WeatherProxy wProxy;
    WeatherSvc weatherSvc;

    @Autowired
    public WeatherController(WeatherProxy wProxy, WeatherSvc weatherSvc) {
        this.wProxy = wProxy;
        this.weatherSvc = weatherSvc;
    }

    static final String OWMApiUrl = "https://api.openweathermap.org/";

    static final String weatherPath = "/data/2.5/weather";

    static final String geoCodingPath = "/geo/1.0/direct";

    @GetMapping("weather")
    public SimpleWeather getWeather(){
        GeocodingResponce  loc = wProxy.getCityCoord("Minsk",TOKEN).stream().findFirst().orElse(null);
        return (loc==null)?null: weatherSvc.apply(wProxy.getWeather(loc.getLat(),loc.getLon(),TOKEN));
    }

    @GetMapping("weather/{city}")
    public SimpleWeather getWeather(@PathVariable String city){
        GeocodingResponce loc = wProxy.getCityCoord(city,TOKEN).stream().findFirst().orElse(null);
        return (loc==null)?null:  weatherSvc.apply(wProxy.getWeather(loc.getLat(),loc.getLon(),TOKEN));
    }


    @FeignClient(name = "weather", url = OWMApiUrl)
    public interface WeatherProxy {
        @GetMapping(weatherPath)
        WeatherAPI25Response getWeather(
                @RequestParam double lat,
                @RequestParam double lon,
                @RequestParam String appid
                );

        @GetMapping(geoCodingPath)
        Set<GeocodingResponce> getCityCoord(
                @RequestParam("q") String cityName,
                @RequestParam String appid
        );
    }
}

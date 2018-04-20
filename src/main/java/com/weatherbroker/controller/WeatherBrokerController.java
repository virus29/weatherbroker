package com.weatherbroker.controller;

import com.weatherbroker.service.WeatherBrokerService;
import com.weatherbroker.view.PositiveResponseView;
import com.weatherbroker.view.forecast.ForecastFull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping
public class WeatherBrokerController {

        @Autowired
    private WeatherBrokerService weatherBrokerService;

    /**
     * Получение прогноза погоды по Названию города, с Yahoo
     * @param cityName - Название города
     * @return - Прогноз погоды по cityName
     */
    @GetMapping(path = "/cityName")
    public ResponseEntity findForecastByCityName(@RequestParam String cityName) throws ParseException {
        weatherBrokerService.findForecastByCityName(cityName);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);
    }
}

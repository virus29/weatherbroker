package com.weatherbroker.controller;

import com.weatherbroker.service.WeatherBrokerService;
import com.weatherbroker.view.forecast.ForecastFull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(path = "/{cityName}")
    public ResponseEntity<ForecastFull> findForecastByCityName(@PathVariable String cityName) {
        ResponseEntity<ForecastFull> result = weatherBrokerService.findForecastByCityName(cityName);
        return result;

    }
}

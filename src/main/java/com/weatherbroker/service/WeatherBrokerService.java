package com.weatherbroker.service;

import com.weatherbroker.view.forecast.ForecastFull;
import org.springframework.http.ResponseEntity;

public interface WeatherBrokerService {
    /**
     * Получение прогноза погоды по Названию города, с Yahoo
     * @param cityName Название города
     * @return Выводит прогноз погоды по параметрам тип информации и название города
     */
    public ResponseEntity<ForecastFull> findForecastByCityName(String cityName);
}

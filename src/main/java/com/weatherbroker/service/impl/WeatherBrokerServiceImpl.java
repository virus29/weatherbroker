package com.weatherbroker.service.impl;


import com.weatherbroker.entity.ForecastWeather;
import com.weatherbroker.entity.WeatherBroker;
import com.weatherbroker.service.WeatherBrokerService;
import com.weatherbroker.view.forecast.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Repository
public class WeatherBrokerServiceImpl implements WeatherBrokerService {
    /**
     * Получение прогноза погоды по Названию города, с Yahoo
     *
     * @param cityName Название города
     * @return Выводит прогноз погоды по параметрам тип информации и название города
     */
    @Override
    public ResponseEntity<ForecastFull> findForecastByCityName(String cityName) {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("cityName", cityName);

        String url = "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"{cityName}\") and u=\"c\"&format=json&env= store://datatables.org/alltableswithkeys";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ForecastFull> result = restTemplate.getForEntity(url, ForecastFull.class, vars);
        return result;
    }

    private ForecastFull forecastFull;

    /**
     * Конвертер из DTO ForecastFullT в Entity WeatherBroker
     * @param forecastFull
     * @return
     * @throws ParseException
     */
    public WeatherBroker converterFromForecastFullToEntity(ForecastFull forecastFull) throws ParseException {
        WeatherBroker weatherBroker = new WeatherBroker();
        weatherBroker.setCity(forecastFull.getQuery().getResults().getChannel().getLocation().getCity());
        weatherBroker.setDateRequest(forecastFull.getQuery().getResults().getChannel().getItem().getCondition().getDate());
        List<Forecast> f = forecastFull.getQuery().getResults().getChannel().getItem().getForecast();
        List<ForecastWeather> fw = new ArrayList<>();
        for (Forecast forecast : f) {
            ForecastWeather fwr = new ForecastWeather();
            Date date = new SimpleDateFormat("dd MMM yyyy", Locale.US).parse(forecast.getDate());
            fwr.setDate(date);
            fwr.setDay(forecast.getDay());
            fwr.setHigh(forecast.getHigh());
            fwr.setLow(forecast.getLow());
            fwr.setText(forecast.getText());

            fw.add(fwr);
        }
        weatherBroker.setForecastWeather(fw);
        return weatherBroker;
    }
}

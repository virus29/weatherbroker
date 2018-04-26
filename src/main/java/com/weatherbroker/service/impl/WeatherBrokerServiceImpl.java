package com.weatherbroker.service.impl;


import com.weatherbroker.entity.ForecastWeather;
import com.weatherbroker.entity.WeatherBroker;
import com.weatherbroker.jms.WeatherPublisher;
import com.weatherbroker.service.WeatherBrokerService;
import com.weatherbroker.view.forecast.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.jms.Topic;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



@Service
@Repository
public class WeatherBrokerServiceImpl implements WeatherBrokerService {

    private Logger logger= (Logger) LoggerFactory.getLogger(WeatherBrokerServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    WeatherPublisher weatherPublisher;

    /**
     * Отправка прогноза погоды в Базу через JMS, полученного по Названию города, с Yahoo
     * @param cityName Название города
     */
    @Override
    @Transactional
    public void findForecastByCityName(String cityName) throws ParseException {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("cityName", cityName);

        String url = "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"{cityName}\") and u=\"c\"&format=json&env= store://datatables.org/alltableswithkeys";

        ResponseEntity<ForecastFull> forecastFull = restTemplate.getForEntity(url, ForecastFull.class, vars);

        WeatherBroker wb =converterFromForecastFullToEntity(forecastFull.getBody());

        logger.info(wb.toString()+"отправляемый объект");

        weatherPublisher.sendWeather(wb);
        logger.info(wb.toString()+" объект ОТПРАВЛЕН !!!!!!!!!!!!!!!!!!!!!!!");

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
            logger.info("fwr");

            fw.add(fwr);
            logger.info("fw");
        }
        weatherBroker.setForecastWeather(fw);
        logger.info("weatherBroker");
        return weatherBroker;
    }
}

package com.weatherbroker.jms;

import com.weatherbroker.entity.WeatherBroker;
import com.weatherbroker.repository.WeatherBrokerRepository;
import com.weatherbroker.service.impl.WeatherBrokerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class WeatherSubscriber {
    private final Logger log = LoggerFactory.getLogger(WeatherSubscriber.class);
    @Autowired
    private WeatherBrokerRepository weatherBrokerRepository;

    @Transactional
    @JmsListener(destination = "weatherbrokertopic")
    public void listener(WeatherBroker weatherBroker) {
        log.info("Объект пришел JMS topic: " + weatherBroker.toString());
        weatherBrokerRepository.save(weatherBroker);
//        log.info(weatherBrokerRepository.findById(weatherBroker.getId()).toString() + " объект сохраняемый в базе");
    }
}

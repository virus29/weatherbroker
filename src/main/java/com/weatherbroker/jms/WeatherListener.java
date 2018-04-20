package com.weatherbroker.jms;

import com.weatherbroker.entity.WeatherBroker;
import com.weatherbroker.repository.WeatherBrokerRepository;
import com.weatherbroker.service.impl.WeatherBrokerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.transaction.annotation.Transactional;

public class WeatherListener {
    @Autowired
    private WeatherBrokerRepository weatherBrokerRepository;
    private Logger logger = (Logger) LoggerFactory.getLogger(WeatherBrokerServiceImpl.class);

    @Transactional
    @JmsListener(destination = "weatherbrokertopic")
    public void listener(WeatherBroker weatherBroker) {
        logger.info("Объект пришел JMS topic: " + weatherBroker.toString());
        weatherBrokerRepository.save(weatherBroker);
        logger.info(weatherBrokerRepository.findById(weatherBroker.getId()).toString() + " объект сохраняемый в базе");
    }
}

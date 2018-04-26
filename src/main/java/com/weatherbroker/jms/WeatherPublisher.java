package com.weatherbroker.jms;

import com.weatherbroker.entity.WeatherBroker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Topic;

@Component
public class WeatherPublisher {

    private final Logger log = LoggerFactory.getLogger(WeatherPublisher.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(lookup = "java:/jms/topic/weatherbrokertopic")
    private Topic weatherBrokerTopic;

    public void sendWeather(final WeatherBroker wb) {
        log.info("sending actual weather in JMS...");
        jmsTemplate.convertAndSend(weatherBrokerTopic, wb);
        log.info("actual weather was send");
    }
}
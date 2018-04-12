package com.weatherbroker.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "weather_broker")
public class WeatherBroker {

    /**
     * Id Запроса
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     *Город
     */
    @Column(name = "city")
    private String city;

    /**
     *Дата запроса погоды
     */
    @Column(name = "date_request")
    private String dateRequest;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "weatherBroker", cascade = {CascadeType.ALL},orphanRemoval = true)
    private List<ForecastWeather> forecastWeather;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(String dateRequest) {
        this.dateRequest = dateRequest;
    }

    public List<ForecastWeather> getForecastWeather() {
        return forecastWeather;
    }

    public void setForecastWeather(List<ForecastWeather> forecastWeather) {
        this.forecastWeather = forecastWeather;
    }
}

package com.weatherbroker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "forecast_weather")
public class ForecastWeather implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Id forecast_weather
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version=1;

    /**
     * Дата прогноза погоды
     */
    @Column(name = "date")
    private Date date;

    /**
     * день недели
     */
    @Column(name = "day")
    private String day;

    /**
     * максимальная температура
     */
    @Column(name = "high")
    private String high;

    /**
     * минимальная температура
     */
    @Column(name = "low")
    private String low;

    /**
     * Дополнительная информация о погоде
     */
    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weather_broker_id")
    private WeatherBroker weatherBroker;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public WeatherBroker getWeatherBroker() {
        return weatherBroker;
    }

    public void setWeatherBroker(WeatherBroker weatherBroker) {
        this.weatherBroker = weatherBroker;
    }
}

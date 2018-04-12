package com.weatherbroker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "forecast_weather")
public class ForecastWeather {
    /**
     * Дата прогноза погоды
     */
    @Column(name = "date")
    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}

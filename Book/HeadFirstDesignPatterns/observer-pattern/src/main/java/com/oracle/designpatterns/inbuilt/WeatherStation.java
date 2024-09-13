package com.oracle.designpatterns.inbuilt;

import java.util.Observable;
import java.util.Observer;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        Observer currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        Observer forecastDisplay = new ForecastDisplay(weatherData);

        weatherData.setMeasurements(39, 97, 43.5);
    }
}

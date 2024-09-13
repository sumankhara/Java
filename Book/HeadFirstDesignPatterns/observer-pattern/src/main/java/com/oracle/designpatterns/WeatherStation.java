package com.oracle.designpatterns;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        Observer currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(36, 98, 30.4);
        weatherData.setMeasurements(39, 98, 39.4);
        weatherData.setMeasurements(38, 92, 54.4);
    }
}

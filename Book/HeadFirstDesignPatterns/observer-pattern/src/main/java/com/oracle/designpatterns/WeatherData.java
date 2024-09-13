package com.oracle.designpatterns;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {
    private double temp;
    private double humidity;
    private double pressure;
    private List<Observer> observers;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
      observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
      observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(temp, humidity, pressure));
    }

    public void measurementChanged() {
        notifyObservers();
    }

    public void setMeasurements(double temp, double humidity, double pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }
}

package javainaction.ch8.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject {
    List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
       observers.add(observer);
    }

    @Override
    public void notifyObservers(String tweet) {
       observers.forEach(observer -> observer.notify(tweet));
    }
}

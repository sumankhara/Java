package javainaction.ch8.designpatterns.observer;

public interface Subject {
    void registerObserver(Observer observer);
    void notifyObservers(String tweet);
}

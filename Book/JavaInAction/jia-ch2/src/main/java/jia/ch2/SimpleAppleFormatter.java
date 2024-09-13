package jia.ch2;

public class SimpleAppleFormatter implements AppleFormatter {
    @Override
    public void accept(Apple apple) {
        System.out.println("An apple of " + apple.getWeight() + "g");
    }
}

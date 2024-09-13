package jia.ch2;

public class FancyAppleFormatter implements AppleFormatter{
    @Override
    public void accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        System.out.println("A " + characteristic + " " + apple.getColour() + " apple");
    }
}

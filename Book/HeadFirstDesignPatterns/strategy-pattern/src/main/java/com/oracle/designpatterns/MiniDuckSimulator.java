package com.oracle.designpatterns;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.display();
        duck.performFly();
        duck.performQuack();

        duck.setFlyBehavior(new FlyNoWay());
        duck.performFly();
        duck.performQuack();
    }
}

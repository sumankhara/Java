package com.oracle.designpatterns;

public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("T'm flying!");
    }
}

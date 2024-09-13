package com.oracle.designpatterns.swing;

import javax.swing.*;

public class SwingObserverExample {
    private JFrame jFrame;

    public static void main(String[] args) {
        SwingObserverExample swingObserverExample = new SwingObserverExample();
        swingObserverExample.go();
    }

    public void go() {
        jFrame = new JFrame("Swing Observer Example");

        JButton jButton = new JButton("Should I do it?");
        jButton.addActionListener(event -> System.out.println("Don't do it, you might regret it!"));
        jButton.addActionListener(event -> System.out.println("Come on, do it!"));

        jFrame.add(jButton);
        jFrame.setSize(100, 100);
        jFrame.setVisible(true);
    }
}

package com.example.dllo.day19;

/**
 * Created by dllo on 16/5/20.
 */
public class HungryDemo {
    private static HungryDemo hungryDemo = new HungryDemo();

    private HungryDemo() {
    }

    public static HungryDemo getInstance() {

        return hungryDemo;
    }
}

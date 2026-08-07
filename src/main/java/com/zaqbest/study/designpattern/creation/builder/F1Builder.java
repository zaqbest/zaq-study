package com.zaqbest.study.designpattern.creation.builder;

/**
 * 建造一辆F1赛车
 */
public class F1Builder extends CarBuilder{

    @Override
    public void buildWheels() {
        car.setWheels(4);
    }

    @Override
    public void buildColor() {
        car.setColor("red");
    }

    @Override
    public void buildSpeed() {
        car.setSpeed("fast");
    }
}

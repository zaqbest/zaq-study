package com.zaqbest.study.designpattern.creation.builder;

/**
 * 建造一个8个轮子的擎天柱
 */
public class TruckBuilder extends CarBuilder{

    @Override
    public void buildWheels() {
        car.setWheels(8);
    }

    @Override
    public void buildColor() {
        car.setColor("black");
    }

    @Override
    public void buildSpeed() {
        car.setSpeed("medium");
    }
}

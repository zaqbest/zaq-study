package com.zaqbest.study.designpattern.creation.builder;

public class Worker {
    private CarBuilder builder;

    public void setBuilder(CarBuilder builder) {
        this.builder = builder;
    }

    public Car getCar(){return builder.getCar();};

    public void constructNewCar(){
        builder.createNewCar();
        builder.buildColor();
        builder.buildSpeed();
        builder.buildWheels();
    }


}

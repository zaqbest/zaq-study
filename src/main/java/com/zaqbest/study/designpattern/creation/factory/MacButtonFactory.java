package com.zaqbest.study.designpattern.creation.factory;

public class MacButtonFactory implements ButtonFactory{
    @Override
    public Button createButton() {
        return new MacButton();
    }
}

package com.zaqbest.study.designpattern.creation.prototype;

public class Cookie implements Cloneable{
    public Object clone() throws CloneNotSupportedException{
        return (Cookie)super.clone();
    }
}

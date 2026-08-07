package com.zaqbest.study.designpattern.creation.singleton;

/**
 * 通过enum实现的单例模式，完美单例
 */
public enum SingletonEnum {
    INSTANCE;

    public void func(){
        System.out.println("aaaa");
    }

    public static void main(String[] args) {
        SingletonEnum s = SingletonEnum.INSTANCE;
        s.func();
    }
}



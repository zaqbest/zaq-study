package com.zaqbest.study.designpattern.creation.prototype;

public class Setup {
    public static void main(String[] args) {
        CookieMachine cookieMachine = new CookieMachine();
        cookieMachine.setCookie(new Cookie());

        for (int i = 0; i < 100; i++){
            cookieMachine.makeCookie();
        }
    }
}

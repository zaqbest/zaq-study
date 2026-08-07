package com.zaqbest.study.designpattern.creation.prototype;

public class CookieMachine {
    Cookie cookie;

    public void setCookie(Cookie cookie){this.cookie = cookie; }

    public Cookie makeCookie(){
        try{
            return (Cookie) cookie.clone();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        return null;
    }
}

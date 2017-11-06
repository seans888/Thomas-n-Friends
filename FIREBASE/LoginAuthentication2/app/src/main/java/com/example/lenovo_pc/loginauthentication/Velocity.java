package com.example.lenovo_pc.loginauthentication;

/**
 * Created by LENOVO_PC on 23/10/2017.
 */

public class Velocity {
    String velocityId;
    String value;

    boolean singleTap, doubleTap, longPress, swipe;
    float x, y, sX, sY, fX, fY;
    float totalTime;


    public Velocity(String velocityId, String value,
                    boolean singleTap, boolean doubleTap, boolean longPress, boolean swipe,
                    float x, float y, float sX, float sY, float fX, float fY,
                    float totalTime) {
        this.velocityId = velocityId;
        this.value = value;
        this.singleTap = singleTap;
        this.doubleTap = doubleTap;
        this.longPress = longPress;
        this.x = x;
        this.y = y;
        this.sX = sX;
        this.sY = sY;
        this.fX = fX;
        this.fY = fY;
        this.totalTime = totalTime;
    }

    public String getVelocityId() {
        return velocityId;
    }

    public String getValue() {
        return value;
    }
}


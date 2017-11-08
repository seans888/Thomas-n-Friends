package com.example.lenovo_pc.loginauthentication;

/**
 * Created by LENOVO_PC on 23/10/2017.
 */

public class Velocity {
    String velocityId;
    String value;

    boolean singleTap, doubleTap, longPress;


    public Velocity(String velocityId, String value, boolean singleTap, boolean doubleTap, boolean longPress) {
        this.velocityId = velocityId;
        this.value = value;
        this.singleTap = singleTap;
        this.doubleTap = doubleTap;
        this.longPress = longPress;
    }
}


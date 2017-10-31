package com.example.lenovo_pc.loginauthentication;

/**
 * Created by LENOVO_PC on 23/10/2017.
 */

public class Velocity {
    String velocityId;
    String value;


    public Velocity(String velocityId, String value) {
        this.velocityId = velocityId;
        this.value = value;
    }

    public String getVelocityId() {
        return velocityId;
    }

    public String getValue() {
        return value;
    }
}


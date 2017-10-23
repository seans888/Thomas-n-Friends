package com.example.lenovo_pc.loginauthentication;

/**
 * Created by LENOVO_PC on 23/10/2017.
 */

public class Velocity {
    String velocityId;
    String velocity;

    public Velocity(){

    }

    public Velocity(String velocityId, String velocity) {
        this.velocityId = velocityId;
        this.velocity = velocity;
    }

    public String getVelocityId() {
        return velocityId;
    }

    public String getVelocity() {
        return velocity;
    }
}

package com.example.lenovo_pc.loginauthentication;

/**
 * Created by LENOVO_PC on 07/11/2017.
 */

class GestureDetails{
    public float xPosition, yPosition, initialX, initialY, finalX, finalY;
    public long totalTime;
    public String myName, deviceName, deviceMan;

    public GestureDetails(float x, float y,
                          float sX, float sY,
                          float fX, float fY,
                          long totalTime, String myName,
                          String deviceName, String deviceMan) {
        this.xPosition = x;
        this.yPosition = y;
        this.initialX = sX;
        this.initialY = sY;
        this.finalX = fX;
        this.finalY = fY;
        this.totalTime = totalTime;
        this.myName = myName;
        this.deviceName = deviceName;
        this.deviceMan = deviceMan;
    }
}

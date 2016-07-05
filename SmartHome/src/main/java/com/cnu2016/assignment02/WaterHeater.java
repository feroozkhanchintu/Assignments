package com.cnu2016.assignment02;

public class WaterHeater extends Appliance {
    
    int timer;
    
    public WaterHeater() {
        status = false;
        timer = 0;
    }
    
    public void start() {
        status = true;
    }
    
    public void stop() {
        status = false;
    }
}

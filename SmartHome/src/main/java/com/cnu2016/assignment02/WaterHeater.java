package com.cnu2016.assignment02;

public class WaterHeater extends Appliance {
    
    static int timer;
    
    public void start() {
        status = true;
    }
    
    public void stop() {
        status = false;
    }
}

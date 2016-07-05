package com.cnu2016.assignment02;

public class AirConditioner extends Appliance{
    
    public AirConditioner() {
        status = false;
    }
    
    public void start() {
        status = true;
    }
    
    public void stop() {
        status = false;
    }
}

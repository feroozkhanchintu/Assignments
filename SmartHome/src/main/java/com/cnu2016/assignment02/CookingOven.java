package com.cnu2016.assignment02;

public class CookingOven extends Appliance{
    
    public CookingOven() {
        status = false;
    }
    
    public void start() {
        status = true;
    }
    
    public void stop() {
        status = false;
    }
}

package com.cnu2016.assignment02;

public abstract class Appliance {
    
    //True: On False: Off
    Boolean status;
    
    public abstract void start();
    
    public abstract void stop();
}

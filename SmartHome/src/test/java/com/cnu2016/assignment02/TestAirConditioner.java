package com.cnu2016.assignment02;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestAirConditioner {
    @Test
    public void evaluateStop() {
        AirConditioner AC = new AirConditioner();
        AC.stop();
        assertEquals(false, AC.getStatus());
    } 
    
    @Test
    public void evaluateStart() {
        AirConditioner AC = new AirConditioner();
        AC.start();
        assertEquals(true, AC.getStatus());
    } 
}

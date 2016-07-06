package com.cnu2016.assignment02;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestWaterHeater {
    @Test
    public void evaluateStop() {
        WaterHeater WH = new WaterHeater();
        WH.stop();
        assertEquals(false, WH.getStatus());
    } 
    
    @Test
    public void evaluateStart() {
        WaterHeater WH = new WaterHeater();
        WH.start();
        assertEquals(true, WH.getStatus());
    } 
}

package com.cnu2016.assignment02;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestCookingOven {
    @Test
    public void evaluateStop() {
        CookingOven Co = new CookingOven();
        Co.stop();
        assertEquals(false, Co.getStatus());
    } 
    
    @Test
    public void evaluateStart() {
        CookingOven Co = new CookingOven();
        Co.start();
        assertEquals(true, Co.getStatus());
    }
}

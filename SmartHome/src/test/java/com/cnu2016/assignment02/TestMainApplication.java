package com.cnu2016.assignment02;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;

public class TestMainApplication {
    @Test
    public void evaluateApplication1() {
        MainApplication mainApp = new MainApplication();
        ArrayList<String> output = mainApp.application("/projects/Assignments/SmartHome/src/test/java/com/cnu2016/assignment02/test_instruction.txt");
        assertEquals("true false false", output.get(0));
    } 
    
    @Test
    public void evaluateApplication2() {
        MainApplication mainApp = new MainApplication();
        ArrayList<String> output = mainApp.application("/projects/Assignments/SmartHome/src/test/java/com/cnu2016/assignment02/test_instruction_1.txt");
        System.out.println(output);
        assertEquals("false true false", output.get(0));
        assertEquals("false false false", output.get(1));
    } 
    
    
    @Test
    public void evaluateApplication3() {
        MainApplication mainApp = new MainApplication();
        ArrayList<String> output = mainApp.application("/projects/Assignments/SmartHome/src/test/java/com/cnu2016/assignment02/test_instruction_2.txt");
        System.out.println(output);
        assertEquals("false false true", output.get(0));
    } 
    
    @Test
    public void evaluateApplication4() {
        MainApplication mainApp = new MainApplication();
        ArrayList<String> output = mainApp.application("/projects/Assignments/SmartHome/src/test/java/com/cnu2016/assignment02/test_instruction_3.txt");
        System.out.println(output);
        assertEquals("Wrong Appliance", output.get(0));
    } 
}

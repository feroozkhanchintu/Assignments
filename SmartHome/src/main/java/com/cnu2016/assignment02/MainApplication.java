package com.cnu2016.assignment02;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;


public class MainApplication {
    
    public static ArrayList<Event> fileReadUtil(String filePath) {
        ArrayList<Event> events = new ArrayList<Event>();
        BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(filePath));
			while ((sCurrentLine = br.readLine()) != null) {
				String[] splited = sCurrentLine.split(" ");
				Event event = new Event();
				event.appliance = splited[0];
				event.startTime = Integer.parseInt(splited[1]);
				event.status = splited[2];
				events.add(event);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return events;
    }
    
    public ArrayList<String> application(String filePath) {
        
        ArrayList<String> output = new ArrayList<String>();
        ArrayList<Event> events = MainApplication.fileReadUtil(filePath);
		
		int position = 0;
		AirConditioner AC = new AirConditioner();
		CookingOven CO = new CookingOven();
		WaterHeater WH = new WaterHeater();
		
		//Considering 1 millisecond to be 1 hour
		for (int timer = 1; timer <= 24; timer++) {
		    try {
                Thread.sleep(1);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
		    if (WH.timer == timer - 2) {
		        WH.stop();
		        WH.timer = 30; //random value >24
		        String currentStatus = AC.status + " " + WH.status + " " + CO.status;
		        output.add(currentStatus);
		    }
		    if (events.size() <= position) {continue;}
		    while(events.get(position).startTime == timer) {
		        if ("AC".equals(events.get(position).appliance)) {
		            if ("ON".equals(events.get(position).status)) {
		                AC.start();
		            } else {
		                AC.stop();
		            }
		        } else if ("WH".equals(events.get(position).appliance)) {
		            if ("ON".equals(events.get(position).status)) {
		                WH.start();
		                WH.timer = timer;
		            } else {
		                WH.stop();
		                WH.timer = 30;
		            }
		        } else if ("CO".equals(events.get(position).appliance)) {
		            if ("ON".equals(events.get(position).status)) {
		                CO.start();
		            } else {
		                CO.stop();
		            }
		        } else {
		            output.add("Wrong Appliance");
		        }
		        String currentStatus = AC.status + " " + WH.status + " " + CO.status;
		        output.add(currentStatus);
		        position++;
		        if (events.size() <= position) {break;}
		    }
		}
		return output;
    }
    
    public static void main(String[] args) {
		MainApplication mainApp = new MainApplication();
		ArrayList<String> output = mainApp.application("/projects/Assignments/SmartHome/instructions.txt");
		System.out.println(output);
    }
}


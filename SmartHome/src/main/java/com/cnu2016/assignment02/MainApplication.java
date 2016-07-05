package com.cnu2016.assignment02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainApplication {
    public static void main(String[] args) {
        System.out.println("Hello World!!!");
		ArrayList<Event> events = new ArrayList<Event>();
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("/projects/Assignments/SmartHome/instructions.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				String[] splited = sCurrentLine.split(" ");
				Event event = new Event();
				event.appliance = splited[0];
				event.startTime = Integer.parseInt(splited[1]);
				event.status = splited[2];
				events.add(event);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		int position = 0;
		AirConditioner AC = new AirConditioner();
		CookingOven CO = new CookingOven();
		WaterHeater WH = new WaterHeater();
		
		for (int timer = 0; timer <= 24; timer++) {
		    if (WH.timer == timer - 2) {
		        WH.stop();
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
		            } else {
		                WH.stop();
		            }
		        } else if ("CO".equals(events.get(position).appliance)) {
		            if ("ON".equals(events.get(position).status)) {
		                CO.start();
		            } else {
		                CO.stop();
		            }
		        } else {
		            System.out.println("Wrong Appliance");
		        }
		        System.out.println("Aircondition Status " + AC.status);
		        System.out.println("Water Heater Status " + WH.status);
		        System.out.println("Cooking Oven Status " + CO.status);
		        position++;
		        if (events.size() <= position) {break;}
		    }
		}
    }
}

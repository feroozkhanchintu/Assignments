package com.cnu2016.assignment02;

import java.util.Comparator;

public class EventComparator implements Comparator<Event>{
    @Override
    public int compare(Event a, Event b) {
        return a.startTime - b.startTime;
    }
}

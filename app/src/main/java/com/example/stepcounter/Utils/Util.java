package com.example.stepcounter.Utils;

import android.text.format.DateFormat;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public abstract class Util {
    public static long getToday(){
        Calendar c= Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTimeInMillis();
    }
    public static long getTomorrow(){
        Calendar c= Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,1);
        c.set(Calendar.MILLISECOND,0);
        c.add(Calendar.DATE,1);
        return c.getTimeInMillis();
    }

    public static long getPrevious(int n){
        Calendar c= Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        Log.v("StepCounterCalendar1",String.valueOf(c.getTimeInMillis()));
        c.add(Calendar.DATE,-n);
        Log.v("StepCounterCalendar2",String.valueOf(c.getTimeInMillis())+":"+Calendar.DATE);
        Long g = c.getTimeInMillis();
        Log.v("StepCounterCalendar3", DateFormat.format("MM/dd/yyyy",new Date(g)).toString());
        return c.getTimeInMillis();
    }
}

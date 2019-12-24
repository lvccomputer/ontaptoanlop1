package com.dev.lvc.math1.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeUtils {
    public static SimpleDateFormat formatDate = new SimpleDateFormat( "dd/MM/yyyy" );
    public static SimpleDateFormat formatTime = new SimpleDateFormat( "HH:mm:ss" );
    public static String date() {
        Calendar calendar = Calendar.getInstance();
        return formatDate.format( calendar.getTime() );
    }
    public static String time(){
        Calendar calendar = Calendar.getInstance();
        return formatTime.format( calendar.getTime() );
    }
}

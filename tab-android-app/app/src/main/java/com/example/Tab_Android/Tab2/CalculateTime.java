package com.example.Tab_Android.Tab2;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CalculateTime {

    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getHourDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.HOURS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getMinutesDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.MINUTES.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String caltime(SimpleDateFormat format, String oldDate, String newDate){
        long date = getDateDiff(format, oldDate, newDate);
        String suffix;
        if(date > 0){
            suffix = "일 전";
        }else if(getHourDiff(format, oldDate, newDate) > 0){
            date = getHourDiff(format, oldDate, newDate);
            System.out.println("시간:"+date);
            suffix = "시간 전";
        }else if(getMinutesDiff(format, oldDate, newDate) > 0){
            date = getMinutesDiff(format, oldDate, newDate);
            System.out.println("분:"+date);
            suffix = "분 전";
        }else{
            date = getMinutesDiff(format, oldDate, newDate);
            suffix = "초 전";
        }

        return (int) date + suffix;
    }

}

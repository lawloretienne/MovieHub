package com.etiennelawlor.moviehub.util;

import android.text.format.DateUtils;

import com.etiennelawlor.moviehub.MovieHubApplication;
import com.etiennelawlor.moviehub.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by etiennelawlor on 11/8/15.
 */
public class DateUtility {

    // region Constants
    public static final int FORMAT_RELATIVE = 0;
    public static final int FORMAT_ABSOLUTE = 1;
    // endregion

    public static Calendar getCalendar(String timestamp, String pattern){
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getDefault());
        try {
            Date date = sdf.parse(timestamp);
            calendar.setTime(date);
        } catch (ParseException e){
            e.printStackTrace();
        }

        return calendar;
    }

    public static boolean isSameYear(Calendar cal1, Calendar cal2){
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        return (year1 == year2);
    }

    public static boolean isToday(Calendar calendar){
        return DateUtils.isToday(calendar.getTimeInMillis());
    }

    public static boolean isTomorrow(Calendar calendar){
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        return DateUtils.isToday(cal.getTimeInMillis());
    }

    public static boolean isSameDayOfWeek(Calendar cal1, Calendar cal2){
        int dayOfWeek1 = cal1.get(Calendar.DAY_OF_WEEK);
        int dayOfWeek2 = cal2.get(Calendar.DAY_OF_WEEK);

        return (dayOfWeek1 == dayOfWeek2);
    }

    public static long getTimeUnitDiff(Calendar cal1, Calendar cal2, TimeUnit timeUnit) {
        long diffInMillies = cal1.getTime().getTime() - cal2.getTime().getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static String getFormattedDateAndTime(Calendar calendar, int format) {
        String formattedTime = "";
        switch (format) {
            case FORMAT_ABSOLUTE:
                formattedTime = getFormattedAbsoluteDateAndTime(calendar);
                break;
            case FORMAT_RELATIVE:
                formattedTime = getFormattedRelativeDateAndTime(calendar);
                break;
            default:
                break;
        }

        return formattedTime;
    }

    public static String getFormattedAbsoluteDateAndTime(Calendar calendar) {
        String formattedAbsoluteDateAndTime;
        long days = getTimeUnitDiff(calendar, Calendar.getInstance(), TimeUnit.DAYS);
        if(days <= -7){
            formattedAbsoluteDateAndTime = String.format("%s %s %s",
                    getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)),
                    getFormattedDate(calendar),
                    getFormattedTime(calendar));
        } else if(days>-7 && days<7){
            if(isToday(calendar)){
                formattedAbsoluteDateAndTime = getFormattedTime(calendar);
            } else if(isSameDayOfWeek(calendar, Calendar.getInstance())){
                formattedAbsoluteDateAndTime = String.format("%s %s %s",
                        getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)),
                        getFormattedDate(calendar),
                        getFormattedTime(calendar));
            } else {
                formattedAbsoluteDateAndTime = String.format("%s %s", getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)), getFormattedTime(calendar));
            }
        } else {
            formattedAbsoluteDateAndTime = String.format("%s %s %s",
                    getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)),
                    getFormattedDate(calendar),
                    getFormattedTime(calendar));
        }

        return formattedAbsoluteDateAndTime;
    }

    public static String getFormattedRelativeDateAndTime(Calendar calendar){
        String formattedRelativeDateAndTime;

        long days = getTimeUnitDiff(calendar, Calendar.getInstance(), TimeUnit.DAYS);
        if(days<=-30){
            formattedRelativeDateAndTime = getFormattedDate(calendar);
        } else if(days>-30 && days<=-7){
            formattedRelativeDateAndTime = String.format("%d days ago", Math.abs(days));
        } else if(days>-7 && days<7){
            long seconds = getTimeUnitDiff(calendar, Calendar.getInstance(), TimeUnit.SECONDS);
            if(seconds>-60 && seconds<=0){
                formattedRelativeDateAndTime = "Just now";
            } else {
                formattedRelativeDateAndTime = DateUtils.getRelativeTimeSpanString(calendar.getTimeInMillis(), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            }
        } else if(days>=7 && days < 30){
            formattedRelativeDateAndTime = String.format("In %d days", days);
        } else {
            formattedRelativeDateAndTime = getFormattedDate(calendar);
        }
        return formattedRelativeDateAndTime;
    }

    public static String getFormattedDate(Calendar calendar) {
        String formattedDate;

        String month = getMonth(calendar.get(Calendar.MONTH));
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        if (isSameYear(calendar, Calendar.getInstance())) {
            formattedDate = String.format("%s %d", month, day);
        } else {
            formattedDate = String.format("%s %d, %d", month, day, year);
        }

        return formattedDate;
    }

    public static String getFormattedTime(Calendar calendar) {
        String formattedAbsoluteTime = String.format("%d:%02d %s",
                getHour(calendar.get(Calendar.HOUR)),
                calendar.get(Calendar.MINUTE),
                getMeridiem(calendar.get(Calendar.AM_PM)));

        return formattedAbsoluteTime;
    }

    public static String getMonth(int month) {
        String[] months = MovieHubApplication.getInstance().getResources().getStringArray(R.array.months);
        return months[month];
    }

    private static String getDayOfWeek(int dayOfWeek){
        String[] days = MovieHubApplication.getInstance().getResources().getStringArray(R.array.days);
        return days[dayOfWeek];
    }

    private static String getMeridiem(int meridiem){
        String[] meridiems = MovieHubApplication.getInstance().getResources().getStringArray(R.array.meridiems);
        return meridiems[meridiem];
    }

    private static int getHour(int h){
        if(h == 0)
            return 12;
        else
            return h;
    }
}

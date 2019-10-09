package com.fossourier.nicolas.mynews.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.fossourier.nicolas.mynews.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class DateHelper {

    public static String convertDate(String nytDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
        Date date = null;
        try { date = inputFormat.parse(nytDate); }
        catch (ParseException e) { e.printStackTrace(); }
        return outputFormat.format(date);
    }

    public static String convertDatePicker(String datePicker) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
        Date date = null;
        try { date = inputFormat.parse(datePicker); }
        catch (ParseException e) { e.printStackTrace(); }
        return outputFormat.format(date);
    }

    @SuppressLint("SetTextI18n")
    public static String pickerFormatDate(int yearInt, int monthInt, int dayInt, TextView textDate) {
        String year = Integer.toString(yearInt);
        String month = (monthInt + 1 < 10) ? "0" +
                (monthInt + 1) : Integer.toString(monthInt + 1);
        String day = (dayInt < 10) ? "0" + dayInt : Integer.toString(dayInt);
        textDate.setText(day + "/" + month + "/" + year);
        return year + month + day;
    }

    @SuppressLint("SetTextI18n")
    public static String pickerFormatDateTest(int yearInt, int monthInt, int dayInt) {
        String year = Integer.toString(yearInt);
        String month = (monthInt + 1 < 10) ? "0" +
                monthInt : Integer.toString(monthInt);
        String day = (dayInt < 10) ? "0" + dayInt : Integer.toString(dayInt);
        return year + month + day;
    }

    public static boolean datesAreValid(Context context, String beginDate, String endDate) {
        if (!beginDate.isEmpty() && !endDate.isEmpty()
                && Integer.parseInt(beginDate) > Integer.parseInt(endDate)) {
            Toast.makeText(context,
                    context.getResources().getString(R.string.checkBeginAndEndDate),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (beginDate.isEmpty() || endDate.isEmpty()){
            Toast.makeText(context,
                    context.getResources().getString(R.string.checkDateSelected),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean testDatesAreValid(String beginDate, String endDate) {
        return beginDate.isEmpty() || endDate.isEmpty()
                || Integer.parseInt(beginDate) <= Integer.parseInt(endDate);
    }

    public static Calendar setTimeNotif(String savedTime) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        Calendar tempCalendar = Calendar.getInstance(Locale.getDefault());
        if (!savedTime.equals("")) {
            Date date = null;
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            try {
                date = sdf.parse(savedTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert date != null;
            tempCalendar.setTime(date);
            if (tempCalendar.get(Calendar.HOUR_OF_DAY) <= calendar.get(Calendar.HOUR_OF_DAY) ){
                if (tempCalendar.get(Calendar.MINUTE) < calendar.get(Calendar.MINUTE)){
                    calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK)+1);
                }
            }
            calendar.set(Calendar.HOUR_OF_DAY, tempCalendar.get(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, tempCalendar.get(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, 0);
        }
        return calendar;
    }
}

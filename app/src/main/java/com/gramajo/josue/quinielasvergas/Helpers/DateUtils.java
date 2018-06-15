package com.gramajo.josue.quinielasvergas.Helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by josuegramajo on 6/15/18.
 */

public class DateUtils {
    public static DateUtils INSTANCE = new DateUtils();

    public String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public Date stringToDate(String d){
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            return format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

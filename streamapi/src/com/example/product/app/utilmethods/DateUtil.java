package com.example.product.app.utilmethods;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * this is a simple java class that converts most of the strings dates in to date objects
 * source url::http://viralpatel.net/blogs/check-string-is-valid-date-java/
 */
public class DateUtil {
    // List of all date formats that we want to parse.
    // Add your own format here.
    private static List<SimpleDateFormat>
    dateFormats = new ArrayList<SimpleDateFormat>() {
        {
            add(new SimpleDateFormat("M/dd/yyyy"));
            add(new SimpleDateFormat("dd.M.yyyy"));
            add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.MMM.yyyy"));
            add(new SimpleDateFormat("dd-MMM-yyyy"));
        }
    };

    /**
     * Convert String with various formats into java.util.Date
     *
     * @param input
     *            Date as a string
     * @return java.util.Date object if input string is parsed
     * 			successfully else returns null
     */
    public static Date convertToDate(String input) {
        Date date = null;
        if(null == input) {
            return null;
        }
        for (SimpleDateFormat format : dateFormats) {
            try {
                //format.setLenient(false);
                date = format.parse(input);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) {
                break;
            }
        }

        return date;
    }
}



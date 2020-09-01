package it.marcosautto.parthenopeddit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    public String parseDate(String uglyDate) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = sdf1.parse(uglyDate);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = sdf2.format(date);
        return newDate;
    }
}

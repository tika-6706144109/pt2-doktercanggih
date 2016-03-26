package team.iyooo.id.doktercanggih.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tikafby 03/12/16.
 */
public class DateHelper {
    public final static String OLDFORMAT = "yyyy-MM-dd";
    public final static String DATE = "dd-MM-yyyy";
    public final static String NEWFORMAT = "dd MMMM yyyy";


    public static String dateParseToString(String dateInput, String old_format, String formatDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(old_format);
            Date date;
            date = sdf.parse(dateInput);
            Locale id = new Locale("in", "ID");
            SimpleDateFormat format = new SimpleDateFormat(formatDate, id);
            String result = format.format(date);
            return result;
        } catch (ParseException e) {
            return dateInput;
        }
    }
}

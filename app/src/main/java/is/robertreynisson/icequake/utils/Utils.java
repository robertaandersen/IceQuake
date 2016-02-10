package is.robertreynisson.icequake.utils;

import android.util.Log;

import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDateTime;

import java.util.Locale;

/**
 * Created by robert on 9.2.2016.
 */
public class Utils {

    public static void logger(String tag, String msg) { Log.d(tag, msg); }

    public static String PrettyDateFormatter(long date) {
        // Do additional formatting here (e.g. prettytime etc.)
        return NiceDate(new LocalDateTime(date));
    }

    private static String NiceDate(LocalDateTime localDateTime) {
        Locale l = Locale.getDefault();
        return
                localDateTime.dayOfWeek().getAsShortText(l)
                        +" " + localDateTime.getDayOfMonth()
                        +" " +localDateTime.toString("MMM")
                        +" " + localDateTime.getYear()
                        +" "+ getClock(localDateTime);
    }

    public static String getClock(LocalDateTime localDateTime) {
        int min = localDateTime.get(DateTimeFieldType.minuteOfHour());
        String minutes = min < 10 ? "0"+min : min+"";
        return localDateTime.get(DateTimeFieldType.hourOfDay())
                +":"+ minutes
                +":"+localDateTime.get(DateTimeFieldType.secondOfMinute());
    }

    public static String PrettyDateFormatter(String timestamp) {
        return timestamp;
    }
}

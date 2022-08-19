package libraryapp.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateUtils {
    private DateUtils() {
        throw new UnsupportedOperationException("Thou shall not instantiated!!");
    }

    public static Calendar todayAsCalendar() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.clear(Calendar.MINUTE);
        now.clear(Calendar.SECOND);
        now.clear(Calendar.MILLISECOND);
        return now;
    }

    public static Date today() {
        Calendar now = todayAsCalendar();
        return now.getTime();
    }

    public static Date todayPlus(int days) {
        Calendar now = todayAsCalendar();
        now.add(Calendar.DATE, days);
        return now.getTime();
    }
}

package entities.extension;

import java.util.Calendar;
import java.util.Date;

public class DateExtension {
    public static Date AddDaysToDate(Date date, int numberOfDays)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, numberOfDays);
        return c.getTime();
    }
}

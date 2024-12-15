package net.aplat.stocks.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtil {
    public static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}

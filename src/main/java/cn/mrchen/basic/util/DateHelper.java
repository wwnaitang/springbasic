package cn.mrchen.basic.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateHelper {

    /**
     * 在date的基础上增加或减少天
     * @param date
     * @param days
     * @return
     */
    public static Date calculateByDays(Date date, int days) {
        boolean isAdd = true;
        if (date == null) {
            return null;
        } else if (days == 0) {
            return (Date) date.clone();
        } else if (days < 0) {
            isAdd = false;
            days = days * (-1);
        }

        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        if (isAdd) {
            localDateTime = localDateTime.plusDays(days);
        } else {
            localDateTime = localDateTime.minusDays(days);
        }
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Date newDate = Date.from(zonedDateTime.toInstant());
        return newDate;
    }
}

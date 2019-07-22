package cn.mrchen.basic.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateHelper {

    /**
     * 在date的基础上增加或减少天
     * @param date
     * @param days
     * @return
     * @deprecated 建议使用dateCalculation(Date date, ChronoUnit chronoUnit, long size)
     */
    @Deprecated
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

    /**
     *
     * @param date
     * @param chronoUnit
     * @param size
     * @return
     */
    public static Date dateCalculation(Date date, ChronoUnit chronoUnit, long size) {
        boolean isAdd = true;
        if (date == null || chronoUnit == null) {
            throw new RuntimeException("Date or unit of account is empty, cannot be calculated");
        } else if (size == 0) {
            return (Date) date.clone();
        } else if (size < 0) {
            isAdd = false;
            size = size * (-1);
        }

        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        if (isAdd) {
            localDateTime = localDateTime.plus(size, chronoUnit);
        } else {
            localDateTime = localDateTime.minus(size, chronoUnit);
        }
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Date newDate = Date.from(zonedDateTime.toInstant());
        return newDate;
    }
}

package cn.mrchen;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TimeTest {

    @Test
    public void test01() {
        LocalDateTime nowLocalDate = LocalDateTime.now();
        LocalDateTime localDate = LocalDateTime.now().plusDays(1);
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = nowLocalDate.atZone(zoneId).toInstant();
        Long now = instant.toEpochMilli();
        instant = localDate.atZone(zoneId).toInstant();
        Long day2 = instant.toEpochMilli();
        System.out.println(nowLocalDate);
        System.out.println(localDate);
        System.out.println(day2 - now);
        System.out.println(24*60*60*1000L);
    }
}

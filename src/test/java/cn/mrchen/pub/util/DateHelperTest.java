package cn.mrchen.pub.util;

import org.junit.Test;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateHelperTest {

    @Test
    public void dateCalculationTest01() {
        Date now = new Date(System.currentTimeMillis());
        Date after = DateHelper.dateCalculation(now, ChronoUnit.YEARS, -7);
        after = DateHelper.dateCalculation(after, ChronoUnit.MONTHS, -5);
        after = DateHelper.dateCalculation(after, ChronoUnit.DAYS, 7);
        now = after;
        after = DateHelper.dateCalculation(after, ChronoUnit.MONTHS, 1);
        System.out.println(now.toString());
        System.out.println(after.toString());
    }
}

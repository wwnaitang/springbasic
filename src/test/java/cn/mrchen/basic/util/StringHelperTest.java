package cn.mrchen.basic.util;

import org.junit.Test;

public class StringHelperTest {

    @Test
    public void test01() {
        String str = "mrchen";
        String hexStr = StringHelper.str2HexStr(str);
        System.out.println(hexStr);
        System.out.println(StringHelper.hexStr2Str(hexStr));
    }
}

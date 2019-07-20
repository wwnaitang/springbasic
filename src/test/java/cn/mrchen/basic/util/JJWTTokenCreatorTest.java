package cn.mrchen.basic.util;

import org.junit.Test;

import java.lang.reflect.Method;

public class JJWTTokenCreatorTest {

    public static Class clazz = null;
    public static JJWTTokenCreator jjwtTokenCreator = null;

    static {
        try {
            clazz = Class.forName("cn.mrchen.basic.util.JJWTTokenCreator");
            jjwtTokenCreator = (JJWTTokenCreator) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test01() throws Exception {

        Method generalKey = clazz.getDeclaredMethod("generalKey");
        generalKey.setAccessible(true);
        generalKey.invoke(jjwtTokenCreator);
    }

}

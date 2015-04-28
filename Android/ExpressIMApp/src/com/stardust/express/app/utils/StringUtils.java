package com.stardust.express.app.utils;

/**
 * Created by Gyb on 2015/4/27.
 */
public class StringUtils {

    public static boolean isNotNull(String s) {
        return s != null && !isEmpty(s);
    }

    public static boolean isEmpty(String s) {
        return s.trim().length() == 0;
    }
}

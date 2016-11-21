package com.stardust.express.app.utils;

import net.sourceforge.pinyin4j.PinyinHelper;

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

    // 返回中文的首字母
    public static String getPinYin(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }
}

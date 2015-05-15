package com.stardust.express.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * app˽��Ŀ¼�±���SharedPreferences�ļ�
 *
 * @author qianjunping
 */
public class SharedUtil {

    /**
     * ��map�����е����м�ֵ�Զ�����,map��key��Ӧ�����key value��Ӧ�����value
     *
     * @param context
     * @param fileName
     * @param map
     */
    public static void putMap(Context context, String fileName,
                              Map<String, String> map) {
        if (map != null && map.size() > 0) {
            SharedPreferences preferences = context.getSharedPreferences(
                    fileName, Context.MODE_PRIVATE);
            Editor editor = preferences.edit();
            Set<String> keySet = map.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = map.get(key);
                editor.putString(key, value);
            }
            editor.commit();
        }
    }

    /**
     * ����һ��String
     *
     * @param context
     * @param fileName ������ļ���
     * @param key
     * @param value
     */
    public static void putString(Context context, String fileName, String key,
                                 String value) {
        SharedPreferences preferences = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * ��ȡһ��String
     *
     * @param context
     * @param fileName
     * @param key
     * @return
     */
    public static String getString(Context context, String fileName, String key) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(
                    fileName, Context.MODE_PRIVATE);
            return preferences.getString(key, "");
        }
        return "";
    }

    /**
     * ����boolean
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String fileName, String key,
                                  boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * ��ȡbooleanֵ<br>
     * <p/>
     * ���Ҳ�����ֵĬ�Ϸ��� false
     *
     * @param context
     * @param fileName
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String fileName,
                                     String key) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(
                    fileName, Context.MODE_PRIVATE);
            return preferences.getBoolean(key, false);
        }
        return false;
    }

    // /////////////////////////////////////////////////////////////////////
    // ////////////////////// ������Ĭ���ļ��е�Api /////////////////////////
    // ////////////////////////////////////////////////////////////////////

    /**
     * ��map�����е����м�ֵ�Զ�������Ĭ�ϵ��ļ���,map��key��Ӧ�����key value��Ӧ�����value
     *
     * @param context
     * @param map
     */
    public static void putMap(Context context, Map<String, String> map) {
        if (map != null && map.size() > 0) {
            SharedPreferences preferences = context.getSharedPreferences(
                    context.getPackageName(), Context.MODE_PRIVATE);
            Editor editor = preferences.edit();
            Set<String> keySet = map.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = map.get(key);
                editor.putString(key, value);
            }
            editor.commit();
        }
    }

    /**
     * ����Ĭ�ϵ��ļ�������,Ĭ����PackageNameΪ�ļ���
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * ��ȡĬ�ϱ����ļ��е����,Ĭ����PackageNameΪ�ļ���
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(
                    context.getPackageName(), Context.MODE_PRIVATE);
            return preferences.getString(key, "");
        }
        return "";
    }

    /**
     * ����Ĭ�ϵ��ļ�������,Ĭ����PackageNameΪ�ļ���
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * ��ȡĬ�ϱ����ļ��е����,Ĭ����PackageNameΪ�ļ���<br>
     * <p/>
     * ���Ҳ�����ֵĬ�Ϸ��� false
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(
                    context.getPackageName(), Context.MODE_PRIVATE);
            return preferences.getBoolean(key, false);
        }
        return false;
    }

    public static long getLong(Context context, String key) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(
                    context.getPackageName(), Context.MODE_PRIVATE);
            return preferences.getLong(key, -1);
        }
        return -1;
    }

    public static void putLong(Context context, String key, long value) {
        SharedPreferences preferences = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * ����������
     *
     * @param context
     * @return
     */
    public static void clear(Context context) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
            Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
        }
    }
}

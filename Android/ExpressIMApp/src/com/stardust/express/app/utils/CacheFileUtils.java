package com.stardust.express.app.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Sylar on 15/5/25.
 */
public class CacheFileUtils {

    public static void cleanExternalFiles(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalFilesDir("/"));
        }
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     *
     * @param directory
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
                if (item.isDirectory()) {
                    deleteFilesByDirectory(item);
                }
            }
        }
    }
}
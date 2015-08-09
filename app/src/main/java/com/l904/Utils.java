package com.l904;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by santosh on 8/9/15.
 */
public class Utils {
    public static String TAG = "Utils";
    public static void copyDBToExternal(Context ctx){
        String from = "/data/data/com.l904/databases/todoList.db";
        try {
            FileInputStream fis=new FileInputStream(new File(from));
            FileOutputStream fos=new FileOutputStream("/sdcard/todo.db");
            Log.d(TAG," "+ctx.getExternalFilesDir(null));
            byte [] buffer = new byte[1024];
            while(fis.read(buffer)>0){
                fos.write(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.gufra.net.base.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private static String TAG = "gufra.FileManager";
    private static File file = null;
    private static String readString = "";

    public static void writeFile(Context context, String fileName, String text) {
        try {
            Log.d(TAG,"写入文件"+fileName);
            file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);
            if (file.exists()) {
                file.delete();
                Log.e(TAG, "是否创建成功：" + file.createNewFile());
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.close();
            Log.d(TAG,"写入完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(Context context, String fileName){
        try {
            Log.d(TAG,"读取文件"+fileName);
            file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            readString = reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return readString;
    }
}

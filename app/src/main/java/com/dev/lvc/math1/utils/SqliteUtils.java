package com.dev.lvc.math1.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dev.lvc.math1.activities.MainActivity;
import com.dev.lvc.math1.models.History;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class SqliteUtils {
    public static SQLiteDatabase readDataBaseFromAssets(String filename, Context context) {
        File file = context.getDatabasePath(filename);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }

            InputStream inputStream = null;
            try {
                inputStream = context.getAssets().open(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024 * 8];
            int numOfBytesToRead;
            try {
                while ((numOfBytesToRead = inputStream.read(buffer)) > 0)
                    outputStream.write(buffer, 0, numOfBytesToRead);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return SQLiteDatabase.openOrCreateDatabase(file, null);
    }
    public static ArrayList<History> getHistories(MainActivity mainActivity){
        ArrayList<History> histories = new ArrayList<>();
        Cursor cursor = mainActivity.historySqlite.rawQuery("select * from History",null);
        if (cursor!= null && cursor.moveToFirst()) {
            do {
                History history = new History();
                history.setLesson(cursor.getString(1));
                history.setResult("Điểm "+ cursor.getString(3)+"/10 thời gian còn lại "+ cursor.getString(2));
                history.setTime(cursor.getString(4));
                histories.add(history);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return histories;
    }
}

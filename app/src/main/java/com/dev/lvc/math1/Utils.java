package com.dev.lvc.math1;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.dev.lvc.math1.models.Answer;
import com.dev.lvc.math1.models.ItemQuestionPractice;
import com.dev.lvc.math1.models.Practice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Utils {
    private static final String TAG = "cuong";
    public static String URI = "file:///android_asset/";

    public static String loadJSONFromAssets(Context context, String nameJson) {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open(nameJson);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");


        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        return json;
    }

    public static ArrayList<ItemQuestionPractice> loadPracticeJsonData(Context context, String id, String practiceid) {
        ArrayList<ItemQuestionPractice> answers = new ArrayList<>();

        try {
            JSONObject practiceObject = new JSONObject(Utils.loadJSONFromAssets(context, "chuong" + id + "_bai_" + practiceid + ".json"));
            JSONArray practiceJsonArray = practiceObject.getJSONArray("chuong" + id + "_bai_" + practiceid);
            for (int index = 0; index < practiceJsonArray.length(); index++) {
                JSONObject itemQuestion = practiceJsonArray.getJSONObject(index);
                ItemQuestionPractice practice = new ItemQuestionPractice();
                practice.setA(itemQuestion.getString("A"));
                practice.setB(itemQuestion.getString("B"));
                practice.setC(itemQuestion.getString("C"));
                practice.setD(itemQuestion.getString("D"));
                practice.setRequest("Câu "+itemQuestion.getString("id")+": " + itemQuestion.getString("yeucau"));
                practice.setRequestText(itemQuestion.getString("chu"));
                practice.setRequestImage(itemQuestion.getString("hinh"));
                practice.setResult(itemQuestion.getString("check"));
                answers.add(practice);
            }
        } catch (JSONException e) {
            Log.e(TAG, "ERROR JSON: " + e);
        }
        return answers;
    }
}

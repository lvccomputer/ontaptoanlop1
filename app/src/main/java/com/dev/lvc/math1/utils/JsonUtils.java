package com.dev.lvc.math1.utils;

import android.content.Context;
import android.util.Log;

import com.dev.lvc.math1.models.PracticeQuestion;
import com.dev.lvc.math1.models.TestsQuestion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonUtils {
    private static final String TAG = "cuong";
    public static String URI = "file:///android_asset/";

    public static String loadJSONFromAssets(Context context, String nameJson) {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open(nameJson);
            Log.e(TAG, "inputStream: "+inputStream );
            int size = inputStream.available();
            Log.e(TAG, "size: "+size );
            byte[] buffer = new byte[size];
            Log.e(TAG, "buffer: "+buffer );
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

            Log.e(TAG, "loadJSONFromAssets: "+json );

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        return json;

    }

    public static ArrayList<PracticeQuestion> loadPracticeJsonData(Context context, String id, String practiceid) {
        ArrayList<PracticeQuestion> answers = new ArrayList<>();

        try {
            JSONObject practiceObject = new JSONObject(JsonUtils.loadJSONFromAssets(context, "chuong" + id + "_bai_" + practiceid + ".json"));
            JSONArray practiceJsonArray = practiceObject.getJSONArray("chuong" + id + "_bai_" + practiceid);
            for (int index = 0; index < practiceJsonArray.length(); index++) {
                JSONObject itemQuestion = practiceJsonArray.getJSONObject(index);
                PracticeQuestion practice = new PracticeQuestion();
                practice.setA(itemQuestion.getString("A"));
                practice.setB(itemQuestion.getString("B"));
                practice.setC(itemQuestion.getString("C"));
                practice.setD(itemQuestion.getString("D"));
                practice.setId(Integer.parseInt(itemQuestion.getString("id")));
                practice.setRequest( itemQuestion.getString("yeucau"));
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
    public static ArrayList<TestsQuestion> loadTestsJsonData(Context context, String id){
        ArrayList<TestsQuestion> testsQuestionArrayList = new ArrayList<>();
        try {
            JSONObject practiceObject = new JSONObject(JsonUtils.loadJSONFromAssets(context, "kiemtra.json"));
            JSONArray practiceJsonArray = practiceObject.getJSONArray("bai_" + id);
            for (int index = 0; index < practiceJsonArray.length(); index++) {
                JSONObject itemQuestion = practiceJsonArray.getJSONObject(index);
                TestsQuestion testsQuestion = new TestsQuestion();
                testsQuestion.setA(itemQuestion.getString("A"));
                testsQuestion.setB(itemQuestion.getString("B"));
                testsQuestion.setC(itemQuestion.getString("C"));
                testsQuestion.setD(itemQuestion.getString("D"));
                testsQuestion.setId(Integer.parseInt(itemQuestion.getString("id")));
                testsQuestion.setRequest( itemQuestion.getString("yeucau"));
                testsQuestion.setRequestText(itemQuestion.getString("chu"));
                testsQuestion.setRequestImage(itemQuestion.getString("hinh"));
                testsQuestion.setResult(itemQuestion.getString("check"));
                testsQuestionArrayList.add(testsQuestion);
            }
        } catch (JSONException e) {
            Log.e(TAG, "ERROR JSON: " + e);
        }
        return testsQuestionArrayList;
    }
}

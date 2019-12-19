package com.dev.lvc.math1;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static String URI = "file:///android_asset/";

    public static String loadJSONFromAssets(Context context,String nameJson){
        String json = null;
        try
        {
            InputStream inputStream = context.getAssets().open( nameJson );
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read( buffer );
            inputStream.close();
            json = new String( buffer , "UTF-8" );


        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        return json;
    }

}

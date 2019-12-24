package com.dev.lvc.math1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dev.lvc.math1.R;

public class SplashScreenActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        intent();
    }
    private void intent() {
        handler.postDelayed(() -> {
            startActivity( new Intent( SplashScreenActivity.this, MainActivity.class ) );
            finish();
        },3000);
    }
}

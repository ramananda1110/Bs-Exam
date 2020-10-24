package com.orko.androidtestproject.view;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.orko.androidtestproject.R;

public class SplashScreen extends AppCompatActivity implements Runnable {

    private static final int SPLASH_DELAY = 1500;
    private Handler handler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


    }


    @Override
    protected void onStart() {
        super.onStart();
        handler = new Handler();
        handler.postDelayed(this, SPLASH_DELAY);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(this);
        handler = null;
    }

    @Override
    public void run() {

        MainActivity.start(this);
    }


}
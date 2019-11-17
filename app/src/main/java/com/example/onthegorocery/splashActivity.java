package com.example.onthegorocery;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TimerTask timert = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(splashActivity.this,signin_activity.class));
                finish();
            }
        };
        Timer time = new Timer();
        time.schedule(timert,2000);
    }
}

package com.example.retrofit_demo_2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.retrofit_demo_2.R;

public class SplashScreen_Activity extends AppCompatActivity {

    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;

    int page;
    boolean isLogin=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler=new Handler();
        Runnable runnable;

        preferences = getSharedPreferences("myPref",MODE_PRIVATE);
        editor = preferences.edit();

        isLogin=preferences.getBoolean("isLogin",false);
        runnable=new Runnable() {
            @Override
            public void run() {

                if (isLogin){
                    Intent intent=new Intent(SplashScreen_Activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent=new Intent(SplashScreen_Activity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        handler.postDelayed(runnable,5000);
    }
}
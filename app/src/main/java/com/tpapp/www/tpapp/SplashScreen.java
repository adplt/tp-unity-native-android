package com.tpapp.www.tpapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity{

    private final int PANJANG_SPLASH_SCREEN = 5000;
    private Session sm;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_splash_screen);

        sm = new Session(SplashScreen.this);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                if(sm.getStatusLogin() == true){
                    startActivity(new Intent(SplashScreen.this, Home.class));
                }else{
                    startActivity(new Intent(SplashScreen.this, Login.class));
                }
                finish();
            }
        }, PANJANG_SPLASH_SCREEN);
    }

}
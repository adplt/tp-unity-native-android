package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.HashMap;

public class Event extends AppCompatActivity{

    private Button b_1;
    private Button b_2;
    private Session sm;
    private HashMap<String,String> user, data;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_10);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        pd = new ProgressDialog(Event.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        b_1 = (Button) findViewById(R.id.accept_button);
        b_1.setTextColor(getResources().getColor(R.color.white_background));

        b_2 = (Button) findViewById(R.id.decline_button);
        b_2.setTextColor(getResources().getColor(R.color.white_background));

        sm = new Session(Event.this);

        if(sm.getStatusLogin() == true){
            data = sm.getEvent();
            user = sm.getLogin();

            b_1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    new AlertDialog.Builder(Event.this)
                        .setTitle("Thanks !")
                        .setMessage("You've been accepted this event. For make sure, let's check your schedule for more info and status.")
                        .setIcon(R.drawable.ic_logo_48dp)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onBackPressed();
                            }
                        }).show();
                }
            });

            b_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(Event.this)
                        .setTitle("Thanks !")
                        .setMessage("You've been declined this event. For make sure, let's check your schedule for more info and status.")
                        .setIcon(R.drawable.ic_logo_48dp)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onBackPressed();
                            }
                        }).show();
                }
            });
        }else{
            new AlertDialog.Builder(Event.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(Event.this).execute();
                    }
                })
                .show();
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
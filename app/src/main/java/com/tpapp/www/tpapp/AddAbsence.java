package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class AddAbsence extends AppCompatActivity{

    private RelativeLayout rl_1,rl_2,rl_3;
    private TextView tv;
    private EditText et_1,et_2,et_3,et_4,et_5;
    private ProgressDialog pd;
    private Session sm;
    private HashMap<String,String> user;
    private DatePicker dp;
    private TimePicker tp;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_add_absence);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_7);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        pd = new ProgressDialog(AddAbsence.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        et_5 = (EditText) findViewById(R.id.description_absence);
        et_5.setTextColor(getResources().getColor(R.color.black_background));

        rl_2 = (RelativeLayout) findViewById(R.id.date_absence);
        rl_3 = (RelativeLayout) findViewById(R.id.time_absence);

        rl_2.setVisibility(View.GONE);
        rl_3.setVisibility(View.GONE);

        tv = (TextView) findViewById(R.id.save_absence_label);
        tv.setTextColor(getResources().getColor(R.color.white_background));

        sm = new Session(AddAbsence.this);

        if(sm.getStatusLogin() == true){
            user = sm.getLogin();

            dp = (DatePicker) findViewById(R.id.get_date_absence);
            tp = (TimePicker) findViewById(R.id.get_time_absence);

            et_1 = (EditText) findViewById(R.id.start_date_field_absence);
            et_2 = (EditText) findViewById(R.id.start_time_field_absence);
            et_3 = (EditText) findViewById(R.id.end_date_field_absence);
            et_4 = (EditText) findViewById(R.id.end_time_field_absence);

            et_1.setInputType(InputType.TYPE_NULL);
            et_2.setInputType(InputType.TYPE_NULL);
            et_3.setInputType(InputType.TYPE_NULL);
            et_4.setInputType(InputType.TYPE_NULL);

            et_1.setTextColor(getResources().getColor(R.color.black_background));
            et_2.setTextColor(getResources().getColor(R.color.black_background));
            et_3.setTextColor(getResources().getColor(R.color.black_background));
            et_4.setTextColor(getResources().getColor(R.color.black_background));

            et_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDate(et_1);
                }
            });
            et_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getTime(et_2);
                }
            });
            et_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDate(et_3);
                }
            });
            et_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getTime(et_4);
                }
            });

            rl_1 = (RelativeLayout) findViewById(R.id.save_absence);
            rl_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(et_1.getText().toString() == "" || et_1.getText().equals("") || et_2.getText().toString() == "" || et_2.getText().equals("")){
                        Snackbar.make(v, "Date & time in 'start' must be filled !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }else if(et_3.getText().toString() == "" || et_3.getText().equals("") || et_4.getText().toString() == "" || et_4.getText().equals("")){
                        Snackbar.make(v, "Date & time in 'until' must be filled !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }else if(et_5.getText().toString() == "" || et_5.getText().equals("")){
                        Snackbar.make(v, "Description must be filled !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }else{
                        Date date_1,date_2,date_3;

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        DateFormat df = sdf;
                        String date_now = df.format(Calendar.getInstance().getTime());
                        String date_for_compare_1 = et_1.getText() + " " + et_2.getText();
                        String date_for_compare_2 = et_3.getText() + " " + et_4.getText();

                        try{
                            date_1 = sdf.parse(date_for_compare_1);
                            date_2 = sdf.parse(date_for_compare_2);
                            date_3 = sdf.parse(date_now);

                            if(date_3.compareTo(date_1)<0){
                                Snackbar.make(v, "Start time is greater than current time.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            }else if(date_3.compareTo(date_2)<0){
                                Snackbar.make(v, "Until time is greater than current time.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            }else{
                                //Compare time

                                String line = "no_prm=" + user.get(Session.NO_PRM_LOGIN) +
                                        "&start=" + et_1.getText() + "%20" + et_2.getText() +
                                        "&until=" + et_3.getText() + "%20" + et_4.getText() +
                                        "&note=" + et_5.getText();
                                line = line.replaceAll(" ", "%20");
                                new InputAbsence().execute(line);
                            }
                        }catch(ParseException e){
                            e.printStackTrace();
                        }
                    }
                }
            });
        }else{
            new AlertDialog.Builder(AddAbsence.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(AddAbsence.this).execute();
                    }
                })
                .show();
        }
    }

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
            .setMessage("Are you sure want to exit ?")
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(AddAbsence.this, Absence.class));
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){}
            })
            .show();
    }

    public void getDate(final EditText et){
        rl_2.setVisibility(View.VISIBLE);
        ((ViewGroup) rl_2.getParent()).removeView(rl_2);

        new AlertDialog.Builder(AddAbsence.this)
            .setView(rl_2)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Calendar c = Calendar.getInstance();
                    c.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
                    String line = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
                    et.setText(line);
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            })
            .show();
    }

    public void getTime(final EditText et){
        rl_3.setVisibility(View.VISIBLE);
        ((ViewGroup) rl_3.getParent()).removeView(rl_3);

        new AlertDialog.Builder(AddAbsence.this)
            .setView(rl_3)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int hour, minute;
                    String hour_s, minue_s;

                    hour = tp.getCurrentHour();
                    minute = tp.getCurrentMinute();

                    if (hour < 10) {
                        hour_s = "0" + hour;
                    } else {
                        hour_s = String.valueOf(hour);
                    }

                    if (minute < 10) {
                        minue_s = "0" + minute;
                    } else {
                        minue_s = String.valueOf(minute);
                    }

                    String line = hour_s + ":" + minue_s + ":00";
                    et.setText(line);
                }
            })
            .show();
    }

    public class InputAbsence extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected String doInBackground(String... params){
            MainClass c = new MainClass("add_absence.php?" + params[0]);
            c.executeURL();
            return "Add absence successfully !";
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);

            new AlertDialog.Builder(AddAbsence.this)
                .setTitle("Add Absence")
                .setIcon(R.drawable.ic_logo_48dp)
                .setMessage(s)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(AddAbsence.this, Absence.class));
                        }
                })
                .show();

            if(pd.isShowing()){
                pd.dismiss();
            }
        }
    }

}
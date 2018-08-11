package com.tpapp.www.tpapp;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AlarmSetting extends AppCompatActivity {

    private Spinner s_1,s_2,s_3;
    private TextView tv;
    private Session sm;
    private HashMap<String,String> user,data;
    private ProgressDialog pd;
    private AlarmManager am;
    private PendingIntent pi;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_alarm_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_3);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        pd = new ProgressDialog(AlarmSetting.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        tv = (TextView) findViewById(R.id.save_label);
        tv.setTextColor(getResources().getColor(R.color.white_background));

        s_1 = (Spinner) findViewById(R.id.alarm_option);
        s_2 = (Spinner) findViewById(R.id.remind_me_at_option);
        s_3 = (Spinner) findViewById(R.id.sound_option);

        ArrayAdapter<CharSequence> remind_me_at_option = ArrayAdapter.createFromResource(getBaseContext(),R.array.remind_me_at_option,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> sound_option = ArrayAdapter.createFromResource(getBaseContext(),R.array.sound_option,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> alarm_option = ArrayAdapter.createFromResource(getBaseContext(),R.array.alarm_option,android.R.layout.simple_spinner_item);

        remind_me_at_option.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sound_option.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alarm_option.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s_1.setAdapter(alarm_option);
        s_2.setAdapter(remind_me_at_option);
        s_3.setAdapter(sound_option);

        sm = new Session(AlarmSetting.this);

        if(sm.getStatusLogin() == true){
            data = sm.getAlarmSetting();
            user = sm.getLogin();

            s_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(s_1.getSelectedItem().toString() == "Off" || s_1.getSelectedItem().equals("Off")){

                        s_2.setEnabled(false);
                        s_3.setEnabled(false);

                        if(am != null){
                            am.cancel(pi);
                            intent.putExtra("Extra", "no");
                            sendBroadcast(intent);
                        }
                    }else{
                        s_2.setEnabled(true);
                        s_3.setEnabled(true);

                        List<Data.Support> list_s = getSupportSchedule();

                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                        int year = 0, month = 0, day = 0, hour = 0, minute = 0, second = 0;

                        if(s_2.getSelectedItem().toString() == "1 Hour" || s_2.getSelectedItem().equals("1 Hour")){
                            year = 1;
                        }else if(s_2.getSelectedItem().toString() == "2 Hour" || s_2.getSelectedItem().equals("2 Hour")){
                            year = 2;
                        }else if(s_2.getSelectedItem().toString() == "3 Hour" || s_2.getSelectedItem().equals("3 Hour")){
                            year = 3;
                        }else if(s_2.getSelectedItem().toString() == "1 Day" || s_2.getSelectedItem().equals("1 Day")){
                            day = 1;
                        }else if(s_2.getSelectedItem().toString() == "2 Days" || s_2.getSelectedItem().equals("2 Days")){
                            day = 2;
                        }else if(s_2.getSelectedItem().toString() == "3 Days" || s_2.getSelectedItem().equals("3 Days")){
                            day = 3;
                        }else{
                            day = 7;
                        }

                        if(list_s.size()!=0){
                            for(int i=0;i<list_s.size();i++){
                                try{
                                    Calendar c = Calendar.getInstance();

                                    year = sdf.parse(list_s.get(i).getSupportStart()).getYear() - year;
                                    month = sdf.parse(list_s.get(i).getSupportStart()).getMonth() - month;
                                    day = sdf.parse(list_s.get(i).getSupportStart()).getDay() - day;

                                    hour = sdf.parse(list_s.get(i).getSupportStart()).getHours() - hour;
                                    minute = sdf.parse(list_s.get(i).getSupportStart()).getMinutes() - minute;
                                    second =  sdf.parse(list_s.get(i).getSupportStart()).getSeconds() - second;

                                    c.set(Calendar.YEAR, year);
                                    c.set(Calendar.MONTH, month);
                                    c.set(Calendar.DAY_OF_WEEK, day);

                                    c.set(Calendar.HOUR_OF_DAY, hour);
                                    c.set(Calendar.MINUTE, minute);
                                    c.set(Calendar.SECOND, second);

                                    intent = new Intent(AlarmSetting.this,AlarmReceiver.class);
                                    intent.putExtra("Extra", "yes");
                                    pi = PendingIntent.getBroadcast(AlarmSetting.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                    am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pi);
                                }catch(ParseException e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent){
                    if(s_1.getSelectedItem().toString() == "Off" || s_1.getSelectedItem().equals("Off")){
                        s_2.setEnabled(false);
                        s_3.setEnabled(false);

                        if(am != null){
                            am.cancel(pi);
                            intent.putExtra("Extra", "no");
                            sendBroadcast(intent);
                        }
                    }else{
                        s_2.setEnabled(true);
                        s_3.setEnabled(true);

                        List<Data.Support> list_s = getSupportSchedule();

                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                        int year = 0, month = 0, day = 0, hour = 0, minute = 0, second = 0;

                        if(s_2.getSelectedItem().toString() == "1 Hour" || s_2.getSelectedItem().equals("1 Hour")){
                            year = 1;
                        }else if(s_2.getSelectedItem().toString() == "2 Hour" || s_2.getSelectedItem().equals("2 Hour")){
                            year = 2;
                        }else if(s_2.getSelectedItem().toString() == "3 Hour" || s_2.getSelectedItem().equals("3 Hour")){
                            year = 3;
                        }else if(s_2.getSelectedItem().toString() == "1 Day" || s_2.getSelectedItem().equals("1 Day")) {
                            day = 1;
                        }else if(s_2.getSelectedItem().toString() == "2 Days" || s_2.getSelectedItem().equals("2 Days")){
                            day = 2;
                        }else if(s_2.getSelectedItem().toString() == "3 Days" || s_2.getSelectedItem().equals("3 Days")){
                            day = 3;
                        }else{
                            day = 7;
                        }

                        if(list_s.size() != 0){
                            for(int i = 0; i < list_s.size(); i++){
                                try{
                                    Calendar c = Calendar.getInstance();

                                    year = sdf.parse(list_s.get(i).getSupportStart()).getYear() - year;
                                    month = sdf.parse(list_s.get(i).getSupportStart()).getMonth() - month;
                                    day = sdf.parse(list_s.get(i).getSupportStart()).getDay() - day;

                                    hour = sdf.parse(list_s.get(i).getSupportStart()).getHours() - hour;
                                    minute = sdf.parse(list_s.get(i).getSupportStart()).getMinutes() - minute;
                                    second = sdf.parse(list_s.get(i).getSupportStart()).getSeconds() - second;

                                    c.set(Calendar.YEAR, year);
                                    c.set(Calendar.MONTH, month);
                                    c.set(Calendar.DAY_OF_WEEK, day);

                                    c.set(Calendar.HOUR_OF_DAY, hour);
                                    c.set(Calendar.MINUTE, minute);
                                    c.set(Calendar.SECOND, second);

                                    intent = new Intent(AlarmSetting.this, AlarmReceiver.class);
                                    intent.putExtra("Extra", "yes");
                                    pi = PendingIntent.getBroadcast(AlarmSetting.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                    am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
                                }catch(ParseException e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            });

            if(data.get(Session.SETTING_ALARM) != null){
                //Set Alarm
                if(data.get(Session.SETTING_ALARM).toString() == "On" || data.get(Session.SETTING_ALARM).equals("On")){
                    s_1.setSelection(0);
                }else if(data.get(Session.SETTING_ALARM).toString() == "Off" || data.get(Session.SETTING_ALARM).equals("Off")){
                    s_1.setSelection(1);
                }
            }

            if(data.get(Session.SETTING_REMIND) != null){
                //Set Reminder
                if(data.get(Session.SETTING_REMIND).toString() == "1 Hour" || data.get(Session.SETTING_REMIND).equals("1 Hour")){
                    s_1.setSelection(0);
                }else if(data.get(Session.SETTING_REMIND).toString() == "2 Hour" || data.get(Session.SETTING_REMIND).equals("2 Hour")){
                    s_1.setSelection(1);
                }else if(data.get(Session.SETTING_REMIND).toString() == "3 Hour" || data.get(Session.SETTING_REMIND).equals("3 Hour")){
                    s_1.setSelection(2);
                }else if(data.get(Session.SETTING_REMIND).toString() == "1 Day" || data.get(Session.SETTING_REMIND).equals("1 Day")){
                    s_1.setSelection(3);
                }else if(data.get(Session.SETTING_REMIND).toString() == "2 Days" || data.get(Session.SETTING_REMIND).equals("2 Days")){
                    s_1.setSelection(4);
                }else if(data.get(Session.SETTING_REMIND).toString() == "3 Days" || data.get(Session.SETTING_REMIND).equals("3 Days")){
                    s_1.setSelection(5);
                }else if(data.get(Session.SETTING_REMIND).toString() == "1 Week" || data.get(Session.SETTING_REMIND).equals("1 Week")){
                    s_1.setSelection(6);
                }
            }

            if(data.get(Session.SETTING_SOUND) != null){
                //Set Sound
                if(data.get(Session.SETTING_SOUND).toString() == "Any.DO Pop"  || data.get(Session.SETTING_SOUND).equals("Any.DO Pop")){
                    s_1.setSelection(0);
                }else if(data.get(Session.SETTING_SOUND).toString() == "Basic Bell"  || data.get(Session.SETTING_SOUND).equals("Basic Bell")){
                    s_1.setSelection(1);
                }else if(data.get(Session.SETTING_SOUND).toString() == "Chime"  || data.get(Session.SETTING_SOUND).equals("Chime")){
                    s_1.setSelection(2);
                }else if(data.get(Session.SETTING_SOUND).toString() == "Dawn Chorus"  || data.get(Session.SETTING_SOUND).equals("Dawn Chorus")){
                    s_1.setSelection(3);
                }else if(data.get(Session.SETTING_SOUND).toString() == "Ecliptic"  || data.get(Session.SETTING_SOUND).equals("Ecliptic")){
                    s_1.setSelection(4);
                }else if(data.get(Session.SETTING_SOUND).toString() == "Flying in The Sky"  || data.get(Session.SETTING_SOUND).equals("Flying in The Sky")){
                    s_1.setSelection(5);
                }else if(data.get(Session.SETTING_SOUND).toString() == "Glisando Tone"  || data.get(Session.SETTING_SOUND).equals("Glisando Tone")){
                    s_1.setSelection(6);
                }else if(data.get(Session.SETTING_SOUND).toString() == "Hangout Call" || data.get(Session.SETTING_SOUND).equals("Hangout Call")){
                    s_1.setSelection(7);
                }else if(data.get(Session.SETTING_SOUND).toString() == "Ice Blue Tone" || data.get(Session.SETTING_SOUND).equals("Ice Blue Tone")){
                    s_1.setSelection(8);
                }else if(data.get(Session.SETTING_SOUND).toString() == "Journey" || data.get(Session.SETTING_SOUND).equals("Journey")){
                    s_1.setSelection(9);
                }
            }

            RelativeLayout b = (RelativeLayout) findViewById(R.id.save_button);
            b.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    sm.setAlarmSetting(s_1.getSelectedItem().toString(), s_2.getSelectedItem().toString(), s_3.getSelectedItem().toString());
                    new AlertDialog.Builder(AlarmSetting.this)
                        .setTitle("Setting")
                        .setMessage("Save setting successfully !")
                        .setIcon(R.drawable.ic_logo_48dp)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                        .show();
                }
            });
        }else{
            new AlertDialog.Builder(AlarmSetting.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    new Logout(AlarmSetting.this).execute();
                    }
                })
                .show();
        }
    }

    public List<Data.Support> getSupportSchedule(){
        MainClass c = new MainClass("support_list.php?no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
        return c.requestSupport();
    }

}
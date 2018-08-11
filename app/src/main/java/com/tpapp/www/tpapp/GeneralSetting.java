package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class GeneralSetting extends AppCompatActivity{

    private Spinner s;
    private RelativeLayout rl;
    private TextView tv_1,tv_2;
    private Session sm;
    private HashMap<String,String>user, data;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_general_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_2);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        pd = new ProgressDialog(GeneralSetting.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        tv_2 = (TextView) findViewById(R.id.save_label);
        tv_2.setTextColor(getResources().getColor(R.color.white_background));

        sm = new Session(GeneralSetting.this);

        if(sm.getStatusLogin() == true){
            tv_1 = (TextView) findViewById(R.id.example);

            s = (Spinner) findViewById(R.id.follow_up_text_size_option);
            ArrayAdapter<CharSequence> follow_up_text_size_option = ArrayAdapter.createFromResource(this,R.array.follow_up_text_size_option,android.R.layout.simple_spinner_item);
            follow_up_text_size_option.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s.setAdapter(follow_up_text_size_option);

            data = sm.getGeneralSetting();
            user = sm.getLogin();

            if(data.get(Session.SETTING_SIZE) != null){
                if(data.get(Session.SETTING_SIZE).toString() == "Small" || data.get(Session.SETTING_SIZE).equals("Small")){
                    s.setSelection(0);
                }else if(data.get(Session.SETTING_SIZE).toString() == "Medium"  || data.get(Session.SETTING_SIZE).equals("Medium")){
                    s.setSelection(1);
                }else if(data.get(Session.SETTING_SIZE).toString() == "Large" || data.get(Session.SETTING_SIZE).equals("Large")){
                    s.setSelection(2);
                }
            }

            s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        tv_1.setTextSize(Float.parseFloat("15"));
                        tv_1.setText("Small");
                    } else if (position == 1) {
                        tv_1.setTextSize(Float.parseFloat("20"));
                        tv_1.setText("Medium");
                    } else {
                        tv_1.setTextSize(Float.parseFloat("30"));
                        tv_1.setText("Large");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent){}
            });

            rl = (RelativeLayout) findViewById(R.id.save_button);
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(GeneralSetting.this).setTitle("Setting")
                            .setMessage("Save setting successfully !")
                            .setIcon(R.drawable.ic_logo_48dp)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sm.setGeneralSetting(s.getSelectedItem().toString());
                            }
                        })
                        .show();
                }
            });
        }else {
            new AlertDialog.Builder(GeneralSetting.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(GeneralSetting.this).execute();
                    }
                })
                .show();
        }

    }

}
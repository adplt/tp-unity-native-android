package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.SettingAdapter;
import Data.SettingItem;

public class Setting extends AppCompatActivity{

    private ListView lv;
    private Session sm;
    private HashMap<String,String> user;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_18);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        pd = new ProgressDialog(Setting.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        sm = new Session(Setting.this);
        user = sm.getLogin();

        SettingItem si = new SettingItem();
        List<SettingItem> list_si = new ArrayList<>();

        si.setTittle("Account");
        si.setNote("Set up your name account, birthday, etc.");
        list_si.add(si);

        si = new SettingItem();
        si.setTittle("Alarm");
        si.setNote("Set up your alarm for your support reminder.");
        list_si.add(si);

        si = new SettingItem();
        si.setTittle("General");
        si.setNote("Set up your general setting like text size for follow up, etc.");
        list_si.add(si);

        lv = (ListView) findViewById(R.id.list_setting);
        SettingAdapter sa = new SettingAdapter(this,R.layout.interface_listview_setting,list_si);
        lv.setAdapter(sa);

        if(sm.getStatusLogin() == true){
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    if(position == 0){
                        MainClass c = new MainClass("tp_list.php?no_prm=" + user.get(Session.NO_PRM_LOGIN));
                        sm.setLogin(c.getTP());
                        startActivity(new Intent(Setting.this, AccountSetting.class));
                    }else if(position == 1){
                        startActivity(new Intent(Setting.this, AlarmSetting.class));
                    }else{
                        startActivity(new Intent(Setting.this, GeneralSetting.class));
                    }
                }
            });
        }else{
            new AlertDialog.Builder(Setting.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(Setting.this).execute();
                    }
                })
                .show();
        }
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(Setting.this,Home.class));
    }

}
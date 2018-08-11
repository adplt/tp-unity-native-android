package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class HistoryDetail extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Session sm;
    private ProgressDialog pd;
    private HashMap<String,String> data,user;
    private TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv_7,tv_8,tv_9,tv_10,tv_11,tv_12,tv_nav_menu;
    private NavigationView nv;
    private DrawerLayout dl;
    private com.mikhaellopez.circularimageview.CircularImageView civ;
    private ImageView iv_nav_menu;
    private View v;
    private static final String URL_PHOTO_PROFILE = "http://json.tpunity.com/picture/";
    private static final String URL_BACKGROUND_PROFILE = "http://json.tpunity.com/background/";
    private ProgressBar pb_1,pb_2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_13);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, com.tpapp.www.tpapp.R.string.navigation_drawer_open, com.tpapp.www.tpapp.R.string.navigation_drawer_close);
        dl.setDrawerListener(toggle);

        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        pd = new ProgressDialog(HistoryDetail.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        sm = new Session(HistoryDetail.this);

        if(sm.getStatusLogin() == true){
            data = sm.getCandidateStudent();
            user = sm.getLogin();

            v = nv.getHeaderView(0);
            getNavImage();

            tv_1 = (TextView) findViewById(R.id.name_icon);
            tv_2 = (TextView) findViewById(R.id.email_label);
            tv_3 = (TextView) findViewById(R.id.address_icon);
            tv_4 = (TextView) findViewById(R.id.phone_number_label);
            tv_5 = (TextView) findViewById(R.id.work_number_label);
            tv_6 = (TextView) findViewById(R.id.date_icon);
            tv_7 = (TextView) findViewById(R.id.alumni_label);
            tv_8 = (TextView) findViewById(R.id.date_label);
            tv_9 = (TextView) findViewById(R.id.major_label);
            tv_10 = (TextView) findViewById(R.id.tp_name_label);
            tv_11 = (TextView) findViewById(R.id.result_label);
            tv_12 = (TextView) findViewById(R.id.note_icon);

            tv_1.setText(data.get(Session.CS_NAME));
            tv_2.setText(data.get(Session.CS_EMAIL));
            tv_3.setText(data.get(Session.CS_ADDRESS));
            tv_4.setText(data.get(Session.CS_PHONE_NUMBER));
            tv_5.setText(data.get(Session.CS_WORK_NUMBER));
            tv_6.setText(data.get(Session.CS_COMPANY));
            tv_7.setText(data.get(Session.CS_ALUMNI));

            String date_format = "";

            try{
                SimpleDateFormat format = new SimpleDateFormat("EEE , dd-MMM-yyyy HH:mm:ss:SS a");
                String date = data.get(Session.CS_DATE_FOLLOW_UP);
                Date parse_date = format.parse(date);
                date_format = String.valueOf(parse_date);
                System.out.println("Date: "+date_format);
            }catch(ParseException e){}

            tv_8.setText(data.get(Session.CS_DATE_FOLLOW_UP));
            tv_9.setText(data.get(Session.CS_DEGREE));
            tv_10.setText(user.get(Session.TP_NAME_LOGIN));

            int result = Integer.parseInt(data.get(Session.CS_RESULT));
            String result_string = "";

            if(result == 0){
                result_string = "No, he/she doesn't interest";
            }else{
                result_string = "Yes, he/she interest";
            }

            tv_11.setText(result_string);
            tv_12.setText(data.get(Session.CS_NOTE));
        }else{
            new AlertDialog.Builder(HistoryDetail.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(HistoryDetail.this).execute();
                    }
                })
                .show();
        }
    }

    @Override
    public void onBackPressed(){
        if(dl.isDrawerOpen(GravityCompat.END)){
            dl.closeDrawer(GravityCompat.END);
        }else{
            super.onBackPressed();
        }
    }

    public void goToProfile(View v){
        dl.closeDrawer(GravityCompat.END);
        startActivity(new Intent(HistoryDetail.this, ProfileLogin.class));
    }

    public void getNavImage(){
        new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();
        ImageLoaderConfiguration ilc = new ImageLoaderConfiguration.Builder(HistoryDetail.this).build();
        ImageLoader.getInstance().init(ilc);

        pb_1 = (ProgressBar) v.findViewById(R.id.pg_1);
        pb_2 = (ProgressBar) v.findViewById(R.id.pg_2);

        tv_nav_menu = (TextView) v.findViewById(R.id.tp_login_name_label);
        iv_nav_menu = (ImageView) v.findViewById(R.id.profile_background);
        civ = (com.mikhaellopez.circularimageview.CircularImageView) v.findViewById(R.id.user_profile_picture);

        tv_nav_menu.setText(user.get(Session.TP_NAME_LOGIN));

        ImageLoader.getInstance().displayImage(URL_PHOTO_PROFILE + user.get(Session.PICTURE_LOGIN), civ, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                pb_1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                pb_1.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                pb_1.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                pb_1.setVisibility(View.GONE);
            }
        });

        ImageLoader.getInstance().displayImage(URL_BACKGROUND_PROFILE + user.get(Session.BACKGROUND_LOGIN), iv_nav_menu, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                pb_2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                pb_2.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                pb_2.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                pb_2.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.icon_nav_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu){
            dl.openDrawer(GravityCompat.END);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        dl.closeDrawer(GravityCompat.END);

        if(id == R.id.follow_up_nav_menu){
            new RequestPermission(HistoryDetail.this).execute("no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
        }else if(id == R.id.input_data_nav_menu){
            startActivity(new Intent(HistoryDetail.this, InputData.class));
        }else if(id == R.id.view_absence_nav_menu){
            startActivity(new Intent(HistoryDetail.this, Absence.class));
        }else if(id == R.id.check_schedule_nav_menu){
            startActivity(new Intent(HistoryDetail.this, Schedule.class));
        }else if(id == R.id.product_nav_menu){
            startActivity(new Intent(HistoryDetail.this, Product.class));
        }else if(id == R.id.setting_nav_menu){
            startActivity(new Intent(HistoryDetail.this, Setting.class));
        }else if(id == R.id.about_nav_menu){
            new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage("TP App Version 1.0.0")
                .setIcon(R.drawable.ic_logo_48dp)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).show();
        }else if(id == R.id.logout_nav_menu){
            new Logout(HistoryDetail.this).execute();
        }

        return true;
    }

}
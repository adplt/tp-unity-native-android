package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.HashMap;
import java.util.List;

public class EventDetail extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Session sm;
    private ProgressDialog pd;
    private HashMap<String,String> user,data;
    private NavigationView nv;
    private DrawerLayout dl;
    private TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv_7,tv_8,tv_nav_menu;
    private com.mikhaellopez.circularimageview.CircularImageView civ;
    private ImageView iv_nav_menu;
    private ProgressBar pb_1,pb_2,pb_3,pb_4,pb_5;
    private View v;
    private LinearLayout ly;
    private RelativeLayout rl_1,rl_2;
    private static final String URL_PHOTO_PROFILE = "http://json.tpunity.com/picture/";
    private static final String URL_BACKGROUND_PROFILE = "http://json.tpunity.com/background/";
    private AsyncTask<String,Void,String> accept;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_20);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, com.tpapp.www.tpapp.R.string.navigation_drawer_open, com.tpapp.www.tpapp.R.string.navigation_drawer_close);
        dl.setDrawerListener(toggle);

        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        v = nv.getHeaderView(0);

        pd = new ProgressDialog(EventDetail.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        sm = new Session(EventDetail.this);

        data = sm.getEvent();
        user = sm.getLogin();

        ly = (LinearLayout) findViewById(R.id.unnamed_186);
        tv_6 = (TextView) findViewById(R.id.error_message_1);
        tv_6.setVisibility(View.GONE);

        pb_4 = (ProgressBar) findViewById(R.id.pg_1);
        pb_5 = (ProgressBar) findViewById(R.id.pg_2);

        tv_7 = (TextView) findViewById(R.id.accept_label);
        tv_7.setTextColor(getResources().getColor(R.color.white_background));

        tv_8 = (TextView) findViewById(R.id.decline_label);
        tv_8.setTextColor(getResources().getColor(R.color.white_background));

        rl_1 = (RelativeLayout) findViewById(R.id.accept_button);
        rl_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept = new AsyncTask<String, Void, String>(){

                    private String message;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        pd.show();
                    }

                    @Override
                    protected String doInBackground(String... params){
                        MainClass c = new MainClass("confirm_join.php?id_event=" + data.get(Session.E_ID));
                        Log.e("Profile", "confirm_join.php?id_event=" + data.get(Session.E_ID));
                        List<Data.TeamPromotion> list_tp = c.requestConfirmEvent();
                        new MainClass("join_event.php?" + params[0]).executeURL();

                        if(list_tp.size()<Integer.parseInt(data.get(Session.E_TP))){
                            message="You have join this event";
                        }else{
                            message="The event has been full quota. You'll be candidate.";
                        }

                        return message;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);

                        new AlertDialog.Builder(EventDetail.this)
                            .setTitle("Join Event")
                            .setMessage(s)
                            .setIcon(R.drawable.ic_logo_48dp)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which){

                                }
                            })
                            .show();

                        if(pd.isShowing()){
                            pd.dismiss();
                        }
                    }
                };

                accept.execute("no_prm=" + user.get(Session.NO_PRM_LOGIN) + "&id_event=" + data.get(Session.E_ID) + "&start_date=" + data.get(Session.E_START) + "&end_date=" + data.get(Session.E_END) + "&note=Support%20Event");

            }
        });

        rl_2 = (RelativeLayout) findViewById(R.id.decline_button);
        rl_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new AlertDialog.Builder(EventDetail.this)
                    .setTitle("Join Event")
                    .setMessage("You have been decline this event.")
                    .setIcon(R.drawable.ic_logo_48dp)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            onBackPressed();
                        }
                    })
                    .show();
            }
        });

        if(sm.getStatusLogin() == true){
            getNavImage();

            tv_1 = (TextView) findViewById(R.id.name_label);
            tv_2 = (TextView) findViewById(R.id.address_label);
            tv_3 = (TextView) findViewById(R.id.date_label);
            tv_4 = (TextView) findViewById(R.id.tp_label);
            tv_5 = (TextView) findViewById(R.id.note_label);

            tv_1.setText(data.get(Session.E_NAME));
            tv_2.setText(data.get(Session.E_ADDRESS));
            tv_3.setText(data.get(Session.E_START) + " - " + data.get(Session.E_END));
            tv_4.setText(data.get(Session.E_TP));
            tv_5.setText(data.get(Session.E_NOTE));

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getView();
                }
            }, 5000);
        }else{
            new AlertDialog.Builder(EventDetail.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(EventDetail.this).execute();
                    }
                })
                .show();
        }
    }

    public void goToProfile(View v){
        dl.closeDrawer(GravityCompat.END);
        startActivity(new Intent(EventDetail.this, ProfileLogin.class));
    }

    public void getNavImage(){
        new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();
        ImageLoaderConfiguration ilc = new ImageLoaderConfiguration.Builder(EventDetail.this).build();
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

    public void getView(){
        MainClass c = new MainClass("confirm_join.php?id_event=" + data.get(Session.E_ID));
        Log.e("Profile","confirm_join.php?id_event=" + data.get(Session.E_ID));
        final List<Data.TeamPromotion> list_tp = c.requestConfirmEvent();
        View v_lv = null;
        ViewHolder vh;

        Log.e("TP Join", String.valueOf(list_tp.size()));

        if(list_tp.size() == 0){
            pb_5.setVisibility(View.GONE);
            tv_6.setVisibility(View.VISIBLE);
        }else{
            for(int i=0;i<list_tp.size();i++){
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                vh = new ViewHolder();

                if(v_lv==null){
                    v_lv = inflater.inflate(R.layout.interface_listview_tp_event, null);

                    vh.tv_view_1 = (TextView) v_lv.findViewById(R.id.tp_name);
                    vh.tv_view_2 = (TextView) v_lv.findViewById(R.id.tp_score);

                    vh.civ_comment = (CircularImageView) v_lv.findViewById(R.id.picture_tp);
                    pb_3 = (ProgressBar) v_lv.findViewById(R.id.pg_2);

                    vh.tv_view_1.setText(list_tp.get(i).getName());
                    vh.tv_view_2.setText(String.valueOf(list_tp.get(i).getScore()));

                    Log.e("Picture",URL_PHOTO_PROFILE + list_tp.get(i).getURLImage());

                    ImageLoader.getInstance().displayImage(URL_PHOTO_PROFILE + list_tp.get(i).getURLImage(), vh.civ_comment, new ImageLoadingListener(){

                        @Override
                        public void onLoadingStarted(String imageUri, View view){
                            pb_3.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason){
                            pb_3.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage){
                            pb_3.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view){
                            pb_3.setVisibility(View.GONE);
                        }

                    });

                    ly.addView(v_lv);
                    ly.setId(i);

                    ly.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            Log.e("TP Who Join",list_tp.get(ly.getId()).getID());
                            MainClass c = new MainClass("tp_list.php?no_prm=" + list_tp.get(ly.getId()).getID());
                            Log.e("TP List","tp_list.php?no_prm=" + list_tp.get(ly.getId()).getID());


                            sm.setTPList(c.getTP());

                            if(list_tp.get(ly.getId()).getID() == user.get(Session.NO_PRM_LOGIN) || list_tp.get(ly.getId()).getID().equals(user.get(Session.NO_PRM_LOGIN))){
                                startActivity(new Intent(EventDetail.this, ProfileLogin.class));
                            }else{
                                startActivity(new Intent(EventDetail.this, Profile.class));
                            }
                        }
                    });

                    ly.setOnLongClickListener(new View.OnLongClickListener(){
                        @Override
                        public boolean onLongClick(View v){
                            MainClass c = new MainClass("tp_list.php?no_prm=" + list_tp.get(ly.getId()).getID());
                            sm.setTPList(c.getTP());
                            return true;
                        }
                    });

                    v_lv = null;
                }
            }

            int quota = Integer.parseInt(data.get(Session.E_TP)) - list_tp.size();

            if(list_tp.size()<Integer.parseInt(data.get(Session.E_TP))){
                tv_6.setVisibility(View.VISIBLE);
                tv_6.setText("There's still " + quota + " more quota. Come on ! Let's join !");
            }else{
                tv_6.setVisibility(View.VISIBLE);
                tv_6.setText("The quota is full. You'll be a candidate if you join.");
            }

            pb_5.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.icon_nav_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case com.tpapp.www.tpapp.R.id.menu:
                dl.openDrawer(GravityCompat.END);
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed(){
        if(dl.isDrawerOpen(GravityCompat.END)){
            dl.closeDrawer(GravityCompat.END);
        }else{
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        dl.closeDrawer(GravityCompat.END);

        if(id == R.id.follow_up_nav_menu){
            new RequestPermission(EventDetail.this).execute("no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
        }else if(id == R.id.input_data_nav_menu){
            startActivity(new Intent(EventDetail.this,InputData.class));
        }else if(id == R.id.view_absence_nav_menu){
            startActivity(new Intent(EventDetail.this,Absence.class));
        }else if(id == R.id.check_schedule_nav_menu){
            startActivity(new Intent(EventDetail.this,Schedule.class));
        }else if(id == R.id.setting_nav_menu){
            startActivity(new Intent(EventDetail.this, Setting.class));
        }else if(id == R.id.product_nav_menu){
            startActivity(new Intent(EventDetail.this, Product.class));
        }else if(id == com.tpapp.www.tpapp.R.id.about_nav_menu){
            new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage("TP App Version 1.0.0")
                .setIcon(R.drawable.ic_logo_48dp)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).show();
        }else if(id == R.id.logout_nav_menu){
            new Logout(EventDetail.this).execute();
        }

        return true;
    }

    class ViewHolder{
        private CircularImageView civ_comment;
        private TextView tv_view_1,tv_view_2;
    }

}
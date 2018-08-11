package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Session sm;
    private ProgressDialog pd;
    private HashMap<String,String> data,user,ratting;
    private RelativeLayout rl;
    private RatingBar rb_1,rb_2,rb_3;
    private TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv_7,tv_8,tv_nav_menu;
    private NavigationView nv;
    private DrawerLayout dl;
    private Drawable d_1,d_2,d_3;
    private EditText et;
    private com.mikhaellopez.circularimageview.CircularImageView civ_nav_menu,civ;
    private ImageView iv_nav_menu,iv;
    private View v;
    private LinearLayout ly;
    private static final String URL_PHOTO_PROFILE = "http://json.tpunity.com/picture/";
    private static final String URL_BACKGROUND_PROFILE = "http://json.tpunity.com/background/";
    private ProgressBar pb_1,pb_2,pb_3,pb_4,pb_5,pb_6;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_15);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, com.tpapp.www.tpapp.R.string.navigation_drawer_open, com.tpapp.www.tpapp.R.string.navigation_drawer_close);
        dl.setDrawerListener(toggle);

        if(dl.isDrawerOpen(GravityCompat.END)){
            dl.closeDrawer(GravityCompat.END);
        }

        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        v = nv.getHeaderView(0);

        pd = new ProgressDialog(Profile.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        sm = new Session(Profile.this);

        data = sm.getTPList();
        user = sm.getLogin();

        et = (EditText) findViewById(R.id.comment_from_others);
        ly = (LinearLayout) findViewById(R.id.unnamed_180);

        rl = (RelativeLayout) findViewById(R.id.rating_button);
        tv_8 = (TextView) findViewById(R.id.error_message_3);
        tv_8.setVisibility(View.GONE);

        pb_3 = (ProgressBar) findViewById(R.id.pg_1);
        pb_4 = (ProgressBar) findViewById(R.id.pg_2);
        pb_6 = (ProgressBar) findViewById(R.id.pg_3);

        rb_1 = (RatingBar) findViewById(R.id.value_1);
        rb_2 = (RatingBar) findViewById(R.id.value_2);
        rb_3 = (RatingBar) findViewById(R.id.value_3);

        d_1 = rb_1.getProgressDrawable();
        d_2 = rb_2.getProgressDrawable();
        d_3 = rb_3.getProgressDrawable();

        d_1.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        d_2.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        d_3.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);

        tv_1 = (TextView) findViewById(R.id.name_label);
        tv_2 = (TextView) findViewById(R.id.score_label);
        tv_3 = (TextView) findViewById(R.id.no_prm_label);
        tv_4 = (TextView) findViewById(R.id.gender_label);
        tv_5 = (TextView) findViewById(R.id.submit_rating);
        tv_6 = (TextView) findViewById(R.id.note_label);
        tv_7 = (TextView) findViewById(R.id.comment_profile_label);

        tv_1.setText(data.get(Session.TP_NAME));
        tv_2.setText(data.get(Session.SCORE));
        tv_3.setText(data.get(Session.NO_PRM));
        tv_4.setText(data.get(Session.GENDER));

        tv_5.setTextColor(getResources().getColor(R.color.white_background));

        if(validateRatting() == false){
            tv_7.setVisibility(View.GONE);
            ly.setVisibility(View.GONE);
        }else{
            ratting = sm.getRatting();

            rb_1.setEnabled(false);
            rb_1.setRating(Float.parseFloat(ratting.get(Session.RATTING_TIME_MANAGEMENT)));

            rb_2.setEnabled(false);
            rb_2.setRating(Float.parseFloat(ratting.get(Session.RATTING_INITIATIVE)));

            rb_3.setEnabled(false);
            rb_3.setRating(Float.parseFloat(ratting.get(Session.RATTING_RESPONSIBLE)));

            tv_6.setVisibility(View.GONE);
            et.setVisibility(View.GONE);
            rl.setVisibility(View.GONE);

            tv_7.setVisibility(View.VISIBLE);
            ly.setVisibility(View.VISIBLE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getView();
                }
            }, 5000);
        }

        rl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(et.getText().toString() == "" || et.getText().toString().equals("")){
                    Snackbar.make(v, "Note must be filled !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else{
                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                    String line = "no_prm_login=" + user.get(Session.NO_PRM_LOGIN) + "&no_prm=" + data.get(Session.NO_PRM)
                            + "&time_management=" + rb_1.getRating() + "&inititative=" + rb_2.getRating() + "&responsible=" + rb_3.getRating()
                            + "&date_created=" + date + "&note=" + et.getText();
                    line =  line.replaceAll(" ", "%20");
                    Log.e("Insert Ratting",line);
                    new AsyncTask<String,Void,String>(){
                        @Override
                        protected void onPreExecute(){
                            super.onPreExecute();
                            pd.show();
                        }

                        @Override
                        protected String doInBackground(String... params){
                            MainClass c = new MainClass("insert_ratting.php?" + params[0]);
                            c.executeURL();
                            return "Ratting successfully !";
                        }

                        @Override
                        protected void onPostExecute(String s){
                            super.onPostExecute(s);

                            new AlertDialog.Builder(Profile.this)
                                    .setTitle("Ratting")
                                    .setMessage(s)
                                    .setIcon(R.drawable.ic_logo_48dp)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            rb_1.setEnabled(false);
                                            rb_2.setEnabled(false);
                                            rb_3.setEnabled(false);

                                            tv_6.setVisibility(View.GONE);
                                            et.setVisibility(View.GONE);
                                            rl.setVisibility(View.GONE);

                                            tv_7.setVisibility(View.VISIBLE);
                                            ly.setVisibility(View.VISIBLE);

                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    getView();
                                                    pb_6.setVisibility(View.GONE);
                                                }
                                            }, 5000);
                                        }
                                    })
                                    .show();

                            if(pd.isShowing()){
                                pd.dismiss();
                            }
                        }
                    }.execute(line);
                }
            }
        });

        if(sm.getStatusLogin() == true){
            getNavImage();

            civ = (com.mikhaellopez.circularimageview.CircularImageView) findViewById(R.id.picture);
            iv = (ImageView) findViewById(R.id.background);

            ImageLoader.getInstance().displayImage(URL_PHOTO_PROFILE + data.get(Session.PICTURE), civ, new ImageLoadingListener() {

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

            ImageLoader.getInstance().displayImage(URL_BACKGROUND_PROFILE + data.get(Session.BACKGROUND), iv, new ImageLoadingListener() {

                @Override
                public void onLoadingStarted(String imageUri, View view){
                    pb_4.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason){
                    pb_4.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage){
                    pb_4.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view){
                    pb_4.setVisibility(View.GONE);
                }

            });
        }else{
            new AlertDialog.Builder(Profile.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(Profile.this).execute();
                    }
                })
                .show();
        }
    }

    public void goToProfile(View v){
        dl.closeDrawer(GravityCompat.END);
        startActivity(new Intent(Profile.this, ProfileLogin.class));
    }

    public boolean validateRatting(){
        String line = "no_prm_login=" + user.get(Session.NO_PRM_LOGIN) + "&no_prm=" + data.get(Session.NO_PRM);
        MainClass c = new MainClass("check_ratting.php?" + line,Profile.this);
        return c.validateRatting();
    }

    public void getNavImage(){
        new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();
        ImageLoaderConfiguration ilc = new ImageLoaderConfiguration.Builder(Profile.this).build();
        ImageLoader.getInstance().init(ilc);

        pb_1 = (ProgressBar) v.findViewById(R.id.pg_1);
        pb_2 = (ProgressBar) v.findViewById(R.id.pg_2);

        tv_nav_menu = (TextView) v.findViewById(R.id.tp_login_name_label);
        iv_nav_menu = (ImageView) v.findViewById(R.id.profile_background);
        civ_nav_menu = (com.mikhaellopez.circularimageview.CircularImageView) v.findViewById(R.id.user_profile_picture);

        tv_nav_menu.setText(user.get(Session.TP_NAME_LOGIN));

        ImageLoader.getInstance().displayImage(URL_PHOTO_PROFILE + user.get(Session.PICTURE_LOGIN), civ_nav_menu, new ImageLoadingListener() {

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
        MainClass c = new MainClass("comment_list.php?no_prm_login=" + data.get(Session.NO_PRM));
        Log.e("Profile","comment_list.php?no_prm_login=" + data.get(Session.NO_PRM));
        final List<Data.Comment> list_c = c.requestComment();
        View v_lv = null;
        ViewHolder vh;

        Log.e("Profile", String.valueOf(list_c.size()));

        if(list_c.size() == 0){
            pb_6.setVisibility(View.GONE);
            tv_8.setVisibility(View.VISIBLE);
        }else{
            for(int i=0;i<list_c.size();i++){
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                vh = new ViewHolder();

                if(v_lv==null){
                    v_lv = inflater.inflate(R.layout.interface_listview_comment, null);

                    vh.tv_view_1 = (TextView) v_lv.findViewById(R.id.tp_name);
                    vh.tv_view_2 = (TextView) v_lv.findViewById(R.id.comment);
                    vh.tv_view_3 = (TextView) v_lv.findViewById(R.id.score);
                    vh.tv_view_4 = (TextView) v_lv.findViewById(R.id.date);

                    vh.civ_comment = (CircularImageView) v_lv.findViewById(R.id.profile_picture);
                    pb_5 = (ProgressBar) v_lv.findViewById(R.id.pg);

                    vh.tv_view_1.setText(list_c.get(i).getFrom());
                    vh.tv_view_2.setText(list_c.get(i).getComment());
                    vh.tv_view_3.setText(String.valueOf(list_c.get(i).getScore()));
                    vh.tv_view_4.setText(list_c.get(i).getDateCreated());

                    ImageLoader.getInstance().displayImage(URL_PHOTO_PROFILE + list_c.get(i).getURLImage(), vh.civ_comment, new ImageLoadingListener() {

                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                            pb_5.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                            pb_5.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            pb_5.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {
                            pb_5.setVisibility(View.GONE);
                        }

                    });

                    ly.addView(v_lv);
                    ly.setId(i);

                    ly.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.e("Profile", list_c.get(ly.getId()).getNoPRM());
                            MainClass c = new MainClass("tp_list.php?no_prm=" + list_c.get(ly.getId()).getNoPRM());
                            sm.setTPList(c.getTP());

                            if(list_c.get(ly.getId()).getNoPRM() == user.get(Session.NO_PRM_LOGIN) || list_c.get(ly.getId()).getNoPRM().equals(user.get(Session.NO_PRM_LOGIN))) {
                                startActivity(new Intent(Profile.this, ProfileLogin.class));
                            }else{
                                startActivity(new Intent(Profile.this, Profile.class));
                            }
                        }
                    });

                    ly.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            MainClass c = new MainClass("tp_list.php?no_prm=" + list_c.get(ly.getId()).getNoPRM());
                            sm.setTPList(c.getTP());
                            return true;
                        }
                    });

                    v_lv = null;
                }
            }

            pb_6.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed(){
        if(dl.isDrawerOpen(GravityCompat.END)){
            dl.closeDrawer(GravityCompat.END);
        }else{
            startActivity(new Intent(Profile.this, Home.class));
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
            case R.id.menu:
                dl.openDrawer(GravityCompat.END);
                break;
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        dl.closeDrawer(GravityCompat.END);

        if(id == R.id.follow_up_nav_menu){
            new RequestPermission(Profile.this).execute("no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
        }else if(id == R.id.input_data_nav_menu){
            startActivity(new Intent(Profile.this, InputData.class));
        }else if(id == R.id.view_absence_nav_menu){
            startActivity(new Intent(Profile.this, Absence.class));
        }else if(id == R.id.check_schedule_nav_menu){
            startActivity(new Intent(Profile.this, Schedule.class));
        }else if(id == R.id.product_nav_menu){
            startActivity(new Intent(Profile.this, Product.class));
        }else if(id == R.id.setting_nav_menu){
            startActivity(new Intent(Profile.this, Setting.class));
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
            new Logout(Profile.this).execute();
        }

        return true;
    }

    class ViewHolder{
        private CircularImageView civ_comment;
        private TextView tv_view_1,tv_view_2,tv_view_3,tv_view_4;
    }

}
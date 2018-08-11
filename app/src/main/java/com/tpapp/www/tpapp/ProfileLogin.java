package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
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
import android.widget.RatingBar;
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
import Data.Ratting;

public class ProfileLogin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Session sm;
    private ProgressDialog pd;
    private HashMap<String,String> user;
    private RelativeLayout rl;
    private RatingBar rb_1,rb_2,rb_3;
    private TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv_nav_menu;
    private NavigationView nv;
    private DrawerLayout dl;
    private Drawable d_1,d_2,d_3;
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
        setContentView(R.layout.activity_profile_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_16);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.setDrawerListener(toggle);

        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        v = nv.getHeaderView(0);

        pd = new ProgressDialog(ProfileLogin.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        pb_3 = (ProgressBar) findViewById(R.id.pg_1);
        pb_4 = (ProgressBar) findViewById(R.id.pg_2);
        pb_6 = (ProgressBar) findViewById(R.id.pg_3);

        tv_5 = (TextView) findViewById(R.id.edit_profile_label);
        tv_5.setTextColor(getResources().getColor(R.color.white_background));

        tv_6 = (TextView) findViewById(R.id.error_message_2);
        tv_6.setVisibility(View.GONE);

        ly = (LinearLayout) findViewById(R.id.unnamed_181);

        sm = new Session(ProfileLogin.this);
        user = sm.getLogin();

        rl = (RelativeLayout) findViewById(R.id.rating_button);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileLogin.this, EditProfile.class));
            }
        });

        rb_1 = (RatingBar) findViewById(R.id.value_4);
        rb_2 = (RatingBar) findViewById(R.id.value_5);
        rb_3 = (RatingBar) findViewById(R.id.value_6);

        rb_1.setEnabled(false);
        rb_2.setEnabled(false);
        rb_3.setEnabled(false);

        d_1 = rb_1.getProgressDrawable();
        d_2 = rb_2.getProgressDrawable();
        d_3 = rb_3.getProgressDrawable();

        d_1.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
        d_2.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
        d_3.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);

        if(sm.getStatusLogin() == true){
            getNavImage();

            civ = (com.mikhaellopez.circularimageview.CircularImageView) findViewById(R.id.picture);
            iv = (ImageView) findViewById(R.id.background_profile_login);

            ImageLoader.getInstance().displayImage(URL_PHOTO_PROFILE + user.get(Session.PICTURE_LOGIN), civ, new ImageLoadingListener() {

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

            ImageLoader.getInstance().displayImage(URL_BACKGROUND_PROFILE + user.get(Session.BACKGROUND_LOGIN), iv, new ImageLoadingListener() {

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

            tv_1 = (TextView) findViewById(R.id.name_icon);
            tv_2 = (TextView) findViewById(R.id.score_label);
            tv_3 = (TextView) findViewById(R.id.no_prm_label);
            tv_4 = (TextView) findViewById(R.id.gender_label);

            tv_1.setText(user.get(Session.TP_NAME_LOGIN));
            tv_2.setText(user.get(Session.SCORE_LOGIN));
            tv_3.setText(user.get(Session.NO_PRM_LOGIN));
            tv_4.setText(user.get(Session.GENDER_LOGIN));

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try{
                        getView();
                    }catch(Exception e){

                    }
                }
            }, 5000);
        }else{
            new AlertDialog.Builder(ProfileLogin.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(ProfileLogin.this).execute();
                    }
                })
                .show();
        }
    }

    public void goToProfile(View v){
        dl.closeDrawer(GravityCompat.END);
    }

    public void getNavImage(){
        new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();
        ImageLoaderConfiguration ilc = new ImageLoaderConfiguration.Builder(ProfileLogin.this).build();
        ImageLoader.getInstance().init(ilc);

        pb_1 = (ProgressBar) v.findViewById(R.id.pg_1);
        pb_2 = (ProgressBar) v.findViewById(R.id.pg_2);

        tv_nav_menu = (TextView) v.findViewById(R.id.tp_login_name_label);
        iv_nav_menu = (ImageView) v.findViewById(R.id.profile_background);
        civ_nav_menu = (com.mikhaellopez.circularimageview.CircularImageView) v.findViewById(R.id.user_profile_picture);

        tv_nav_menu.setText(user.get(Session.TP_NAME_LOGIN));

        ImageLoader.getInstance().displayImage(URL_PHOTO_PROFILE + user.get(Session.PICTURE_LOGIN), civ_nav_menu, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view){
                pb_1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason){
                pb_1.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage){
                pb_1.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view){
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

    public Ratting getRatting(){
        MainClass d = new MainClass("user_ratting.php?no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
        return d.requestUserRatting();
    }

    public void getView(){
        Ratting r = getRatting();
        if(r != null){
            rb_1.setRating(r.getTimeManagement());
            rb_2.setRating(r.getInitiative());
            rb_3.setRating(r.getResponsible());
        }
        
        MainClass c = new MainClass("comment_list.php?no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
        Log.e("Profile Login","comment_list.php?no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
        final List<Data.Comment> list_c = c.requestComment();
        View v_lv = null;
        ViewHolder vh;

        Log.e("Profile Login", String.valueOf(list_c.size()));

        if(list_c.size() == 0){
            pb_6.setVisibility(View.GONE);
            tv_6.setVisibility(View.VISIBLE);
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
                        public void onLoadingStarted(String imageUri, View view){
                            pb_5.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason){
                            pb_5.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage){
                            pb_5.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view){
                            pb_5.setVisibility(View.GONE);
                        }

                    });

                    ly.addView(v_lv);
                    ly.setId(i);

                    ly.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            MainClass c = new MainClass("tp_list.php?no_prm=" + list_c.get(ly.getId()).getNoPRM());
                            sm.setTPList(c.getTP());

                            if(list_c.get(ly.getId()).getNoPRM() == user.get(Session.NO_PRM_LOGIN) || list_c.get(ly.getId()).getNoPRM().equals(user.get(Session.NO_PRM_LOGIN))){
                                startActivity(new Intent(ProfileLogin.this, ProfileLogin.class));
                            }else{
                                startActivity(new Intent(ProfileLogin.this, Profile.class));
                            }
                        }
                    });

                    ly.setOnLongClickListener(new View.OnLongClickListener(){
                        @Override
                        public boolean onLongClick(View v){
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
            startActivity(new Intent(ProfileLogin.this, Home.class));
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
            new RequestPermission(ProfileLogin.this).execute("no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
        }else if(id == R.id.input_data_nav_menu){
            startActivity(new Intent(ProfileLogin.this, InputData.class));
        }else if(id == R.id.view_absence_nav_menu){
            startActivity(new Intent(ProfileLogin.this, Absence.class));
        }else if(id == R.id.check_schedule_nav_menu){
            startActivity(new Intent(ProfileLogin.this, Schedule.class));
        }else if(id == R.id.product_nav_menu){
            startActivity(new Intent(ProfileLogin.this, Product.class));
        }else if(id == R.id.setting_nav_menu){
            startActivity(new Intent(ProfileLogin.this, Setting.class));
        }else if(id == R.id.about_nav_menu){
            new AlertDialog.Builder(this)
                    .setTitle("About")
                    .setMessage("TP App Version 1.0.0")
                    .setIcon(R.drawable.ic_logo_48dp)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){}
                    }).show();
        }else if(id == R.id.logout_nav_menu){
            new Logout(ProfileLogin.this).execute();
        }

        return true;
    }

    class ViewHolder{
        private CircularImageView civ_comment;
        private TextView tv_view_1,tv_view_2,tv_view_3,tv_view_4;
    }

}
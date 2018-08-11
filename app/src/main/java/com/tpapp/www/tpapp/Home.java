package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import java.util.HashMap;
import java.util.List;
import Adapter.TPListAdapter;
import Data.TeamPromotion;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView lv;
    private SwipyRefreshLayout srl;
    private ProgressDialog pd;
    private Session sm;
    private NavigationView nv;
    private DrawerLayout dl;
    private HashMap<String,String> user;
    private View v;
    private TextView tv_nav_menu;
    private com.mikhaellopez.circularimageview.CircularImageView civ;
    private ImageView iv_nav_menu;
    private static final String URL_PHOTO_PROFILE = "http://json.tpunity.com/picture/";
    private static final String URL_BACKGROUND_PROFILE = "http://json.tpunity.com/background/";
    private ProgressBar pb_1,pb_2;
    private BroadcastReceiver br;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_1);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //doNotification(0);
                startActivity(new Intent(Home.this,ListPeople.class));
            }
        });
        */

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, com.tpapp.www.tpapp.R.string.navigation_drawer_close);
        dl.setDrawerListener(toggle);

        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        v = nv.getHeaderView(0);

        srl = (SwipyRefreshLayout) findViewById(R.id.refresh_1);
        lv = (ListView) findViewById(R.id.rangking_list);

        pd = new ProgressDialog(Home.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        sm = new Session(Home.this);
        user = sm.getLogin();

        srl = (SwipyRefreshLayout) findViewById(R.id.refresh_1);
        srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srl.setRefreshing(true);

                        try{
                            getView();
                        }catch(Exception e){

                        }
                        srl.setRefreshing(false);
                    }
                }, 5000);
            }
        });

        if(sm.getStatusLogin() == true){
            getNavImage();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    srl.setRefreshing(true);
                    try{
                        getView();
                    }catch(Exception e){

                    }
                    srl.setRefreshing(false);
                }
            }, 5000);

            br = new BroadcastReceiver(){
                @Override
                public void onReceive(Context context, Intent intent){
                    if(intent.getAction().endsWith(GCMIntentService.REGISTRATION_SUCCESS)){
                        Toast.makeText(getBaseContext(), "Token: " + intent.getStringExtra("Token"), Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getBaseContext(),"Reigstration token error.",Toast.LENGTH_LONG).show();
                    }
                }
            };

            if(checkPlayServices() == true){
                boolean flag = checkPlayServices();
                Log.e("Check Play Service", String.valueOf(flag));
            }
        }else{
            new AlertDialog.Builder(Home.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(Home.this).execute();
                    }
                })
                .show();
        }
    }

    private boolean checkPlayServices(){
        GoogleApiAvailability gaa = GoogleApiAvailability.getInstance();
        int resultCode = gaa.isGooglePlayServicesAvailable(this);

        if(resultCode == ConnectionResult.SUCCESS){
            return true;
        }else{
            if(gaa.isUserResolvableError(resultCode)){
                gaa.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }else{
                Toast.makeText(this, "This device is not supported.", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
    }

    @Override
    public void onBackPressed(){
        if(dl.isDrawerOpen(GravityCompat.END)){
            dl.closeDrawer(GravityCompat.END);
        }else{
            moveTaskToBack(true);
        }
    }

    public void getNavImage(){
        new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();
        ImageLoaderConfiguration ilc = new ImageLoaderConfiguration.Builder(Home.this).build();
        ImageLoader.getInstance().init(ilc);

        pb_1 = (ProgressBar) v.findViewById(R.id.pg_1);
        pb_2 = (ProgressBar) v.findViewById(R.id.pg_2);

        tv_nav_menu = (TextView) v.findViewById(R.id.tp_login_name_label);
        iv_nav_menu = (ImageView) v.findViewById(R.id.profile_background);
        civ = (com.mikhaellopez.circularimageview.CircularImageView) v.findViewById(R.id.user_profile_picture);

        tv_nav_menu.setText(user.get(Session.TP_NAME_LOGIN));

        ImageLoader.getInstance().displayImage(URL_PHOTO_PROFILE + user.get(Session.PICTURE_LOGIN), civ, new ImageLoadingListener() {

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
            public void onLoadingStarted(String imageUri, View view){
                pb_2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason){
                pb_2.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage){
                pb_2.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view){
                pb_2.setVisibility(View.GONE);
            }

        });

    }

    public void getView(){
        MainClass jd = new MainClass("tp_list.php");
        List<TeamPromotion> list_a = jd.requestTP();

        if(list_a != null){
            TPListAdapter tpla = new TPListAdapter(Home.this, 0, list_a,user.get(Session.NO_PRM_LOGIN));
            lv.setAdapter(tpla);

            lv.setLongClickable(false);
            lv.setDivider(null);
            lv.setDividerHeight(0);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TeamPromotion tp = (TeamPromotion) lv.getAdapter().getItem(position);

                    if(tp.getID() == user.get(Session.NO_PRM_LOGIN) || tp.getID().equals(user.get(Session.NO_PRM_LOGIN))){
                        startActivity(new Intent(Home.this, ProfileLogin.class));
                    }else{
                        sm.setTPList(tp);
                        startActivity(new Intent(Home.this, Profile.class));
                    }
                }
            });

            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    return false;
                }
            });

            lv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });
        }
    }

    public void goToProfile(View v){
        dl.closeDrawer(GravityCompat.END);
        startActivity(new Intent(Home.this, ProfileLogin.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.icon_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.menu:
                dl.openDrawer(GravityCompat.END);
                break;
            case R.id.history:
                startActivity(new Intent(Home.this,History.class));
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
            new RequestPermission(Home.this).execute("no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
            Log.e("RequestPermission", "no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
        }else if(id == R.id.input_data_nav_menu){
            startActivity(new Intent(Home.this, InputData.class));
        }else if(id == R.id.view_absence_nav_menu){
            startActivity(new Intent(Home.this, Absence.class));
        }else if(id == R.id.check_schedule_nav_menu){
            startActivity(new Intent(Home.this, Schedule.class));
        }else if(id == R.id.product_nav_menu){
            startActivity(new Intent(Home.this, Product.class));
        }else if(id == R.id.setting_nav_menu){
            startActivity(new Intent(Home.this, Setting.class));
        }else if(id == R.id.about_nav_menu){
            new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage("TP App Version 1.0.0")
                .setIcon(R.drawable.ic_logo_48dp)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){}
                }).show();
        }else if(id == R.id.logout_nav_menu){
            new Logout(Home.this).execute();
        }

        return true;
    }

    @Override
    protected void onResume(){
        super.onResume();
        LocalBroadcastManager.getInstance(Home.this).registerReceiver(br, new IntentFilter(GCMIntentService.REGISTRATION_SUCCESS));
        LocalBroadcastManager.getInstance(Home.this).registerReceiver(br, new IntentFilter(GCMIntentService.REGISTRATION_FAILED));
        Log.e("HomeActivity", "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        LocalBroadcastManager.getInstance(Home.this).unregisterReceiver(br);
        Log.e("HomeActivity", "onPause");
    }

}
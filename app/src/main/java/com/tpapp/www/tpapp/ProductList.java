package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.HashMap;

import Adapter.ProductListAdapter;

public class ProductList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ListView lv;
    private NavigationView nv;
    private DrawerLayout dl;
    private Session sm;
    private ProgressDialog pd;
    private TextView tv,tv_nav_menu;
    private com.mikhaellopez.circularimageview.CircularImageView civ;
    private ImageView iv_nav_menu;
    private HashMap<String,String> user,data;
    private SwipyRefreshLayout srl;
    private ProgressBar pb_1,pb_2;
    private View v;
    private static final String URL_PHOTO_PROFILE = "http://json.tpunity.com/picture/";
    private static final String URL_BACKGROUND_PROFILE = "http://json.tpunity.com/background/";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_26);
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

        pd = new ProgressDialog(ProductList.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        sm = new Session(ProductList.this);
        user = sm.getLogin();
        data = sm.getFaculty();

        tv = (TextView) findViewById(R.id.unnamed_171);
        //tv.setText(data.get(Session.F_NAME));

        lv = (ListView) findViewById(R.id.degree_list);

        if(sm.getStatusLogin() == true){
            getNavImage();

            /*srl = (SwipyRefreshLayout) findViewById(R.id.refresh_7);
            srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh(SwipyRefreshLayoutDirection direction) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            srl.setRefreshing(true);
                            getView();
                            srl.setRefreshing(false);
                        }
                    }, 5000);
                }
            });*/

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
            new AlertDialog.Builder(ProductList.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(ProductList.this).execute();
                    }
                })
                .show();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        dl.closeDrawer(GravityCompat.END);

        if(id == R.id.follow_up_nav_menu){
            new RequestPermission(ProductList.this).execute("no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
        }else if(id == R.id.input_data_nav_menu){
            startActivity(new Intent(ProductList.this, InputData.class));
        }else if(id == R.id.view_absence_nav_menu){
            startActivity(new Intent(ProductList.this, Absence.class));
        }else if(id == R.id.check_schedule_nav_menu){
            startActivity(new Intent(ProductList.this, Schedule.class));
        }else if(id == R.id.product_nav_menu){
            startActivity(new Intent(ProductList.this, Product.class));
        }else if(id == R.id.setting_nav_menu){
            startActivity(new Intent(ProductList.this, Setting.class));
        }else if(id == R.id.about_nav_menu){
            new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage("TP App Version 1.0.0")
                .setIcon(R.drawable.ic_logo_48dp)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
        }else if(id == R.id.logout_nav_menu){
            new Logout(ProductList.this).execute();
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
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

    public void getNavImage(){
        new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration ilc = new ImageLoaderConfiguration.Builder(ProductList.this).build();
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

    public void goToProfile(View v){
        dl.closeDrawer(GravityCompat.END);
        startActivity(new Intent(ProductList.this, ProfileLogin.class));
    }

    public void getView(){
        String line = data.get(Session.F_NAME).replaceAll(" ","%20");
        MainClass c = new MainClass("degree_list.php?name=" + line);
        Log.e("ProductList", "degree_list.php?name=" + line);

        ProductListAdapter pla = new ProductListAdapter(ProductList.this, R.layout.interface_listview_product_list, c.requestDegree(),data.get(Session.F_NAME));
        lv.setAdapter(pla);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Data.Degree dd = (Data.Degree) lv.getAdapter().getItem(position);
                sm.setDegree(dd);
                startActivity(new Intent(ProductList.this, ProductDetail.class));*/
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return true;
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return true;
            }
        });
    }

}

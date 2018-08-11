package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import Adapter.AbsenceAdapter;
import Adapter.OptionAdapter;

public class Absence extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ProgressDialog pd;
    private Session sm;
    private NavigationView nv;
    private DrawerLayout dl;
    private RelativeLayout rl_1,rl_2,rl_3,rl_4;
    private DatePicker dp;
    private TimePicker tp;
    private TextView tv_nav_menu,tv;
    private ListView lv_1, lv_2;
    private com.mikhaellopez.circularimageview.CircularImageView civ;
    private ImageView iv_nav_menu;
    private SwipyRefreshLayout srl;
    private HashMap<String,String> user;
    private View v;
    private static final String URL_PHOTO_PROFILE = "http://json.tpunity.com/picture/";
    private static final String URL_BACKGROUND_PROFILE = "http://json.tpunity.com/background/";
    private ProgressBar pb_1,pb_2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_5);
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

        pd = new ProgressDialog(Absence.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        sm = new Session(Absence.this);
        user = sm.getLogin();

        tv = (TextView) findViewById(R.id.select_date);
        tv.setTextColor(getResources().getColor(R.color.white_background));

        rl_1 = (RelativeLayout) findViewById(R.id.date_select);
        rl_2 = (RelativeLayout) findViewById(R.id.time_select);
        rl_3 = (RelativeLayout) findViewById(R.id.select_date_absence);
        rl_4 = (RelativeLayout) findViewById(R.id.option_layout_absence);

        rl_1.setVisibility(View.GONE);
        rl_2.setVisibility(View.GONE);
        rl_4.setVisibility(View.GONE);

        rl_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });

        dp = (DatePicker) findViewById(R.id.date_picker);
        tp = (TimePicker) findViewById(R.id.time_picker);

        lv_1 = (ListView) findViewById(R.id.lda);
        lv_2 = (ListView) findViewById(R.id.option_listview_absence);

        String [] option = new String[1];
        option[0] = "Delete";
        OptionAdapter oa = new OptionAdapter(this,R.layout.interface_listview_option,option);
        lv_2.setAdapter(oa);

        lv_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opt = lv_2.getAdapter().getItem(position).toString();

                if (opt == "Delete" || opt.equals("Delete")) {
                    Data.Absence da = (Data.Absence) lv_1.getAdapter().getItem(position);
                    final String line_2 = "id=" + da.getID();

                    new AlertDialog.Builder(Absence.this)
                            .setMessage("Are you sure want to delete this ?")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new AsyncTask<String,Void,String>(){

                                        @Override
                                        protected void onPreExecute(){
                                            super.onPreExecute();
                                            pd.show();
                                        }

                                        @Override
                                        protected String doInBackground(String... params){
                                            MainClass c = new MainClass("delete_absence.php?" + params[0]);
                                            c.executeURL();
                                            return "Delete absence successfully !";
                                        }

                                        @Override
                                        protected void onPostExecute(String s){
                                            super.onPostExecute(s);

                                            if(pd.isShowing()){
                                                pd.dismiss();
                                            }
                                        }
                                    }.execute(line_2);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                }
            }
        });

        if(sm.getStatusLogin() == true){
            getNavImage();

            srl = (SwipyRefreshLayout) findViewById(R.id.refresh_8);
            srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh(SwipyRefreshLayoutDirection direction) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                getView();
                            }catch(Exception e){

                            }
                            srl.setRefreshing(false);
                        }
                    }, 5000);
                }
            });

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    srl.setRefreshing(true);
                    getView();
                    srl.setRefreshing(false);
                }
            }, 5000);
        }else{
            new AlertDialog.Builder(Absence.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(Absence.this).execute();
                    }
                })
                .show();
        }
    }

    public void goToProfile(View v){
        dl.closeDrawer(GravityCompat.END);
        startActivity(new Intent(Absence.this, ProfileLogin.class));
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.icon_absence, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.menu:
                dl.openDrawer(GravityCompat.END);
                break;
            case R.id.add:
                startActivity(new Intent(Absence.this, AddAbsence.class));
                break;
            case R.id.salary:
                new CalculateSalary().execute("no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
                break;

        }

        return true;
    }

    @Override
    public void onBackPressed(){
        if(dl.isDrawerOpen(GravityCompat.END)){
            dl.closeDrawer(GravityCompat.END);
        }else{
            startActivity(new Intent(Absence.this,Home.class));
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        dl.closeDrawer(GravityCompat.END);

        if(id == R.id.follow_up_nav_menu){
            new RequestPermission(Absence.this).execute("no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
        }else if(id == R.id.input_data_nav_menu){
            startActivity(new Intent(Absence.this, InputData.class));
        }else if(id == R.id.view_absence_nav_menu){
            startActivity(new Intent(Absence.this, Absence.class));
        }else if(id == R.id.check_schedule_nav_menu){
            startActivity(new Intent(Absence.this, Schedule.class));
        }else if(id == R.id.product_nav_menu){
            startActivity(new Intent(Absence.this, Product.class));
        }else if(id == R.id.setting_nav_menu){
            startActivity(new Intent(Absence.this, Setting.class));
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
            new Logout(Absence.this).execute();
        }

        return true;
    }

    public void getNavImage(){
        new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();
        ImageLoaderConfiguration ilc = new ImageLoaderConfiguration.Builder(Absence.this).build();
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
        MainClass c = new MainClass("absence_list.php?date_filter=&no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
        List<Data.Absence> list_a = c.requestAbsence();

        if(list_a != null){
            AbsenceAdapter aa = new AbsenceAdapter(Absence.this, R.layout.interface_listview_support_available, list_a);
            lv_1.setAdapter(aa);

            lv_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Data.Absence da = (Data.Absence) lv_1.getAdapter().getItem(position);
                    sm.setAbsence(da);
                    startActivity(new Intent(Absence.this, AbsenceDetail.class));
                }
            });

            lv_1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    doOption();
                    return true;
                }
            });
        }
    }

    public void getView(final String line){
        getNavImage();

        Data.Absence a = new Data.Absence();
        MainClass c = new MainClass("absence_list.php?" + line);

        AbsenceAdapter aa = new AbsenceAdapter(Absence.this, R.layout.interface_listview_support_available, c.requestAbsence());
        lv_1.setAdapter(aa);

        lv_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Data.Absence da = (Data.Absence) lv_1.getAdapter().getItem(position);
                sm.setAbsence(da);
                startActivity(new Intent(Absence.this, AbsenceDetail.class));
            }
        });

        lv_1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                doOption();
                return true;
            }
        });
    }

    public void getDate(){
        rl_1.setVisibility(View.VISIBLE);
        ((ViewGroup) rl_1.getParent()).removeView(rl_1);

        new AlertDialog.Builder(Absence.this)
            .setView(rl_1)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){
                    Calendar c = Calendar.getInstance();
                    c.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
                    String line = "date_filter=" + date + "&no_prm_login=" + user.get(Session.NO_PRM_LOGIN);
                    System.out.println(line);
                    getView(line);
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){}
            })
            .show();
    }

    public void getTime(){
        rl_2.setVisibility(View.VISIBLE);
        ((ViewGroup) rl_2.getParent()).removeView(rl_2);

        new AlertDialog.Builder(Absence.this)
            .setTitle("Select Time")
            .setIcon(R.drawable.ic_logo_48dp)
            .setView(rl_1)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){
                    new Toolbar.OnMenuItemClickListener(){
                        @Override
                        public boolean onMenuItemClick(MenuItem item){
                            return true;
                        }
                    };
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){}
            })
            .show();
    }

    public void doOption(){
        rl_4.setVisibility(View.VISIBLE);
        ((ViewGroup)rl_4.getParent()).removeView(rl_4);

        new AlertDialog.Builder(Absence.this)
            .setView(rl_4)
            .setCancelable(true)
            .show();
    }

    public class CalculateSalary extends AsyncTask<String,Void,Integer>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected Integer doInBackground(String... params){
            MainClass c = new MainClass("calculate_salary.php?" + params[0]);
            int salary = c.calculateSalary();
            return salary;
        }

        @Override
        protected void onPostExecute(Integer salary){
            super.onPostExecute(salary);

            new AlertDialog.Builder(Absence.this)
                .setTitle("My Salary")
                .setMessage("Your salary for this month is: Rp " + salary + ".\n\nNote: this's only the prediction based the last currency / hour. If there's a mistake, please contact PIC TP.\n\nThank you.")
                .setIcon(R.drawable.ic_logo_48dp)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();


            if(pd.isShowing()){
                pd.dismiss();
            }
        }

    }

}
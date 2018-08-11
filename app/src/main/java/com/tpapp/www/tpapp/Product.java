package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.AbsenceAdapter;
import Adapter.ProductAdapter;
import Data.Branch;
import Data.Faculty;

public class Product extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView nv;
    private DrawerLayout dl;
    private Session sm;
    private ProgressDialog pd;
    private View v;
    private TextView tv_nav_menu;
    private com.mikhaellopez.circularimageview.CircularImageView civ;
    private ImageView iv_nav_menu;
    private HashMap<String,String> user;
    private ExpandableListView elv;
    private static final String URL_PHOTO_PROFILE = "http://json.tpunity.com/picture/";
    private static final String URL_BACKGROUND_PROFILE = "http://json.tpunity.com/background/";
    private ProgressBar pb_1,pb_2;
    private SwipyRefreshLayout srl;
    private HashMap<String,List<String>> faculty,note;
    private List<String> list_absence,list_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_19);
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

        pd = new ProgressDialog(Product.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }

        sm = new Session(Product.this);
        user = sm.getLogin();

        elv = (ExpandableListView) findViewById(R.id.product_category);

        /*srl = (SwipyRefreshLayout) findViewById(R.id.refresh_7);
        srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction){
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        getView();
                        srl.setRefreshing(false);
                    }
                }, 5000);
            }
        });*/

        if(sm.getStatusLogin() == true){
            getNavImage();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getView();
                }
            }, 5000);
        }else{
            new AlertDialog.Builder(Product.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(Product.this).execute();
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
            new RequestPermission(Product.this).execute("no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
        }else if(id == R.id.input_data_nav_menu){
            startActivity(new Intent(Product.this, InputData.class));
        }else if(id == R.id.view_absence_nav_menu){
            startActivity(new Intent(Product.this, Absence.class));
        }else if(id == R.id.check_schedule_nav_menu){
            startActivity(new Intent(Product.this, Schedule.class));
        }else if(id == R.id.product_nav_menu){
            startActivity(new Intent(Product.this, Product.class));
        }else if(id == R.id.setting_nav_menu){
            startActivity(new Intent(Product.this, Setting.class));
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
            new Logout(Product.this).execute();
        }

        return true;
    }

    @Override
    public void onBackPressed(){
        if(dl.isDrawerOpen(GravityCompat.END)){
            dl.closeDrawer(GravityCompat.END);
        }else{
            startActivity(new Intent(Product.this,Home.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.icon_product, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.menu:
                dl.openDrawer(GravityCompat.END);
                break;
            case R.id.search:
                break;
        }

        return true;
    }

    public void getView(){
        /*ProductAdapter aa = new ProductAdapter(this, getLocation(), getFaculty());
        elv.setAdapter(aa);

        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //Data.Faculty f = (Faculty) elv.getAdapter().getItem(groupPosition);
                //sm.setFaculty(f);

                Log.e("Faculty: ", elv.getAdapter().getItem(groupPosition).toString());
                startActivity(new Intent(Product.this, ProductList.class));
                return true;
            }
        });*/

        faculty = getFaculty();
        note = getNote();
        list_absence = new ArrayList<>(faculty.keySet());
        list_detail = new ArrayList<>(note.keySet());
        AbsenceAdapterNew aa = new AbsenceAdapterNew(this, faculty, note, list_absence, list_detail);
        elv.setAdapter(aa);

        elv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /*if(ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    new AlertDialog.Builder(Product.this)
                        .setMessage("Are you sure want to delete this ?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pd.show();
                                getView();
                                pd.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                }*/
                return true;
            }
        });

        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Data.Faculty f = new Data.Faculty();
                f.setFacultyName(elv.getAdapter().getItem(groupPosition).toString());
                sm.setFaculty(f);
                Log.e("Faculty",elv.getAdapter().getItem(groupPosition).toString());
                startActivity(new Intent(Product.this, ProductList.class));
                return true;
            }
        });
    }

    private void doMySearch(String query){
        Log.e("Product Search", query);
    }

    public void getNavImage(){
        new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();
        ImageLoaderConfiguration ilc = new ImageLoaderConfiguration.Builder(Product.this).build();
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

    public List<Data.Branch> getLocation(){
        MainClass c = new MainClass("branch_list.php");
        ArrayList<Data.Branch> branch = (ArrayList<Branch>) c.requestBranch();
        return branch;
    }

    /*public HashMap<String, List<Faculty>> getFaculty(){
        HashMap<String,List<Faculty>> faculty = new HashMap<>();
        List<Data.Branch> list_b = getLocation();
        List<Data.Faculty> list_faculty;

        for(int i=0;i<list_b.size();i++){
            MainClass c = new MainClass("faculty_list.php?id=" + list_b.get(i).getID());
            Log.e("Faculty","faculty_list.php?id=" + list_b.get(i).getID());

            if(c.requestFaculty().size()!=0){
                list_faculty = c.requestFaculty();
                faculty.put(String.valueOf(list_b.get(i).getID()), list_faculty);
            }else{
                faculty = null;
            }
        }

        return faculty;
    }*/

    public HashMap<String, List<String>> getFaculty(){
        MainClass c;
        HashMap<String,List<String>> faculty_ = new HashMap<>();
        List<String> faculty_name;
        List<Data.Faculty> tamp;
        List<Data.Branch> branch = getLocation();

        for(int i=0;i<branch.size();i++){
            c = new MainClass("faculty_list.php?id=" + branch.get(i).getID());
            Log.e("Product","faculty_list.php?id=" + branch.get(i).getID());
            tamp = c.requestFaculty();
            Log.e("Tamp Size", String.valueOf(tamp.size()));
            faculty_name = new ArrayList<>();

            if(tamp==null){

            }else{
                for(int j=0;j<tamp.size();j++){
                    faculty_name.add(tamp.get(j).getFacultyName());
                }
            }

            faculty_.put("" + i, faculty_name);
        }

        return faculty_;
    }

    public HashMap<String, List<String>> getNote(){
        MainClass c;
        HashMap<String,List<String>> note_ = new HashMap<>();
        List<String> faculty_note;
        List<Data.Faculty> tamp;
        List<Data.Branch> branch = getLocation();

        for(int i=1;i<branch.size();i++){
            c = new MainClass("faculty_list.php?id=" + branch.get(i).getID());
            Log.e("Product","faculty_list.php?id=" + branch.get(i).getID());
            tamp = c.requestFaculty();
            Log.e("Tamp Size", String.valueOf(tamp.size()));
            faculty_note = new ArrayList<>();

            if(tamp==null){

            }else{
                for(int j=0;j<tamp.size();j++){
                    faculty_note.add(tamp.get(j).getNote());
                }
            }

            note_.put("" + i, faculty_note);

        }

        return note_;
    }

    public void goToProfile(View v){
        dl.closeDrawer(GravityCompat.END);
        startActivity(new Intent(Product.this, ProfileLogin.class));
    }

    public class AbsenceAdapterNew extends BaseExpandableListAdapter{

        private Context c;
        private HashMap<String,List<String>> absence_per_month,detail_per_month;
        private List<String> list_absence,list_detail,branch;

        public AbsenceAdapterNew(Context c, HashMap<String, List<String>> absence_per_month, HashMap<String, List<String>> detail_per_month, List<String> list_abscence, List<String> list_detail){
            this.c=c;
            this.absence_per_month=absence_per_month;
            this.detail_per_month=detail_per_month;
            this.list_absence=list_abscence;
            this.list_detail=list_detail;

            branch = new ArrayList<>();

            for(int i=0;i<getLocation().size();i++){
                branch.add(getLocation().get(i).getBranchName());
            }
        }

        @Override
        public int getGroupCount(){
            return list_absence.size();
        }

        @Override
        public int getChildrenCount(int child){
            return absence_per_month.get(list_absence.get(child)).size();
        }

        @Override
        public Object getGroup(int parent){
            return list_absence.get(parent);
        }

        @Override
        public Object getChild(int parent, int child){
            return absence_per_month.get(list_absence.get(parent)).get(child);
        }

        @Override
        public long getGroupId(int parent){
            return parent;
        }

        @Override
        public long getChildId(int parent, int child){
            return child;
        }

        @Override
        public boolean hasStableIds(){
            return true;
        }

        @Override
        public View getGroupView(int parent, boolean isExpanded, View convert_view, ViewGroup parent_view) {

            String group_tittle = branch.get(parent);

            if(convert_view == null){
                LayoutInflater li = (LayoutInflater) c.getSystemService(LAYOUT_INFLATER_SERVICE);
                convert_view = li.inflate(R.layout.content_product_parent,parent_view,false);
            }

            TextView tv = (TextView) convert_view.findViewById(R.id.parent);
            tv.setText(group_tittle);
            tv.setTypeface(null, Typeface.BOLD);

            return convert_view;
        }

        @Override
        public View getChildView(int parent, int child, boolean last_child, View convert_view, ViewGroup parent_view) {
            String child_tittle="",child_detail="";

            if(absence_per_month.get(list_absence.get(parent)).size() != 0){
                child_tittle = absence_per_month.get(list_absence.get(parent)).get(child);
            }else if(detail_per_month.get(list_detail.get(parent)).size() != 0){
                child_detail = detail_per_month.get(list_detail.get(parent)).get(child);
            }

            if(convert_view == null){
                LayoutInflater li = (LayoutInflater) c.getSystemService(LAYOUT_INFLATER_SERVICE);
                convert_view = li.inflate(R.layout.content_product_child,parent_view,false);
            }

            TextView tv_1 = (TextView) convert_view.findViewById(R.id.child);
            tv_1.setText(child_tittle);

            TextView tv_2 = (TextView) convert_view.findViewById(R.id.detail);
            tv_2.setText(child_detail);

            return convert_view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

}
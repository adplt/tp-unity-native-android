package com.tpapp.www.tpapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.HashMap;

import Adapter.TPListChatAdapter;
import Data.TeamPromotion;

public class ListPeople extends AppCompatActivity{

    private ListView lv;
    private Session sm;
    private HashMap<String,String> user;
    private SwipyRefreshLayout srl;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_list_people);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_25);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        sm = new Session(ListPeople.this);
        user = sm.getLogin();

        lv = (ListView) findViewById(R.id.list_people_listview);

        srl = (SwipyRefreshLayout) findViewById(R.id.refresh_2);
        srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                try{
                    getView();
                }catch(Exception e){

                }
                srl.setRefreshing(false);
            }
        });

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

    public void getView(){
        MainClass c = new MainClass("tp_list_random.php");
        TPListChatAdapter tp_list_random = new TPListChatAdapter(ListPeople.this,R.layout.interface_listview_team_promotion,c.requestTP(),user.get(Session.TP_NAME_LOGIN));
        lv.setAdapter(tp_list_random);

        lv.setLongClickable(false);
        lv.setDivider(null);
        lv.setDividerHeight(0);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TeamPromotion tp = (TeamPromotion) lv.getAdapter().getItem(position);
                sm.setTPList(tp);
                startActivity(new Intent(ListPeople.this, Chat.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.icon_search_chat, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.search:
                break;
        }

        return true;
    }

}

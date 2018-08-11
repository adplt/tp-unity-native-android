package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import Adapter.EventAdapter;

public class EventSchedule extends android.support.v4.app.Fragment{

    private ProgressDialog pd;
    private Session sm;
    private View v;
    private ListView lv;
    private DatePicker dp;
    private RelativeLayout rl_1,rl_2;
    private HashMap<String,String> user;
    private SwipyRefreshLayout srl;
    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v = inflater.inflate(R.layout.activity_event_schedule,container,false);

        pd = new ProgressDialog(getContext());
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        sm = new Session(getContext());
        user = sm.getLogin();

        rl_1 = (RelativeLayout) v.findViewById(R.id.date_event);
        rl_2 = (RelativeLayout) v.findViewById(R.id.event_select_date);
        dp = (DatePicker) v.findViewById(R.id.date_picker_event);

        rl_1.setVisibility(View.GONE);
        lv = (ListView) v.findViewById(R.id.event_list);

        tv = (TextView) v.findViewById(R.id.select_date_2);
        tv.setTextColor(getResources().getColor(R.color.white_background));

        srl = (SwipyRefreshLayout) v.findViewById(R.id.refresh_5);
        srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            getView("date_filter=&id=" + user.get(Session.BRANCH_LOGIN));
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

                try{
                    getView("date_filter=&id=" + user.get(Session.BRANCH_LOGIN));
                }catch(Exception e){

                }

                Log.e("getView", "date_filter=&id=" + user.get(Session.BRANCH_LOGIN));
                srl.setRefreshing(false);
            }
        }, 5000);

        rl_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getDate();
            }
        });

        return v;
    }

    public void getView(String line){
        MainClass c = new MainClass("event_list.php?" + line);

        EventAdapter ea = new EventAdapter(v.getContext(), R.layout.interface_listview_event, c.requestEvent());
        lv.setAdapter(ea);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Data.Event de = (Data.Event) lv.getAdapter().getItem(position);
                sm.setEvent(de);
                startActivity(new Intent(v.getContext(), EventDetail.class));
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return true;
            }
        });
    }

    public void getDate(){
        rl_1.setVisibility(View.VISIBLE);
        ((ViewGroup) rl_1.getParent()).removeView(rl_1);

        new AlertDialog.Builder(v.getContext())
            .setView(rl_1)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Calendar c = Calendar.getInstance();
                    c.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
                    String line = "date_filter=" + date + "&no_prm_login=" + user.get(Session.NO_PRM_LOGIN);
                    System.out.println(line);
                    getView(line);
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            })
            .show();
    }
}
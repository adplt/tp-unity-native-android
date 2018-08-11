package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.os.Bundle;
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

import Adapter.SupportAdapter;

public class SupportSchedule extends android.support.v4.app.Fragment{

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
        v = inflater.inflate(R.layout.activity_support_schedule,container,false);

        pd = new ProgressDialog(getContext());
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        sm = new Session(getContext());
        user = sm.getLogin();

        dp = (DatePicker) v.findViewById(R.id.date_picker_support);

        tv = (TextView) v.findViewById(R.id.select_date_3);
        tv.setTextColor(getResources().getColor(R.color.white_background));

        rl_1 = (RelativeLayout) v.findViewById(R.id.date_support);
        rl_2 = (RelativeLayout) v.findViewById(R.id.support_select_date);

        rl_1.setVisibility(View.GONE);

        lv = (ListView) v.findViewById(R.id.support_list);

        srl = (SwipyRefreshLayout) v.findViewById(R.id.refresh_6);
        srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                try{
                    getView("date_filter=&no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
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
                    getView("date_filter=&no_prm_login=" + user.get(Session.NO_PRM_LOGIN));
                }catch(Exception e){

                }

                srl.setRefreshing(false);
            }
        }, 5000);

        rl_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });

        return v;
    }

    public void getView(String line){
        MainClass c = new MainClass("support_list.php?" + line);

        SupportAdapter sa = new SupportAdapter(v.getContext(), R.layout.interface_listview_support_available, c.requestSupport());
        lv.setAdapter(sa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Data.Support ds = (Data.Support) lv.getAdapter().getItem(position);
                sm.setSupport(ds);
                //startActivity(new Intent(v.getContext(), AbsenceDetail.class));
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

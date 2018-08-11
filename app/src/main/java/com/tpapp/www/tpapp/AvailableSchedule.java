package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
import Adapter.AvailableAdapter;
import Adapter.OptionAdapter;

public class AvailableSchedule extends android.support.v4.app.Fragment{

    private ProgressDialog pd;
    private Session sm;
    private View v;
    private ListView lv_1, lv_2;
    private DatePicker dp;
    private RelativeLayout rl_1,rl_2,rl_3;
    private HashMap<String,String> user;
    private SwipyRefreshLayout srl;
    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v = inflater.inflate(R.layout.activity_available_schedule,container,false);

        pd = new ProgressDialog(getContext());
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        sm = new Session(v.getContext());
        user = sm.getLogin();

        rl_1 = (RelativeLayout) v.findViewById(R.id.date_available);
        rl_2 = (RelativeLayout) v.findViewById(R.id.available_select_date);
        rl_3 = (RelativeLayout) v.findViewById(R.id.option_layout_available_schedule);

        dp = (DatePicker) v.findViewById(R.id.date_picker_available);

        tv = (TextView) v.findViewById(R.id.select_date_1);
        tv.setTextColor(getResources().getColor(R.color.white_background));

        rl_1.setVisibility(View.GONE);
        rl_3.setVisibility(View.GONE);

        lv_1 = (ListView) v.findViewById(R.id.available_list);
        lv_2 = (ListView) v.findViewById(R.id.option_listview_available_schedule);

        String[] option = new String[1];
        option[0] = "Delete";
        OptionAdapter oa = new OptionAdapter(v.getContext(),R.layout.interface_listview_option,option);
        lv_2.setAdapter(oa);

        lv_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opt = lv_2.getAdapter().getItem(position).toString();

                if(opt == "Delete" || opt.equals("Delete")){
                    Data.Available da = (Data.Available) lv_1.getAdapter().getItem(position);
                    final String line_2 = "id=" + da.getID();

                    new AlertDialog.Builder(v.getContext())
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
                                        MainClass c = new MainClass("delete_available.php?" + params[0]);
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

        srl = (SwipyRefreshLayout) v.findViewById(R.id.refresh_4);
        srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            getView("no_prm_login=" + user.get(Session.NO_PRM_LOGIN) + "&date_filter=");
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
                try {
                    getView("no_prm_login=" + user.get(Session.NO_PRM_LOGIN) + "&date_filter=");
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

    public void getView(final String line){
        MainClass c = new MainClass("available_list.php?" + line);

        AvailableAdapter aa = new AvailableAdapter(v.getContext(), R.layout.interface_listview_support_available, c.requestAvailable());
        lv_1.setAdapter(aa);

        lv_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Data.Available da = (Data.Available) lv_1.getAdapter().getItem(position);
                sm.setAvailable(da);
                startActivity(new Intent(v.getContext(), AvailableDetail.class));
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
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){}
            })
            .show();
    }

    public void doOption(){
        rl_3.setVisibility(View.VISIBLE);
        ((ViewGroup)rl_3.getParent()).removeView(rl_3);

        new AlertDialog.Builder(v.getContext())
            .setView(rl_3)
            .setCancelable(true)
            .show();
    }

}
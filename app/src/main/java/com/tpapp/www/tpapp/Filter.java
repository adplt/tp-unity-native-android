package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import Data.DataTitle;

public class Filter extends AppCompatActivity{

    private ProgressDialog pd;
    //private CheckBox cb_1,cb_2,cb_3,cb_4,cb_5,cb_6,cb_7,cb_8,cb_9;
    private Spinner s;
    private RelativeLayout rl;
    private int position;
    private List<DataTitle> list_d;
    private Session sm;
    private TextView tv;
    private HashMap<String,String> user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_filter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_11);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        pd = new ProgressDialog(Filter.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        tv = (TextView) findViewById(R.id.follow_up_label);
        tv.setTextColor(getResources().getColor(R.color.white_background));

        sm = new Session(Filter.this);
        user = sm.getLogin();

        /*
        cb_1 = (CheckBox) findViewById(R.id.name_checkbox);
        cb_2 = (CheckBox) findViewById(R.id.address_checkbox);
        cb_3 = (CheckBox) findViewById(R.id.email_checkbox);
        cb_4 = (CheckBox) findViewById(R.id.phone_number_checkbox);
        cb_5 = (CheckBox) findViewById(R.id.work_number_checkbox);
        cb_6 = (CheckBox) findViewById(R.id.company_checkbox);
        cb_7 = (CheckBox) findViewById(R.id.alumni_checkbox);
        cb_8 = (CheckBox) findViewById(R.id.major_checkbox);
        cb_9 = (CheckBox) findViewById(R.id.select_all_checkbox);

        cb_1.setChecked(true);
        cb_4.setChecked(true);
        cb_5.setChecked(true);

        cb_1.setEnabled(false);
        cb_4.setEnabled(false);
        cb_5.setEnabled(false);

        cb_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb_2.isChecked()){
                    Toast.makeText(getBaseContext(), cb_2.getText() + " selected", Toast.LENGTH_LONG).show();
                }

                if (cb_2.isChecked() && cb_3.isChecked() && cb_6.isChecked() && cb_7.isChecked() && cb_8.isChecked()) {
                    cb_9.setChecked(true);
                    Toast.makeText(getBaseContext(), "You have made it select all", Toast.LENGTH_LONG).show();
                }else{
                    cb_9.setChecked(false);
                }
            }
        });

        cb_3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cb_3.isChecked()){
                    Toast.makeText(getBaseContext(), cb_3.getText() + " selected", Toast.LENGTH_LONG).show();
                }

                if(cb_2.isChecked() && cb_3.isChecked() && cb_6.isChecked() && cb_7.isChecked() && cb_8.isChecked()){
                    cb_9.setChecked(true);
                    Toast.makeText(getBaseContext(), "You have made it select all", Toast.LENGTH_LONG).show();
                }else{
                    cb_9.setChecked(false);
                }
            }
        });

        cb_6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cb_6.isChecked()){
                    Toast.makeText(getBaseContext(), cb_6.getText() + " selected", Toast.LENGTH_LONG).show();
                }

                if(cb_2.isChecked() && cb_3.isChecked() && cb_6.isChecked() && cb_7.isChecked() && cb_8.isChecked()){
                    cb_9.setChecked(true);
                    Toast.makeText(getBaseContext(), "You have made it select all", Toast.LENGTH_LONG).show();
                }else{
                    cb_9.setChecked(false);
                }
            }
        });

        cb_7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cb_7.isChecked()){
                    Toast.makeText(getBaseContext(), cb_7.getText() + " selected", Toast.LENGTH_LONG).show();
                }

                if(cb_2.isChecked() && cb_3.isChecked() && cb_6.isChecked() && cb_7.isChecked() && cb_8.isChecked()){
                    cb_9.setChecked(true);
                    Toast.makeText(getBaseContext(), "You have made it select all", Toast.LENGTH_LONG).show();
                }else{
                    cb_9.setChecked(false);
                }
            }
        });

        cb_8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cb_8.isChecked()){
                    Toast.makeText(getBaseContext(), cb_8.getText() + " selected", Toast.LENGTH_LONG).show();
                }

                if(cb_2.isChecked() && cb_3.isChecked() && cb_6.isChecked() && cb_7.isChecked() && cb_8.isChecked()){
                    cb_9.setChecked(true);
                    Toast.makeText(getBaseContext(), "You have made it select all", Toast.LENGTH_LONG).show();
                }else{
                    cb_9.setChecked(false);
                }
            }
        });

        cb_9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cb_9.isChecked()){
                    cb_1.setChecked(true);
                    cb_2.setChecked(true);
                    cb_3.setChecked(true);
                    cb_4.setChecked(true);
                    cb_5.setChecked(true);
                    cb_6.setChecked(true);
                    cb_7.setChecked(true);
                    cb_8.setChecked(true);
                    Toast.makeText(getBaseContext(), cb_9.getText() + " selected", Toast.LENGTH_LONG).show();
                }else{
                    cb_2.setChecked(false);
                    cb_3.setChecked(false);
                    cb_6.setChecked(false);
                    cb_7.setChecked(false);
                    cb_8.setChecked(false);
                }
            }
        });*/

        rl = (RelativeLayout) findViewById(R.id.lets_do_follow_up);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                new DoFollowUp().execute(list_d.get(getCurrentPosition()).getID());
            }
        });

        if(sm.getStatusLogin() == true){
            new RetrievingDataTittle().execute();
        }else {
            new AlertDialog.Builder(Filter.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(Filter.this).execute();
                    }
                })
                .show();
        }
    }

    public List<DataTitle>dataType(){
        MainClass data_student_candidate = new MainClass("follow_up_data.php");
        List<DataTitle> list_d = data_student_candidate.requestDataTitle();

        return list_d;
    }

    public void setCurrentPosition(int position){
        this.position=position;
    }

    public int getCurrentPosition(){
        return position;
    }

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
            .setMessage("Are you sure want to exit ?")
            .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Filter.this, Home.class));
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){}
            })
            .show();
    }

    public class RetrievingDataTittle extends AsyncTask<Void,Void,String[]>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected String[] doInBackground(Void... params){
            list_d = dataType();
            String[]data = new String[list_d.size()];

            for(int i=0;i<list_d.size();i++){
                data[i] = list_d.get(i).getDataName();
            }

            return data;
        }

        @Override
        protected void onPostExecute(String[]data){
            super.onPostExecute(data);

            if(data.length != 0){
                s = (Spinner) findViewById(R.id.follow_up_data_option);
                ArrayAdapter<String> data_type = new ArrayAdapter<>(Filter.this, android.R.layout.simple_spinner_item, data);
                data_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s.setAdapter(data_type);

                s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                        setCurrentPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent){}
                });
            }else{
                new AlertDialog.Builder(Filter.this)
                    .setMessage("Data in your selected data title is empty. Please check your internet connection.")
                    .setPositiveButton("Refresh", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            new RetrievingDataTittle().execute();
                        }
                    })
                    .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Filter.this,Home.class));
                        }
                    })
                    .show();
            }

            if(pd.isShowing()){
                pd.dismiss();
            }
        }

    }

    public class DoFollowUp extends AsyncTask<String,Void,String>{

        String parameter;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected String doInBackground(String... params){
            parameter = params[0];
            String message = "";

            MainClass c = new MainClass("follow_up.php?id_data=" + params[0]);

            if(c.requestCandidateStudent().size()!=0){
                sm.setCandidateStudent(c.requestCandidateStudent().get(0));
                sm.setDataTittle(list_d.get(getCurrentPosition()).getID());
            }else{
                message = "Unable to retrieving data follow up. Refresh again ?";
            }

            return message;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            if(result == "" || result.equals("")){
                getFragmentManager().beginTransaction().replace(R.id.include_layout, new FollowUp()).commit();
            }else{
                new AlertDialog.Builder(Filter.this)
                    .setMessage(result)
                    .setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new DoFollowUp().execute(parameter);
                        }
                    })
                    .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which){}
                    })
                    .show();
            }

            if(pd.isShowing()){
                pd.dismiss();
            }
        }

    }

}
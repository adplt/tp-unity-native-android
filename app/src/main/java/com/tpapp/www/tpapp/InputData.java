package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.List;

import Data.DataTitle;

public class InputData extends AppCompatActivity{

    private AlertDialog.Builder adb_1,adb_2,adb_3;
    private EditText et;
    private Session sm;
    private ProgressDialog pd;
    private RelativeLayout rl_1,rl_2;
    private Spinner s;
    private List<DataTitle> list_d;
    private int position;
    private HashMap<String,String> user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_input_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_14);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        pd = new ProgressDialog(InputData.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        sm = new Session(InputData.this);
        user = sm.getLogin();

        rl_1 = (RelativeLayout) findViewById(R.id.spinner_input_data_layout);
        rl_2 = (RelativeLayout) findViewById(R.id.edit_text_input_data_layout);
        s = (Spinner) findViewById(R.id.data_type);
        et = (EditText) findViewById(R.id.new_data_tittle_field);

        if(sm.getStatusLogin() == true){
            new RetrievingDataTittle().execute();
            validateDataTittle(rl_1, rl_2);
        }else{
            new AlertDialog.Builder(InputData.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(InputData.this).execute();
                    }
                })
                .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.icon_input_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.change:
                Intent i = new Intent(InputData.this, InputData.class);

                //Closing all the Activities
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                //Add new Flag to start new Activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(i);
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
            .setMessage("Are you sure want to exit ?")
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(InputData.this, Home.class));

                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            })
            .show();
    }

    public void setCurrentPosition(int position){
        this.position=position;
    }

    public int getCurrentPosition(){
        return position;
    }

    public List<DataTitle>dataType(){
        DataTitle d = new DataTitle();
        MainClass data_student_candidate = new MainClass("follow_up_data.php");
        List<DataTitle> list_d = data_student_candidate.requestDataTitle();
        return list_d;
    }

    private void validateDataTittle(final RelativeLayout rl_1,final RelativeLayout rl_2){
        ((ViewGroup) rl_1.getParent()).removeView(rl_1);
        ((ViewGroup) rl_2.getParent()).removeView(rl_2);

        adb_1 = new AlertDialog.Builder(InputData.this);
        adb_1.setTitle("Input Data");
        adb_1.setIcon(R.drawable.ic_logo_48dp);
        adb_1.setMessage("Choose data type or input new data type");
        adb_1.setView(rl_1);
        adb_1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                };

                if(list_d.size() != 0) {
                    sm.setDataTittle(list_d.get(getCurrentPosition()));
                }

                getFragmentManager().beginTransaction().replace(com.tpapp.www.tpapp.R.id.input_data_layout, new Form()).commit();
            }
        });
        adb_1.setNeutralButton("Create New", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputDataAlertBuild(rl_1, rl_2);
            }
        });
        adb_1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(InputData.this, Home.class));
            }
        });
        adb_1.show();
    }

    private void inputDataAlertBuild(final RelativeLayout rl_1,final RelativeLayout rl_2){
        adb_2 = new AlertDialog.Builder(InputData.this);
        adb_2.setTitle("Input Data");
        adb_2.setMessage("Input new data title here !");
        adb_2.setIcon(R.drawable.ic_logo_48dp);
        adb_2.setView(rl_2);
        adb_2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(et.getText().toString() == "" || et.getText().toString().equals("")){
                    adb_3 = new AlertDialog.Builder(InputData.this);
                    adb_3.setTitle("Input Data");
                    adb_3.setMessage("Data tittle can not be empty. If you want to continue input data, please back or cancel to cancel input data !");
                    adb_3.setIcon(R.drawable.ic_logo_48dp);
                    adb_3.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((ViewGroup) rl_2.getParent()).removeView(rl_2);
                            inputDataAlertBuild(rl_1, rl_2);
                        }
                    });
                    adb_3.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(InputData.this, Home.class));
                        }
                    });
                    adb_3.show();
                }else{
                    String line = "dt_name=" + et.getText();
                    new InputDataTitle().execute(line);
                    sm.setDataTittle(et.getText().toString());
                    getFragmentManager().beginTransaction().replace(R.id.input_data_layout, new Form()).commit();
                }
            }
        });
        adb_2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                validateDataTittle(rl_1, rl_2);
            }
        });
        adb_2.show();
    }

    public class RetrievingDataTittle extends AsyncTask<Void,Void,String[]>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected String[]doInBackground(Void... params){
            list_d = dataType();
            String[]data = new String[list_d.size()];

            for(int i=0;i<list_d.size();i++){
                data[i] = list_d.get(i).getDataName();
            }

            return data;
        }

        @Override
        protected void onPostExecute(String[] data){
            super.onPostExecute(data);

            if(data.length != 0){
                ArrayAdapter<String> data_type = new ArrayAdapter<>(InputData.this, android.R.layout.simple_spinner_item, data);
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
                new AlertDialog.Builder(InputData.this)
                    .setMessage("Unable to retrieving data follow up. Refresh again ?")
                    .setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new RetrievingDataTittle().execute();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(InputData.this,Home.class));
                        }
                    })
                    .show();
            }

            if(pd.isShowing()){
                pd.dismiss();
            }
        }

    }

    public class InputDataTitle extends AsyncTask<String,Void,Void>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected Void doInBackground(String... params){
            MainClass c = new MainClass("input_data_title.php?" + params[0]);
            c.executeURL();
            return null;
        }

        @Override
        protected void onPostExecute(Void s){
            super.onPostExecute(s);

            if(pd.isShowing()){
                pd.dismiss();
            }
        }
    }

}
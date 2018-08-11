package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import Data.Degree;
import Data.TeamPromotion;

public class AccountSetting extends AppCompatActivity{

    private RelativeLayout rl;
    private TextView tv;
    private Session sm;
    private HashMap<String,String> user;
    private EditText et_1,et_2,et_3,et_4,et_5;
    private Spinner s;
    private ProgressDialog pd;
    private List<Degree> list_dd;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_account_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_4);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        tv = (TextView) findViewById(R.id.save_label);
        tv.setTextColor(getResources().getColor(R.color.white_background));

        pd = new ProgressDialog(AccountSetting.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        sm = new Session(AccountSetting.this);

        if(sm.getStatusLogin() == true){
            user = sm.getLogin();

            et_1 = (EditText) findViewById(R.id.tp_name);
            et_1.setTextColor(getResources().getColor(R.color.black_background));

            et_2 = (EditText) findViewById(R.id.tp_email);
            et_2.setTextColor(getResources().getColor(R.color.black_background));

            et_3 = (EditText) findViewById(R.id.tp_address);
            et_3.setTextColor(getResources().getColor(R.color.black_background));

            et_4 = (EditText) findViewById(R.id.tp_phone_number);
            et_4.setTextColor(getResources().getColor(R.color.black_background));

            et_5 = (EditText) findViewById(R.id.tp_work_number);
            et_5.setTextColor(getResources().getColor(R.color.black_background));

            new RetrievingDegree().execute();

            et_1.setText(user.get(Session.TP_NAME_LOGIN));
            et_2.setText(user.get(Session.EMAIL_LOGIN));
            et_3.setText(user.get(Session.ADDRESS_LOGIN));
            et_4.setText(user.get(Session.PHONE_NUMBER_LOGIN));
            et_5.setText(user.get(Session.WORK_NUMBER_LOGIN));

            rl = (RelativeLayout) findViewById(R.id.save_button);
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String line = "id_login=" + user.get(Session.NO_PRM_LOGIN) +
                        "&name_login=" + et_1.getText() +
                        "&email_login=" + et_2.getText() +
                        "&address_login=" + et_3.getText() +
                        "&phone_number_login=" + et_4.getText() +
                        "&work_number_login=" + et_5.getText() +
                        "&degree_login=" + getCurrentPosition();
                    line = line.replaceAll(" ", "%20");
                    System.out.println(line);
                    new UpdateProfile().execute(line);

                    TeamPromotion tp = new TeamPromotion();
                    tp.setID(user.get(Session.NO_PRM_LOGIN));
                    tp.setName(et_1.getText().toString());
                    tp.setGender(user.get(Session.GENDER_LOGIN));
                    tp.setJoinDate(user.get(Session.JOIN_DATE_LOGIN));
                    tp.setPassword(user.get(Session.PASSWORD_LOGIN));
                    tp.setEmail(et_2.getText().toString());
                    tp.setAddress(et_3.getText().toString());
                    tp.setPhoneNumber(et_4.getText().toString());
                    tp.setWorkNumber(et_5.getText().toString());
                    tp.setBirthday(user.get(Session.BIRTHDAY_LOGIN));
                    tp.setURLImage(user.get(Session.PICTURE_LOGIN));
                    tp.setURLBackground(user.get(Session.BACKGROUND_LOGIN));
                    tp.setScore(Integer.parseInt(user.get(Session.SCORE_LOGIN)));
                    tp.setDegree(getCurrentPosition());

                    sm.setLogin(tp);
                }
            });
        }else{
            new AlertDialog.Builder(AccountSetting.this)
                .setMessage("Your session has expired, please login.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Logout(AccountSetting.this).execute();
                    }
                })
                .show();
        }
    }

    public List<Data.Degree> dataType(){
        MainClass degree = new MainClass("degree_list.php?");
        List<Data.Degree> list_dd = degree.requestDegree();
        return list_dd;
    }

    public void setCurrentPosition(int position){
        this.position=position;
    }

    public int getCurrentPosition(){
        return position;
    }

    public class UpdateProfile extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected String doInBackground(String... params){
            MainClass c = new MainClass("update_profile.php?" + params[0]);
            c.executeURL();
            return "Save setting successfully !";
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);

            new AlertDialog.Builder(AccountSetting.this).setTitle("Setting")
                .setMessage(s)
                .setIcon(R.drawable.ic_logo_48dp)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){}
                })
                .show();

            if(pd.isShowing()){
                pd.dismiss();
            }
        }

    }

    public class RetrievingDegree extends AsyncTask<Void,Void,String[]>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected String[] doInBackground(Void... params){
            list_dd = dataType();
            String[]data = new String[list_dd.size()];

            for(int i=0;i<list_dd.size();i++){
                data[i] = list_dd.get(i).getDegreeName();
            }

            return data;
        }

        @Override
        protected void onPostExecute(String[]data){
            super.onPostExecute(data);

            if(data.length != 0){
                s = (Spinner) findViewById(R.id.degree);
                ArrayAdapter<String> data_type = new ArrayAdapter<>(AccountSetting.this, android.R.layout.simple_spinner_item, data);
                data_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s.setAdapter(data_type);
                s.setSelection(Integer.parseInt(user.get(Session.ID_DEGREE_LOGIN)));

                s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                        setCurrentPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent){}
                });
            }else{
                new AlertDialog.Builder(AccountSetting.this)
                        .setMessage("Unable to retrieving data follow up. Refresh again ?")
                        .setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new RetrievingDegree().execute();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(AccountSetting.this, Setting.class));
                            }
                        })
                        .show();
            }

            if(pd.isShowing()){
                pd.dismiss();
            }
        }

    }

}
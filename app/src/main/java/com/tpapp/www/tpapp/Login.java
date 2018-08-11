package com.tpapp.www.tpapp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.net.InetAddress;

public class Login extends AppCompatActivity{

    private ProgressDialog pd;
    private EditText prm;
    private EditText pass;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#42A5F5"));
        setContentView(R.layout.activity_login);

        pd = new ProgressDialog(Login.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        prm = (EditText) findViewById(R.id.prm_number_field);
        prm.setTextColor(getResources().getColor(R.color.black_background));

        pass = (EditText) findViewById(R.id.password_field);
        pass.setTextColor(getResources().getColor(R.color.black_background));

        Button login = (Button) findViewById(R.id.login_button);
        login.setTextColor(getResources().getColor(R.color.white_background));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((prm.getText().toString() != "" || !prm.getText().equals("")) || (pass.getText().toString() != "" || !pass.getText().equals(""))){
                    new AsyncTask<String, String, String>(){

                        private String pesan;
                        private boolean flag;

                        @Override
                        protected void onPreExecute(){
                            super.onPreExecute();
                            pd.show();
                        }

                        @Override
                        protected String doInBackground(String... params){

                            if(isConnectingToInternet()){
                                MainClass c = new MainClass(params[0],Login.this);
                                flag = c.validateLogin();

                                if(flag == false){
                                    pesan = "Invalid PRM Number & Password";
                                }else{
                                    pesan = "";
                                }
                            }else{
                                pesan = "No Internet Connection";
                            }

                            return pesan;
                        }

                        @Override
                        protected void onPostExecute(String s){
                            super.onPostExecute(s);

                            if(s.equals("")){
                                startActivity(new Intent(Login.this, Home.class));
                            }else{
                                new AlertDialog.Builder(Login.this)
                                        .setMessage(s)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                                            @Override
                                            public void onClick(DialogInterface dialog, int which){}
                                        })
                                        .show();
                            }

                            if(pd.isShowing()){
                                pd.dismiss();
                            }
                        }

                    }.execute("login.php?noprm=" + prm.getText() + "&password=" + pass.getText());
                }else{
                    new AlertDialog.Builder(Login.this)
                        .setMessage("Invalid PRM Number & Password")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){}
                        })
                        .show();
                }

            }


        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        moveTaskToBack(true);
    }

    //Checking for all possible internet providers
    public boolean isConnectingToInternet(){
        try{
            InetAddress ia = InetAddress.getByName("tpunity.com"); //You can replace it with your name

            if(ia.equals("")){
                Log.e("Try - isConnectInternet", "false");
                return false;
            }else{
                Log.e("Try - isConnectInternet", "true");
                return true;
            }
        }catch(Exception e){
            Log.e("Catch - isConnectInternet", "false");
            return false;
        }
    }

}
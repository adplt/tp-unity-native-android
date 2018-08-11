package com.tpapp.www.tpapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class Logout extends AsyncTask<Void,Void,Void>{

    private ProgressDialog pd;
    private Session sm;
    private Context c;

    public Logout(Context c){
        pd = new ProgressDialog(c);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        this.c = c;
        sm = new Session(c);
    }

    private void doLogout(){
        sm.logoutUser();
        Intent i = new Intent(c, Login.class);

        //Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        c.startActivity(i);
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        pd.show();
    }

    @Override
    protected Void doInBackground(Void... params){
        doLogout();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);
        pd.dismiss();
    }

}
package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class RequestPermission extends AsyncTask<String,Void,Boolean>{

    private String line;
    private ProgressDialog pd;
    private Context c;

    public RequestPermission(Context c){
        this.c=c;

        pd = new ProgressDialog(c);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        pd.show();
    }

    @Override
    protected Boolean doInBackground(String... params){
        MainClass c = new MainClass("validate_is_follow_up.php?" + params[0]);
        line = params[0];
        return c.validateFollowUp();
    }

    @Override
    protected void onPostExecute(Boolean result){
        super.onPostExecute(result);

        Log.e("RP", String.valueOf(result));

        if(result == true){
            c.startActivity(new Intent(c, Filter.class));
        }else{
            new AlertDialog.Builder(c)
                .setMessage("You don't have any access to follow up or you don't have internet connection. Please contact staff for help or try to refresh again ?")
                .setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new RequestPermission(c).execute(line);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
        }

        if(pd.isShowing()){
            pd.dismiss();
        }
    }

}
package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class Form extends Fragment{

    private EditText et_1,et_2,et_3,et_4,et_5,et_6,et_7,et_8;
    private View v;
    private Button b;
    private ProgressDialog pd;
    private Session sm;
    private HashMap<String,String> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v = inflater.inflate(R.layout.activity_form, container, false);

        et_1 = (EditText) v.findViewById(R.id.full_name_field);
        et_1.setTextColor(getResources().getColor(R.color.black_background));

        et_2 = (EditText) v.findViewById(R.id.email_label);
        et_2.setTextColor(getResources().getColor(R.color.black_background));

        et_3 = (EditText) v.findViewById(R.id.address_icon);
        et_3.setTextColor(getResources().getColor(R.color.black_background));

        et_4 = (EditText) v.findViewById(R.id.phone_number_label);
        et_4.setTextColor(getResources().getColor(R.color.black_background));

        et_5 = (EditText) v.findViewById(R.id.work_number_label);
        et_5.setTextColor(getResources().getColor(R.color.black_background));

        et_6 = (EditText) v.findViewById(R.id.date_icon);
        et_6.setTextColor(getResources().getColor(R.color.black_background));

        et_7 = (EditText) v.findViewById(R.id.alumni_label);
        et_7.setTextColor(getResources().getColor(R.color.black_background));

        et_8 = (EditText) v.findViewById(R.id.major_label);
        et_8.setTextColor(getResources().getColor(R.color.black_background));

        pd = new ProgressDialog(v.getContext());
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        sm = new Session(v.getContext());
        data = sm.getDataTittle();
        final String code_data_title = data.get(Session.DT_ID);

        b = (Button) v.findViewById(R.id.submit_button);
        b.setTextColor(getResources().getColor(R.color.white_background));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(et_1.getText().toString() == "" || et_1.getText().toString().equals("")){
                    Snackbar.make(v, "Full name must be filled !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else{
                    if((et_4.getText().toString() == "" || et_4.getText().toString().equals("")) && (et_5.getText().toString() == "" || et_5.getText().toString().equals(""))){
                        Snackbar.make(v, "Either phone number and work number must be filled !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }else{
                        String line = "id="+code_data_title+
                            "&name="+et_1.getText().toString()+
                            "&email="+et_2.getText().toString()+
                            "&address="+et_3.getText().toString()+
                            "&phone_number="+et_4.getText().toString()+
                            "&work_number="+et_5.getText().toString()+
                            "&company="+et_6.getText().toString()+
                            "&alumni="+et_7.getText().toString()+
                            "&degree="+et_8.getText().toString();
                        line = line.replace(" ", "%20");
                        System.out.println(line);
                        new AsyncTask<String,Void,String>(){

                            @Override
                            protected void onPreExecute(){
                                super.onPreExecute();
                                pd.show();
                            }

                            @Override
                            protected String doInBackground(String... params){
                                MainClass c = new MainClass("input_data_cs.php?" + params[0]);
                                c.executeURL();
                                return "Save setting successfully !";
                            }

                            @Override
                            protected void onPostExecute(String s){
                                super.onPostExecute(s);

                                new AlertDialog.Builder(v.getContext())
                                        .setTitle("Input Data")
                                        .setMessage(s)
                                        .setIcon(R.drawable.ic_logo_48dp)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                getFragmentManager().beginTransaction().replace(R.id.form_input_data, new Form()).commit();
                                            }
                                        })
                                        .show();

                                if(pd.isShowing()){
                                    pd.dismiss();
                                }
                            }

                        }.execute(line);
                    }
                }
            }
        });

        return v;
    }

}
package com.tpapp.www.tpapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class FollowUp extends Fragment{

    private RelativeLayout rl_1,rl_2;
    private EditText et;
    private View v;
    private Spinner s;
    private Session sm;
    private HashMap<String,String> data,user,setting;
    private TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv_7,tv_8,tv_9;
    private int position;
    private String id_cs;
    private ProgressDialog pd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        v = inflater.inflate(R.layout.activity_follow_up,container,false);
        rl_1 = (RelativeLayout) v.findViewById(R.id.irl);
        et = (EditText) v.findViewById(R.id.input_response_field);
        s = (Spinner) v.findViewById(R.id.ro);

        pd = new ProgressDialog(v.getContext());
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage("Please wait...");

        rl_1.setVisibility(View.GONE);
        sm = new Session(v.getContext());
        data = sm.getCandidateStudent();
        setting = sm.getGeneralSetting();
        user = sm.getLogin();

        tv_1 = (TextView) v.findViewById(R.id.name_icon);
        tv_1.setText(data.get(Session.CS_NAME));

        tv_2 = (TextView) v.findViewById(R.id.email_label);
        tv_2.setText(data.get(Session.CS_EMAIL));

        tv_3 = (TextView) v.findViewById(R.id.address_icon);
        tv_3.setText(data.get(Session.CS_ADDRESS));

        tv_4 = (TextView) v.findViewById(R.id.phone_number_label);
        tv_4.setText(data.get(Session.CS_PHONE_NUMBER));

        tv_5 = (TextView) v.findViewById(R.id.work_number_label);
        tv_5.setText(data.get(Session.CS_WORK_NUMBER));

        tv_6 = (TextView) v.findViewById(R.id.date_icon);
        tv_6.setText(data.get(Session.CS_COMPANY));

        tv_7 = (TextView) v.findViewById(R.id.alumni_label);
        tv_7.setText(data.get(Session.CS_ALUMNI));

        tv_8 = (TextView) v.findViewById(R.id.major_label);
        tv_8.setText(data.get(Session.CS_DEGREE));

        tv_9 = (TextView) v.findViewById(R.id.response_label);
        tv_9.setTextColor(getResources().getColor(R.color.white_background));

        if(setting.get(Session.SETTING_SIZE) != null){
            if (setting.get(Session.SETTING_SIZE).toString() == "Small" || setting.get(Session.SETTING_SIZE).equals("Small")) {
                tv_1.setTextSize(Float.parseFloat("15"));
                tv_2.setTextSize(Float.parseFloat("15"));
                tv_3.setTextSize(Float.parseFloat("15"));
                tv_4.setTextSize(Float.parseFloat("15"));
                tv_5.setTextSize(Float.parseFloat("15"));
                tv_6.setTextSize(Float.parseFloat("15"));
                tv_7.setTextSize(Float.parseFloat("15"));
                tv_8.setTextSize(Float.parseFloat("15"));
            }else if(setting.get(Session.SETTING_SIZE) == "Medium" || setting.get(Session.SETTING_SIZE).equals("Medium")){
                tv_1.setTextSize(Float.parseFloat("20"));
                tv_2.setTextSize(Float.parseFloat("20"));
                tv_3.setTextSize(Float.parseFloat("20"));
                tv_4.setTextSize(Float.parseFloat("20"));
                tv_5.setTextSize(Float.parseFloat("20"));
                tv_6.setTextSize(Float.parseFloat("20"));
                tv_7.setTextSize(Float.parseFloat("20"));
                tv_8.setTextSize(Float.parseFloat("20"));
                System.out.println(data.get(Session.SETTING_SIZE));
            }else if(setting.get(Session.SETTING_SIZE) == "Large" || setting.get(Session.SETTING_SIZE).equals("Large")){
                tv_1.setTextSize(Float.parseFloat("30"));
                tv_2.setTextSize(Float.parseFloat("30"));
                tv_3.setTextSize(Float.parseFloat("30"));
                tv_4.setTextSize(Float.parseFloat("30"));
                tv_5.setTextSize(Float.parseFloat("30"));
                tv_6.setTextSize(Float.parseFloat("30"));
                tv_7.setTextSize(Float.parseFloat("30"));
                tv_8.setTextSize(Float.parseFloat("30"));
                System.out.println(data.get(Session.SETTING_SIZE));
            }
        }

        setIDCS(data.get(Session.CS_ID));

        ArrayAdapter<CharSequence> aac = ArrayAdapter.createFromResource(v.getContext(),R.array.follow_up_result_option,android.R.layout.simple_spinner_item);
        aac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(aac);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setCurrentPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });

        rl_2 = (RelativeLayout) v.findViewById(com.tpapp.www.tpapp.R.id.response_button);
        rl_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getResponse();
            }
        });

        return v;
    }

    public void getResponse(){
        rl_1.setVisibility(View.VISIBLE);
        ((ViewGroup)rl_1.getParent()).removeView(rl_1);

        new AlertDialog.Builder(v.getContext())
            .setTitle("Give Response")
            .setMessage("Give he/she responses here !")
            .setIcon(R.drawable.ic_logo_48dp)
            .setView(rl_1)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){
                    if(et.getText().toString() == "" || et.getText().toString().equals("")){
                        new AlertDialog.Builder(v.getContext())
                            .setTitle("Give Response")
                            .setMessage("Response note can not be empty. If you want to continue follow up, please back !")
                            .setIcon(R.drawable.ic_logo_48dp)
                            .setPositiveButton("Back", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getResponse();
                                }
                            })
                            .show();
                    }else{
                        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                        String line = "";

                        if(s.getSelectedItemPosition() == 0){
                            line = "no_prm=" + user.get(Session.NO_PRM_LOGIN) + "&follow_up_date=" + date + "&result=1&note=" + et.getText().toString() + "&id_cs=" + getIDCS();
                        }else if(s.getSelectedItemPosition() == 1){
                            line = "no_prm=" + user.get(Session.NO_PRM_LOGIN) + "&follow_up_date=" + date + "&result=0&note=" + et.getText().toString() + "&id_cs=" + getIDCS();
                        }

                        line = line.replaceAll(" ", "%20");

                        MainClass c = new MainClass("give_response.php?" + line);
                        c.executeURL();

                        new NewFollowUp().execute();

                        et.setText("");
                        s.setSelection(0);
                    }
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){}
            })
            .show();
    }

    public void setCurrentPosition(int position){
        this.position=position;
    }

    public int getCurrentPosition(){
        return position;
    }

    public void setIDCS(String id_cs){
        this.id_cs=id_cs;
    }

    public String getIDCS(){
        return id_cs;
    }

    public class NewFollowUp extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... aVoid){
            MainClass c = new MainClass("follow_up.php?id_data=" + sm.getDataTittle().get(Session.DT_ID));
            sm.setCandidateStudent(c.requestCandidateStudent().get(0));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);

            getFragmentManager().beginTransaction().replace(R.id.follow_up,new FollowUp()).commit();

            if(pd.isShowing()){
                pd.dismiss();
            }
        }
    }

}
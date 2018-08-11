package com.tpapp.www.tpapp;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import Data.*;
import Data.Event;

public class MainClass extends Application{

    private String url_link;
    private Context c;
    private Session sm;
    private final static String link = "http://json.tpunity.com/";

    public MainClass(){
        super();
    }

    public MainClass(String url_line){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        url_link = link + url_line;
    }

    public MainClass(String url_line, Context c){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        url_link = link + url_line;
        this.c=c;
        sm = new Session(c);
    }

    public StringBuffer executeURL(){
        StringBuffer sb = new StringBuffer();
        URLConnection connect;
        BufferedReader br = null;
        String line = "";

        try{
            URL url = new URL(url_link);
            connect = url.openConnection();
            connect.connect();

            if(connect.getInputStream() != null) {
                //mengambil response
                InputStream is = connect.getInputStream();
                //buat nampung buffer dari response.
                br = new BufferedReader(new InputStreamReader(is));
                //buat menampung String dari BufferedReader yang udah dibagi2.

                //dari BufferedReader kan String'a dalam bentuk 1 line doang, nah ini mw di potong2 string'a.
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
        }catch(Exception e){
            //MalformedURLException, IOException
            sb = null;
        }finally{
            try{
                br.close();
            }catch(Exception e){
                //IOException
                sb=null;
                Log.e("ExecuteURL",e.getMessage());
            }
        }

        return sb;
    }

    public boolean validateLogin(){
        boolean flag = true;
        TeamPromotion tp = new TeamPromotion();

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray object_tp = object.getJSONArray("team_promotion_user");

            if(object_tp.getJSONObject(0)!=null){
                JSONObject field = object_tp.getJSONObject(0);

                if(!field.isNull("no_prm")){
                    tp.setID(field.getString("no_prm"));
                    tp.setName(field.getString("tp_name"));
                    tp.setGender(field.getString("gender"));
                    tp.setScore(field.getInt("score"));
                    tp.setPassword(field.getString("password"));
                    tp.setEmail(field.getString("email"));
                    tp.setAddress(field.getString("address"));
                    tp.setPhoneNumber(field.getString("phone_number"));
                    tp.setWorkNumber(field.getString("work_number"));
                    tp.setDegree(field.getInt("id_degree"));
                    tp.setBranch(field.getInt("id_branch"));
                    tp.setJoinDate(field.getString("join_date"));
                    tp.setBirthday(field.getString("birth_date"));
                    tp.setURLImage(field.getString("picture"));
                    tp.setURLBackground(field.getString("background"));

                    sm.setLogin(tp);
                }else{
                    flag = false;
                }
            }
        }catch(JSONException e){
            flag = false;
        }

        return flag;
    }

    public boolean validateRatting(){
        Data.Ratting r;
        boolean flag = true;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("ratting");

            if(array.getJSONObject(0)!=null){
                JSONObject field = array.getJSONObject(0);

                if(!field.isNull("id")){
                    r = new Data.Ratting();

                    r.setID(field.getInt("id"));
                    r.setFrom(field.getString("from"));
                    r.setTo(field.getString("to"));
                    r.setTimeManagement(BigDecimal.valueOf(field.getDouble("time_management")).floatValue());
                    r.setInitiative(BigDecimal.valueOf(field.getDouble("initiative")).floatValue());
                    r.setResponsible(BigDecimal.valueOf(field.getDouble("responsible")).floatValue());
                    r.setDateCreated(field.getString("date_created"));
                    r.setComment(field.getString("comment"));

                    sm.setRatting(r);
                }else{
                    flag = false;
                }
            }
        }catch(Exception er){
            //JSONException
            flag = false;
        }

        return flag;
    }

    public Data.TeamPromotion getTP(){
        TeamPromotion tp = new TeamPromotion();

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray object_tp = object.getJSONArray("tp_list");

            if(object_tp.getJSONObject(0)!=null){
                JSONObject field= object_tp.getJSONObject(0);

                if(!field.isNull("no_prm")){
                    tp.setID(field.getString("no_prm"));
                    tp.setName(field.getString("tp_name"));
                    tp.setGender(field.getString("gender"));
                    tp.setScore(field.getInt("score"));
                    tp.setPassword(field.getString("password"));
                    tp.setEmail(field.getString("email"));
                    tp.setAddress(field.getString("address"));
                    tp.setPhoneNumber(field.getString("phone_number"));
                    tp.setWorkNumber(field.getString("work_number"));
                    tp.setDegree(field.getInt("id_degree"));
                    tp.setBranch(field.getInt("id_branch"));
                    tp.setJoinDate(field.getString("join_date"));
                    tp.setBirthday(field.getString("birth_date"));
                    tp.setURLImage(field.getString("picture"));
                    tp.setURLBackground(field.getString("background"));
                }else{
                    tp = null;
                }
            }
        }catch(JSONException e){
            tp = null;
        }

        return tp;
    }

    public int calculateSalary(){
        int salary,total_hour = 0;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("calculate_salary");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);
                    total_hour = total_hour + field.getInt("hour_count");
                }
            }

            salary = total_hour * 14000;
        }catch(JSONException e){
            salary = 0;
        }

        return salary;
    }

    public boolean validateFollowUp(){
        boolean flag = true;
        Log.e("Initial", String.valueOf(flag));
        List<Data.TeamPromotion> list_tp = new ArrayList<>();
        TeamPromotion tp;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("validate_follow_up");

            if(array.getJSONObject(0)!=null){
                JSONObject field = array.getJSONObject(0);

                if(field.isNull("no_prm")){
                    flag = false;
                }
            }else{
                flag = false;
            }
        }catch(Exception e){
            //JSONException
            flag = false;
        }

        return flag;
    }

    public List<Data.TeamPromotion>requestTP(){
        List<Data.TeamPromotion> list_tp = new ArrayList<>();
        TeamPromotion tp;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("tp_list");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    tp = new TeamPromotion();

                    tp.setID(field.getString("no_prm"));
                    tp.setName(field.getString("tp_name"));
                    tp.setGender(field.getString("gender"));
                    tp.setScore(field.getInt("score"));
                    tp.setPassword(field.getString("password"));
                    tp.setEmail(field.getString("email"));
                    tp.setAddress(field.getString("address"));
                    tp.setPhoneNumber(field.getString("phone_number"));
                    tp.setWorkNumber(field.getString("work_number"));
                    tp.setDegree(field.getInt("id_degree"));
                    tp.setBranch(field.getInt("id_branch"));
                    tp.setJoinDate(field.getString("join_date"));
                    tp.setBirthday(field.getString("birth_date"));
                    tp.setURLImage(field.getString("picture"));
                    tp.setURLBackground(field.getString("background"));

                    list_tp.add(tp);
                }
            }
        }catch(Exception e){
            //JSONException
            list_tp = null;
        }

        return list_tp;
    }

    public List<Data.CandidateStudent>requestCandidateStudent(){
        List<Data.CandidateStudent> list_cs = new ArrayList<>();
        CandidateStudent cs;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("follow_up_history");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    cs = new CandidateStudent();

                    cs.setID(field.getString("id"));
                    cs.setName(field.getString("candidate_name"));
                    cs.setAddress(field.getString("address"));
                    cs.setEmail(field.getString("email"));
                    cs.setWorkNumber(field.getString("work_number"));
                    cs.setPhoneNumber(field.getString("phone_number"));
                    cs.setCompany(field.getString("company"));
                    cs.setAlumni(field.getString("alumni"));
                    cs.setDegree(field.getString("degree"));
                    cs.setDataTittle(field.getString("data_name"));
                    cs.setDateFollowUp(field.getString("follow_up_date"));
                    cs.setResult(field.getString("result"));
                    cs.setNote(field.getString("description"));

                    list_cs.add(cs);
                }
            }
        }catch(Exception e){
            //JSONException
            list_cs = null;
        }

        return list_cs;
    }

    public List<Data.Support>requestSupport(){
        List<Data.Support> list_s = new ArrayList<>();
        Support s;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("support_list");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    s = new Data.Support();

                    s.setID(field.getInt("id"));
                    s.setSupportStart(field.getString("support_from"));
                    s.setSupportEnd(field.getString("support_until"));
                    s.setJobDescription(field.getString("description"));

                    list_s.add(s);
                }
            }
        }catch(Exception e){
            //JSONException
            list_s = null;
        }

        return list_s;
    }

    public List<Data.Absence>requestAbsence(){
        List<Data.Absence> list_a = new ArrayList<>();
        Data.Absence a;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("list_absence");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    a = new Data.Absence();

                    a.setID(field.getInt("id"));
                    a.setAbsenceIn(field.getString("clock_in"));
                    a.setAbsenceOut(field.getString("clock_out"));
                    a.setInfoSupport(field.getString("description"));

                    list_a.add(a);
                }else{
                    list_a = null;
                }
            }
        }catch(Exception e){
            //JSONException
            list_a = null;
        }

        return list_a;
    }

    public List<DataTitle>requestDataTitle(){
        List<DataTitle> list_d = new ArrayList<>();
        DataTitle d;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("follow_up_data");

            for(int i=0;i<array.length();i++){
                JSONObject field = array.getJSONObject(i);

                d = new DataTitle();

                d.setID(field.getString("id"));
                d.setDataName(field.getString("data_name"));

                list_d.add(d);
            }
        }catch(Exception e){
            //JSONException
            list_d = null;
        }

        return list_d;
    }

    public List<Data.Event>requestEvent(){
        List<Data.Event> list_e = new ArrayList<>();
        Data.Event e;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("event_list");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    e = new Event();

                    e.setID(field.getInt("id"));
                    e.setName(field.getString("event_name"));
                    e.setAddress(field.getString("location"));
                    e.setStart(field.getString("start"));
                    e.setEnd(field.getString("end"));
                    e.setTP(field.getInt("total_tp"));
                    e.setNote(field.getString("note"));
                    //e.setEventPicture(field.getString("picture"));

                    list_e.add(e);
                }
            }
        }catch(Exception er){
            //JSONException
            list_e = null;
        }

        return list_e;
    }

    public List<Data.Available>requestAvailable(){
        List<Data.Available> list_av = new ArrayList<>();
        Data.Available av;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("available_list");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    av = new Data.Available();

                    av.setID(field.getInt("id"));
                    av.setStart(field.getString("available_from"));
                    av.setUntil(field.getString("available_until"));

                    list_av.add(av);
                }
            }
        }catch(Exception er){
            //JSONException
            list_av = null;
        }

        return list_av;
    }

    public List<Data.Ratting>requestRatting(){
        List<Data.Ratting> list_r = new ArrayList<>();
        Data.Ratting r;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("ratting");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    r = new Data.Ratting();

                    r.setID(field.getInt("id"));
                    r.setFrom(field.getString("from"));
                    r.setTo(field.getString("to"));
                    r.setTimeManagement(BigDecimal.valueOf(field.getDouble("time_management")).floatValue());
                    r.setInitiative(BigDecimal.valueOf(field.getDouble("initiative")).floatValue());
                    r.setResponsible(BigDecimal.valueOf(field.getDouble("responsible")).floatValue());
                    r.setDateCreated(field.getString("date_created"));
                    r.setComment(field.getString("comment"));

                    list_r.add(r);
                }
            }
        }catch(Exception er){
            //JSONException
            list_r = null;
        }

        return list_r;
    }

    public List<Data.Comment>requestComment(){
        List<Data.Comment> list_c = new ArrayList<>();
        Data.Comment c;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("ratting");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    c = new Data.Comment();

                    c.setID(field.getInt("id"));
                    c.setNoPRM(field.getString("no_prm"));
                    c.setFrom(field.getString("from"));
                    c.setScore(field.getInt("score"));
                    c.setURLImage(field.getString("picture"));
                    c.setDateCreated(field.getString("date_created"));
                    c.setComment(field.getString("comment"));

                    list_c.add(c);
                }
            }
        }catch(Exception er){
            //JSONException
            list_c = null;
        }

        return list_c;
    }

    public List<Data.Branch>requestBranch(){
        List<Data.Branch> list_b = new ArrayList<>();
        Data.Branch b;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("branch_list");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    b = new Data.Branch();

                    b.setID(field.getInt("id"));
                    b.setBranchName(field.getString("branch_name"));
                    b.setCode(field.getString("code"));

                    list_b.add(b);
                }
            }
        }catch(Exception er){
            //JSONException
            list_b = null;
        }

        return list_b;
    }

    public List<Data.Faculty>requestFaculty(){
        List<Data.Faculty> list_f = new ArrayList<>();
        Data.Faculty f;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("faculty_list");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    f = new Data.Faculty();

                    f.setID(field.getInt("id"));
                    f.setFacultyName(field.getString("faculty_name"));
                    f.setNote(field.getString("note"));

                    list_f.add(f);
                }
            }
        }catch(Exception er){
            //JSONException
            list_f = null;
        }

        return list_f;
    }

    public List<Data.Degree>requestDegree(){
        List<Data.Degree> list_dd = new ArrayList<>();
        Data.Degree d;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("degree_list");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    d = new Data.Degree();

                    d.setID(field.getInt("id"));
                    d.setDegreeName(field.getString("degree_name"));

                    list_dd.add(d);
                }
            }
        }catch(Exception er){
            //JSONException
            list_dd = null;
        }

        return list_dd;
    }

    public Data.Ratting requestUserRatting(){
        Data.Ratting r = new Data.Ratting();

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("ratting");

            if(array.getJSONObject(0)!=null){
                JSONObject field = array.getJSONObject(0);

                float
                    satu = Float.parseFloat(String.valueOf(field.getDouble("tm"))),
                    dua = Float.parseFloat(String.valueOf(field.getDouble("i"))),
                    tiga = Float.parseFloat(String.valueOf(field.getDouble("r"))),
                    num = Float.parseFloat(String.valueOf(field.getInt("number_id")));

                satu = satu / num;
                dua = dua / num;
                tiga = tiga / num;

                r.setID(0);
                r.setFrom("");
                r.setTo("");
                r.setTimeManagement(satu);
                r.setInitiative(dua);
                r.setResponsible(tiga);
                r.setDateCreated("");
                r.setComment("");
            }
        }catch(Exception er){
            //JSONException
            r = null;
        }

        return r;
    }

    public List<Data.TeamPromotion>requestConfirmEvent(){
        List<Data.TeamPromotion> list_tp = new ArrayList<>();
        Data.TeamPromotion tp;

        //Ambil data dari JSON Object
        try{
            JSONObject object = new JSONObject(executeURL().toString());
            JSONArray array = object.getJSONArray("join_event");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    tp = new Data.TeamPromotion();

                    tp.setID(field.getString("no_prm"));
                    tp.setName(field.getString("tp_name"));
                    tp.setGender(field.getString("gender"));
                    tp.setScore(field.getInt("score"));
                    tp.setPassword(field.getString("password"));
                    tp.setEmail(field.getString("email"));
                    tp.setAddress(field.getString("address"));
                    tp.setPhoneNumber(field.getString("phone_number"));
                    tp.setWorkNumber(field.getString("work_number"));
                    tp.setDegree(field.getInt("id_degree"));
                    tp.setBranch(field.getInt("id_branch"));
                    tp.setJoinDate(field.getString("join_date"));
                    tp.setBirthday(field.getString("birth_date"));
                    tp.setURLImage(field.getString("picture"));
                    tp.setURLBackground(field.getString("background"));

                    list_tp.add(tp);
                }
            }
        }catch(Exception er){
            //JSONException
            list_tp = null;
        }

        return list_tp;
    }

}
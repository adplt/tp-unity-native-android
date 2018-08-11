package com.tpapp.www.tpapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Rating;
import android.support.v4.app.NavUtils;
import java.util.HashMap;
import Data.*;
import Data.Absence;
import Data.Event;
import Data.CandidateStudent;
import Data.Product;
import Data.Support;

public class Session{

    private SharedPreferences sp;
    private SharedPreferences.Editor e;
    private Context c;

    //UserLogin
    public static final String NO_PRM_LOGIN = "NO_PRM_LOGIN";
    public static final String TP_NAME_LOGIN = "TP_NAME_LOGIN";
    public static final String GENDER_LOGIN = "GENDER_LOGIN";
    public static final String SCORE_LOGIN = "SCORE_LOGIN";
    public static final String PASSWORD_LOGIN = "PASSWORD_LOGIN";
    public static final String IS_LOGIN = "IS_LOGIN";
    public static final String EMAIL_LOGIN = "EMAIL_LOGIN";
    public static final String ADDRESS_LOGIN = "ADDRESS_LOGIN";
    public static final String WORK_NUMBER_LOGIN = "WORK_NUMBER_LOGIN";
    public static final String PHONE_NUMBER_LOGIN = "PHONE_NUMBER_LOGIN";
    public static final String ID_DEGREE_LOGIN = "ID_DEGREE_LOGIN";
    public static final String JOIN_DATE_LOGIN = "JOIN_DATE_LOGIN";
    public static final String BRANCH_LOGIN = "BRANCH_LOGIN";
    public static final String BIRTHDAY_LOGIN = "BIRTHDAY_LOGIN";
    public static final String PICTURE_LOGIN = "PICTURE_LOGIN";
    public static final String BACKGROUND_LOGIN = "BACKGROUND_LOGIN";

    //Home
    public static final String NO_PRM = "NO_PRM";
    public static final String TP_NAME = "TP_NAME";
    public static final String GENDER = "GENDER";
    public static final String SCORE = "SCORE";
    public static final String PASSWORD = "PASSWORD";
    public static final String EMAIL = "EMAIL";
    public static final String ADDRESS = "ADDRESS";
    public static final String WORK_NUMBER = "WORK_NUMBER";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String DEGREE = "DEGREE";
    public static final String JOIN_DATE = "JOIN_DATE";
    public static final String BRANCH = "BRANCH";
    public static final String BIRTHDAY = "BIRTHDAY";
    public static final String PICTURE = "PICTURE";
    public static final String BACKGROUND = "BACKGROUND";

    //StudentCandidate
    public static final String CS_ID = "CS_ID";
    public static final String CS_NAME = "CS_NAME";
    public static final String CS_EMAIL = "CS_EMAIL";
    public static final String CS_ADDRESS = "CS_ADDRESS";
    public static final String CS_PHONE_NUMBER = "CS_PHONE_NUMBER";
    public static final String CS_WORK_NUMBER = "CS_WORK_NUMBER";
    public static final String CS_COMPANY = "CS_COMPANY";
    public static final String CS_ALUMNI = "CS_ALUMNI";
    public static final String CS_DEGREE = "CS_DEGREE";
    public static final String CS_DATA_TITTLE = "CS_DATA_TITTLE";
    public static final String CS_DATE_FOLLOW_UP = "CS_DATE_FOLLOW_UP";
    public static final String CS_TP_NAME = "CS_TP_NAME";
    public static final String CS_RESULT = "CS_RESULT";
    public static final String CS_NOTE = "CS_NOTE";

    //Event
    public static final String E_ID = "E_ID";
    public static final String E_NAME = "E_NAME";
    public static final String E_ADDRESS = "E_ADDRESS";
    public static final String E_START = "E_START";
    public static final String E_END = "E_END";
    public static final String E_TP = "E_TP";
    public static final String E_NOTE = "E_NOTE";

    //DataTittle
    public static final String DT_ID = "DT_ID";
    public static final String DT_NAME = "DT_NAME";

    //Setting
    public static final String SETTING_ALARM = "SETTING_ALARM";
    public static final String SETTING_REMIND = "SETTING_REMIND";
    public static final String SETTING_SOUND = "SETTING_SOUND";
    public static final String SETTING_SIZE = "SETTING_SOUND";

    //Absence
    public static final String AB_ID = "AB_ID";
    public static final String AB_CLOCK_IN = "AB_CLOCK_IN";
    public static final String AB_CLOCK_OUT = "AB_CLOCK_OUT";
    public static final String AB_DESCRIPTION = "AB_DESCRIPTION";

    //Available
    public static final String AV_ID = "AV_ID";
    public static final String AV_START = "AV_START";
    public static final String AV_UNTIL = "AV_UNTIL";

    //Ratting
    public static final String RATTING_ID = "RATTING_ID";
    public static final String RATTING_FROM = "RATTING_FROM";
    public static final String RATTING_TO = "RATTING_TO";
    public static final String RATTING_TIME_MANAGEMENT = "RATTING_TIME_MANAGEMENT";
    public static final String RATTING_INITIATIVE = "RATTING_INITIATIVE";
    public static final String RATTING_RESPONSIBLE = "RATTING_RESPONSIBLE";
    public static final String RATTING_DATE_CREATED = "RATTING_DATE_CREATED";
    public static final String RATTING_COMMENT = "RATTING_COMMENT";

    //Support
    public static final String SP_ID = "SP_ID";
    public static final String SP_FROM = "SP_FROM";
    public static final String SP_UNTIL = "SP_UNTIL";
    public static final String SP_DESCRIPTION = "SP_DESCRIPTION";

    //Faculty
    public static final String F_ID = "F_ID";
    public static final String F_NAME = "F_NAME";
    public static final String F_NOTE = "F_NOTE";

    //Degree
    public static final String D_ID = "D_ID";
    public static final String D_NAME = "D_NAME";

    //Branch
    public static final String B_ID = "B_ID";
    public static final String B_NAME = "B_NAME";
    public static final String B_CODE = "B_CODE";

    //Product
    public static final String P_ID = "P_ID";
    public static final String P_BRANCH = "P_BRANCH";
    public static final String P_FACULTY = "P_FACULTY";
    public static final String P_DEGREE = "P_DEGREE";
    public static final String P_REQUIREMENT = "P_REQUIREMENT";

    public Session(Context c){
        this.c=c;

        sp = c.getSharedPreferences("UserLogin", Context.MODE_PRIVATE); // 0 - for private mode
        e = sp.edit();
    }

    public void setLogin(TeamPromotion tp){
        sp = c.getSharedPreferences("UserLogin", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putString(NO_PRM_LOGIN, tp.getID());
        e.putString(TP_NAME_LOGIN, tp.getName());
        e.putString(GENDER_LOGIN, tp.getGender());
        e.putInt(SCORE_LOGIN, tp.getScore());
        e.putString(PASSWORD_LOGIN, tp.getPassword());
        e.putString(EMAIL_LOGIN, tp.getEmail());
        e.putString(ADDRESS_LOGIN, tp.getAddress());
        e.putString(PHONE_NUMBER_LOGIN, tp.getPhoneNumber());
        e.putString(WORK_NUMBER_LOGIN, tp.getWorkNumber());
        e.putInt(ID_DEGREE_LOGIN, tp.getDegree());
        e.putString(JOIN_DATE_LOGIN, tp.getJoinDate());
        e.putInt(BRANCH_LOGIN, tp.getBranch());
        e.putString(BIRTHDAY_LOGIN, tp.getBirthday());
        e.putString(PICTURE_LOGIN, tp.getURLImage());
        e.putString(BACKGROUND_LOGIN, tp.getURLBackground());
        e.putBoolean(IS_LOGIN, true);

        e.commit();
    }

    public void setTPList(TeamPromotion tp){
        sp = c.getSharedPreferences("Home", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putString(NO_PRM, tp.getID());
        e.putString(TP_NAME, tp.getName());
        e.putString(GENDER, tp.getGender());
        e.putInt(SCORE, tp.getScore());
        e.putString(PASSWORD, tp.getPassword());
        e.putString(EMAIL, tp.getEmail());
        e.putString(ADDRESS, tp.getAddress());
        e.putString(PHONE_NUMBER, tp.getPhoneNumber());
        e.putString(WORK_NUMBER, tp.getWorkNumber());
        e.putString(DEGREE, String.valueOf(tp.getDegree()));
        e.putString(JOIN_DATE, tp.getJoinDate());
        e.putInt(BRANCH, tp.getBranch());
        e.putString(BIRTHDAY, tp.getBirthday());
        e.putString(PICTURE, tp.getURLImage());
        e.putString(BACKGROUND, tp.getURLBackground());

        e.commit();
    }

    public void setAbsence(Absence a){
        sp = c.getSharedPreferences("Absence", Context.MODE_PRIVATE); // 0 - for private mode
        e = sp.edit();

        e.putInt(AB_ID, a.getID());
        e.putString(AB_CLOCK_IN, a.getAbsenceIn());
        e.putString(AB_CLOCK_OUT, a.getAbsenceOut());
        e.putString(AB_DESCRIPTION, a.getInfoSupport());

        e.commit();
    }

    public void setEvent(Event ev){
        sp = c.getSharedPreferences("Event", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putInt(E_ID, ev.getID());
        e.putString(E_NAME, ev.getName());
        e.putString(E_ADDRESS, ev.getAddress());
        e.putString(E_START, ev.getStart());
        e.putString(E_END, ev.getEnd());
        e.putInt(E_TP, ev.getTP());
        e.putInt(E_TP, ev.getTP());
        e.putString(E_NOTE, ev.getNote());

        e.commit();
    }

    public void setCandidateStudent(CandidateStudent cs){
        sp = c.getSharedPreferences("CandidateStudent", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putString(CS_ID, cs.getID());
        e.putString(CS_NAME, cs.getName());
        e.putString(CS_EMAIL, cs.getEmail());
        e.putString(CS_ADDRESS, cs.getAddress());
        e.putString(CS_PHONE_NUMBER, cs.getPhoneNumber());
        e.putString(CS_WORK_NUMBER, cs.getWorkNumber());
        e.putString(CS_COMPANY, cs.getCompany());
        e.putString(CS_ALUMNI, cs.getAlumni());
        e.putString(CS_DEGREE, cs.getDegree());
        e.putString(CS_DATA_TITTLE, cs.getDataTittle());
        e.putString(CS_DATE_FOLLOW_UP, cs.getDateFollowUp());
        e.putString(CS_RESULT, cs.getResult());
        e.putString(CS_NOTE, cs.getNote());

        e.commit();
    }

    public void setSupport(Support s){
        sp = c.getSharedPreferences("Support", Context.MODE_PRIVATE); // 0 - for private mode
        e = sp.edit();

        e.putInt(SP_ID, s.getID());
        e.putString(SP_FROM, s.getSupportStart());
        e.putString(SP_UNTIL, s.getSupportEnd());
        e.putString(SP_DESCRIPTION, s.getJobDescription());

        e.commit();
    }

    public void setDataTittle(DataTitle dt){
        sp = c.getSharedPreferences("DataTittle", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putString(DT_ID, dt.getID());
        e.putString(DT_NAME, dt.getDataName());

        e.commit();
    }

    public void setDataTittle(String dt){
        sp = c.getSharedPreferences("DataTittle", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putString(DT_ID, dt);

        e.commit();
    }

    public void setAccountSetting(){
        sp = c.getSharedPreferences("AccountSetting", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.commit();
    }

    public void setAlarmSetting(String alarm, String remind, String sound){
        sp = c.getSharedPreferences("AlarmSetting", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putString(SETTING_ALARM, alarm);
        e.putString(SETTING_REMIND, remind);
        e.putString(SETTING_SOUND, sound);

        e.commit();
    }

    public void setGeneralSetting(String size){
        sp = c.getSharedPreferences("GeneralSetting", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putString(SETTING_SIZE, size);

        e.commit();
    }

    public void setAvailable(Data.Available da){
        sp = c.getSharedPreferences("Available", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putInt(AV_ID, da.getID());
        e.putString(AV_START, da.getStart());
        e.putString(AV_UNTIL, da.getUntil());

        e.commit();
    }

    public void setRatting(Ratting r){
        sp = c.getSharedPreferences("Ratting", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putInt(RATTING_ID, r.getID());
        e.putString(RATTING_FROM, r.getFrom());
        e.putString(RATTING_TO, r.getTo());
        e.putFloat(RATTING_TIME_MANAGEMENT, r.getTimeManagement());
        e.putFloat(RATTING_INITIATIVE, r.getInitiative());
        e.putFloat(RATTING_RESPONSIBLE, r.getResponsible());
        e.putString(RATTING_DATE_CREATED, r.getDateCreated());
        e.putString(RATTING_COMMENT, r.getComment());

        e.commit();
    }

    public void setFaculty(Faculty f){
        sp = c.getSharedPreferences("Faculty", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putInt(F_ID, f.getID());
        e.putString(F_NAME, f.getFacultyName());
        e.putString(F_NOTE, f.getNote());

        e.commit();
    }

    public void setDegree(Degree d){
        sp = c.getSharedPreferences("Degree", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putInt(D_ID, d.getID());
        e.putString(D_NAME, d.getDegreeName());

        e.commit();
    }

    public void setBranch(Branch b){
        sp = c.getSharedPreferences("Branch", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putInt(B_ID, b.getID());
        e.putString(B_NAME, b.getBranchName());
        e.putString(B_CODE, b.getCode());

        e.commit();
    }

    public void setProduct(Product p){
        sp = c.getSharedPreferences("Product", Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putInt(P_ID, p.getID());
        e.putString(P_BRANCH, p.getBranchName());
        e.putString(P_FACULTY, p.getFacultyName());
        e.putString(P_DEGREE, p.getDegreeName());
        e.putString(P_REQUIREMENT, p.getNote());

        e.commit();
    }

    public HashMap<String,String> getLogin(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("UserLogin", Context.MODE_PRIVATE);

        data.put(NO_PRM_LOGIN, sp.getString(NO_PRM_LOGIN, null));
        data.put(TP_NAME_LOGIN, sp.getString(TP_NAME_LOGIN, null));
        data.put(GENDER_LOGIN, sp.getString(GENDER_LOGIN, null));
        data.put(SCORE_LOGIN, String.valueOf(sp.getInt(SCORE_LOGIN, 0)));
        data.put(PASSWORD_LOGIN, sp.getString(PASSWORD_LOGIN, null));
        data.put(EMAIL_LOGIN, sp.getString(EMAIL_LOGIN, null));
        data.put(ADDRESS_LOGIN, sp.getString(ADDRESS_LOGIN, null));
        data.put(PHONE_NUMBER_LOGIN, sp.getString(PHONE_NUMBER_LOGIN, null));
        data.put(WORK_NUMBER_LOGIN, sp.getString(WORK_NUMBER_LOGIN, null));
        data.put(ID_DEGREE_LOGIN, String.valueOf(sp.getInt(ID_DEGREE_LOGIN, 0)));
        data.put(JOIN_DATE_LOGIN, sp.getString(JOIN_DATE_LOGIN, null));
        data.put(BRANCH_LOGIN, String.valueOf(sp.getInt(BRANCH_LOGIN, 0)));
        data.put(BIRTHDAY_LOGIN, sp.getString(BIRTHDAY_LOGIN, null));
        data.put(PICTURE_LOGIN, sp.getString(PICTURE_LOGIN, null));
        data.put(BACKGROUND_LOGIN, sp.getString(BACKGROUND_LOGIN, null));

        return data;
    }

    public HashMap<String,String> getTPList(){
        HashMap<String, String> data = new HashMap<>();
        sp = c.getSharedPreferences("Home", Context.MODE_PRIVATE); //0 means private

        data.put(NO_PRM, sp.getString(NO_PRM, null));
        data.put(TP_NAME, sp.getString(TP_NAME, null));
        data.put(GENDER, sp.getString(GENDER, null));
        data.put(SCORE, String.valueOf(sp.getInt(SCORE, 0)));
        data.put(PASSWORD,sp.getString(PASSWORD, null));
        data.put(EMAIL, sp.getString(EMAIL, null));
        data.put(ADDRESS, sp.getString(ADDRESS, null));
        data.put(PHONE_NUMBER, sp.getString(PHONE_NUMBER, null));
        data.put(WORK_NUMBER, sp.getString(WORK_NUMBER, null));
        data.put(DEGREE, sp.getString(DEGREE, null));
        data.put(JOIN_DATE, sp.getString(JOIN_DATE, null));
        data.put(BRANCH, String.valueOf(sp.getInt(BRANCH, 0)));
        data.put(BIRTHDAY, sp.getString(BIRTHDAY, null));
        data.put(PICTURE, sp.getString(PICTURE, null));
        data.put(BACKGROUND, sp.getString(BACKGROUND, null));

        return data;
    }

    public HashMap<String,String> getAbsence(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("Absence", Context.MODE_PRIVATE); // 0 - for private mode

        data.put(AB_ID, String.valueOf(sp.getInt(AB_ID, 0)));
        data.put(AB_CLOCK_IN, sp.getString(AB_CLOCK_IN, null));
        data.put(AB_CLOCK_OUT, sp.getString(AB_CLOCK_OUT, null));
        data.put(AB_DESCRIPTION, sp.getString(AB_DESCRIPTION, null));

        return data;
    }

    public HashMap<String,String> getEvent(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("Event", Context.MODE_PRIVATE); //0 means private

        data.put(E_ID, String.valueOf(sp.getInt(E_ID, 0)));
        data.put(E_NAME, sp.getString(E_NAME,null));
        data.put(E_ADDRESS, sp.getString(E_ADDRESS, null));
        data.put(E_START, sp.getString(E_START, null));
        data.put(E_END, sp.getString(E_END, null));
        data.put(E_TP, String.valueOf(sp.getInt(E_TP, 0)));
        data.put(E_NOTE, sp.getString(E_NOTE, null));

        return data;
    }

    public HashMap<String,String> getCandidateStudent(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("CandidateStudent", Context.MODE_PRIVATE); //0 means private

        data.put(CS_ID, sp.getString(CS_ID,null));
        data.put(CS_NAME, sp.getString(CS_NAME,null));
        data.put(CS_EMAIL, sp.getString(CS_EMAIL, null));
        data.put(CS_ADDRESS, sp.getString(CS_ADDRESS, null));
        data.put(CS_PHONE_NUMBER, sp.getString(CS_PHONE_NUMBER, null));
        data.put(CS_WORK_NUMBER,sp.getString(CS_WORK_NUMBER, null));
        data.put(CS_COMPANY, sp.getString(CS_COMPANY,null));
        data.put(CS_ALUMNI, sp.getString(CS_ALUMNI, null));
        data.put(CS_DEGREE, sp.getString(CS_DEGREE, null));
        data.put(CS_DATA_TITTLE, sp.getString(CS_DATA_TITTLE, null));
        data.put(CS_DATE_FOLLOW_UP, sp.getString(CS_DATE_FOLLOW_UP, null));
        data.put(CS_TP_NAME, sp.getString(CS_TP_NAME, null));
        data.put(CS_RESULT,sp.getString(CS_RESULT, null));
        data.put(CS_NOTE,sp.getString(CS_NOTE, null));

        return data;
    }

    public HashMap<String,String> getSupport(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("Support", Context.MODE_PRIVATE); // 0 - for private mode

        data.put(SP_ID, String.valueOf(sp.getInt(SP_ID, 0)));
        data.put(SP_FROM, sp.getString(SP_FROM, null));
        data.put(SP_UNTIL,sp.getString(SP_UNTIL, null));
        data.put(SP_DESCRIPTION,sp.getString(SP_DESCRIPTION, null));

        return data;
    }

    public HashMap<String,String> getDataTittle(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("DataTittle", Context.MODE_PRIVATE); //0 means private

        data.put(DT_ID, sp.getString(DT_ID,null));

        return data;
    }

    public HashMap<String,String> getAccountSetting(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("AccountSetting", Context.MODE_PRIVATE); //0 means private

        return data;
    }

    public HashMap<String,String> getAlarmSetting(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("AlarmSetting", Context.MODE_PRIVATE); //0 means private

        data.put(SETTING_ALARM, sp.getString(SETTING_ALARM,null));
        data.put(SETTING_REMIND, sp.getString(SETTING_REMIND,null));
        data.put(SETTING_SOUND, sp.getString(SETTING_SOUND,null));

        return data;
    }

    public HashMap<String,String> getGeneralSetting(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("GeneralSetting", Context.MODE_PRIVATE); //0 means private

        data.put(SETTING_SIZE, sp.getString(SETTING_SIZE, null));

        return data;
    }

    public HashMap<String,String> getAvailable(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("Available", Context.MODE_PRIVATE); //0 means private

        data.put(AV_ID, String.valueOf(sp.getInt(AV_ID, 0)));
        data.put(AV_START, sp.getString(AV_START, null));
        data.put(AV_UNTIL, sp.getString(AV_UNTIL, null));

        return data;
    }

    public HashMap<String,String> getRatting(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("Ratting", Context.MODE_PRIVATE); //0 means private

        data.put(RATTING_ID, String.valueOf(sp.getInt(RATTING_ID, 0)));
        data.put(RATTING_FROM, sp.getString(RATTING_FROM, null));
        data.put(RATTING_TO, sp.getString(RATTING_TO, null));
        data.put(RATTING_TIME_MANAGEMENT, String.valueOf(sp.getFloat(RATTING_TIME_MANAGEMENT, 0)));
        data.put(RATTING_INITIATIVE, String.valueOf(sp.getFloat(RATTING_INITIATIVE, 0)));
        data.put(RATTING_RESPONSIBLE, String.valueOf(sp.getFloat(RATTING_RESPONSIBLE, 0)));
        data.put(RATTING_DATE_CREATED, sp.getString(RATTING_DATE_CREATED, null));
        data.put(RATTING_COMMENT, sp.getString(RATTING_COMMENT, null));

        return data;
    }

    public HashMap<String,String> getFaculty(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("Faculty", Context.MODE_PRIVATE); //0 means private

        data.put(F_ID,String.valueOf(sp.getInt(F_ID, 0)));
        data.put(F_NAME, sp.getString(F_NAME, null));
        data.put(F_NOTE, sp.getString(F_NOTE, null));

        return data;
    }

    public HashMap<String,String> getDegree(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("Degree", Context.MODE_PRIVATE); //0 means private

        data.put(D_ID,String.valueOf(sp.getInt(D_ID, 0)));
        data.put(D_NAME, sp.getString(D_NAME, null));

        return data;
    }

    public HashMap<String,String> getBranch(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("Branch", Context.MODE_PRIVATE); //0 means private

        data.put(B_ID,String.valueOf(sp.getInt(B_ID,0)));
        data.put(B_NAME, sp.getString(B_NAME, null));
        data.put(B_CODE, sp.getString(B_CODE, null));

        return data;
    }

    public HashMap<String,String> getProduct(){
        HashMap<String,String> data = new HashMap<>();
        sp = c.getSharedPreferences("Product", Context.MODE_PRIVATE); //0 means private

        data.put(P_ID,String.valueOf(sp.getInt(P_ID,0)));
        data.put(P_BRANCH, sp.getString(B_NAME, null));
        data.put(P_FACULTY, sp.getString(P_FACULTY, null));
        data.put(P_DEGREE, sp.getString(P_DEGREE, null));
        data.put(P_REQUIREMENT, sp.getString(P_REQUIREMENT, null));

        return data;
    }

    public void logoutUser(){
        sp = c.getSharedPreferences("UserLogin", Context.MODE_PRIVATE); // 0 - for private mode
        e = sp.edit();

        e.clear();
        e.commit();
    }

    public boolean getStatusLogin(){
        sp = c.getSharedPreferences("UserLogin", Context.MODE_PRIVATE); //0 means private
        return sp.getBoolean(IS_LOGIN, false);
    }

}
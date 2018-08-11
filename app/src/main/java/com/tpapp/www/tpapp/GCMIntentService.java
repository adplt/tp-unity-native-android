package com.tpapp.www.tpapp;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;
import java.util.HashMap;

public class GCMIntentService extends IntentService{

    private Session sm = new Session(getApplication());
    private HashMap<String,String> user;
    public static final String REGISTRATION_SUCCESS = "RegistrationSuccess";
    public static final String REGISTRATION_FAILED = "RegistrationFailed";

    public GCMIntentService(){
        super("");

    }

    @Override
    public void onHandleIntent(Intent intent){
        InstanceID instanceID = InstanceID.getInstance(this);
        user = sm.getLogin();
        Intent i;

        try{
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.e("GCMIntentService","token: " + token);
            i = new Intent(REGISTRATION_SUCCESS);
            i.putExtra("Token",token);
            new MainClass("GCM.php?id=" + user.get(Session.NO_PRM_LOGIN) + "&gcm_id=" + token).executeURL();
        }catch(IOException e){
            i = new Intent(REGISTRATION_FAILED);
        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }

}
package com.tpapp.www.tpapp;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

public class GCMIDListenerService extends InstanceIDListenerService{

    @Override
    public void onTokenRefresh(){
        Intent intent = new Intent(this, GCMIntentService.class);
        startService(intent);
    }
}

package com.tpapp.www.tpapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent){
        String get_string = intent.getExtras().getString("Extra");

        Log.e("onReceive_Key",get_string);
        Intent i = new Intent(context,RingtonePlayingService.class);
        i.putExtra("Extra",get_string);
        context.startActivity(i);
    }

}

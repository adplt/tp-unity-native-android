package com.tpapp.www.tpapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class RingtonePlayingService extends Service{

    private MediaPlayer mp;
    private boolean flag;

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.e("onStartCommand_Alarm", "Start id:" + startId + " Intent: " + intent);

        String state = intent.getExtras().getString("Extra");

        Log.e("onStartCommand_Ringtone state extra: ",state);

        if(state == "on" || state.equals("on")){
            startId = 1;
        }else{
            startId = 0;
        }

        if(flag == false && startId == 1){
            Log.e("flag == false","alarm on");

            doNotification();

            mp = MediaPlayer.create(this,R.raw.music);
            mp.start();

            flag = true;
            startId = 0;
        }else if(flag == true && startId == 0){
            Log.e("flag == true","alarm off");

            mp.stop();
            mp.reset();
            flag = false;
            startId = 0;
        }else if(flag == false && startId == 0){
            Log.e("flag == false","alarm off");

            flag = false;
            startId = 0;
        }else if(flag == true && startId == 1){
            Log.e("flag == true","alarm on");

            flag = true;
            startId = 1;
        }else{

        }

        return START_NOT_STICKY;
    }

    public void doNotification(){
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.logo_1);

        NotificationCompat.Builder notif = new NotificationCompat.Builder(getApplicationContext());
        notif.setSmallIcon(R.drawable.ic_logo_48dp);
        notif.setLargeIcon(b);
        notif.setContentTitle("Event Info");
        notif.setContentText("New stand expo here. Let's check out !");
        notif.setNumber(1);
        notif.setAutoCancel(true);

        TaskStackBuilder tsb = TaskStackBuilder.create(getApplicationContext());
        tsb.addParentStack(Event.class);
        tsb.addNextIntent(new Intent(getApplicationContext(), Event.class));

        notif.setContentIntent(tsb.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT));
        notif.setShowWhen(true);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, notif.build());
    }

}

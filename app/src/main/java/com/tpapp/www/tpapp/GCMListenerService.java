package com.tpapp.www.tpapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;

public class GCMListenerService extends GcmListenerService{

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        doNotification(message);
    }

    public void doNotification(String message){
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.logo_1);

        NotificationCompat.Builder notif = new NotificationCompat.Builder(getApplicationContext());
        notif.setSmallIcon(R.drawable.ic_logo_48dp);
        notif.setLargeIcon(b);
        notif.setContentTitle("Event Info");
        notif.setContentText(message);
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

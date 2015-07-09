package com.or_oz.posture;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by oroz7_000 on 6/18/2015.
 */
public class AlertReceiver extends BroadcastReceiver {
    // Called when a broadcast is made targeting this class
    @Override
    public void onReceive(Context context, Intent intent) {
        //create instance of an alarm
        Alarm alarm = new Alarm();
        Log.d("AlertReceiever", "Regular notif");
        //send the notification
        alarm.sendNotif(context);

    }


}

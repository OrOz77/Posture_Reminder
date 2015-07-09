package com.or_oz.posture;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.GregorianCalendar;

/**
 * Created by oroz7_000 on 6/22/2015.m
 */
//called when device boots up. used for maintaining scheduled alarm on reboot
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        boolean notif = preferences.getBoolean("pref_notif_on", true);
        boolean smartNotif = preferences.getBoolean("pref_smartNotif_on", false);

        Alarm alarm = new Alarm();

        //checks if any notifications were enabled
        if(notif){
            alarm.setAlarm(context);
        }
       if(smartNotif){
           alarm.setSmartAlarm(context);
       }

        Log.d("OR", "BOOT Recieved");


    }
}

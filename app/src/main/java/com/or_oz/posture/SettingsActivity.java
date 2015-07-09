package com.or_oz.posture;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

/**
 * Created by oroz7_000 on 6/20/2015.
 */
public class SettingsActivity extends ActionBarActivity {
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //settings made into fragment to support action bar
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment()).commit();
    }

    @Override
    public void onPause(){
        //set the alarms onPause as this state is always reached when app is exited
        super.onPause();
        Alarm alarm = new Alarm();
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        boolean notif = preferences.getBoolean("pref_notif_on", true);
        boolean smartNotif = preferences.getBoolean("pref_smartNotif_on", false);

        //checks settings to see which alarms to set/cancel
        if (notif){
            alarm.setAlarm(this);
        }
        else{
            alarm.cancelAlarm(this);
        }

        if(smartNotif){
            alarm.setSmartAlarm(this);
        }
        else {
            alarm.cancelSmartAlarm(this);
        }


    }



}


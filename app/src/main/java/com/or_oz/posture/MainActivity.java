package com.or_oz.posture;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;






public class MainActivity extends ActionBarActivity {

    // Allows us to notify the user that something happened in the background
    NotificationManager notificationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //image view that displays app logo on main screen
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.app_logo_small);

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        boolean notif = preferences.getBoolean("pref_notif_on", true);
        //shows toast oncreate if alarm not set
        if(!notif){
            Toast.makeText(this, "Notification Alerts Disabled", Toast.LENGTH_SHORT).show();
        }




    }

    //called when test notification is pressed
    public void showNotification() {

        Alarm alarm = new Alarm();
        alarm.sendNotif(this);



    }

    //launches settings activity when floating action button pressed
    public void fabSettings(View view){
        Log.i("PRESSED", "FAB");
        Intent intent = new Intent(getApplicationContext(),
                SettingsActivity.class);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,
                    SettingsActivity.class);
            startActivity(intent);
            Log.i("test", "settings pressed");
            return true;
        }
        if (id == R.id.test_notif){
           showNotification();
            Log.i("test", "notif pressed");
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


}

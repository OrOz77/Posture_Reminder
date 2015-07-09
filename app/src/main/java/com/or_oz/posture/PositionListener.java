package com.or_oz.posture;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by oroz7_000 on 6/27/2015.
 */
public class PositionListener extends Service implements SensorEventListener {
  //  private View view;
    private SensorManager sensorManager;
    Sensor mSensor;
    private TextView text;
    private Alarm mAlarm;
    private Handler handler;
    private boolean badPosture;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //uses power manager to check screen on/off
        PowerManager pManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
        if(Build.VERSION.SDK_INT >=20){
            //isInteractive for newer OS
            if(pManager.isInteractive()){
                mAlarm = new Alarm();
                badPosture = false;

                //creates sensor listener for accelerometer
                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sensorManager.registerListener(this, mSensor,
                        SensorManager.SENSOR_DELAY_NORMAL);

                Log.d("PosList", "SCREEN ON");
            }
            else {
                Log.d("PosList", "SCREEN OFF");
            }
        }
        else{
            //isScreenOn for older OS
            if(pManager.isScreenOn()){
                mAlarm = new Alarm();
                badPosture = false;

                //creates sensor listener for accelerometer
                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sensorManager.registerListener(this, mSensor,
                        SensorManager.SENSOR_DELAY_NORMAL);

                Log.d("PosList", "SCREEN ON");
            }
            else{
                Log.d("PosList", "SCREEN OFF");
            }

        }


     //this old code was used to display the phone angle on screen
     //   view = findViewById(R.id.aboutView);
      //  view.setBackgroundColor(Color.WHITE);


      //  LinearLayout lView = new LinearLayout(this);


      //  lView.setGravity(View.TEXT_ALIGNMENT_CENTER);

     //   text = new TextView(this);

      //  lView.addView(text);
      //  setContentView(lView);
       return START_STICKY;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        //called when phone angle changes
        badPosture = false;
        getAccelerometer(event);
        sensorManager.unregisterListener(this);
        stopSelf();
    }

    private void getAccelerometer(SensorEvent event){
        int inclination = getInc(event);
//        text.setText("Angle is: " + inclination);

        if (inclination < 35 || inclination > 145)
        {
            // device is flat
            Log.d("ACCEL", "DEVICE FLAT");
          //  text.setBackgroundColor(Color.RED);

            badPosture = true;
            mAlarm.sendNotif(this);



        }
        else
        {
            // device is not flat between 35 and 145 degrees
              Log.d("ACCEL", "DEVICE NOT FLAT");
            badPosture = false;
         //   text.setBackgroundColor(Color.GREEN);


        }



    }

    private int getInc(SensorEvent event){
        //get x,y,z phone position values
        float[] g = new float[3];
        g = event.values.clone();

        //formula for norm of g
        double norm_Of_g = Math.sqrt(g[0] * g[0] + g[1] * g[1] + g[2] * g[2]);


        g[0] = (float) (g[0] / norm_Of_g);
        g[1] = (float) (g[1] / norm_Of_g);
        g[2] = (float) (g[2] / norm_Of_g);

        //finding inclination angle, the angle between the phone and y axis (the ground)
        int inclination = (int) Math.round(Math.toDegrees(Math.acos(g[2])));
        return inclination;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

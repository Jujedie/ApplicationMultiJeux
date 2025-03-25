package com.example.applicationmultijeux.suivi;

import android.content.Intent;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.Sensor;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmultijeux.MainActivity;
import com.example.applicationmultijeux.R;

public class SuiviActivity extends AppCompatActivity implements SensorEventListener
{
    private static final int TIMER_TEMPS = 90;

    private Intent intent;
    private Dessin dessin;
    private int points;
    private SensorManager sensorManager;
    private Sensor accelerometre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivi);

        this.points = 100;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        TextView timer = findViewById(R.id.Timer);
        timer.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, (int) (screenHeight * 0.05)));


        this.intent = getIntent();
        String forme = intent.getStringExtra("forme");
        String niveau = intent.getStringExtra("difficulte");

        this.dessin = findViewById(R.id.Dessin);
        this.dessin.setForme(forme);
        this.dessin.setNiveau(niveau);
        this.dessin.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, (int) (screenHeight * 0.8)));

        sensorManager   = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (accelerometre != null)
        {
            sensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_FASTEST);
        }

        Timer timerGame = new Timer(SuiviActivity.TIMER_TEMPS, this);
        timerGame.start();
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            float[] values = event.values;

            this.dessin.ajouterLigne(values[0],values[1]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    @Override
    protected void onPause() {
        super.onPause();
        this.sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void finirPartie() {
        int malus = this.dessin.getPointMalus();
        this.points += malus;

        Intent resultIntent = new Intent(SuiviActivity.this,MainActivity.class);
        resultIntent.putExtra("ScoreSuivi", this.points);

        setResult(MainActivity.RESULT_OK, resultIntent);
        finish();
    }
}
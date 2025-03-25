package com.example.applicationmultijeux.suivi;

import static com.example.applicationmultijeux.R.*;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.os.Bundle;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.applicationmultijeux.MainActivity;
import com.example.applicationmultijeux.R;


public class SuiviActivity extends AppCompatActivity implements SensorEventListener {
    private Intent        intent;

    private Dessin        dessin;
    private Sensor        gyroscopeSensor;
    private SensorManager sensorManager;
    private int           points;

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
        timer.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, (int) (screenHeight * 0.2)));

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscopeSensor != null) {
            sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        this.intent = getIntent();
        String forme = intent.getStringExtra("forme");
        String niveau = intent.getStringExtra("difficulte");

        this.dessin = (Dessin) findViewById(id.Dessin);
        this.dessin.setForme(forme);
        this.dessin.setNiveau(niveau);
        this.dessin.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, (int) (screenHeight * 0.8)));

        Timer timerGame = new Timer(60, this);
        timerGame.start();
        Log.d("SuiviActivity", "onCreate: Fin de onCreate");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float deltaX = event.values[0];
            float deltaY = event.values[1];

            //this.dessin.ajouterLigne(deltaX,deltaY);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onDeleted() {}

    public void finirPartie()
    {
        int malus = this.dessin.getPointMalus();
        this.points += malus;

        Intent resultIntent = new Intent();
        resultIntent.putExtra("Score", this.points);

        setResult(MainActivity.RESULT_OK, resultIntent);
        finish();
    }
}
package com.example.applicationmultijeux.suivi;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import com.example.applicationmultijeux.R;


public class SuiviActivity extends Activity implements SensorEventListener {
    private Intent        intent;

    private Dessin        dessin;
    private Sensor        gyroscopeSensor;
    private SensorManager sensorManager;
    private Timer         timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivi);

        TextView timer = findViewById(R.id.Timer);

        sensorManager   = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        this.intent   = getIntent();
        String forme  = intent.getStringExtra("forme");
        String niveau = intent.getStringExtra("niveau");

        this.dessin = findViewById(R.id.Dessin);
        this.dessin.setForme(forme);
        this.dessin.setNiveau(niveau);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float deltaX = event.values[0];
            float deltaY = event.values[1];
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
}
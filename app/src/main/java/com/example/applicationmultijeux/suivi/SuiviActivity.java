package com.example.applicationmultijeux.suivi;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.OrientationEventListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmultijeux.MainActivity;
import com.example.applicationmultijeux.R;

public class SuiviActivity extends AppCompatActivity
{
    private Intent intent;
    private Dessin dessin;
    private int orientation;
    private int points;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivi);

        this.points = 100;
        this.orientation = 1;

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

        Timer timerGame = new Timer(60, this);
        timerGame.start();

        OrientationEventListener mOrientationListener = new OrientationEventListener(getApplicationContext())
        {
            @Override
            public void onOrientationChanged(int orientation)
            {
                if (orientation != ORIENTATION_UNKNOWN) {
                    SuiviActivity.this.orientation = orientation;
                }
            }
        };

        if (mOrientationListener.canDetectOrientation()) {
            mOrientationListener.enable();
        }


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                dessin.ajouterLigne(orientation);
                handler.postDelayed(this, 55);
            }
        };
        handler.post(runnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(runnable);
    }

    public void finirPartie() {
        int malus = this.dessin.getPointMalus();
        this.points += malus;
        Log.d("RESULTAT", "Points malus : " + malus);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("Score", this.points);

        setResult(MainActivity.RESULT_OK, resultIntent);
        finish();
    }
}
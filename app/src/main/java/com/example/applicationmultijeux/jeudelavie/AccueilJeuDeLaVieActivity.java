package com.example.applicationmultijeux.jeudelavie;

import com.example.applicationmultijeux.R;

import android.app.Activity;
import android.os.Bundle;

public class AccueilJeuDeLaVieActivity extends Activity
{
    private boolean[][] tableauJeuVie;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
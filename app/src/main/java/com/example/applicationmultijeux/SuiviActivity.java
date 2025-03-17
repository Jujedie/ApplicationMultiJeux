package com.example.applicationmultijeux;

import android.app.Activity;
import android.os.Bundle;

public class SuiviActivity extends Activity
{
    protected boolean isInAcceuil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.isInAcceuil = true;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_suivi);
    }
}
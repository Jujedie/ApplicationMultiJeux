package com.example.applicationmultijeux.jeudelavie;

import com.example.applicationmultijeux.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AccueilJeuDeLaVieActivity extends Activity
{
    private boolean[][] tableauJeuVie;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_jeudelavie);

        Spinner spinner = (Spinner) findViewById(R.id.accueilJDLV3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
        (
            this,
            R.array.lstTaillesGrillesJDLV,
            android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
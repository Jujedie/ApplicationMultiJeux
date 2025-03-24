package com.example.applicationmultijeux.suivi;

import com.example.applicationmultijeux.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AccueilSuiviActivity extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_suivi);

        Spinner spinner = (Spinner) findViewById(R.id.accueilSuivi3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
        (
            this,
            R.array.listeForme,
            android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.accueilSuivi5);
        adapter = ArrayAdapter.createFromResource
        (
            this,
            R.array.listeNiveau,
            android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
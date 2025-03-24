package com.example.applicationmultijeux.suivi;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import com.example.applicationmultijeux.R;


public class SuiviActivity extends Activity
{
    private Dessin dessin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivi);

        // Récupérer les données de l'intent
        Intent intent = getIntent();
        String forme = intent.getStringExtra("forme");
        String niveau= intent.getStringExtra("niveau");

        this.dessin = findViewById(R.id.Dessin);
    }



    /*
    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_suivi);

        Spinner spinner = (Spinner) findViewById(R.id.SpinnerForme);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.listeForme,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.SpinnerNiveau);
        adapter = ArrayAdapter.createFromResource(
                this,
                R.array.listeNiveau,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
     */
}
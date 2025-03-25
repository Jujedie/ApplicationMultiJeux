package com.example.applicationmultijeux.jeudelavie;

import com.example.applicationmultijeux.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        lancerJeuDeLaVie();
    }

    public void lancerJeuDeLaVie()
    {
        Button btnLancer = findViewById(R.id.accueilJDLV6);

        btnLancer.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(AccueilJeuDeLaVieActivity.this, JeuDeLaVieActivity.class);
                startActivity(intent);
            }
        });
    }
}
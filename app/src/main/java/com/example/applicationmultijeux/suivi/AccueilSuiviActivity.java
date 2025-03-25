package com.example.applicationmultijeux.suivi;

import com.example.applicationmultijeux.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        lancerSuivi();
    }

    public void lancerSuivi()
    {
        Button btnLancer = findViewById(R.id.accueilSuivi6);

        btnLancer.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(AccueilSuiviActivity.this, SuiviActivity.class);
                startActivity(intent);
            }
        });
    }
}
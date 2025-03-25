package com.example.applicationmultijeux.jeudelavie;

import com.example.applicationmultijeux.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AccueilJeuDeLaVieActivity extends AppCompatActivity
{
    private Spinner spinnerTailleGrille;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_jeudelavie);

        spinnerTailleGrille = (Spinner) findViewById(R.id.accueilJDLV3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
        (
            this,
            R.array.lstTaillesGrillesJDLV,
            android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTailleGrille.setAdapter(adapter);

        lancerJeuDeLaVie();
    }

    public void lancerJeuDeLaVie()
    {
        Button   btnLancer  = findViewById(R.id.accueilJDLV6);
        EditText edtDensite = (EditText) findViewById(R.id.accueilJDLV5);

        btnLancer.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(AccueilJeuDeLaVieActivity.this, JeuDeLaVieActivity.class);
                intent.putExtra("TailleGrille", (String) spinnerTailleGrille.getSelectedItem());
                intent.putExtra("DensiteCellulesVivantes", edtDensite.getText());
                startActivity(intent);
            }
        });
    }
}
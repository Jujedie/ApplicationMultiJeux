package com.example.applicationmultijeux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.widget.ArrayAdapter;
import android.widget.Spinner;
=======
import android.view.View;
import android.widget.Button;
>>>>>>> a2078e0241a7c6ffd931251c9cb252a70286e549

public class MainActivity extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void accueilTicTacToe()
    {
        Button btnAccueilTicTacToe = findViewById(R.id.tv2);

        btnAccueilTicTacToe.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, TicTacToeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void accueilJeuDeLaVie()
    {
        Button btnAccueilJeuDeLaVie = findViewById(R.id.tv3);

        btnAccueilJeuDeLaVie.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, JeuDeLaVieActivity.class);
                startActivity(intent);
            }
        });
    }

    public void accueilSuivi(Bundle savedInstanceState)
    {
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
    }

    public void quitterApplication()
    {
        this.finish();
    }
}

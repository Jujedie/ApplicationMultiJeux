package com.example.applicationmultijeux;

import com.example.applicationmultijeux.jeudelavie.AccueilJeuDeLaVieActivity;
import com.example.applicationmultijeux.suivi.AccueilSuiviActivity;
import com.example.applicationmultijeux.tictactoe.AccueilTicTacToeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accueilTicTacToe();
        accueilJeuDeLaVie();
        accueilSuivi();
        quitterApplication();
    }

    public void accueilTicTacToe()
    {
        Button btnAccueilTicTacToe = findViewById(R.id.main2);

        btnAccueilTicTacToe.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, AccueilTicTacToeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void accueilJeuDeLaVie()
    {
        Button btnAccueilJeuDeLaVie = findViewById(R.id.main3);

        btnAccueilJeuDeLaVie.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, AccueilJeuDeLaVieActivity.class);
                startActivity(intent);
            }
        });
    }

    public void accueilSuivi()
    {
        Button btnAccueilSuivi = findViewById(R.id.main4);

        btnAccueilSuivi.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, AccueilSuiviActivity.class);
                startActivity(intent);
            }
        });
    }

    public void quitterApplication()
    {
        Button btnQuitter = findViewById(R.id.main5);

        btnQuitter.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                finish();
            }
        });
    }
}
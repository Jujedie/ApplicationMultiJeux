package com.example.applicationmultijeux;

import com.example.applicationmultijeux.jeudelavie.AccueilJeuDeLaVieActivity;
import com.example.applicationmultijeux.suivi.AccueilSuiviActivity;
import com.example.applicationmultijeux.tictactoe.AccueilTicTacToeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK)
        {
            if (data != null && data.hasExtra("ScoreTicTacToe"))
            {
                int score = data.getIntExtra("ScoreTicTacToe", 0);
                TextView txtScore = findViewById(R.id.main6);
                txtScore.setText("Jeu du TicTacToe : " + String.valueOf(score) + "a gagné");
            }
            if (data != null && data.hasExtra("ScoreJDLV"))
            {
                int score = data.getIntExtra("ScoreJDLV", 0);
                TextView txtScore = findViewById(R.id.main6);
                txtScore.setText("Jeu de la vie : " + String.valueOf(score) + " itération(s)");
            }
            if (data != null && data.hasExtra("ScoreSuivi"))
            {
                int score = data.getIntExtra("ScoreSuivi", 0);
                TextView txtScore = findViewById(R.id.main6);
                txtScore.setText("Jeu du suivi : " + String.valueOf(score) + "%");
            }
        }
    }

    public void accueilTicTacToe()
    {
        Button btnAccueilTicTacToe = findViewById(R.id.main2);

        btnAccueilTicTacToe.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, AccueilTicTacToeActivity.class);
                startActivityForResult(intent, 2);
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
                startActivityForResult(intent, 2);
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
                startActivityForResult(intent, 2);
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
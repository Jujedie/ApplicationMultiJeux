package com.example.applicationmultijeux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public void accueilSuivi()
    {

    }

    public void quitterApplication()
    {
        this.finish();
    }
}

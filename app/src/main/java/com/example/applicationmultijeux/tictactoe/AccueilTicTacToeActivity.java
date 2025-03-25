package com.example.applicationmultijeux.tictactoe;

import com.example.applicationmultijeux.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AccueilTicTacToeActivity extends AppCompatActivity
{
    private Spinner spinnerTailleGrille;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_tictactoe);

        spinnerTailleGrille = (Spinner) findViewById(R.id.accueilTicTacToe5);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
        (
            this,
            R.array.lstTaillesGrillesTicTacToe,
            android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTailleGrille.setAdapter(adapter);

        lancerTicTacToe();
    }

    public void lancerTicTacToe()
    {
        Button btnSolo = findViewById(R.id.accueilTicTacToe2);
        Button btn1contre1 = findViewById(R.id.accueilTicTacToe3);

        btnSolo.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(AccueilTicTacToeActivity.this, TicTacToeActivity.class);
                intent.putExtra("Solo", "Solo");
                intent.putExtra("TailleGrille", (String) spinnerTailleGrille.getSelectedItem());
                startActivity(intent);
            }
        });

        btn1contre1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(AccueilTicTacToeActivity.this, TicTacToeActivity.class);
                intent.putExtra("1contre1", "1contre1");
                intent.putExtra("TailleGrille", (String) spinnerTailleGrille.getSelectedItem());
                startActivity(intent);
            }
        });
    }
}
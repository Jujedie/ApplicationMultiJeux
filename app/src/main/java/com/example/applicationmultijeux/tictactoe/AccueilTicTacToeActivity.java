package com.example.applicationmultijeux.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.applicationmultijeux.R;

public class AccueilTicTacToeActivity extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_tictactoe);

        Spinner spinner = (Spinner) findViewById(R.id.accueilTicTacToe5);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
        (
            this,
            R.array.lstTaillesGrillesTicTacToe,
            android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
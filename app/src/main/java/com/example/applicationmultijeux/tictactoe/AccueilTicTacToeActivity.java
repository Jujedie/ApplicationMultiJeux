package com.example.applicationmultijeux.tictactoe;

import com.example.applicationmultijeux.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class AccueilTicTacToeActivity extends AppCompatActivity
{
    private Spinner spinnerTailleGrille;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_tictactoe);

        spinnerTailleGrille();
        lancerTicTacToe();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            if (data != null && data.hasExtra("ScoreTicTacToe"))
            {
                int score = data.getIntExtra("ScoreTicTacToe", 0);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("ScoreTicTacToe", score);
                setResult(RESULT_OK, returnIntent);

                finish();
            }
        }
    }

    public void spinnerTailleGrille()
    {
        spinnerTailleGrille = (Spinner) findViewById(R.id.accueilTicTacToe5);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
        (
            this,
            R.array.lstTaillesGrillesTicTacToe,
            android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTailleGrille.setAdapter(adapter);

        spinnerTailleGrille.post(() ->
        {
            ((TextView)spinnerTailleGrille.getSelectedView()).setTextColor
            (
                ContextCompat.getColor(this, R.color.clair)
            );

            spinnerTailleGrille.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    if (view != null)
                    {
                        ((TextView) view).setTextColor
                        (
                            ContextCompat.getColor(AccueilTicTacToeActivity.this, R.color.clair)
                        );
                    }
                }

                public void onNothingSelected(AdapterView<?> parent) {}
            });
        });
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
                startActivityForResult(intent, 1);
            }
        });
    }
}
package com.example.applicationmultijeux.jeudelavie;

import com.example.applicationmultijeux.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class AccueilJeuDeLaVieActivity extends AppCompatActivity
{
    private Spinner spinnerTailleGrille;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_jeudelavie);

        spinnerTailleGrille();
        lancerJeuDeLaVie();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            if (data != null && data.hasExtra("ScoreJDLV"))
            {
                int score = data.getIntExtra("ScoreJDLV", 0);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("ScoreJDLV", score);
                setResult(RESULT_OK, returnIntent);

                finish();
            }
        }
    }

    public void spinnerTailleGrille()
    {
        spinnerTailleGrille = (Spinner) findViewById(R.id.accueilJDLV3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
        (
            this,
            R.array.lstTaillesGrillesJDLV,
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
                            ContextCompat.getColor(AccueilJeuDeLaVieActivity.this, R.color.clair)
                        );
                    }
                }

                public void onNothingSelected(AdapterView<?> parent) {}
            });
        });
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

                if (edtDensite.getText() == null || edtDensite.getText().equals(""))
                {
                    intent.putExtra("DensiteCellulesVivantes", "50");
                }
                else
                {
                    intent.putExtra("DensiteCellulesVivantes", edtDensite.getText().toString());
                }

                startActivityForResult(intent, 1);
            }
        });
    }
}
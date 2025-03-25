package com.example.applicationmultijeux.suivi;

import com.example.applicationmultijeux.R;
import com.example.applicationmultijeux.jeudelavie.AccueilJeuDeLaVieActivity;

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

public class AccueilSuiviActivity extends AppCompatActivity
{
    private Spinner spinnerForme;
    private Spinner spinnerDifficulte;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_suivi);

        spinnerForme = (Spinner) findViewById(R.id.accueilSuivi3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
        (
            this,
            R.array.listeForme,
            android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerForme.setAdapter(adapter);

        spinnerForme.post(() ->
        {
            ((TextView)spinnerForme.getSelectedView()).setTextColor
            (
                ContextCompat.getColor(this, R.color.clair)
            );

            spinnerForme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    if (view != null)
                    {
                        ((TextView) view).setTextColor(ContextCompat.getColor(AccueilSuiviActivity.this, R.color.clair)
                        );
                    }
                }

                public void onNothingSelected(AdapterView<?> parent) {}
            });
        });

        spinnerDifficulte = (Spinner) findViewById(R.id.accueilSuivi5);
        adapter = ArrayAdapter.createFromResource
        (
            this,
            R.array.listeNiveau,
            android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulte.setAdapter(adapter);

        spinnerDifficulte.post(() ->
        {
            ((TextView)spinnerDifficulte.getSelectedView()).setTextColor
            (
                ContextCompat.getColor(this, R.color.clair)
            );

            spinnerDifficulte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    if (view != null)
                    {
                        ((TextView) view).setTextColor
                        (
                            ContextCompat.getColor(AccueilSuiviActivity.this, R.color.clair)
                        );
                    }
                }

                public void onNothingSelected(AdapterView<?> parent) {}
            });
        });

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
                intent.putExtra("forme", (String) spinnerForme.getSelectedItem());
                intent.putExtra("difficulte", (String) spinnerDifficulte.getSelectedItem());
                startActivity(intent);
            }
        });
    }
}
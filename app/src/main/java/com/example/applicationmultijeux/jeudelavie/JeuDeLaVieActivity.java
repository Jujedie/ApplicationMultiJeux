package com.example.applicationmultijeux.jeudelavie;

import com.example.applicationmultijeux.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class JeuDeLaVieActivity extends Activity
{
    private Grille      grilleJeuVie;
    private boolean[][] tableauJeuVie;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeudelavie);

        View view = findViewById(R.id.view);



        boolean[][] avtDernierTab = new boolean[2][2];
        boolean[][] dernierTab    = new boolean[2][3];
        int     nbTabPareil   = 0;

        while (nbTabPareil < 1000)
        {
            grilleJeuVie.etatSuivant();
            Cellule[] tabCellule = (Cellule[]) grilleJeuVie.getSetCellule().toArray();
            for (int lig = 0; lig < tableauJeuVie.length; lig++)
            {
                for (int col = 0; col < tableauJeuVie.length; col++)
                {
                    tableauJeuVie[lig][col] = tabCellule[lig*9+col].getEtat();
                }
            }

            if (tableauJeuVie.equals(dernierTab) || tableauJeuVie.equals(avtDernierTab))
                nbTabPareil++;
            else
                nbTabPareil=0;

            avtDernierTab = dernierTab.clone();
            dernierTab  =   this.tableauJeuVie.clone();
        }


    }


}
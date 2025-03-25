package com.example.applicationmultijeux.jeudelavie;

import com.example.applicationmultijeux.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class JeuDeLaVieActivity extends AppCompatActivity
{
    private Grille      grilleJeuVie;
    private boolean[][] tableauJeuVie;
    private MaVue      dessin;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        dessin = new MaVue(this);

        Intent myIntent = getIntent();

        int taille = Integer.parseInt(myIntent.getStringExtra("TailleGrille").substring(0, 2));
        int proportion = Integer.parseInt((myIntent.getStringExtra("DensiteCellulesVivantes")));

        if (proportion > 100) proportion = 100;
        if (proportion != 0 ) proportion = proportion / 100;

        Log.d("TAG", proportion + "");

        grilleJeuVie = FabGrille.creation(taille, proportion);

        View view = findViewById(R.id.view);

        boolean[][] avtDernierTab = new boolean[2][2];
        boolean[][] dernierTab    = new boolean[2][3];
        int     nbTabPareil   = 0;

        while (nbTabPareil < 100)
        {
            grilleJeuVie.etatSuivant();
            Cellule[] tabCellule = new Cellule[grilleJeuVie.getSetCellule().size()];

            if (grilleJeuVie.getSetCellule().toArray(new Cellule[0]) instanceof Cellule[])
                tabCellule = (Cellule[]) grilleJeuVie.getSetCellule().toArray(new Cellule[0]); // null?

            if (grilleJeuVie == null)
            {
                throw new RuntimeException("grilleJeuVie est null !");
            }
            if (grilleJeuVie.getSetCellule() == null)
            {
                throw new RuntimeException("getSetCellule est null !");
            }

            if (grilleJeuVie.getSetCellule().toArray() == null)
            {
                throw new RuntimeException("toArray est null !");
            }


            int tailleRcnCarre = (int) Math.round(Math.sqrt(grilleJeuVie.getSetCellule().size()));
            tableauJeuVie = new boolean[tailleRcnCarre][tailleRcnCarre];
            for (int lig = 0; lig < tableauJeuVie.length; lig++)
            {
                for (int col = 0; col < tableauJeuVie.length; col++)
                {
                    tableauJeuVie[lig][col] = tabCellule[lig*tailleRcnCarre+col].getEtat();
                }
            }


            if (tableauJeuVie.equals(dernierTab) || tableauJeuVie.equals(avtDernierTab))
                nbTabPareil++;
            else
                nbTabPareil=0;

            avtDernierTab = dernierTab.clone();
            dernierTab  =   this.tableauJeuVie.clone();
        }


        setContentView(dessin);
    }

    class MaVue extends View
    {
        Paint dessin = new Paint();

        public MaVue(Context context)
        {
            super(context);
            setFocusable(true);
            dessin.setColor(Color.BLACK);
            dessin.setAntiAlias(false);
        }

        public void onDraw(Canvas canvas)
        {
            float taille = canvas.getWidth()/tableauJeuVie.length;
            for (int lig = 0; lig < tableauJeuVie.length; lig++)
            {
                for (int col = 0; col < tableauJeuVie.length; col++)
                {
                    canvas.drawRect(taille*col,
                                    taille*lig,
                                    taille*tableauJeuVie.length-col,
                                    taille*tableauJeuVie.length-lig,
                                    dessin);
                }
            }

            try {
                Thread.sleep(100);
            }
            catch(Exception ex) {ex.printStackTrace();}
            this.invalidate();
        }
    }


}
package com.example.applicationmultijeux.tictactoe;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class TicTacToeActivity extends Activity
{
    private VueMorpion vueMorpion;
    int joueur;
    int tailleGrille = 4;
    char[][] grille = new char[this.tailleGrille][this.tailleGrille];
    int nbCoups;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.joueur = 1;
        this.nbCoups = 0;
        for (int i = 0; i < this.tailleGrille; i++) {
            for (int j = 0; j < this.tailleGrille; j++) {
                grille[i][j] = ' ';

            }
        }

        setContentView(new VueMorpion(this, this.grille));
    }

    public void click(int lig, int col)
    {
        if (this.grille[lig][col] == ' ')
        {
            this.nbCoups++;
            if (this.joueur == 1)
            {
                this.grille[lig][col] = 'O';
                this.joueur = 2;
                this.verifVictoire('O', lig, col);

            }
            else if (this.joueur == 2)
            {
                this.grille[lig][col] = 'X';
                this.joueur = 1;
                this.verifVictoire('X', lig, col);
            }
        }
    }

    public boolean verifVictoire(char cara, int lig, int col)
    {
        boolean ligneGagne = true;
        for (int j = 0; j < this.tailleGrille; j++)
        {
            if (this.grille[lig][j] != cara)
            {
                ligneGagne = false;
                break;
            }
        }
        if (ligneGagne)
        {
            Log.d("TAG", "Joueur " + cara +" gagne");
            return true;
        }

        boolean colonneGagne = true;
        for (int i = 0; i < this.tailleGrille; i++)
        {
            if (this.grille[i][col] != cara)
            {
                colonneGagne = false;
                break;
            }
        }
        if (colonneGagne)
        {
            Log.d("TAG", "Joueur " + cara +" gagne");
            return true;
        }

        if (lig == col)
        {
            boolean diagonale1Gagne = true;
            for (int i = 0; i < this.tailleGrille; i++)
            {
                if (this.grille[i][i] != cara)
                {
                    diagonale1Gagne = false;
                    break;
                }
            }
            if (diagonale1Gagne)
            {
                Log.d("TAG", "Joueur " + cara +" gagne");
                return true;
            }
        }

        if (lig + col == this.tailleGrille - 1) {
            boolean diagonale2Gagne = true;
            for (int i = 0; i < this.tailleGrille; i++) {
                if (this.grille[i][this.tailleGrille - 1 - i] != cara) {
                    diagonale2Gagne = false;
                    break;
                }
            }
            if (diagonale2Gagne)
            {
                Log.d("TAG", "Joueur " + cara +" gagne");
                return true;
            }
        }

        if (this.nbCoups == Math.pow(this.tailleGrille, 2))
        {
            Log.d("TAG", "Match nul");
        }

        return false;
    }
}


class VueMorpion extends View
{
    Paint paint = new Paint();
    private final char[][] grille;
    private int tailleGrille;
    private int margeLargeur;
    private int margeHauteur;
    private int nbLignes;
    public VueMorpion(Context context, char[][] grille)
    {
        super(context);
        this.grille = grille;

    }
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        this.tailleGrille = Math.min(getWidth(), getHeight()) - 200;
        this.margeLargeur = (getWidth() - tailleGrille)/2;
        this.margeHauteur = (getHeight() - tailleGrille)/2;
        this.nbLignes = grille.length;
        this.dessinerGrille(canvas, this.grille);
    }
    public void dessinerO(Canvas canvas, int x, int y, int taille)
    {
        paint.setColor(Color.parseColor("green"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);

        canvas.drawCircle(
                x,
                y,
                taille,
                paint
        );
    }

    public void dessinerX(Canvas canvas, int x, int y, int taille)
    {
        int ecart = taille/2;
        paint.setColor(Color.parseColor("red"));
        canvas.drawLine(x-ecart, y-ecart, x+ecart, y + ecart , paint);
        canvas.drawLine(x+ecart, y-ecart, x-ecart, y + ecart , paint);
    }
    public void dessinerGrille(Canvas canvas, char[][] grille)
    {
        paint.setColor(Color.parseColor("black"));

        int x = margeLargeur;
        int y = margeHauteur;
        //dessin lignes verticales
        for (int i = 1; i < this.nbLignes; i++)
        {
            x = this.margeLargeur + i * (this.tailleGrille/(this.nbLignes));
            canvas.drawLine(x, this.margeHauteur, x, this.tailleGrille + this.margeHauteur, paint);
        }
        //dessin lignes horizontales
        for (int i = 1; i < this.nbLignes; i++)
        {
            y = this.margeHauteur + (i * this.tailleGrille/(this.nbLignes));
            canvas.drawLine(this.margeLargeur, y, this.margeLargeur + this.tailleGrille, y, paint);
        }
        for (int i = 0; i < this.nbLignes; i++)
        {
            for (int j = 0; j < this.nbLignes; j++)
            {
                x = this.margeLargeur + i * ((this.tailleGrille/(this.nbLignes))) + (this.tailleGrille/(this.nbLignes))/2;
                y = this.margeHauteur + j * ((this.tailleGrille/(this.nbLignes))) + (this.tailleGrille/(this.nbLignes))/2;
                if (grille[i][j] == 'O')
                {
                    dessinerO(canvas, x, y, (this.tailleGrille/(this.nbLignes))/3);
                }
                if (grille[i][j] == 'X')
                {
                    dessinerX(canvas, x, y, (this.tailleGrille/(this.nbLignes))/2);
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();

        int tailleCellule = this.tailleGrille / this.nbLignes;


        int col = (int) ((y - this.margeHauteur) / tailleCellule);
        int lig = (int) ((x - this.margeLargeur) / tailleCellule);
        if (col >= 0 && col < nbLignes && lig >= 0 && lig < nbLignes) {
            ((MainActivity) getContext()).click(lig, col);
        }
        invalidate();

        return true;
    }
}
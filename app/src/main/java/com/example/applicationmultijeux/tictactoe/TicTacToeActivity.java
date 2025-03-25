package com.example.applicationmultijeux.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class TicTacToeActivity extends AppCompatActivity
{
    //SI ON EST EN MODE SOLO, LE JOUEUR IA A POUR ID 2
    private VueMorpion vueMorpion;
    String mode;
    Joueur j1;
    Joueur j2;
    Joueur jCourant;
    int tailleGrille;
    char[][] grille;
    int nbCoups;
    int nbParties;
    boolean interactionAutorisee = true;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        this.mode = intent.hasExtra("Solo") ? "Solo" : "1contre1";

        this.j1 = new Joueur(1, 'O');
        this.j2 = new Joueur(2, 'X');


        this.nbParties = 0;
        this.tailleGrille = Integer.parseInt(intent.getStringExtra("TailleGrille").split("x")[0]);

        this.grille = new char[this.tailleGrille][this.tailleGrille];

        this.nouvellePartie();
        setContentView(new VueMorpion(this, this.grille));


    }

    private void nouvellePartie()
    {
        if (this.nbParties != 0)
        {
            this.j1.setSymbole(this.j2.getSymbole());
            if (this.j2.getSymbole() == 'X')
            {
                this.j2.setSymbole('O');
            }
            else
            {
                this.j2.setSymbole('X');
            }
            this.nbCoups = 0;
        }
        this.initGrille();
        this.jCourant = this.j1;
        Log.d("TAG", this.jCourant.getSymbole() + "");
        this.nbParties ++;

    }

    private void initGrille()
    {
        for (int i = 0; i < this.tailleGrille; i++) {
            for (int j = 0; j < this.tailleGrille; j++) {
                grille[i][j] = ' ';
            }
        }
    }

    public boolean getInteractionAutorisee()
    {
        return this.interactionAutorisee;
    }

    public void clickIA()
    {
        int lig = (int) (Math.random() * this.tailleGrille);;
        int col = (int) (Math.random() * this.tailleGrille);;
        while (this.grille[lig][col] != ' ')
        {
            lig = (int) (Math.random() * this.tailleGrille);
            col = (int) (Math.random() * this.tailleGrille);
        }
        this.nbCoups++;
        this.grille[lig][col] = this.jCourant.getSymbole();
        this.verifVictoire(this.jCourant.getSymbole(), lig, col);
        this.jCourant = this.j1;
        this.interactionAutorisee = true;
    }

    public void click(int lig, int col)
    {
        if (this.grille[lig][col] == ' ')
        {
            this.nbCoups++;
            this.grille[lig][col] = this.jCourant.getSymbole();
            this.verifVictoire(this.jCourant.getSymbole(), lig, col);
            if (this.mode.equals("Solo"))
            {
                this.interactionAutorisee = false;
                this.jCourant = this.j2;
                this.clickIA();
            }
            else if (this.jCourant == this.j1)
            {
                this.jCourant = j2;
            }
            else
            {
                this.jCourant = j1;
            }
        }
    }

    public boolean verifVictoire(char cara, int lig, int col)
    {
        int alignementNecessaire = 4;

        if (this.tailleGrille < alignementNecessaire)
        {
            alignementNecessaire = this.tailleGrille;
        }

        if (verifAlignement(lig, 0, 0, 1, cara, alignementNecessaire))
        {
            this.jCourant.setScore(this.jCourant.getScore() + 1);
            return true;
        }

        if (verifAlignement(0, col, 1, 0, cara, alignementNecessaire))
        {
            this.jCourant.setScore(this.jCourant.getScore() + 1);
            return true;
        }

        int departx = lig - Math.min(lig, col);
        int departy = col - Math.min(lig, col);
        if (verifAlignement(departx, departy, 1, 1, cara, alignementNecessaire))
        {
            this.jCourant.setScore(this.jCourant.getScore() + 1);
            return true;
        }

        departx = lig - Math.min(lig, this.tailleGrille - 1 - col);
        departy = col + Math.min(lig, this.tailleGrille - 1 - col);
        if (verifAlignement(departx, departy, 1, -1, cara, alignementNecessaire))
        {
            this.jCourant.setScore(this.jCourant.getScore() + 1);
            return true;
        }

        if (this.nbCoups == Math.pow(this.tailleGrille, 2))
        {
            Log.d("TAG", "Match nul");
        }

        return false;
    }

    private boolean verifAlignement(int xdepart, int ydepart, int xajout, int yajout, char cara, int alignement)
    {
        int compteur = 0;
        while (xdepart >= 0 && xdepart < this.tailleGrille && ydepart >= 0 && ydepart < this.tailleGrille)
        {
            if (this.grille[xdepart][ydepart] == cara)
            {
                compteur ++;
                if (compteur == alignement)
                {
                    return true;
                }
            }
            else
            {
                compteur = 0;
            }
            xdepart += xajout;
            ydepart += yajout;
        }
        return false;
    }

    private void finJeu()
    {
        this.interactionAutorisee = false;

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
        if (!((TicTacToeActivity) getContext()).getInteractionAutorisee())
        {
            return true;
        }
        float x = event.getX();
        float y = event.getY();

        int tailleCellule = this.tailleGrille / this.nbLignes;


        int col = (int) ((y - this.margeHauteur) / tailleCellule);
        int lig = (int) ((x - this.margeLargeur) / tailleCellule);
        if (col >= 0 && col < nbLignes && lig >= 0 && lig < nbLignes) {
            ((TicTacToeActivity) getContext()).click(lig, col);
        }
        invalidate();

        return true;
    }
}
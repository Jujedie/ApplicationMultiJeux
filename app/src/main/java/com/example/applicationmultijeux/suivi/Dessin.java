package com.example.applicationmultijeux.suivi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Dessin extends View {
    private static Paint peintureLigne;
    private static final int LONGUEUR_LIGNE = 7;

    private Paint peinture;
    private int couleur = 0xFFFFFF00;
    private int epaisseur = 10;

    private int startX;
    private int startY;

    private String forme;
    private String niveau;
    private List<float[]> lignes;

    public Dessin(Context context) {
        super(context);
        init();
    }

    public Dessin(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    protected void init() {
        this.startX = -1;
        this.startY = -1;

        this.forme = null;
        this.niveau = null;
        this.lignes = new ArrayList<>();

        Dessin.peintureLigne = new Paint();
        this.peintureLigne.setColor(0xFFFF0000);
        this.peintureLigne.setStyle(Paint.Style.STROKE);
        this.peintureLigne.setStrokeWidth(20F);

        initStylePeinture();
    }

    public void initStylePeinture() {
        this.peinture = new Paint();
        this.peinture.setColor(this.couleur);
        this.peinture.setStyle(Paint.Style.STROKE);
        this.peinture.setStrokeWidth((float) (this.epaisseur));
    }

    public Paint getPeinture() {
        return this.peinture;
    }

    public int getCouleur() {
        return this.couleur;
    }

    public String getForme() {
        return this.forme;
    }

    public String getNiveau() {
        return this.niveau;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
        this.initStylePeinture();
        invalidate();
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.forme != null && this.niveau != null) {
            int largeur = getWidth();
            int hauteur = getHeight();
            this.epaisseur = getEpaisseur(this.niveau);

            if (this.forme.equals("Cercle")) {
                int rayon = Math.min(largeur, hauteur) / 3;
                canvas.drawCircle(largeur / 2, hauteur / 2, rayon, this.peinture);

                if (this.startY == -1 || this.startX == -1) {
                    float rayonAjuste = rayon + this.epaisseur / 2;
                    this.startX = (int) (rayonAjuste * Math.cos(180));
                    this.startY = (int) (rayonAjuste * Math.sin(180));
                }
            } else if (this.forme.equals("Triangle")) {
                Path path = new Path();

                path.reset();
                path.moveTo(largeur / 2, 65);
                path.lineTo(65, hauteur - 65);
                path.lineTo(largeur - 65, hauteur - 65);
                path.close();

                canvas.drawPath(path, this.peinture);

                if (this.startY == -1 || this.startX == -1) {
                    this.startX = largeur / 2;
                    this.startY = 65;
                }
            } else if (this.forme.equals("Carré")) {
                int longueurCote = Math.min(largeur, hauteur) - 65;
                canvas.drawRect(65, 65, longueurCote, longueurCote, this.peinture);

                if (this.startY == -1 || this.startX == -1) {
                    this.startY = 65;
                    this.startX = 65;
                }
            }

            for (float[] ligne : this.lignes) {
                canvas.drawLine(ligne[0], ligne[1], ligne[2], ligne[3], Dessin.peintureLigne);
            }
        }
    }

    private int getEpaisseur() {
        return this.epaisseur;
    }

    private int getEpaisseur(String niveau) {
        int epaisseur = 70;
        if (niveau.equals("Facile")) {
            epaisseur = 130;
        } else if (niveau.equals("Moyen")) {
            epaisseur = 100;
        }
        initStylePeinture();
        return epaisseur;
    }

    public void ajouterLigne(int orientation) {
        if (this.startX != -1 && this.startY != -1) {

            float endX = (float) (startX + Dessin.LONGUEUR_LIGNE * Math.cos(Math.toRadians(orientation)));
            float endY = (float) (startY + Dessin.LONGUEUR_LIGNE * Math.sin(Math.toRadians(orientation)));

            endX = Math.max(0, Math.min(endX, getWidth()));
            endY = Math.max(0, Math.min(endY, getHeight()));

            this.lignes.add(new float[]{this.startX, this.startY, endX, endY});

            this.startX = (int) (endX);
            this.startY = (int) (endY);

            invalidate();
        }
    }

    public int getPointMalus() {
        int nbLigneEnDehors = 0;

        int largeur = getWidth();
        int hauteur = getHeight();

        if (this.forme.equals("Cercle")) {
            int rayon = Math.min(largeur, hauteur) / 3;

            for (float[] ligne : this.lignes) {
                for (int numPoint = 0; numPoint <= 1; numPoint++) {
                    double d = Math.pow(ligne[0] - largeur / 2, 2) + Math.pow(ligne[1] - hauteur / 2, 2);

                    if (!(Math.pow(rayon - (this.getEpaisseur() / 2), 2) <= Math.pow(d, 2) && Math.pow(d, 2) <= Math.pow(rayon + (this.getEpaisseur() / 2), 2))) {
                        nbLigneEnDehors++;
                        break;
                    }
                }
            }
        } else if (this.forme.equals("Triangle")) {

        }

        return (int) (100 * (nbLigneEnDehors / this.lignes.size()));
    }
}
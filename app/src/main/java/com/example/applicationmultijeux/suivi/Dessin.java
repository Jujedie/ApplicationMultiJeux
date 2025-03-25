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
    private static final int LONGUEUR_LIGNE = 3;

    private Paint peinture;
    private int couleur = 0xFFBB86FC;
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
        this.peintureLigne.setColor(0xFFD32F2F);
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
                    this.startX = largeur / 2;
                    this.startY = hauteur / 2 - rayon;
                    if (startY < 0){startY = startY * -1;}
                    if (startX < 0){startX = startX * -1;}
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

    public void ajouterLigne(float x, float y) {
        if (this.startX != -1 && this.startY != -1) {
            float endX = startX + x;
            float endY = startY + y;

            endX = Math.max(0, Math.min(endX, getWidth()));
            endY = Math.max(0, Math.min(endY, getHeight()));

            this.lignes.add(new float[]{this.startX, this.startY, endX, endY});

            this.startX = (int) (endX);
            this.startY = (int) (endY);

            invalidate();
        }
    }

    public int getPointMalus() {
        double nbLigneEnDehors = 0.0;

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

            float[] p1 = {largeur / 2, 65};
            float[] p2 = {65, hauteur - 65};
            float[] p3 = {largeur - 65, hauteur - 65};

            for (float[] ligne : this.lignes) {
                for (int numPoint = 0; numPoint <= 1; numPoint++) {
                    float x = ligne[numPoint * 2];
                    float y = ligne[numPoint * 2 + 1];

                    if (!isPointInTriangle(x, y, p1, p2, p3)) {
                        nbLigneEnDehors++;
                        break;
                    }
                }
            }

        }else if (this.forme.equals("Carré")) {

            int longueurCote = Math.min(largeur, hauteur) - 65;

            for (float[] ligne : this.lignes) {
                for (int numPoint = 0; numPoint <= 1; numPoint++) {
                    float x = ligne[numPoint * 2];
                    float y = ligne[numPoint * 2 + 1];

                    if (x < 65 || x > longueurCote || y < 65 || y > longueurCote) {
                        nbLigneEnDehors++;
                        break;
                    }
                }
            }


        }
        if (nbLigneEnDehors == 0) {return 0;}

        Log.d("DEBUG", "=======================================" + (int)(100 * ((double) nbLigneEnDehors / this.lignes.size())) + "=======================================");
        return  (int)(100 * ((double) nbLigneEnDehors / this.lignes.size()) * -1);
    }

    private boolean isPointInTriangle(float x, float y, float[] p1, float[] p2, float[] p3) {
        float d1 = sign(x, y, p1[0], p1[1], p2[0], p2[1]);
        float d2 = sign(x, y, p2[0], p2[1], p3[0], p3[1]);
        float d3 = sign(x, y, p3[0], p3[1], p1[0], p1[1]);

        boolean hasNeg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        boolean hasPos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(hasNeg && hasPos);
    }

    private float sign(float x1, float y1, float x2, float y2, float x3, float y3) {
        return (x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3);
    }
}
package com.example.applicationmultijeux.suivi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class Dessin extends View {
    private Paint  peinture;
    private int    couleur = 0xFFFF0000;
    private int    epaisseur = 1;

    private String forme;
    private String niveau;


    public Dessin(Context context)
    {
        super(context);
        initStylePeinture();
    }
    public void initStylePeinture()
    {
        this.peinture = new Paint();
        this.peinture.setColor(this.couleur);
        this.peinture.setStyle(Paint.Style.STROKE);
        this.peinture.setStrokeWidth(this.epaisseur);
    }

    public Paint  getPeinture() {return this.peinture;}
    public int    getCouleur()  {return this.couleur;}
    public String getForme()    {return this.forme;}
    public String getNiveau()   {return this.niveau;}

    public void setCouleur(int couleur)
    {
        this.couleur = couleur;
        this.initStylePeinture();
        invalidate();
    }
    public void setForme (String forme) {this.forme = forme;}
    public void setNiveau(String forme) {this.niveau = forme;}


    @Override
    protected void onDraw(Canvas canvas)
    {
        if (this.forme != null && this.niveau != null) {
            super.onDraw(canvas);
            int largeur    = getWidth();
            int hauteur    = getHeight();
            this.epaisseur = getEpaisseur(this.niveau);

            if (this.forme.equals("Cercle"))
            {
                int rayon = Math.min(largeur, hauteur) / 3;
                canvas.drawCircle(largeur/2,hauteur/2,rayon,this.peinture);
            }
            else if (this.forme.equals("Triangle"))
            {
                Path path = new Path();

                path.reset();
                path.moveTo(largeur / 2, 10);
                path.lineTo(0, hauteur);
                path.lineTo(largeur, hauteur);
                path.close();

                canvas.drawPath(path, this.peinture);
            }
            else if (this.forme.equals("Carré"))
            {

            }
        }
    }
    private int getEpaisseur(String niveau)
    {
        int epaisseur = 2;
        if (niveau.equals("Facile"))
        {
            epaisseur = 10;
        }
        else if (niveau.equals("Moyen"))
        {
            epaisseur = 5;
        }
        return epaisseur;
    }

}

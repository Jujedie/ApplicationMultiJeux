package com.example.applicationmultijeux.suivi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Dessin extends View {
    private Paint  peinture;
    private int    couleur = 0xFFFF0000;
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
        this.peinture.setStyle(Paint.Style.FILL);
    }

    public Paint  getPeinture() {return this.peinture;}
    public int    getCouleur()  {return this.couleur;}
    public String getForme()    {return this.forme;}
    public String getNiveau()   {return this.niveau;}

    public void setCouleur(int couleur) {this.couleur = couleur;}
    public void setForme (String forme) {this.forme = forme;}
    public void setNiveau(String forme) {this.niveau = forme;}


    @Override
    protected void onDraw(Canvas canvas)
    {

    }

}

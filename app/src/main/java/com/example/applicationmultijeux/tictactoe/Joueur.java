package com.example.applicationmultijeux.tictactoe;

public class Joueur
{
    private int id;
    private char symbole;
    private String couleur;
    private int score;
    public Joueur(int id, char symbole)
    {
        this.id = id;
        this.symbole = symbole;
        this.score = 0;
    }
    public int getId()
    {
        return this.id;
    }
    public char getSymbole()
    {
        return this.symbole;
    }
    public int getScore()
    {
        return this.score;
    }
    public String getCouleur()
    {
        return this.couleur;
    }

    public void setSymbole(char symbole)
    {
        this.symbole = symbole;
    }
    public void setScore(int score)
    {
        this.score = score;
    }

}

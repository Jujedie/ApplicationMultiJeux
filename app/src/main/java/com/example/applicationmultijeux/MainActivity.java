package com.example.applicationmultijeux;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void accueilTicTacToe()
    {

    }

    public void accueilJeuDeLaVie()
    {

    }

    public void accueilSuivi()
    {

    }

    public void quitterApplication()
    {
        this.finish();
    }
}

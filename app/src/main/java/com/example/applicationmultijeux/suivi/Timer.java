package com.example.applicationmultijeux.suivi;

import android.widget.TextView;

import com.example.applicationmultijeux.R;

public class Timer extends Thread
{
    private int           timeLeft;
    private SuiviActivity suiviActiv;

    public Timer(int timeInSeconds, SuiviActivity suiviActiv)
    {
        this.timeLeft   = timeInSeconds;
        this.suiviActiv = suiviActiv;
    }

    @Override
    public void run()
    {
        while (this.timeLeft > 0)
        {
            try
            {
                Thread.sleep(1000);
                this.timeLeft--;
                TextView textView = this.suiviActiv.findViewById(R.id.Timer);
                textView.setText( "Temps restant : " + String.format("%02d:%02d", timeLeft/60, timeLeft % 60) );
            }
            catch (Exception e) {}
        }
    }
    public boolean isFinished(){return this.timeLeft == 0;}
}
package com.example.applicationmultijeux;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private int val1;
    private int val2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.val1 = ((int) (Math.random() * 9) + 1);
        this.val2 = ((int) (Math.random() * 9) + 1);

        TextView theOperation = (TextView) findViewById(R.id.operation);
        theOperation.setText(" " + this.val1 + " x " + this.val2 + " = ");
    }

    public void verificationOperation(View view)
    {
        EditText etRep = (EditText) findViewById(R.id.answer);
        TextView isValid = (TextView) findViewById(R.id.isValid);

        int rep = Integer.parseInt(etRep.getText().toString());

        if (this.val1 * this.val2 == rep)
        {
            isValid.setText("Bravo");
        }
        else
        {
            isValid.setText("Dommage");
        }
    }

    public void quitterApplication(View view)
    {
        finish();
    }

    public void nouvelleOperation(View view)
    {
        this.val1 = ((int) (Math.random() * 9) + 1);
        this.val2 = ((int) (Math.random() * 9) + 1);

        TextView theOperation = (TextView) findViewById(R.id.operation);
        theOperation.setText(" " + this.val1 + " x " + this.val2 + " = ");
    }
}
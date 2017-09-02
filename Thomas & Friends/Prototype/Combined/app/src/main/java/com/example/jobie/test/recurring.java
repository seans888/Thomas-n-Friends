package com.example.jobie.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class recurring extends AppCompatActivity {

    //Check History
    public Button but6;
    public void init6() {
        but6=(Button)findViewById(R.id.button6);
        but6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy6 = new Intent(recurring.this, history.class);
                startActivity(toy6);
            }
        });


    }

    //Perform Calibration
    public Button but7;
    public void init7 () {
        but7=(Button)findViewById(R.id.button7);
        but7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy7 = new Intent(recurring.this, calib.class);
                startActivity(toy7);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurring);
        init6();
        init7();
    }
}

package com.example.jobie.touchscreencalibration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class recurring extends AppCompatActivity {

    //Check History
    public Button but8;
    public void init8() {
        but8=(Button)findViewById(R.id.button8);
        but8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy8 = new Intent(recurring.this, history.class);
                startActivity(toy8);
            }
        });


    }

    //Perform Calibration
    public Button but9;
    public void init9 () {
        but9=(Button)findViewById(R.id.button9);
        but9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy9 = new Intent(recurring.this, calib.class);
                startActivity(toy9);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurring);
        init8();
        init9();
    }
}

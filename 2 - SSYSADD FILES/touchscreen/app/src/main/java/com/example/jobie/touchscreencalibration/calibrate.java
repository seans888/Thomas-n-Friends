package com.example.jobie.touchscreencalibration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class calibrate extends AppCompatActivity {

//new user
    public Button but6;

    public void init6(){
        but6=(Button) findViewById(R.id.button6);
        but6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy6 = new Intent(calibrate.this, calib.class);
                startActivity(toy6);
            }
        });
    }
    //recurring user
    public Button but7;

    public void init7() {
        but7=(Button) findViewById(R.id.button7);
        but7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy7 = new Intent(calibrate.this, recurring.class);
                startActivity(toy7);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibrate);
        init7();
        init6();
    }
}

package com.example.jobie.touchscreencalibration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class second extends AppCompatActivity {

    //new user
    public Button but3;

    public void init3() {
        but3=(Button)findViewById(R.id.button3);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy3 = new Intent(second.this, calib.class);
                startActivity(toy3);
            }
        });


    }

    //recurring user
    public Button but10;
    public void init10 () {

        but10=(Button) findViewById(R.id.button10);
        but10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy10 = new Intent(second.this, recurring.class);
                startActivity(toy10);
            }
        });
    }
    //feedback
    public Button but4;

    public void init4() {
        but4=(Button)findViewById(R.id.button4);
        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy4 = new Intent(second.this, feedback.class);
                startActivity(toy4);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init3();
        init4();
        init10();
    }
}

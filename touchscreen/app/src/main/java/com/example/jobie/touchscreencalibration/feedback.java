package com.example.jobie.touchscreencalibration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class feedback extends AppCompatActivity {

    //submit
    public Button but5;




    public void init5() {
        but5 = (Button) findViewById(R.id.button5);
        but5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent toy5 = new Intent(feedback.this, submit.class);
                    startActivity(toy5);
                }


            ;
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        init5();
    }
}

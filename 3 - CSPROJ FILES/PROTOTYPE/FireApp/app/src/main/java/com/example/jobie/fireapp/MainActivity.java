package com.example.jobie.fireapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.crash.internal.service.FirebaseCrashSenderService;

public class MainActivity extends AppCompatActivity {

    private Button mSendData;

    private FirebaseApp mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.getApps(this);


        mRef = Firebase("https://fir-app-62ce1.firebaseio.com/");

        mSendData = (Button) findViewById(R.id.sendData);

        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseApp mRefChild = mRef.getName("Name");

            }
        });

    }



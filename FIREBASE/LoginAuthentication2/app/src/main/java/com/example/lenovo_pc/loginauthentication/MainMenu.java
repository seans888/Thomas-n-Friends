package com.example.lenovo_pc.loginauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainMenu extends AppCompatActivity {
    Button bt_gestureStart, bt_profileView, bt_signOut;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        bt_gestureStart = (Button)findViewById(R.id.bt_gestureStart);
        bt_profileView = (Button)findViewById(R.id.bt_profileView);
        bt_signOut = (Button)findViewById(R.id.bt_signOut);
        mAuth = FirebaseAuth.getInstance();

        bt_gestureStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), GesturesActivity.class));
            }
        });

        bt_profileView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), ViewProfile.class));
            }
        });

        bt_signOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), Authentication.class));
            }
        });
    }
}

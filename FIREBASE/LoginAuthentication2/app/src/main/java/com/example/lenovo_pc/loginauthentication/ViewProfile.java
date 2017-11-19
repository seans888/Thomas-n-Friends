package com.example.lenovo_pc.loginauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ViewProfile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);

        mAuth = FirebaseAuth.getInstance();
        username = (TextView)findViewById(R.id.tvMyName);

        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(), Authentication.class));
        }

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            username.setText("Welcome, " + user.getDisplayName());
        }
    }
}

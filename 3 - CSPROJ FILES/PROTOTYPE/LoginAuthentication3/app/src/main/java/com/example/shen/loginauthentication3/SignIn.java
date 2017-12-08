package com.example.shen.loginauthentication3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by LENOVO_PC on 16/10/2017.
 */

public class SignIn extends AppCompatActivity {
    Button signout;
    private FirebaseAuth mAuth;
    TextView username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        mAuth = FirebaseAuth.getInstance();
        signout = (Button)findViewById(R.id.signout);
        username = (TextView)findViewById(R.id.tvName);

        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(), Authentication.class));
        }

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            username.setText("Welcome, " + user.getDisplayName());
        }

        signout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), Authentication.class));
            }
        });
    }
}

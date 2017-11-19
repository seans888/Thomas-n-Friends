package com.example.lenovo_pc.loginauthentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Authentication extends AppCompatActivity {
    public FirebaseAuth mAuth;
    private EditText email,password,name;
    private Button signin, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        mAuth = FirebaseAuth.getInstance();

        signin = (Button) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);
        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPass);

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), SignIn.class));
        }

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getemail = email.getText().toString().trim();
                String getpassword = password.getText().toString().trim();
                callsignin(getemail, getpassword);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getemail = email.getText().toString().trim();
                String getpassword = password.getText().toString().trim();
                callsignup(getemail, getpassword);

            }
        });
    }

    // Create Account
    private void callsignup(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("TESTING", "createUserWithEmail:onComplete:" + task.isSuccessful());
                if (!task.isSuccessful()) {
                    Toast.makeText(Authentication.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                } else {
                    userProfile();
                    Toast.makeText(Authentication.this, "Created Account", Toast.LENGTH_SHORT).show();
                    Log.d("TESTING", "User profile updated.");
                }
            }
        });
    }

    private void userProfile() {
        FirebaseUser user =  mAuth.getCurrentUser();
        if(user != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(name.getText().toString().trim()).build();
            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>(){
                @Override
                public void onComplete(@NonNull Task<Void> task){
                    if(task.isSuccessful()){
                        Log.d("TESTING", "User Profile Updated!" + task.isSuccessful());
                    }
                }
            });
        }
    }

    private void callsignin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TESTING", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("TESTING", "signInWithEmail:failed", task.getException());
                            Toast.makeText(Authentication.this, "failed",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Intent i = new Intent(Authentication.this, SendData.class);
                            finish();
                            startActivity(i);
                        }
                    }
                });
    }

}

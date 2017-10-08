package com.example.jobie.activitydemo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.Blogin)
        {
            //for username
            EditText a = (EditText)findViewById(R.id.TFusername);
            String str = a.getText().toString();

            //for password
            EditText b = (EditText)findViewById(R.id.TFpassword);
            String pass = b.getText().toString();

            //search database
            String password = helper.searchPass(str);
            if (pass.equals(password))
            {
                //Match password and username + nasa database
                Intent i = new Intent(MainActivity.this, Display.class);
                i.putExtra("Username", str);
                startActivity(i);
            }
            else
            {
                Toast temp = Toast.makeText(MainActivity.this , "Username and Password don't Match!" , Toast.LENGTH_SHORT);
                temp.show();
            }


        }

        if (v.getId() == R.id.Bsignup)
        {
            Intent a = new Intent(MainActivity.this, SignUp.class);
            startActivity(a);
        }
    }
}

package com.example.jobie.activitydemo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.Blogin)
        {

            EditText a = (EditText)findViewById(R.id.TFusername);
            String str = a.getText().toString();
            


            Intent i = new Intent(MainActivity.this, Display.class);
            i.putExtra("Username", str);
            startActivity(i);
        }

        if (v.getId() == R.id.Bsignup)
        {
            Intent a = new Intent(MainActivity.this, SignUp.class);
            startActivity(a);
        }
    }
}

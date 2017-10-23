package com.example.lenovo_pc.loginauthentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendData extends AppCompatActivity {

    EditText etVelocity;
    Button submitData;

    DatabaseReference databaseVelocity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_data);

        databaseVelocity = FirebaseDatabase.getInstance().getReference("velocity");

        etVelocity = (EditText) findViewById(R.id.etVelocity);
        submitData = (Button) findViewById(R.id.submitData);

        submitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addVelocity();
            }
        });
    }

    private void addVelocity(){
        String value = etVelocity.getText().toString().trim();

        if(!TextUtils.isEmpty(value)){
            String id = databaseVelocity.push().getKey();
            Velocity velocity = new Velocity(id, value);
            databaseVelocity.child(id).setValue(velocity);
            Toast.makeText(this, "Value Added", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "ENTER VELOCITY", Toast.LENGTH_LONG).show();
        }
    }
}

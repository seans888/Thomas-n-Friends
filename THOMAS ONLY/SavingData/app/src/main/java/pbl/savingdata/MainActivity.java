package pbl.savingdata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //Define view objects
    EditText editTextName;
    Button buttonAdd;
    Spinner spinnerGenres;

    //Create a database object
    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //database
        databaseArtists = FirebaseDatabase.getInstance().getReference("Artists");


        //Get values from xml
        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonAdd = (Button)  findViewById(R.id.buttonAddArtist);
        spinnerGenres = (Spinner) findViewById(R.id.spinnerGenres);

        //Click Listener
        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Add Artist
                addArtist();
            }
        });
    }
    private void addArtist() {
        String name = editTextName.getText().toString().trim();
        String genre = spinnerGenres.getSelectedItem().toString().trim();

        //Check if filled
        if(!TextUtils.isEmpty(name)) {
            //push to database
           String id = databaseArtists.push().getKey();

            Artist artist = new Artist(id,name,genre);

            databaseArtists.child(id).setValue(artist);

            Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }
    }
}

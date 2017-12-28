package pbl.savingdata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Define view objects
    EditText editTextName;
    Button buttonAdd;
    Spinner spinnerGenres;

    //Create a database object
    DatabaseReference databaseArtists;

    //ListView
    ListView listViewArtists;

    //Create List
    List<Artist> artistList;


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
        listViewArtists = (ListView) findViewById(R.id.listViewArtists);
        artistList = new ArrayList<>();
        //Click Listener
        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Add Artist
                addArtist();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistList.clear();
                for(DataSnapshot artistSnapShot: dataSnapshot.getChildren()) {
                    Artist artist = artistSnapShot.getValue(Artist.class);

                    artistList.add(artist);
                }
                ArtistList adapter = new ArtistList(MainActivity.this, artistList);
                listViewArtists.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

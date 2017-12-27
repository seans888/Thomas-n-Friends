package pbl.savingdata;

/**
 * Created by acer on 28 Dec 2017.
 */
//Create a model class to store attributes
public class Artist {

    String artistId;
    String artistName;
    String artistGenre;

    //blank constructor used for values
    public Artist(){

    }

    public Artist(String artistId, String artistName, String artistGenre) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistGenre() {
        return artistGenre;
    }
}

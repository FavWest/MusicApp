package com.favwest.musicalstructureapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<Playlist> playlists;
    ArrayList<Song> songs;

    //Create a new Gson object (used multiple times in this Activity)
    Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Shared Preference variables
        String sharedPrefFileKey = "com.favwest.musicalstructureapp.SHARED_PREFERENCES";
        SharedPreferences mPreferences = getSharedPreferences(sharedPrefFileKey, MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor;
        preferencesEditor = mPreferences.edit();

        //Set the Music Library button to start the Music Library Activity when clicked
        Button goToMusicLibrary = findViewById(R.id.go_to_music_library);
        goToMusicLibrary.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, MusicLibrary.class);
            startActivity(intent);
        });

        //TODO this checks whether there is a value connected to Keys.PLAYLIST_KEY in Shared Preferences.
        // Is there a better way?
        //Check to see if there is an ArrayList<Playlist> connected to the Keys.PLAYLIST_KEY in Shared Preferences
        if (mPreferences.getString(Keys.PLAYLIST_KEY, "there are no playlists connected to this key")
                .equals("there are no playlists connected to this key")) {

            //If there is no ArrayList<Playlist> in Shared Preferences, create one
            playlists = new ArrayList<>();

            //Create My Mix Playlist
            ArrayList<Song> myMixSongs = new ArrayList<>();
            myMixSongs.add(new Song("Only Time Will Tell", "Asia"));
            myMixSongs.add(new Song("Head Over Heels", "Tears for Fears"));
            myMixSongs.add(new Song("Everybody Wants to Rule the World", "Tears for Fears"));
            myMixSongs.add(new Song("I'd Do Anything For Love (But I Won't Do That)", "Meatloaf"));
            myMixSongs.add(new Song("Bat Out of Hell", "Meatloaf"));
            myMixSongs.add(new Song("Holy Diver", "Dio"));
            myMixSongs.add(new Song("Rainbow in the Dark", "Dio"));
            myMixSongs.add(new Song("(Don't Fear) The Reaper", "Blue Oyster Cult"));
            myMixSongs.add(new Song("Carry On Wayward Son", "Kansas"));
            myMixSongs.add(new Song("The Chain", "Fleetwood Mac"));
            playlists.add(new Playlist("My Mix", myMixSongs));

            //Create Awesome Mix Vol. 1
            ArrayList<Song> awesomeMixVol1Songs = new ArrayList<>();
            awesomeMixVol1Songs.add(new Song("Hooked on a Feeling", "Blue Swede"));
            awesomeMixVol1Songs.add(new Song("Go All the Way", "Raspberries"));
            awesomeMixVol1Songs.add(new Song("Spirit in the Sky", "Norman Greenbaum"));
            awesomeMixVol1Songs.add(new Song("Moonage Daydream", "David Bowie"));
            awesomeMixVol1Songs.add(new Song("Fooled Around and Fell in Love", "Elvin Bishop"));
            awesomeMixVol1Songs.add(new Song("I'm Not in Love", "10cc"));
            awesomeMixVol1Songs.add(new Song("I Want You Back", "The Jackson 5"));
            awesomeMixVol1Songs.add(new Song("Come and Get Your Love", "Redbone"));
            awesomeMixVol1Songs.add(new Song("Cherry Bomb", "The Runaways"));
            awesomeMixVol1Songs.add(new Song("Escape (The Piña Colada Song)", "Rupert Holmes"));
            awesomeMixVol1Songs.add(new Song("O-o-h Child", "Five Stairsteps"));
            awesomeMixVol1Songs.add(new Song("Ain't No Mountain High Enough", "Marvin Gaye and Tammi Terrell"));
            playlists.add(new Playlist("Awesome Mix Vol 1", awesomeMixVol1Songs));

            //Create Awesome Mix Vol. 2
            ArrayList<Song> awesomeMixVol2Songs = new ArrayList<>();
            awesomeMixVol2Songs.add(new Song("Mr. Blue Sky", "Electric Light Orchestra"));
            awesomeMixVol2Songs.add(new Song("Fox on the Run", "Sweet"));
            awesomeMixVol2Songs.add(new Song("Lake Shore Drive", "Aliotta Haynes Jeremiah"));
            awesomeMixVol2Songs.add(new Song("The Chain", "Fleetwood Mac"));
            awesomeMixVol2Songs.add(new Song("Bring It on Home to Me", "Sam Cooke"));
            awesomeMixVol2Songs.add(new Song("Southern Nights", "Glen Campbell"));
            awesomeMixVol2Songs.add(new Song("My Sweet Lord", "George Harrison"));
            awesomeMixVol2Songs.add(new Song("Brandy (You’re a Fine Girl)", "Looking Glass"));
            awesomeMixVol2Songs.add(new Song("Come a Little Bit Closer", "Jay and the Americans"));
            awesomeMixVol2Songs.add(new Song("Wham Bam Shang-A-Lang", "Silver"));
            awesomeMixVol2Songs.add(new Song("Surrender", "Cheap Trick"));
            awesomeMixVol2Songs.add(new Song("Father and Son", "Cat Stevens"));
            awesomeMixVol2Songs.add(new Song("Flash Light", "Parliament"));
            awesomeMixVol2Songs.add(new Song("Guardians Inferno", "The Sneepers featuring David Hasselhoff"));
            playlists.add(new Playlist("Awesome Mix Vol. 2", awesomeMixVol2Songs));

            //Convert the playslists ArrayList to a JSON String and save it to Shared Preferences
            String json = gson.toJson(playlists);
            preferencesEditor.putString(Keys.PLAYLIST_KEY, json);
            preferencesEditor.apply();
        } else {
            //If there is already a playlist in Shared Preferences, get it
            String json = mPreferences.getString(Keys.PLAYLIST_KEY, null);
            playlists = gson.fromJson(json, new TypeToken<ArrayList<Playlist>>() {
            }.getType());
        }

        //Check to see if there is an ArrayList<Song> connected to the MUSIC_LIBRARY_KEY in Shared Preferences
        if (mPreferences.getString(Keys.MUSIC_LIBRARY_KEY, "there are no song lists connected to this key")
                .equals("there are no song lists connected to this key")) {

            //If there is no ArrayList<Song> in Shared Preferences, create one
            songs = new ArrayList<>();
            songs.add(new Song("Only Time Will Tell", "Asia"));
            songs.add(new Song("Head Over Heels", "Tears for Fears"));
            songs.add(new Song("Everybody Wants to Rule the World", "Tears for Fears"));
            songs.add(new Song("I'd Do Anything For Love (But I Won't Do That)", "Meatloaf"));
            songs.add(new Song("Bat Out of Hell", "Meatloaf"));
            songs.add(new Song("Holy Diver", "Dio"));
            songs.add(new Song("Rainbow in the Dark", "Dio"));
            songs.add(new Song("(Don't Fear) The Reaper", "Blue Oyster Cult"));
            songs.add(new Song("Carry On Wayward Son", "Kansas"));
            songs.add(new Song("The Chain", "Fleetwood Mac"));
            songs.add(new Song("Hooked on a Feeling", "Blue Swede"));
            songs.add(new Song("Go All the Way", "Raspberries"));
            songs.add(new Song("Spirit in the Sky", "Norman Greenbaum"));
            songs.add(new Song("Moonage Daydream", "David Bowie"));
            songs.add(new Song("Fooled Around and Fell in Love", "Elvin Bishop"));
            songs.add(new Song("I'm Not in Love", "10cc"));
            songs.add(new Song("I Want You Back", "The Jackson 5"));
            songs.add(new Song("Come and Get Your Love", "Redbone"));
            songs.add(new Song("Cherry Bomb", "The Runaways"));
            songs.add(new Song("Escape (The Piña Colada Song)", "Rupert Holmes"));
            songs.add(new Song("O-o-h Child", "Five Stairsteps"));
            songs.add(new Song("Ain't No Mountain High Enough", "Marvin Gaye and Tammi Terrell"));
            songs.add(new Song("Mr. Blue Sky", "Electric Light Orchestra"));
            songs.add(new Song("Fox on the Run", "Sweet"));
            songs.add(new Song("Lake Shore Drive", "Aliotta Haynes Jeremiah"));
            songs.add(new Song("The Chain", "Fleetwood Mac"));
            songs.add(new Song("Bring It on Home to Me", "Sam Cooke"));
            songs.add(new Song("Southern Nights", "Glen Campbell"));
            songs.add(new Song("My Sweet Lord", "George Harrison"));
            songs.add(new Song("Brandy (You’re a Fine Girl)", "Looking Glass"));
            songs.add(new Song("Come a Little Bit Closer", "Jay and the Americans"));
            songs.add(new Song("Wham Bam Shang-A-Lang", "Silver"));
            songs.add(new Song("Surrender", "Cheap Trick"));
            songs.add(new Song("Father and Son", "Cat Stevens"));
            songs.add(new Song("Flash Light", "Parliament"));
            songs.add(new Song("Guardians Inferno", "The Sneepers featuring David Hasselhoff"));

            //Convert the songs ArrayList to a JSON String and save it to Shared Preferences
            String json = gson.toJson(songs);
            preferencesEditor.putString(Keys.MUSIC_LIBRARY_KEY, json);
            preferencesEditor.apply();
        } else {
            //If there is already a playlist in Shared Preferences, get it
            String json = mPreferences.getString(Keys.MUSIC_LIBRARY_KEY, null);
            songs = gson.fromJson(json, new TypeToken<ArrayList<Song>>() {
            }.getType());
        }

        //Initialize a PlaylistAdapter to put the playlists items into a ListView
        PlaylistAdapter playlistAdapter = new PlaylistAdapter(this, playlists);
        ListView list = findViewById(R.id.playlists);
        list.setAdapter(playlistAdapter);

        //Send the Playlist to the NowPlaying Activity
        list.setOnItemClickListener((parent, view, position, id) -> {
            Playlist selectedPlaylist = (Playlist) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, NowPlaying.class);
            String json = gson.toJson(selectedPlaylist);
            intent.putExtra(Keys.ACTIVITY_SENDING_INTENT, "MainActivity");
            intent.putExtra(Keys.PLAYLIST_EXTRA, json);
            startActivity(intent);
        });

    }
}
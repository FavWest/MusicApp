package com.favwest.musicalstructureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CreateNewPlaylist extends AppCompatActivity {

    //TODO should these be public, or declared here and in MainActivity as private?
    //TODO declaring PLAYLIST_KEY public doesn't work -- why?
    SharedPreferences mPreferences;
    private final String sharedPrefFileKey = "com.favwest.musicalstructureapp.SHARED_PREFERENCES";
    public final String PLAYLIST_KEY = "com.favwest.musicalstructureapp.MainActivity.PLAYLIST_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_playlist);

        //TODO: temp
        EditText enterNewPlaylist = findViewById(R.id.enter_new_playlist);

        mPreferences = getSharedPreferences(sharedPrefFileKey, MODE_PRIVATE);

        Button saveButton = findViewById(R.id.save);

        saveButton.setOnClickListener(new View.OnClickListener() {

            //TODO: currently this resets the Shared Preferences value to whatever is entered
            @Override
            public void onClick(View view) {
                //Create a Gson object to translate the JSON String
                Gson gson = new Gson();
                //Get the JSON String from Shared Preferences
                String json = mPreferences.getString(PLAYLIST_KEY, null);
                //Translate the JSON String into playlists (Arraylist<Playlist>)
                ArrayList<Playlist> playlists = gson.fromJson
                        (json, new TypeToken<ArrayList<Playlist>>(){}.getType());

                //Get the name of the new playlist
                String newPlaylistName = enterNewPlaylist.getText().toString().trim();
                //Add the new playlist to the playlists Arraylist
                playlists.add(new Playlist(newPlaylistName, new ArrayList<Song>()));

                //Create an editor for Shared Preferences
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();

                //Convert playlists to JSON String
                json = gson.toJson(playlists);

                //Put the JSON String in Shared Preferences
                preferencesEditor.putString(PLAYLIST_KEY, json);
                preferencesEditor.apply();

                Toast.makeText(CreateNewPlaylist.this,
                        "Created new playlist: " + newPlaylistName,
                        Toast.LENGTH_LONG).show();
            }
        });

        ArrayList<Song> songs = new ArrayList<>();
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

        SongAdapter<Song> songAdapter = new SongAdapter<Song>(this, songs);
        ListView allSongsList = findViewById(R.id.songs_to_add);
        allSongsList.setAdapter(songAdapter);

    }
}
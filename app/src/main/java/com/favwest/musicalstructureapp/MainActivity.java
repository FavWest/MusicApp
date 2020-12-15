package com.favwest.musicalstructureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button goToMusicLibrary;
    Button goToCreateNewPlaylist;
    public static final String PLAYLIST_EXTRA = "com.favwest.musicalstructureapp.mainactivity.PLAYLIST_EXTRA";
    public static final String ACTIVITY_SENDING_INTENT = "com.favwest.musicalstructureapp.ACTIVITY_SENDING_INTENT";

    //Declare Shared Preference variables
    private SharedPreferences mPreferences;
    private final String sharedPrefFileKey = "com.favwest.musicalstructureapp.SHARED_PREFERENCES";
    public final String PLAYLIST_KEY = "com.favwest.musicalstructureapp.MainActivity.PLAYLIST_KEY";
    public final String MUSIC_LIBRARY_KEY = "com.favwest.musicalstructureapp.MainActivity.MUSIC_LIBRARY_KEY";

    ArrayList<Playlist> playlists;
    ArrayList<Song> songs;

    //Create a new Gson object (used multiple times in this Activity)
    Gson gson = new Gson();
    SharedPreferences.Editor preferencesEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize mPreferences to access Shared Preferences
        mPreferences = getSharedPreferences(sharedPrefFileKey, MODE_PRIVATE);
        preferencesEditor = mPreferences.edit();

        goToMusicLibrary = findViewById(R.id.go_to_music_library);
        goToMusicLibrary.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, MusicLibrary.class);
            startActivity(intent);
        });

        //TODO this checks whether there is a value connected to PLAYLIST_KEY in Shared Preferences.
        // Is there a better way?
        //Check to see if there is an ArrayList<Playlist> connected to the PLAYLIST_KEY in Shared Preferences
        if(mPreferences.getString(PLAYLIST_KEY, "there are no playlists connected to this key")
                .equals("there are no playlists connected to this key")){

            //If there is no ArrayList<Playlist> in Shared Preferences, create one
            playlists = new ArrayList<>();
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
            playlists.add(new Playlist("My Mix", songs));

            //Convert the playslists ArrayList to a JSON String and save it to Shared Preferences
            String json = gson.toJson(playlists);
            preferencesEditor.putString(PLAYLIST_KEY, json);
            preferencesEditor.apply();
        }
        else{
            //If there is already a playlist in Shared Preferences, get it
            String json = mPreferences.getString(PLAYLIST_KEY, null);
            playlists = gson.fromJson(json, new TypeToken<ArrayList<Playlist>>(){}.getType());
        }

        //Check to see if there is an ArrayList<Song> connected to the MUSIC_LIBRARY KEY in Shared Preferences
        if(mPreferences.getString(MUSIC_LIBRARY_KEY, "there are no song lists connected to this key")
                .equals("there are no song lists connected to this key")){

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

            //Convert the songs ArrayList to a JSON String and save it to Shared Preferences
            String json = gson.toJson(songs);
            preferencesEditor.putString(MUSIC_LIBRARY_KEY, json);
            preferencesEditor.apply();
        }
        else{
            //If there is already a playlist in Shared Preferences, get it
            String json = mPreferences.getString(MUSIC_LIBRARY_KEY, null);
            songs = gson.fromJson(json, new TypeToken<ArrayList<Song>>(){}.getType());
        }

        //Initialize a PlaylistAdapter to put the playlists items into a ListView
        PlaylistAdapter<Playlist> playlistAdapter = new PlaylistAdapter<>(this, playlists);
        ListView list = findViewById(R.id.playlists);
        list.setAdapter(playlistAdapter);

        //TODO Use this to send the Playlist to the NowPlaying Activity
        list.setOnItemClickListener((parent, view, position, id) -> {
            Playlist selectedPlaylist = (Playlist)parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, NowPlaying.class);
            String json = gson.toJson(selectedPlaylist);
            intent.putExtra(ACTIVITY_SENDING_INTENT, "MainActivity");
            intent.putExtra(PLAYLIST_EXTRA, json);
            startActivity(intent);
        });

    }
}
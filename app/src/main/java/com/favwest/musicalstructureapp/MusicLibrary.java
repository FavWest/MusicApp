package com.favwest.musicalstructureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MusicLibrary extends AppCompatActivity {

    ListView allSongsList;
    public static final String SONG_NAME_EXTRA = "com.favwest.musicalstructureapp.MusicLibrary.SONG_NAME_EXTRA";
    public static final String ARTIST_NAME_EXTRA = "com.favwest.musicalstructureapp.MusicLibrary.ARTIST_NAME_EXTRA";
    public static final String ACTIVITY_SENDING_INTENT = "com.favwest.musicalstructureapp.ACTIVITY_SENDING_INTENT";

    //Declare Shared Preference variables
    private SharedPreferences mPreferences;
    private final String sharedPrefFileKey = "com.favwest.musicalstructureapp.SHARED_PREFERENCES";
    public final String PLAYLIST_KEY = "com.favwest.musicalstructureapp.MainActivity.PLAYLIST_KEY";
    public final String MUSIC_LIBRARY_KEY = "com.favwest.musicalstructureapp.MainActivity.MUSIC_LIBRARY_KEY";

    private ArrayList<Song> songs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_library);

        //Open Shared Preferences
        mPreferences = getSharedPreferences(sharedPrefFileKey, MODE_PRIVATE);

        //Get the JSON version of the songs ArrayList from Shared Preferences
        String json = mPreferences.getString(MUSIC_LIBRARY_KEY, null);

        //Convert the JSON String to ArrayList<Song> and initialize ArrayList<Song> songs
        Gson gson = new Gson();
        songs = gson.fromJson(json, new TypeToken<ArrayList<Song>>(){}.getType());

        //Create an adapter and set the songs ListView to display the songs list
        SongAdapter<Song> songAdapter = new SongAdapter<Song>(this, songs);
        allSongsList = findViewById(R.id.all_songs_list);
        allSongsList.setAdapter(songAdapter);

        //When a ListView item is clicked, send the song name and artist name
        // to the "Now Playing" Activity and start that Activity
        allSongsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Song selectedSong = (Song)parent.getItemAtPosition(position);
                Intent intent = new Intent(MusicLibrary.this, NowPlaying.class);
                intent.putExtra(ACTIVITY_SENDING_INTENT, "MusicLibrary");
                intent.putExtra(SONG_NAME_EXTRA, selectedSong.getSongName());
                intent.putExtra(ARTIST_NAME_EXTRA, selectedSong.getArtistName());
                startActivity(intent);
            }
        });

    }
}
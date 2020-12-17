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

public class MusicLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_library);

        //Open Shared Preferences
        String sharedPrefFileKey = "com.favwest.musicalstructureapp.SHARED_PREFERENCES";
        SharedPreferences mPreferences = getSharedPreferences(sharedPrefFileKey, MODE_PRIVATE);

        //Get the JSON String of the songs ArrayList from Shared Preferences
        String json = mPreferences.getString(Keys.MUSIC_LIBRARY_KEY, null);

        //Use GSON to Convert the JSON String to ArrayList<Song> and initialize ArrayList<Song> songs
        Gson gson = new Gson();
        ArrayList<Song> songs = gson.fromJson(json, new TypeToken<ArrayList<Song>>() {
        }.getType());

        //Create a SongAdapter and set the songs ListView to display the songs list
        SongAdapter<Song> songAdapter = new SongAdapter<>(this, songs);
        ListView allSongsList = findViewById(R.id.all_songs_list);
        allSongsList.setAdapter(songAdapter);

        //When a ListView item is clicked, send the song name and artist name
        // to the "Now Playing" Activity and start that Activity
        allSongsList.setOnItemClickListener((parent, view, position, id) -> {

            Song selectedSong = (Song) parent.getItemAtPosition(position);
            Intent intent = new Intent(MusicLibrary.this, NowPlaying.class);
            intent.putExtra(Keys.ACTIVITY_SENDING_INTENT, "MusicLibrary");
            intent.putExtra(Keys.SONG_NAME_EXTRA, selectedSong.getSongName());
            intent.putExtra(Keys.ARTIST_NAME_EXTRA, selectedSong.getArtistName());
            startActivity(intent);
        });

        //When the Playlist Button is clicked, go to MainActivity
        Button goToMain = findViewById(R.id.go_to_main);
        goToMain.setOnClickListener(view -> {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        });


    }
}
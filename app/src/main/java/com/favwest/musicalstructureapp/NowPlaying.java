package com.favwest.musicalstructureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NowPlaying extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        Intent intent = getIntent();
        TextView displaySong = findViewById(R.id.song);
        TextView displayArtist = findViewById(R.id.artist);

        //If the Intent came from the Music Library, get the song name and artist name and display them
        String musicLibrarySongName = intent.getStringExtra(MusicLibrary.SONG_NAME_EXTRA);
        String musicLibraryArtistName = intent.getStringExtra(MusicLibrary.ARTIST_NAME_EXTRA);
        displaySong.setText(musicLibrarySongName);
        displayArtist.setText(musicLibraryArtistName);

        //Get the JSON String from Extras
        String json = intent.getStringExtra(MainActivity.PLAYLIST_EXTRA);
        if(json != null){

        //Make a Gson object to translate the JSON
        Gson gson = new Gson();

        //Translate the JSON String to a Playlist object
        Playlist playlist = gson.fromJson(json, new TypeToken<Playlist>(){}.getType());

        //Get the playlist TextView and setText to show the playlist's title
        TextView displayPlaylist = findViewById(R.id.playlist);
        displayPlaylist.setText(playlist.getTitle());

        //Get the ArrayList of songs from the selected playlist
        ArrayList<Song> songs = playlist.getSongs();

        //Display the first song in the list
        String songName = songs.get(0).getSongName();
        String artistName = songs.get(0).getArtistName();
        displaySong.setText(songName);
        displayArtist.setText(artistName);}
    }
}
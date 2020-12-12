package com.favwest.musicalstructureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NowPlaying extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        Intent intent = getIntent();
        String songName = intent.getStringExtra(MusicLibrary.SONG_NAME_EXTRA);
        String artistName = intent.getStringExtra(MusicLibrary.ARTIST_NAME_EXTRA);
        TextView displaySong = findViewById(R.id.song);
        TextView displayArtist = findViewById(R.id.artist);
        displaySong.setText(songName);
        displayArtist.setText(artistName);
        String playlistName = intent.getStringExtra(MainActivity.PLAYLIST_EXTRA);
        displaySong.setText(playlistName);
    }
}
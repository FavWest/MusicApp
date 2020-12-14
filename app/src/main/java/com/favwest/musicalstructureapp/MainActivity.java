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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button goToMusicLibrary;
    Button goToCreateNewPlaylist;
    public static final String PLAYLIST_EXTRA = "com.favwest.musicalstructureapp.mainactivity.PLAYLIST_EXTRA";
    public static final String SONG_NAME_EXTRA = "com.favwest.musicalstructureapp.mainactivity.SONG_NAME_EXTRA";
    public static final String ARTIST_NAME_EXTRA = "com.favwest.musicalstructureapp.mainactivity.ARTIST_NAME_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToMusicLibrary = findViewById(R.id.go_to_music_library);
        goToMusicLibrary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MusicLibrary.class);
                startActivity(intent);
            }
        });

        goToCreateNewPlaylist = findViewById(R.id.go_to_create_new_playlist);
        goToCreateNewPlaylist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CreateNewPlaylist.class);
                startActivity(intent);
            }
        });

        ArrayList<Playlist> playlists = new ArrayList<Playlist>();
        playlists.add(new Playlist("Classic 80s", new ArrayList<>()));

        PlaylistAdapter<Playlist> playlistAdapter = new PlaylistAdapter<Playlist>(this, playlists);
        ListView list = findViewById(R.id.playlists);
        list.setAdapter(playlistAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            //TODO Use this to send the Playlist to the NowPlaying Activity
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Playlist selectedPlaylist = (Playlist)parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, NowPlaying.class);
                intent.putExtra(PLAYLIST_EXTRA, selectedPlaylist.getTitle());
                startActivity(intent);
            }

        });
    }
}
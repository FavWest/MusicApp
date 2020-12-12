package com.favwest.musicalstructureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MusicLibrary extends AppCompatActivity {

    public ArrayList<Song> songs = new ArrayList<>();
    ListView allSongsList;
    public static final String SONG_NAME_EXTRA = "com.favwest.musicalstructureapp.MusicLibrary.SONG_NAME_EXTRA";
    public static final String ARTIST_NAME_EXTRA = "com.favwest.musicalstructureapp.MusicLibrary.ARTIST_NAME_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_library);

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
        allSongsList = findViewById(R.id.all_songs_list);
        allSongsList.setAdapter(songAdapter);

        allSongsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Song selectedSong = (Song)parent.getItemAtPosition(position);
                Intent intent = new Intent(MusicLibrary.this, NowPlaying.class);
                intent.putExtra(SONG_NAME_EXTRA, selectedSong.getSongName());
                intent.putExtra(ARTIST_NAME_EXTRA, selectedSong.getArtistName());
                startActivity(intent);
            }
        });

    }
}
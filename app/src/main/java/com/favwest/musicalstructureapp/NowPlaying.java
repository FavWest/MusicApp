package com.favwest.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class NowPlaying extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        AtomicInteger count = new AtomicInteger(1);
        ArrayList<Song> songs;

        //Get the starting Intent, and song & artist TextViews
        Intent intent = getIntent();
        TextView displaySong = findViewById(R.id.song);
        TextView displayArtist = findViewById(R.id.artist);

        //Check if the the Intent came from the Music Library
        //If so, get the song name and artist name and display them
        if (intent.getStringExtra(Keys.ACTIVITY_SENDING_INTENT).equals("MusicLibrary")) {
            String musicLibrarySongName = intent.getStringExtra(Keys.SONG_NAME_EXTRA);
            String musicLibraryArtistName = intent.getStringExtra(Keys.ARTIST_NAME_EXTRA);
            displaySong.setText(musicLibrarySongName);
            displayArtist.setText(musicLibraryArtistName);
            //If the Intent came from MainActivity, get the Playlist and use it to set the song info
        } else {

            //Get the JSON String from Extras
            String json = intent.getStringExtra(Keys.PLAYLIST_EXTRA);
            if (json != null) {

                //Make a Gson object to translate the JSON
                Gson gson = new Gson();

                //Translate the JSON String to a Playlist object
                Playlist playlist = gson.fromJson(json, new TypeToken<Playlist>() {
                }.getType());

                //Get the playlist TextView and setText to show the playlist's title
                TextView displayPlaylist = findViewById(R.id.playlist);
                displayPlaylist.setText(playlist.getTitle());

                //Get the ArrayList of songs from the selected playlist
                songs = playlist.getSongs();

                //Check that songs list is not empty, then display the first song in the list
                if (songs.size() > 0) {
                    String songName = songs.get(0).getSongName();
                    String artistName = songs.get(0).getArtistName();
                    displaySong.setText(songName);
                    displayArtist.setText(artistName);
                } else {
                    displaySong.setText(R.string.playlist_is_empty);
                }

                //Find the invisible NEXT Button and set it to visible
                Button next = findViewById(R.id.next);
                next.setVisibility(View.VISIBLE);

                /*When the NEXT Button is clicked, move to the next song in the playlist
                and display the song and artist name.
                When the end of the playlist is reached, set button visibility to GONE*/
                next.setOnClickListener(view -> {
                    if (count.get() < songs.size()) {
                        String songName = songs.get(count.get()).getSongName();
                        String artistName = songs.get(count.get()).getArtistName();
                        displaySong.setText(songName);
                        displayArtist.setText(artistName);
                        count.getAndIncrement();
                        if (count.get() == songs.size()) {
                            next.setVisibility(View.GONE);
                        }
                    }
                });
            }
        }

        //When the Music Library Button is clicked, go to Music Library
        Button goToMusicLibrary = findViewById(R.id.go_to_music_library);
        goToMusicLibrary.setOnClickListener(view -> {
            Intent musicLibraryIntent = new Intent(NowPlaying.this, MusicLibrary.class);
            startActivity(musicLibraryIntent);
        });

        //When the Playlists Button is clicked, go to MainActivity
        Button goToMain = findViewById(R.id.go_to_main);
        goToMain.setOnClickListener(view -> {
            Intent mainIntent = new Intent(NowPlaying.this, MainActivity.class);
            startActivity(mainIntent);
        });

        /*When the PLAY Button is clicked, set text color to purple to highlight the song name and
        artist and change the button text to PAUSE.
        When the PAUSE Button is clicked, change the song name and artist text color to gray and
        change the button text to PLAY.
         */
        Button play = findViewById(R.id.play);
        play.setOnClickListener(view -> {
            if (play.getText().equals("Play")) {
                play.setText(R.string.pause);
                displaySong.setTextColor(getResources().getColor(R.color.purple_500));
                displayArtist.setTextColor(getResources().getColor(R.color.purple_500));
            } else {
                play.setText(R.string.play);
                displaySong.setTextColor(getResources().getColor(R.color.gray));
                displayArtist.setTextColor(getResources().getColor(R.color.gray));
            }
        });
    }
}
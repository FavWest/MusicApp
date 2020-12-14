package com.favwest.musicalstructureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

        enterNewPlaylist.setText(mPreferences.getString(PLAYLIST_KEY, "Not found"));

        Button saveButton = findViewById(R.id.save);

        saveButton.setOnClickListener(new View.OnClickListener() {

            //TODO: currently this resets the Shared Preferences value to whatever is entered
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                String newPlaylistName = enterNewPlaylist.getText().toString().trim();
                preferencesEditor.putString(PLAYLIST_KEY, newPlaylistName);
                preferencesEditor.apply();
                Toast.makeText(CreateNewPlaylist.this,
                        mPreferences.getString(PLAYLIST_KEY, "not found"),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
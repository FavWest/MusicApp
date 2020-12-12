package com.favwest.musicalstructureapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SongAdapter<S> extends ArrayAdapter<Song> {

    public SongAdapter(Activity context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.song_list_item, parent, false);
        }

        Song currentSong = getItem(position);

        TextView songNameDisplay = listItemView.findViewById(R.id.song_name_display);
        songNameDisplay.setText(currentSong.getSongName());

        TextView artistNameDisplay = listItemView.findViewById(R.id.artist_name_display);
        artistNameDisplay.setText(currentSong.getArtistName());

        return listItemView;
    }
}

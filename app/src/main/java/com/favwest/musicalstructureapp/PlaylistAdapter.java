package com.favwest.musicalstructureapp;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaylistAdapter<S> extends ArrayAdapter<Playlist> {

    public PlaylistAdapter(Activity context, ArrayList<Playlist> playlists) {
        super(context, 0, playlists);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.playlist_display, parent, false);
        }

        Playlist currentPlaylist = getItem(position);

        TextView playlistDisplay = listItemView.findViewById(R.id.playlist_title);
        playlistDisplay.setText(currentPlaylist.getTitle());

        return listItemView;
    }
}

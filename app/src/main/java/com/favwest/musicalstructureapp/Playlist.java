package com.favwest.musicalstructureapp;

import java.util.ArrayList;

public class Playlist {
    String title;
    ArrayList<Song> songs;
    public Playlist(String title, ArrayList<Song> songs){
        this.title = title;
        this.songs = songs;
    }
    public String getTitle(){return title;}

    public String toString(){return title;}
}

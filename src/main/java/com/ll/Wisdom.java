package com.ll;

public class Wisdom {
    private int id;
    private String saying;
    private String artist;

    public Wisdom(int id, String saying, String artist) {
        this.saying = saying;
        this.artist = artist;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getSaying() {
        return saying;
    }

    public String getArtist() {
        return artist;
    }
    
}


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

    public void setSaying(String newSaying) {
        this.saying = newSaying;
    }

    public void setArtist(String newArtist) {
        this.artist = newArtist;
    }

    public void setId(int newId) {
        this.id = newId;
    }
}


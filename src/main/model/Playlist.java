package model;

import java.util.ArrayList;
import java.util.List;

// Represents a playlist with a name, genre, defined length, total songs, and a username to tag who made it
public class Playlist {

    private List<Song> playlist;
    private int playlistRuntime;
    private int totalSongs;
    public String tag;
    private String playlistName;
    private String playlistGenre;
    private String str = "";

    // EFFECTS: default constructs an empty playlist with a user defined name
    public Playlist(String name, String genre) {
        playlist = new ArrayList<>();
        this.playlistName = name;
        this.playlistGenre = genre;
        this.playlistRuntime = 0;
        this.totalSongs = 0;

    }

    public Playlist(String username, String name, String genre, int songs, int runtime) {
        playlist = new ArrayList<>();
        this.tag = username;
        this.playlistName = name;
        this.playlistGenre = genre;
        this.totalSongs = songs;
        this.playlistRuntime = runtime;
    }

    // REQUIRES: the song cannot have empty fields
    // MODIFIES: this
    // EFFECTS: adds a song to the playlist, and updates total songs and runtime
    public void addSong(Song s) {
        playlist.add(s);
        totalSongs++;
        playlistRuntime = playlistRuntime + s.getRuntime();
    }

    // REQUIRES: s cannot be empty
    // MODIFIES: this
    // EFFECTS: removes a song from the playlist
    public void removeSong(Song s) {
        if (playlist.contains(s)) {
            playlist.remove(s);
            totalSongs--;
            playlistRuntime = playlistRuntime - s.getRuntime();
        }
    }

    // EFFECTS: returns preferred playlist genre
    public String getPlaylistGenre() {
        return playlistGenre;
    }

    // EFFECTS: returns total Runtime of playlist
    public int getTotalRuntime() {
        return playlistRuntime;
    }

    // EFFECTS: returns total Runtime of playlist
    public int getTotalSongs() {
        return totalSongs;
    }

    // EFFECTS: returns username tag
    public String getTag() {
        return tag;
    }

    // EFFECTS: returns playlist name
    public String getPlaylistName() {
        return playlistName;
    }

    // EFFECTS; returns a string of contents of the playlist
    public String toStringPlaylist() {
        for (Song s : playlist) {
            str += "\n" + s.getTitle() + " by " + s.getArtist() + ": " + s.getGenre();
        }
        return  getPlaylistName() + "!: a " + getPlaylistGenre() + " type playlist" + str;
    }

    // MODIFIES: this
    // EFFECTS: changes playlist tag
    public void changeTag(String s) {
        tag = s;
    }

}

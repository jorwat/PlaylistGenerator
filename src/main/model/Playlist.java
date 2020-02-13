package model;

import java.util.ArrayList;
import java.util.List;

// Represents a playlist with a name, defined length, and total of songs
public class Playlist {

    private List<Song> playlist;
    private int playlistRuntime;
    private int totalSongs;
    private String playlistName;
    private String playlistGenre;
    private String str = "";

    // EFFECTS: constructs an empty playlist with a user defined name
    public Playlist(String name, String genre) {
        playlist = new ArrayList<>();
        playlistGenre = genre;
        playlistRuntime = 0;
        totalSongs = 0;
        playlistName = name;
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
}

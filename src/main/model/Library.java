package model;

import ui.PlaylistApp;

import java.util.ArrayList;
import java.util.List;

// Represents a Library of Playlists with a Username
public class Library {

    private List<Playlist> library;
    private int size;
    private String username;
    private String str = "";

    // EFFECTS: creates a library for initialization
    public Library(String username) {
        library = new ArrayList<>();
        this.username = username;
        size = 0;
    }

    // MODIFIES: this
    // EFFECTS: adds a playlist to the library
    public void addPlaylist(Playlist p) {
        library.add(p);
        size++;
    }

    // MODIFIES: this
    // EFFECTS: removes a playlist from the library
    public void removePlaylist(Playlist p) {
        library.remove(p);
        size--;
    }

    // EFFECTS: returns username
    public String getUsername() {
        return username;
    }

    // EFFECTS; returns name of the library
    public String getLibrary() {
        return getUsername() + "'s Library";
    }

    // EFFECTS: returns size of library
    public int getSize() {
        return size;
    }

    // EFFECTS; returns a string of contents of the playlist
    public String viewPlaylists() {
        for (Playlist p : library) {
            str += "\n" + p.getPlaylistName() + ": a "
                    + p.getPlaylistGenre()
                    + " with " + p.getTotalSongs()
                    + " running at " + p.getTotalRuntime();
        }
        return username + "'s Playlist" + str;
    }
}

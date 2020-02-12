package model;

import java.util.ArrayList;
import java.util.List;

// Represents a Library of Playlists with a Username
public class Library {

    private List<Playlist> library;
    private int size;
    private String username;

    // REQUIRES: username that's not null
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


}

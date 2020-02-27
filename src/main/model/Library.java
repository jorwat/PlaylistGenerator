package model;

import persistence.Saveable;
import persistence.Reader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// Represents a Library of Playlists with a Username
public class Library implements Saveable {

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
    public String viewPlaylists(String username) {
        for (Playlist p : library) {
            str += "\n" + p.getPlaylistName() + ": a "
                    + p.getPlaylistGenre()
                    + " playlist with " + p.getTotalSongs()
                    + " song/songs running at " + p.getTotalRuntime() + " seconds!";
        }
        return username + "'s Library" + str;
    }

    // REQUIRES: playlist must exist within the library
    // MODIFIES: this, and playlist
    // EFFECTS: matches string to playlist and adds song to playlist
    public void matchAndAdd(String playlist, Song song) {
        for (Playlist p : library) {
            if (p.getPlaylistName().equals(playlist)) {
                p.addSong(song);
            }
        }
    }

    // REQUIRES: must have playlists in the library
    // EFFECTS: matches string with playlist name and returns total runtime
    public int matchAndFindRuntime(String playlist) {
        int runtime = 0;

        for (Playlist p : library) {
            if (p.getPlaylistName().equals(playlist)) {
                runtime = p.getTotalRuntime();
            }
        }
        return runtime;
    }

    // REQUIRES: playlist must exist within the library, playlist must have songs on it
    // EFFECTS: matches the name of the playlist with string,
    //          and displays songs in the playlist
    public String matchAndViewSongs(String playlist) {
        for (Playlist p : library) {
            if (p.getPlaylistName().equals(playlist)) {
                str = p.toStringPlaylist();
            }
        }
        return str;
    }

    // EFFECTS: saves playlist data to save file
    @Override
    public void save(PrintWriter printWriter) {

        for (Playlist p : library) {
            p.changeTag(username);
            printWriter.print(p.getTag());
            printWriter.print(Reader.DELIMITER);
            printWriter.print(p.getPlaylistName());
            printWriter.print(Reader.DELIMITER);
            printWriter.print(p.getPlaylistGenre());
            printWriter.print(Reader.DELIMITER);
            printWriter.print(p.getTotalSongs());
            printWriter.print(Reader.DELIMITER);
            printWriter.print(p.getTotalRuntime());
            printWriter.print(Reader.PLAYLIST_DELIMITER);
        }
    }

}

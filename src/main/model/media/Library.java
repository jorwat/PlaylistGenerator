package model.media;

import exceptions.NoPlaylistException;
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
    public String viewPlaylists() {
        str = "";

        for (Playlist p : library) {
            str += "\n" + p.getPlaylistName() + ": a "
                    + p.getPlaylistGenre()
                    + " playlist with " + p.getTotalSongs()
                    + " song/songs running at " + p.getTotalRuntime() + " seconds!";
        }
        return str;
    }

    // MODIFIES: this, and playlist
    // EFFECTS: matches string to playlist and adds song to playlist.
    // If no playlist is found NoPlaylistException is thrown.
    public void matchAndAdd(String playlist, Song song) throws NoPlaylistException {
        for (Playlist p : library) {
            if (p.getPlaylistName().equals(playlist)) {
                p.addSong(song);
            } else if (!p.getPlaylistName().equals(playlist) || library == null) {
                throw new NoPlaylistException();
            }
        }
    }

    // EFFECTS: matches string with playlist name and returns total runtime.
    //          If no playlist is found NoPlaylistException is thrown.
    public int matchAndFindRuntime(String playlist) throws NoPlaylistException {
        int runtime = 0;

        for (Playlist p : library) {
            if (p.getPlaylistName().equals(playlist)) {
                runtime = p.getTotalRuntime();
            } else {
                throw new NoPlaylistException();
            }
        }
        return runtime;
    }

    // EFFECTS: matches the name of the playlist with string,
    //          and displays songs in the playlist. If no playlist is found
    //          NoPlaylistException is thrown
    public String matchAndViewSongs(String playlist) throws NoPlaylistException {
        for (Playlist p : library) {
            if (p.getPlaylistName().equals(playlist)) {
                str = p.toStringPlaylist();
            } else {
                throw new NoPlaylistException();
            }
        }
        return str;
    }

    // MODIFIES: this
    // EFFECTS: sets user name before save file
    public void setUsername(String name) {
        username = name;
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

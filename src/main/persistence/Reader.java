package persistence;

import model.Playlist;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read library data from a file
// NOTE: template of reader is based on the teller app, but refactored for my implementation.
public class Reader {
    public static final String DELIMITER = ",";
    public static final String NAME_LIMITER = "\n";

    // EFFECTS: returns a list of playlists parsed from file; throws
    // IOException if an exception is raised when opening / reading the file
    public static List<Playlist> readPlaylists(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of playlists parsed from the list of strings
    // where each string contains the data for one playlist
    private static List<Playlist> parseContent(List<String> fileContent) {
        List<Playlist> playlists = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineData = splitString(line);
            playlists.add(parsePlaylist(lineData));
        }

        return playlists;
    }

    // EFFECTS: returns a list of strings obtained by splitting line with the DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components have size 5 where element 0 is the username of the playlist,
    // element 1 represents the playlist name, element 2 represents the genre of the playlist,
    // element 3 represents the total songs in the playlist, and element 4 represents the total runtime
    // of the playlist
    private static Playlist parsePlaylist(List<String> components) {
        String playlistName = components.get(1);
        String playlistGenre = components.get(2);
        int totalSongs = Integer.parseInt(components.get(3));
        int totalRuntime = Integer.parseInt(components.get(4));
        return new Playlist(playlistName,playlistGenre);
    }

}

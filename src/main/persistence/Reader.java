package persistence;

import model.Playlist;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read library data from a file/files
// NOTE: template of reader is based on the teller app, but refactored for my implementation.
public class Reader {
    public static final String DELIMITER = ",";
    public static final String PLAYLIST_DELIMITER = "\n";

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
            ArrayList<String> lineComponents = splitString(line);
            playlists.add(parsePlaylist(lineComponents));
        }

        return playlists;
    }

    // EFFECTS: returns a list of strings obtained by splitting playlist data with the DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components have size 4 where element 0 is the name of the playlist, element 1 is the genre,
    // element 2 is the total songs, and element 3 is the run time
    private static Playlist parsePlaylist(List<String> components) {
        String username = components.get(0);
        String playlistName = components.get(1);
        String playlistGenre = components.get(2);
        int totalSongs = Integer.parseInt(components.get(3));
        int totalRuntime = Integer.parseInt(components.get(4));
        return new Playlist(username,playlistName,playlistGenre,totalSongs,totalRuntime);
    }




}

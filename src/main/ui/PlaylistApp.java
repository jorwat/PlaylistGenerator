package ui;


import model.Library;
import model.Playlist;
import model.Song;
import persistence.Reader;
import persistence.Writer;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

// Playlist application
public class PlaylistApp extends JFrame {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private static final String PLAYLIST_FILE = "./data/library.txt";
    private Library library;
    private Scanner input;
    private String username;

    // EFFECTS: runs the playlist application. Starts
    public PlaylistApp() {
        runPlaylist();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPlaylist() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        String command;

        loadLibrary();
        System.out.println("\nHello " + username + "!");

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads library from PLAYLIST_FILE if file exists
    // otherwise initializes a library with user selected name.
    private void loadLibrary() {
        try {
            List<Playlist> playlists = Reader.readPlaylists(new File(PLAYLIST_FILE));
            Playlist loadPlaylist = playlists.get(0);
            library = new Library(loadPlaylist.getTag());
            username = loadPlaylist.getTag();
            for (Playlist p : playlists) {
                library.addPlaylist(p);
            }

        } catch (IOException e) {
            init();
        }
    }

    // EFFECTS: saves state of library to PLAYLIST_FILE
    private void saveLibrary() {
        try {
            Writer writer = new Writer(new File(PLAYLIST_FILE));
            writer.write(library);
            writer.close();
            System.out.println("Library Saved to " + PLAYLIST_FILE + "!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save library to file" + PLAYLIST_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes library
    private void init() {
        initialMenu();
        username = input.next();
        library = new Library(username);
    }

    // EFFECTS: displays menu of options to the user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> create playlist");
        System.out.println("\ta -> add song");
        System.out.println("\tv -> find length of a playlist");
        System.out.println("\tx -> view contents of playlist");
        System.out.println("\ts -> save contents of your library");
        System.out.println("\tl -> view contents of library");
        System.out.println("\td -> delete library");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: asks the user for a username
    private void initialMenu() {
        System.out.println("\nHello! What is your name?");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            createPlaylist();
        } else if (command.equals("a")) {
            addSong();
        } else if (command.equals("v")) {
            playlistLength();
        } else if (command.equals("x")) {
            viewSongs();
        } else if (command.equals("s")) {
            saveLibrary();
        } else if (command.equals("l")) {
            viewLibraryContent();
        } else if (command.equals("d")) {
            deleteLibrary(new File(PLAYLIST_FILE));
        } else {
            System.out.println("Selection is not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a playlist in the library
    private void createPlaylist() {
        String name;
        String genre;

        System.out.println("\nWhat would you like to call it?");
        name = input.next();
        System.out.println("\nWhat kind of genre is it");
        genre = input.next();
        Playlist playlist = new Playlist(name,genre);
        library.addPlaylist(playlist);
    }

    // MODIFIES: this
    // EFFECTS: asks the user for a song and adds it to the playlist
    private void addSong() {
        Song song;
        String name;
        String artist;
        String genre;
        int runtime;
        String playlist;

        System.out.println("\nWhat is the song called?");
        name = input.next();
        System.out.println("\nWho is the artist?");
        artist = input.next();
        System.out.println("\nWhat genre is it?");
        genre = input.next();
        System.out.println("\nHow long is it in seconds?");
        runtime = input.nextInt();
        System.out.println("\nWhat playlist would you like to add it to?");
        playlist = input.next();
        song = new Song(name,artist,genre,runtime);

        library.matchAndAdd(playlist, song);
    }

    // EFFECTS; Returns runtime of playlist selected by user
    private void playlistLength() {
        String name;
        System.out.println("\nWhat playlist would you like to check?");
        name = input.next();
        System.out.println(library.matchAndFindRuntime(name));
    }

    // REQUIRES: Playlist must exist
    // EFFECTS: views the playlist as a list of strings
    private void viewSongs() {
        String name;

        System.out.println("\nWhat playlist would you like to view?");
        name = input.next();
        System.out.println(library.matchAndViewSongs(name));
    }

    // REQUIRES: library must have a playlist
    // EFFECTS: displays playlists within the library
    private void viewLibraryContent() {
        System.out.println(library.viewPlaylists(username));
    }

    // REQUIRES: user must close the program to see change reflected
    // MODIFIES: this
    // EFFECTS: deletes PLAYLIST_FILE
    private void deleteLibrary(File file) {
        try {
            Files.deleteIfExists(file.toPath());
            System.out.println("Library deleted at " + PLAYLIST_FILE + "!"
                    + " New Library will be made once application is" + "closed!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Unable to delete as file doesn't exist at" + PLAYLIST_FILE);
        }
    }
}
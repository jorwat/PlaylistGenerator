package ui;


import model.Library;
import model.Playlist;
import model.Song;


import java.util.Scanner;

// Playlist application
public class PlaylistApp {
    private Library library;
    private Scanner input;

    // EFFECTS: runs the playlist application
    public PlaylistApp() {
        runPlaylist();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPlaylist() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        String username;
        String command;

        while (keepGoing) {
            initialMenu();
            username = input.next();
            library = new Library("");
            System.out.println("\nHello " + username + "!");
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

    // EFFECTS: displays menu of options to the user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> create playlist");
        System.out.println("\ta -> add song");
        System.out.println("\tv -> find length of a playlist");
        System.out.println("\ts -> view contents of playlists");
        System.out.println("\tl -> view contents of library");
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
        } else if (command.equals("s")) {
            viewSongs();
        } else if (command.equals("l")) {
            library.viewPlaylists();
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

        System.out.println("\nWhat is the song called");
        name = input.next();
        System.out.println("\nWho is the artist?");
        artist = input.next();
        System.out.println("\nWhat genre is it?");
        genre = input.next();
        System.out.println("\nHow long is it in seconds?");
        runtime = input.nextInt();
        System.out.println("\nWhat playlist would you like to add it too");
        playlist = input.next();
        song = new Song(name,artist,genre,runtime);

        for (Playlist p : library) {
            if (p.getPlaylistName() == playlist) {
                p.addSong(song);
            }
        }
    }

    // EFFECTS; Returns runtime of playlist selected by user
    private int playlistLength() {
        String name;
        int runtime = 0;
        System.out.println("\nWhat playlist would you like to check?");
        name = input.next();

        for (Playlist p : library) {
            if (p.getPlaylistName().equals(name)) {
                runtime = p.getTotalRuntime();
            }
        }
        return runtime;
    }

    // REQUIRES: Playlist must exist
    // EFFECTS: views the playlist as a list of strings
    private void viewSongs() {
        String name;

        System.out.println("\nWhat playlist would you like to view?");
        name = input.next();
        for (Playlist p : library) {
            if (p.getPlaylistName() == name) {
                p.toStringPlaylist();
            }
        }
    }
}
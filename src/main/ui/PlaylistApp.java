package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Library;
import model.Playlist;
import model.Song;
import persistence.Reader;
import persistence.Writer;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

// Playlist application
public class PlaylistApp extends Application {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private static final String PLAYLIST_FILE = "./data/library.txt";

    private Stage window;
    private Library library;
    private String username;
    private int red;
    private int blue;
    private int green;

    // MODIFIES: this
    // EFFECTS: loads library from PLAYLIST_FILE if file exists
    // otherwise initializes a library with user selected name.
    private void loadLibrary() throws IOException {
        try {
            List<Playlist> playlists = Reader.readPlaylists(new File(PLAYLIST_FILE));
            Playlist loadPlaylist = playlists.get(0);
            library = new Library(loadPlaylist.getTag());
            username = loadPlaylist.getTag();
            for (Playlist p : playlists) {
                library.addPlaylist(p);
            }

        } catch (IOException e) {
            throw new IOException();
        }
    }

    // EFFECTS: saves state of library to PLAYLIST_FILE
    private void saveLibrary() throws FileNotFoundException, UnsupportedEncodingException {
        try {
            Writer writer = new Writer(new File(PLAYLIST_FILE));
            writer.write(library);
            writer.close();
            System.out.println("Library Saved to " + PLAYLIST_FILE + "!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save library to file" + PLAYLIST_FILE);
            throw new FileNotFoundException();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new UnsupportedEncodingException();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a playlist in the library
    private void createPlaylist(String name, String genre) {
        Playlist playlist = new Playlist(name, genre);
        library.addPlaylist(playlist);
    }

    // MODIFIES: this
    // EFFECTS: asks the user for a song and adds it to the playlist
    private void addSong(String name, String artist, String genre, String runtime, String playlist) {
        Song song;
        song = new Song(name, artist, genre, Integer.parseInt(runtime));
        library.matchAndAdd(playlist, song);
    }

    // REQUIRES: user must close the program to see change reflected
    // MODIFIES: this
    // EFFECTS: deletes PLAYLIST_FILE
    private void deleteLibrary(File file) throws IOException {
        try {
            Files.deleteIfExists(file.toPath());
            System.out.println("Library deleted at " + PLAYLIST_FILE + "!"
                    + " New Library will be made once application is" + "closed!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        } catch (IOException e) {
            System.out.println("Unable to delete as file doesn't exist at" + PLAYLIST_FILE);
            throw new IOException();
        }
    }

    //EFFECTS: starts application with GUI
    public static void main(String[] args) {
        launch(args);
    }


    // MODIFIES: this
    // EFFECTS: creates a window for the application to operate in and handles scene switching
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        window.setScene(initialScene());
        window.setTitle("MyPersonalPlaylist");
        window.show();
    }


    // MODIFIES: this
    // EFFECTS: returns a scene object that appears at the very beginning. Library checking will occur here.
    private Scene initialScene() {
        VBox s = new VBox();
        Button b = new Button("Start");
        s.setAlignment(Pos.CENTER);

        try {
            loadLibrary();
            b.setOnAction(e -> window.setScene(welcomeScreenScene()));
        } catch (IOException ex) {
            s.getChildren().addAll(b);
            b.setOnAction(e -> window.setScene(noLibraryScene()));
        }
        return new Scene(s, 400, 400);
    }

    // MODIFIES: this
    // EFFECTS: returns a scene object when no library is detected
    private Scene noLibraryScene() {
        VBox s = new VBox();
        Text title = new Text("What is your name?");
        TextField field = new TextField();
        Button b = new Button("Create Library");

        title.setStyle("-fx-font:28 arial;");
        field.setPromptText("Enter your name here and then hit enter");
        field.setFocusTraversable(false);
        s.setAlignment(Pos.CENTER);
        s.getChildren().addAll(title, field, b);
        b.setOnAction(e -> {
            username = field.getText();
            window.setScene(welcomeScreenScene());
        });
        library = new Library(username);

        return new Scene(s, 400, 400);
    }

    // MODIFIES: this
    // EFFECTS: returns a scene object once a library is loaded or instantiated
    private Scene welcomeScreenScene() {
        VBox s = new VBox();
        Text message = new Text("Hello " + username);
        Button b = new Button("Enter Library");

        message.setTextAlignment(TextAlignment.CENTER);
        message.setStyle("-fx-font: 28 arial;");
        b.setPrefSize(100, 40);
        s.setAlignment(Pos.CENTER);
        s.getChildren().addAll(message, b);
        b.setOnAction(e -> window.setScene(mainMenuScene()));

        return new Scene(s, WIDTH, HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: returns a scene object of the main menu with buttons for options
    private Scene mainMenuScene() {
        VBox s = new VBox();
        Text message = new Text(username + "'s Library");
        Button createPlaylist = new Button("Create Playlist");
        Button addSong = new Button("Add a Song");
        Button findPlaylistLength = new Button("Find Length of a Playlist");
        Button viewSongs = new Button("View Contents of a Playlist");
        Button saveLibrary = new Button("Save Contents of your Library");
        Button viewLibrary = new Button("View Contents of Library");
        Button deleteLibrary = new Button("Delete Library");
        Button quit = new Button("Quit application");

        s.setAlignment(Pos.CENTER);
        s.getChildren().addAll(message, createPlaylist, addSong, findPlaylistLength, viewSongs,
                saveLibrary, viewLibrary, deleteLibrary, quit);
        createPlaylist.setOnAction(e -> window.setScene(createPlaylistScene()));
        addSong.setOnAction(e -> window.setScene(addSongScene()));
        findPlaylistLength.setOnAction(e -> window.setScene(playlistLengthScene()));
        viewSongs.setOnAction(e -> window.setScene(viewSongsScene()));
        saveLibrary.setOnAction(e -> window.setScene(saveLibraryScene()));
        viewLibrary.setOnAction(e -> window.setScene(viewLibraryScene()));
        deleteLibrary.setOnAction(e -> window.setScene(deleteLibraryScene()));
        quit.setOnAction(e -> window.close());

        return new Scene(s, WIDTH, HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: returns a scene object of a create playlist screen with actions
    private Scene createPlaylistScene() {
        VBox s = new VBox();
        Text message = new Text(username + "'s Library");
        Text q1 = new Text("What would you like to call it?");
        Text q2 = new Text("What kind of genre is it?");
        TextField nameField = new TextField();
        TextField genreField = new TextField();
        Button submit = new Button("Submit");
        Button menu = new Button("Return to Main Menu");

        s.setAlignment(Pos.CENTER);
        s.getChildren().addAll(message, q1, nameField, q2, genreField, submit, menu);

        submit.setOnAction(e -> createPlaylist(nameField.getText(), genreField.getText()));
        menu.setOnAction(e -> window.setScene(mainMenuScene()));

        return new Scene(s, WIDTH, HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: returns a scene object of the add song scene with actions
    private Scene addSongScene() {
        VBox s = new VBox();
        Text header = new Text(username + "'s Library");
        Text q1 = new Text("What is the song called?");
        Text q2 = new Text("Who is the artist");
        Text q3 = new Text("What genre is it?");
        Text q4 = new Text("What is the runtime? (Only type in Integers)");
        Text q5 = new Text("What playlist would you like to add it too?");
        TextField nameField = new TextField();
        TextField artistField = new TextField();
        TextField genreField = new TextField();
        TextField runtimeField = new TextField();
        TextField playlistField = new TextField();
        Button submit = new Button("Submit");
        Button menu = new Button("Return to Main Menu");

        s.setAlignment(Pos.CENTER);
        s.getChildren().addAll(header, q1, nameField, q2, artistField, q3, genreField, q4, runtimeField,
                q5, playlistField, submit, menu);

        submit.setOnAction(e -> addSong(nameField.getText(), artistField.getText(), genreField.getText(),
                runtimeField.getText(), playlistField.getText()));
        menu.setOnAction(e -> window.setScene(mainMenuScene()));

        return new Scene(s, WIDTH, HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: returns a scene object which prompts the user for a playlist to find a runtime of.
    private Scene playlistLengthScene() {
        VBox s = new VBox();
        Text header = new Text(username + "'s Library");
        TextField field = new TextField();
        Text q1 = new Text("What playlist would you like to check?");
        Button submit = new Button("Submit for runtime");
        Button menu = new Button("Return to Main Menu");

        s.setAlignment(Pos.CENTER);
        s.getChildren().addAll(header, q1, field, submit, menu);
        submit.setOnAction(e -> {
            String playlist = field.getText();
            Text runtime = new Text(Integer.toString(library.matchAndFindRuntime(playlist)));
            s.getChildren().add(runtime);
        });
        menu.setOnAction(e -> window.setScene(mainMenuScene()));

        return new Scene(s, WIDTH, HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: returns a scene object of the view songs scene with actions
    private Scene viewSongsScene() {
        VBox s = new VBox();
        Text header = new Text(username + "'s Library");
        TextField field = new TextField();
        Text q1 = new Text("What playlist would you like to check?");
        Button submit = new Button("Submit for songs");
        Button menu = new Button("Return to Main Menu");

        s.setAlignment(Pos.CENTER);
        s.getChildren().addAll(header, q1, field, submit, menu);
        submit.setOnAction(e -> {
            String playlist = field.getText();
            Text songs = new Text(library.matchAndViewSongs(playlist));
            s.getChildren().add(songs);
        });
        menu.setOnAction(e -> window.setScene(mainMenuScene()));

        return new Scene(s, WIDTH, HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: returns a scene object of the save library scene with actions
    private Scene saveLibraryScene() {
        VBox s = new VBox();
        Button b = new Button("Return to Main Menu");
        Text header = new Text(username + "'s Library");
        s.setAlignment(Pos.CENTER);

        try {
            saveLibrary();
            Text message = new Text(username + "'s Library Saved to " + PLAYLIST_FILE + "!");
            s.getChildren().addAll(header, message, b);
            b.setOnAction(e -> window.setScene(mainMenuScene()));
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Text errorMessage = new Text("Unable to save library to file" + PLAYLIST_FILE);
            s.getChildren().addAll(header, errorMessage, b);
            b.setOnAction(e -> window.setScene(mainMenuScene()));
        }

        return new Scene(s, WIDTH, HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: returns a scene object of the view library scene with actions
    private Scene viewLibraryScene() {
        VBox s = new VBox();
        Text viewLibrary = new Text(library.viewPlaylists(username));
        Text header = new Text(username + "'s Library");
        Button menu = new Button("Return to Main Menu");

        s.setAlignment(Pos.CENTER);
        s.getChildren().addAll(header, viewLibrary, menu);
        menu.setOnAction(e -> window.setScene(mainMenuScene()));

        return new Scene(s, WIDTH, HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: returns a scene object of the delete library scene with actions
    private Scene deleteLibraryScene() {
        VBox s = new VBox();
        Button b = new Button("Return to Main Menu");
        Text header = new Text(username + "'s Library");
        s.setAlignment(Pos.CENTER);

        try {
            deleteLibrary(new File(PLAYLIST_FILE));
            Text message = new Text("Library deleted at " + PLAYLIST_FILE + "!"
                    + " New Library will be made once application is" + "closed!");
            s.getChildren().addAll(header, message, b);
            b.setOnAction(e -> window.setScene(mainMenuScene()));
        } catch (IOException ex) {
            Text message = new Text("Unable to delete the file at " + PLAYLIST_FILE);
            s.getChildren().addAll(header, message, b);
            b.setOnAction(e -> window.setScene(mainMenuScene()));
        }
        return new Scene(s, WIDTH, HEIGHT);
    }

   /* // MODIFIES: this
    // EFFECTS: event handler for a clicking sound within the program
    // CODE FROM STACKOVERFLOW
    // https://stackoverflow.com/questions/44274098/javafx-play-a-sound-when-ever-something-is-clicked

    @Override
    public void handle(ActionEvent event) {
        if (KeyEvent == KeyCode.SPACE) {
            Random rand = new Random();
        }
    }

    */
}
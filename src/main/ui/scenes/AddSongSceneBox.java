package ui.scenes;

import exceptions.NoPlaylistException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.media.Library;
import model.media.Song;
import ui.SceneBox;

// The AddSongScene that prompts the user to add a song
public class AddSongSceneBox extends SceneBox {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private Stage window;
    private Library library;
    private String username;

    private Text q1;
    private Text q2;
    private Text q3;
    private Text q4;
    private Text q5;
    private TextField nameField;
    private TextField artistField;
    private TextField genreField;
    private TextField runtimeField;
    private TextField playlistField;
    private Button submit;
    private Button menu;

    public AddSongSceneBox(Stage window, Library library, String username) {
        super(username);
        this.username = username;
        this.window = window;
        this.library = library;
        q1 = new Text("What is the song called?");
        q2 = new Text("Who is the artist");
        q3 = new Text("What genre is it?");
        q4 = new Text("What is the runtime? (Only type in Integers)");
        q5 = new Text("What playlist would you like to add it too?");
        nameField = new TextField();
        artistField = new TextField();
        genreField = new TextField();
        runtimeField = new TextField();
        playlistField = new TextField();
        submit = new Button("Submit");
        menu = new Button("Return to Main Menu");
        setNodes();
    }

    // MODIFIES: this
    // EFFECTS: sets functionality of buttons
    private void setNodes() {
        this.getChildren().addAll(q1, nameField, q2, artistField, q3, genreField, q4, runtimeField,
                q5, playlistField, submit, menu);
        submit.setOnAction(e -> {
            String str = addSongHelper(nameField.getText(), artistField.getText(), genreField.getText(),
                    runtimeField.getText(), playlistField.getText());
            Text message = new Text(str);
            this.getChildren().add(message);
        });

        menu.setOnAction(e -> window.setScene(new Scene((new MainMenuSceneBox(
                window,library,username)),WIDTH,HEIGHT)));
    }

    // MODIFIES: this
    // EFFECTS: asks the user for a song and adds it to the playlist
    private void addSong(String name, String artist, String genre,
                         String runtime, String playlist) throws NoPlaylistException {
        Song song;
        song = new Song(name, artist, genre, Integer.parseInt(runtime));

        library.matchAndAdd(playlist, song);
    }

    // MODIFIES: this
    // EFFECTS: helper function for addSongScene that prints string and handles exception
    private String addSongHelper(String name, String artist, String genre, String runtime, String playlist) {
        String message;

        try {
            addSong(name, artist, genre, runtime, playlist);
            message = "Song has been added!";
        } catch (NoPlaylistException e) {
            message = "Error. Please enter a valid playlist.";
        }
        return message;
    }
}

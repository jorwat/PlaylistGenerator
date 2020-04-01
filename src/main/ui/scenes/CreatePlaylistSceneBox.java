package ui.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.media.Library;
import model.media.Playlist;
import ui.SceneBox;

// The CreatePlaylist Scene which prompts the user to create a playlist
public class CreatePlaylistSceneBox extends SceneBox {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private Stage window;
    private Library library;
    private String username;

    private Text q1;
    private Text q2;
    private TextField nameField;
    private TextField genreField;
    private Button submit;
    private Button menu;

    public CreatePlaylistSceneBox(Stage window, Library library, String username) {
        super(username);
        this.window = window;
        this.username = username;
        this.library = library;
        q1 = new Text("What would you like to call it?");
        q2 = new Text("What kind of genre is it?");
        nameField = new TextField();
        genreField = new TextField();
        submit = new Button("Submit");
        menu = new Button("Return to Main Menu");
        setNodes();
    }

    // MODIFIES: this
    // EFFECTS: sets functionality of scene nodes.
    private void setNodes() {
        this.getChildren().addAll(q1, nameField, q2, genreField, submit, menu);
        submit.setOnAction(e -> createPlaylist(nameField.getText(), genreField.getText()));
        menu.setOnAction(e -> window.setScene(new Scene((new MainMenuSceneBox(
                window,library,username)), WIDTH, HEIGHT)));
    }

    // MODIFIES: this
    // EFFECTS: creates a playlist in the library
    private void createPlaylist(String name, String genre) {
        Playlist playlist = new Playlist(name, genre);
        library.addPlaylist(playlist);
    }
}

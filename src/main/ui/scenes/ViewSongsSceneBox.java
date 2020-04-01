package ui.scenes;

import exceptions.NoPlaylistException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SceneBox;
import model.media.Library;

public class ViewSongsSceneBox extends SceneBox {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private Stage window;
    private Library library;
    private String username;

    private TextField field;
    private Text q1;
    private Button submit;
    private Button menu;

    public ViewSongsSceneBox(Stage window, Library library, String username) {
        super(username);
        this.window = window;
        this.library = library;
        this.username = username;
        field = new TextField();
        q1 = new Text("What playlist would you like to check?");
        submit = new Button("Submit for songs");
        menu = new Button("Return to Main Menu");
        setNodes();
    }

    // MODIFIES: this
    // EFFECTS: sets functionality of buttons
    private void setNodes() {
        this.getChildren().addAll(q1, field, submit, menu);
        submit.setOnAction(e -> {
            String playlist = field.getText();
            Text songs = new Text(matchAndViewSongsHelper(playlist));
            this.getChildren().add(songs);
        });
        menu.setOnAction(e -> window.setScene(new Scene(
                (new MainMenuSceneBox(window,library,username)),WIDTH,HEIGHT)));
    }

    // EFFECTS: helper function for matchAndViewSongs to print text and handle exception
    private String matchAndViewSongsHelper(String name) {
        String message;

        try {
            message = library.matchAndViewSongs(name);
        } catch (NoPlaylistException e) {
            message = "Please enter a playlist within the library";
        }
        return message;
    }
}

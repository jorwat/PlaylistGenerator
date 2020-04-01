package ui.scenes;

import exceptions.NoPlaylistException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.media.Library;
import ui.SceneBox;

// THe PlaylistLengthScene which prompts the user for a playlist to find a runtime of.
public class PlaylistLengthSceneBox extends SceneBox {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private Stage window;
    private Library library;
    private String username;

    private TextField field;
    private Text q1;
    private Button submit;
    private Button menu;

    public PlaylistLengthSceneBox(Stage window, Library library, String username) {
        super(username);
        this.window = window;
        this.username = username;
        this.library = library;
        field = new TextField();
        q1 = new Text("What playlist would you like to check?");
        submit = new Button("Submit for runtime");
        menu = new Button("Return to Main Menu");
        setNodes();
    }

    // MODIFIES: this
    // EFFECTS: sets functionality of scene nodes.
    private void setNodes() {
        this.getChildren().addAll(q1, field, submit, menu);
        submit.setOnAction(e -> {
            String playlist = field.getText();
            Text runtime = new Text(matchAndFindRuntimeHelper(playlist));
            this.getChildren().add(runtime);
        });
        menu.setOnAction(e -> window.setScene(new Scene((new MainMenuSceneBox(
                window,library,username)),WIDTH,HEIGHT)));
    }

    // EFFECTS: helper function for matchAndFindRuntimeHelper to print text and Handle exception
    private String matchAndFindRuntimeHelper(String name) {
        String message;

        try {
            message = Integer.toString(library.matchAndFindRuntime(name));
        } catch (NoPlaylistException e) {
            message = "Please enter a playlist within the library";
        }
        return message;
    }
}

package ui.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.media.Library;
import ui.SceneBox;

public class ViewLibrarySceneBox extends SceneBox {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private Stage window;
    private Library library;
    private String username;

    Text viewLibrary;
    Button menu;

    public ViewLibrarySceneBox(Stage window, Library library, String username) {
        super(username);
        this.window = window;
        this.username = username;
        this.library = library;
        viewLibrary = new Text(library.viewPlaylists());
        menu = new Button("Return to Main Menu");
        setNodes();
    }

    // MODIFIES: this
    // EFFECTS: sets functionality of buttons
    private void setNodes() {
        this.getChildren().addAll(viewLibrary, menu);
        menu.setOnAction(e -> window.setScene(new Scene((
                new MainMenuSceneBox(window,library,username)),WIDTH,HEIGHT)));
    }
}

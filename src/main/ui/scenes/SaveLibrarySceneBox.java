package ui.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SceneBox;
import model.media.Library;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class SaveLibrarySceneBox extends SceneBox {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private static final String PLAYLIST_FILE = "./data/library.txt";

    private Stage window;
    private Library library;
    private String username;

    private Button button;

    public SaveLibrarySceneBox(Stage window, Library library, String username) {
        super(username);
        this.window = window;
        this.library = library;
        this.username = username;
        button = new Button("Return to Main Menu");
        setNodes();
    }

    // MODIFIES: this
    // EFFECTS: sets functionality of buttons
    private void setNodes() {
        try {
            saveLibrary();
            Text message = new Text(username + "'s Library Saved to " + PLAYLIST_FILE + "!");
            this.getChildren().addAll(message, button);
            button.setOnAction(e -> window.setScene(new Scene((new MainMenuSceneBox(
                    window,library,username)),WIDTH,HEIGHT)));
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Text errorMessage = new Text("Unable to save library to file" + PLAYLIST_FILE);
            this.getChildren().addAll(errorMessage, button);
            button.setOnAction(e -> window.setScene(new Scene((new MainMenuSceneBox(
                    window,library,username)),WIDTH,HEIGHT)));
        }
    }

    // EFFECTS: saves state of library to PLAYLIST_FILE
    private void saveLibrary() throws FileNotFoundException, UnsupportedEncodingException {
        try {
            String name = username;
            library.setUsername(name);
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
}

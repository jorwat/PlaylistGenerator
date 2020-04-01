package ui.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.media.Library;
import ui.SceneBox;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

public class DeleteLibrarySceneBox extends SceneBox {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private static final String PLAYLIST_FILE = "./data/library.txt";

    private Stage window;
    private Library library;
    private String username;

    private Button button;

    public DeleteLibrarySceneBox(Stage window, Library library, String username) {
        super(username);
        this.window = window;
        this.username = username;
        this.library = library;
        button = new Button("Return to Main Menu");
        setNodes();
    }

    // MODIFIES: this
    // EFFECTS: sets functionality of buttons
    private void setNodes() {
        try {
            deleteLibrary(new File(PLAYLIST_FILE));
            Text message = new Text("Library deleted at " + PLAYLIST_FILE + "!"
                    + " New Library will be made once application is" + "closed!");
            this.getChildren().addAll(message, button);
            button.setOnAction(e -> window.setScene(new Scene((new MainMenuSceneBox(
                    window,library,username)),WIDTH,HEIGHT)));
        } catch (IOException ex) {
            Text message = new Text("Unable to delete the file at " + PLAYLIST_FILE);
            this.getChildren().addAll(message, button);
            button.setOnAction(e -> window.setScene(new Scene((new MainMenuSceneBox(
                    window,library,username)),WIDTH,HEIGHT)));
        }
    }

    // REQUIRES: user must close the program to see change reflected
    // MODIFIES: this
    // EFFECTS: deletes PLAYLIST_FILE
    private void deleteLibrary(File file) throws IOException {
        try {
            Files.deleteIfExists(file.toPath());
            System.out.println("Library deleted at " + PLAYLIST_FILE + "!"
                    + " New Library will be made once application is" + " closed!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        } catch (IOException e) {
            System.out.println("Unable to delete as file doesn't exist at" + PLAYLIST_FILE);
            throw new IOException();
        }
    }

}

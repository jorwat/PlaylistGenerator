package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.media.Library;
import model.media.Playlist;
import persistence.Reader;
import ui.scenes.WelcomeScreenSceneBox;

import java.io.*;
import java.util.List;

// Playlist application
public class PlaylistApp extends Application {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private static final String PLAYLIST_FILE = "./data/library.txt";
    private static final String SOUND_FILE = "./data/song.mp3";

    private Stage window;
    private Library library;
    private String username;

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

    //EFFECTS: starts application with GUI
    public static void main(String[] args) {
        launch(args);
    }

    // MODIFIES: this
    // EFFECTS: creates a window for the application to operate in and handles scene switching
    // CODE FROM STACKOVERFLOW
    // https://stackoverflow.com/questions/44274098/javafx-play-a-sound-when-ever-something-is-clicked
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        Media media = new Media(new File(SOUND_FILE).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        MediaView soundPlayer = new MediaView(player);

        Scene initial = initialScene(soundPlayer);
        KeyCodeCombination ctrlM = new KeyCodeCombination(KeyCode.M,KeyCodeCombination.CONTROL_DOWN);
        initial.setOnKeyPressed(event -> {
            if (ctrlM.match(event)) {
                System.out.println(System.getProperty("user.dir"));
                player.play();
            }
        });
        window.setScene(initial);
        window.setTitle("MyPersonalPlaylist");
        window.show();
    }

    // MODIFIES: this
    // EFFECTS: returns a scene object that appears at the very beginning. Library checking will occur here.
    private Scene initialScene(MediaView player) {
        VBox s = new VBox();
        Button b = new Button("Start");
        s.setAlignment(Pos.CENTER);

        try {
            loadLibrary();
            s.getChildren().addAll(b,player);
            b.setOnAction(e -> window.setScene(new Scene((new WelcomeScreenSceneBox(
                    username,window,library)),WIDTH,HEIGHT)));
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
            window.setScene(new Scene((new WelcomeScreenSceneBox(username,window,library)),WIDTH,HEIGHT));
        });
        library = new Library(username);

        return new Scene(s, 400, 400);
    }
}
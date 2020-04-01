package ui.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.SceneBox;
import model.media.Library;

public class MainMenuSceneBox extends SceneBox {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private Stage window;
    private String username;
    private Library library;

    private Button createPlaylist;
    private Button addSong;
    private Button findPlaylistLength;
    private Button viewSongs;
    private Button saveLibrary;
    private Button viewLibrary;
    private Button deleteLibrary;
    private Button quit;

    public MainMenuSceneBox(Stage window, Library library, String username) {
        super(username);
        this.window = window;
        this.library = library;
        this.username = username;
        createPlaylist = new Button("Create Playlist");
        addSong = new Button("Add a Song");
        findPlaylistLength = new Button("Find Length of a Playlist");
        viewSongs = new Button("View Contents of a Playlist");
        saveLibrary = new Button("Save Contents of your Library");
        viewLibrary = new Button("View Contents of Library");
        deleteLibrary = new Button("Delete Library");
        quit = new Button("Quit application");
        setNodes();
    }

    // MODIFIES: this
    // EFFECTS: sets functionality of buttons
    private void setNodes() {
        this.getChildren().addAll(createPlaylist, addSong, findPlaylistLength, viewSongs,
                saveLibrary, viewLibrary, deleteLibrary, quit);
        createPlaylist.setOnAction(e -> window.setScene(new Scene((new CreatePlaylistSceneBox(
                window,library,username)),WIDTH,HEIGHT)));
        addSong.setOnAction(e -> window.setScene(new Scene((new AddSongSceneBox(
                window,library,username)),WIDTH,HEIGHT)));
        findPlaylistLength.setOnAction(e -> window.setScene(new Scene((new PlaylistLengthSceneBox(
                window,library,username)),WIDTH,HEIGHT)));
        viewSongs.setOnAction(e -> window.setScene(new Scene((new ViewSongsSceneBox(
                window,library,username)),WIDTH,HEIGHT)));
        saveLibrary.setOnAction(e -> window.setScene(new Scene((new SaveLibrarySceneBox(
                window,library,username)),WIDTH,HEIGHT)));
        viewLibrary.setOnAction(e -> window.setScene(new Scene((new ViewLibrarySceneBox(
                window,library,username)),WIDTH,HEIGHT)));
        deleteLibrary.setOnAction(e -> window.setScene(new Scene((new DeleteLibrarySceneBox(
                window,library,username)),WIDTH,HEIGHT)));
        quit.setOnAction(e -> window.close());
    }
}

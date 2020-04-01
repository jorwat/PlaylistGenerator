package ui.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.media.Library;

import javax.swing.*;

// Welcome Screen scene where the user is greeted
public class WelcomeScreenSceneBox extends VBox {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private Stage window;
    private Library library;
    private String username;

    Text message;
    Button button;


    public WelcomeScreenSceneBox(String username, Stage window, Library library) {
        super();
        setAlignment(Pos.CENTER);
        this.window = window;
        this.library = library;
        this.username = username;
        message = new Text("Hello " + username);
        button = new Button("Enter Library");
        setNodes();
    }

    // MODIFIES: this
    // EFFECTS: sets functionality of buttons
    private void setNodes() {
        message.setTextAlignment(TextAlignment.CENTER);
        message.setStyle("-fx-font: 28 arial;");
        button.setPrefSize(100, 40);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(message, button);
        button.setOnAction(e -> window.setScene(new Scene((new MainMenuSceneBox(
                window,library,username)),WIDTH,HEIGHT)));
    }



}

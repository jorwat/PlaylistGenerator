package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene initial;
    Scene mainMenu;

    //EFFECTS: starts application with GUI
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        window = stage;

        Label label = new Label("What is your name?");
        TextField text = new TextField();

        text.setPromptText("Enter your name");
        text.setFocusTraversable(false);
    }

}

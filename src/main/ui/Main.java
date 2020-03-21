package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene noLibrary;
    Scene initial;
    TextField input;
    String name;


    //EFFECTS: starts application with GUI
    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings("checkstyle:MethodLength")
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        input = new TextField();

        StackPane s1 = new StackPane();
        Text title = new Text("What is your name?");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setStyle("-fx-font: 28 arial;");
        input.setPromptText("Enter your name");
        input.setFocusTraversable(false);
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        setName(input);
        s1.getChildren().addAll(title, input);
        input.setOnAction(e -> window.setScene(initial));

        StackPane s2 = new StackPane();
        Text message = new Text("Hello " + name);
        message.setTextAlignment(TextAlignment.CENTER);
        message.setStyle("-fx-font: 28 arial;");
        s2.getChildren().addAll(message);

        noLibrary = new Scene(s1, 400, 400);
        initial = new Scene(s2, 1280,720);

        window.setScene(noLibrary);
        window.setTitle("Playlist manager");
        window.show();

    }

    private void setName(TextField field) {
        field.setOnAction(e -> {
            name = field.getText();
        });
    }
}

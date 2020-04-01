package model;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

// A specialized SceneBox that is used for similar scenes
public class SceneBox extends VBox {
    Text header;

    public SceneBox(String username) {
        super();
        header = new Text(username + "'s Library");
        getChildren().addAll(header);
        setAlignment(Pos.CENTER);
    }
}

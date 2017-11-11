package net.blsquad.menus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenu {
    public static Scene getScene()
    {
        Pane menuPane = new Pane();
        menuPane.getChildren().add(new ImageView(new Image("Menu_App.png")));
        Scene scene = new Scene(menuPane, 571, 253);

        return scene;
    }
}

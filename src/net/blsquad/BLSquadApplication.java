package net.blsquad;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.blsquad.menus.MainMenu;
import net.blsquad.utils.BLSquadInfo;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BLSquadApplication extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        try {
            String version = IOUtils.toString(new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/BLSquad/BLSquad/master/version.txt").openConnection().getInputStream())));
            if(!version.equals(BLSquadInfo.getVersion()))
                if(JOptionPane.showConfirmDialog(null, "There is a new version. Do you want to download it ?", "BLSquad", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    Desktop.getDesktop().browse(new URL("https://github.com/BLSquad/BLSquad/releases/latest").toURI());

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Button loginButton = new Button("Login");
        loginButton.setMinWidth(104);
        loginButton.setMinHeight(23);
        loginButton.setTranslateX(277);
        loginButton.setTranslateY(291);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage buttonStage = ((Stage)((Button) event.getSource()).getScene().getWindow());
                buttonStage.setTitle("BLSquad - Menu");
                buttonStage.setScene(MainMenu.getScene());
            }
        });
        
        Button anyDeskButton = new Button("Anydesk");
        anyDeskButton.setMinWidth(104);
        anyDeskButton.setMinHeight(23);
        anyDeskButton.setTranslateX(5);
        anyDeskButton.setTranslateY(292);
        anyDeskButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Files.write(Paths.get("files\\AnyDesk.exe"), IOUtils.toByteArray(new URL("https://download.anydesk.com/AnyDesk.exe").openConnection().getInputStream()));
                    Runtime.getRuntime().exec("files\\AnyDesk.exe");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Pane launcherPane = new Pane();
        launcherPane.getChildren().add(new ImageView(new Image("Launcher.png")));
        launcherPane.getChildren().add(loginButton);
        launcherPane.getChildren().add(anyDeskButton);
        Scene scene = new Scene(launcherPane, 376, 400);
        stage.getIcons().add(new Image("BLSquad.png"));
        stage.setScene(scene);
        stage.setTitle("BLSquad");
        stage.setResizable(false);
        stage.show();
    }
}

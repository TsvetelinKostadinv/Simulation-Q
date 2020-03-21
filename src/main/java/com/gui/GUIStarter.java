/*
 * 21/03/2020 14:44:20
 * GUIStarter.java created by Tsvetelin
 */
package com.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Tsvetelin
 *
 */
public class GUIStarter extends Application
{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        stage.setTitle("Simulation-Q");
        stage.getIcons().add(new Image( getClass().getResourceAsStream("logo.png") ));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println( "Starting..." );
        launch(args);
    }

}

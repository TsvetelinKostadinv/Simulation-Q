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
public class GUIStarter extends Application implements Runnable
{
    
    public static final String FXML_FILE_LOCATION = "main_window.fxml";
    public static final String CSS_FILE_LOCATION = "styles.css";
    public static final String ICON_FILE_LOCATION = "logo.png";

    public static final String TITLE = "Simulation-Q";
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FXML_FILE_LOCATION));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(CSS_FILE_LOCATION).toExternalForm());
        
        stage.setTitle(TITLE);
        stage.getIcons().add(new Image( getClass().getResourceAsStream(ICON_FILE_LOCATION) ));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void run() {
        System.out.println( "Starting..." );
        GUIStarter.launch(new String[0]);
    }
    
    public static void main ( String [] args )
    {
        GUIStarter s = new GUIStarter();
        
        s.run();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class TheniWindow extends Application {
    
     @Override
     public void start(Stage primaryStage) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("../gui/result.fxml"));
        primaryStage.setTitle("foods Marker");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
       /*
        try {
            Parent root = FXMLLoader.load(getClass().getResource("result.fxml")); 
            Scene scene = new Scene(root);            
            primaryStage.setTitle("Mes Resultats");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

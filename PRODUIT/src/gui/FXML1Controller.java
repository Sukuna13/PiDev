/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class FXML1Controller implements Initializable {
    private Parent fxml;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void interfaceemployés(MouseEvent event) {

        try {
            fxml= FXMLLoader.load(getClass().getResource("gereremployer.fxml"));
            root.getChildren ().removeAll ();
            root.getChildren ().setAll (fxml);
            
            } catch (IOException ex) {
            Logger.getLogger(FXML1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void interfaceproduits(MouseEvent event) {
                try {
            fxml= FXMLLoader.load(getClass().getResource("FXML2.fxml"));
            root.getChildren ().removeAll ();
            root.getChildren ().setAll (fxml);
            
            } catch (IOException ex) {
            Logger.getLogger(FXML1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class InsertionController implements Initializable {

    @FXML
    private TextField tfTaille;
    @FXML
    private TextField tfPoid;
    @FXML
    private TextField tfAge;
    @FXML
    private Button btnValider;
    @FXML
    private MenuButton btnSexe;
    @FXML
    private MenuButton btnObjectif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveParametres(ActionEvent event) {
    }
    
}

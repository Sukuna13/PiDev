/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import edu.esprit.entities.caractérestiques_sportif;
import edu.esprit.services.CreateService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private ChoiceBox<String> choice= new ChoiceBox<>();
    private String[] sexe = {"Femme","Homme"};
    @FXML
    private ChoiceBox<String> Obj= new ChoiceBox<>();
    private String[] Objectif = {"Perte de poids", "Gain de poids"};
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choice.getItems().addAll(sexe);
        Obj.getItems().addAll(Objectif);
        
    }    

    @FXML
    private void saveParametres(ActionEvent event) throws IOException {
        
        int taille = Integer.parseInt(tfTaille.getText());
        int poid = Integer.parseInt(tfPoid.getText());
        int age = Integer.parseInt(tfAge.getText());
        String sexe =(choice.getValue());
        String Objectif =(Obj.getValue());
        
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("result.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
        
        
       
        
        caractérestiques_sportif c = new caractérestiques_sportif(taille, poid, age, sexe, Objectif);
        CreateService pc = new CreateService();
        pc.ajoutercaractérestiques(c);
        
        
    }
       
    
}

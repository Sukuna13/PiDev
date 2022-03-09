/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iEvent;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import entities.Commentaire;
import entities.Forum;
import fitnessny.entities.Utilisateur;
import gui.Sportif.GestionSportifController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author nahaw
 */
public class ForumController implements Initializable {
 public static Utilisateur connected ;
    @FXML
    private Button reviewbtn;
    @FXML
    private Button retourbtn;
    @FXML
    private TableView<?> TableForum;
    @FXML
    private AnchorPane page;
    @FXML
    private Text titre;
    @FXML
    private TableColumn<Commentaire,String > col_categorie;
    @FXML
    private TableColumn<?,?> col_rating;
    @FXML
    private TableColumn<Commentaire, String> col_message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
        
    }    

    @FXML
    private void Gotoreview(ActionEvent event) throws IOException  {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("review.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();    
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        GestionSportifController.connected=connected;
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui/Sportif/GestionSportif.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}

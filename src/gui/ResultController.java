/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import edu.esprit.entities.caractérestiques_sportif;
import edu.esprit.services.ReadService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ResultController implements Initializable {

    @FXML
    private TableView<caractérestiques_sportif> table;
    @FXML
    private Button rtr;
    @FXML
    private Button plat;
    @FXML
    private TableColumn<caractérestiques_sportif, Float> taille_sportif;   
    @FXML
    private TableColumn<caractérestiques_sportif, Float> poid_sportif;
    @FXML
    private TableColumn<caractérestiques_sportif, Integer> age_sportif;
    @FXML
    private TableColumn<caractérestiques_sportif, String> objectif_sportif;
    @FXML
    private TableColumn<caractérestiques_sportif, Integer> besoin_calories;
    @FXML
    private TableColumn<caractérestiques_sportif, Integer> besoin_protein;
    @FXML
    private TableColumn<caractérestiques_sportif, Integer> besoin_carbs;
    @FXML
    private TableColumn<caractérestiques_sportif, Integer> Bmi_sportif;
    @FXML
    private TableColumn<caractérestiques_sportif, Integer> besoin_fat;

    
    
    ObservableList<caractérestiques_sportif> List = FXCollections.observableArrayList();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        caractérestiques_sportif caracterestique = new caractérestiques_sportif();
        ReadService pc = new ReadService();
        System.out.println(pc.displayAllcaractérestiques());
      
        
        
        taille_sportif.setCellValueFactory(new PropertyValueFactory<>("taille_sportif"));
        poid_sportif.setCellValueFactory(new PropertyValueFactory<>("poid_sportif"));
        age_sportif.setCellValueFactory(new PropertyValueFactory<>("age_sportif"));
        objectif_sportif.setCellValueFactory(new PropertyValueFactory<>("objectif_nutrition"));
        besoin_calories.setCellValueFactory(new PropertyValueFactory<>("besoin_calories"));
        besoin_protein.setCellValueFactory(new PropertyValueFactory<>("besoin_protein"));
        besoin_carbs.setCellValueFactory(new PropertyValueFactory<>("besoin_carbs"));
        Bmi_sportif.setCellValueFactory(new PropertyValueFactory<>("Bmi_sportif"));
        besoin_fat.setCellValueFactory(new PropertyValueFactory<>("besoin_fat"));
        
        table.setItems(  pc.displayAllcaractérestiqueso());
       // table.setItems(caractérestiques_sportif) caracterestique);

    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
    
        
        
          Parent tableViewParent = FXMLLoader.load(getClass().getResource("Insertion.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
        
        
     
    } 
        
        
       
    

    @FXML
    private void plats(ActionEvent event) throws IOException {
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../views/market.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
        
    }
    
}

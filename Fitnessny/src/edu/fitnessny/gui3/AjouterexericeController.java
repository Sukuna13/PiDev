/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fitnessny.gui3;

import edu.finessny.services.ExerciceCRUD;
import edu.finessny.utils.MyConnection;
import edu.fitnessny.entities.Exercice;
import edu.fitnessny.entities.Programme;
import edu.fitnessny.gui2.AjouterprogrammeController;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author rocky
 */
public class AjouterexericeController implements Initializable {

    @FXML
    private Button afficherExercice;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfserie;
    @FXML
    private TextField tfrepit;
    @FXML
    private TextArea tfdesc;
    @FXML
    private TextField tfcategorie;
    @FXML
    private Button ajoutexercice;
    @FXML
    private AnchorPane ajout;
    @FXML
    private Button btnProg;
    @FXML
    private Button btnExList;
    private Stage stage;
    private Scene scene;
    private Parent root;
//    private AnchorPane pagePub;

//     @FXML
//    AnchorPane rootPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
    }    

   

    @FXML
    private void SaveExercice(ActionEvent event) {
          Exercice  e = new Exercice();
          int i=0;
          if( tfNom.getText().length() <1 )
        {
        tfNom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        i=1;
        }else {
         e.setNomExercice(tfNom.getText());
        }
        
       
          if( tfserie.getText().length() <1 )
        {
        tfserie.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        i=1;
        }else {
         e.setNbrSerie(Integer.parseInt(tfserie.getText()));
        }
        
          if( tfrepit.getText().length() <1 )
        {
        tfrepit.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        i=1;
        }else {
         e.setNbrSerie(Integer.parseInt(tfrepit.getText()));
        }
          
        if( tfdesc.getText().length() <1 )
        {
        tfdesc.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        i=1;
        }else {
         e.setDescriptionExercice(tfdesc.getText());
        }
          
         if( tfcategorie.getText().length() <1 )
        {
        tfcategorie.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        i=1;
        }else {
        e.setCategorieExercice(tfcategorie.getText());
        }
  
        
        
        
        ExerciceCRUD ex = new ExerciceCRUD(); 
        ex.ajouterExercice(e);
             if (i==0){
 Alert alert = new Alert(Alert.AlertType.CONFIRMATION ,"Votre Exercice a ete ajouter"+tfNom.getText()+"", javafx.scene.control.ButtonType.OK);
 alert.showAndWait();
        }else{   Alert alert = new Alert(Alert.AlertType.ERROR ,"Votre  donner est vide "+tfNom.getText()+"", javafx.scene.control.ButtonType.OK);
 alert.showAndWait(); }
       // RefreshTable();
        
        
        
    }
    
    
     @FXML
    private void AfficherExercice(ActionEvent e) {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherExercice.fxml"));
        try {
           
            Parent root = loader.load() ;
            AfficherExerciceController ew = loader.getController();

            afficherExercice.getScene().setRoot(root);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void NavProg(ActionEvent event) throws IOException {
//            

  root = FXMLLoader.load(getClass().getResource("/Users/rocky/Documents/java/Fitnessny/src/edu/fitnessny/gui2/ajouterprogramme.fxml"));

  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
  
  
  
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("./src/edu/fitnessny/gui2/ajouterprogramme.fxml"));
//        try {
//           
//            Parent root = loader.load() ;
//            AjouterprogrammeController ew = loader.getController();
//
//           AfficherProgramme.getScene().setRoot(root);
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        
           
    }

    
        
        
    }

   


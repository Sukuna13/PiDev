/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fitnessny.gui2;

import edu.finessny.services.ProgrammeCRUD;
import edu.fitnessny.entities.Exercice;
import edu.fitnessny.entities.Programme;
import edu.finessny.utils.MyConnection;
import edu.fitnessny.gui3.AfficherExerciceController;
import edu.fitnessny.gui3.AjouterexericeController;
import edu.fitnessny.gui3.AjouterexericeController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author rocky
 */
public class AjouterprogrammeController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfObjectif;
    @FXML
    private TextField tfCategorie;
    @FXML
    private TextArea taDescription;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TableView<Programme> ProgTable;
    @FXML
    private TableColumn<Programme, String> nom;
    @FXML
    private TableColumn<Programme, String> objectif;
    @FXML
    private TableColumn<Programme, String> categorie;
    @FXML
    private TableColumn<Programme, String> description;
    ObservableList<Programme> Programme = AfficherProgramme();
    @FXML
    private Button btnrefresh;
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Programme programme = null;
    @FXML
    private TextField tfsearch;
    @FXML
    private Button btnajoutex;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        

        nom.setCellValueFactory(new PropertyValueFactory<Programme, String>("nomProgramme"));
        objectif.setCellValueFactory(new PropertyValueFactory<Programme, String>("objectifProgramme"));
        categorie.setCellValueFactory(new PropertyValueFactory<Programme, String>("descriptionProgramme"));
        description.setCellValueFactory(new PropertyValueFactory<Programme, String>("categorieProgramme"));
        ProgTable.setItems(Programme);

        // TODO
    }

    @FXML
    private void SaveProgramme(ActionEvent event) {
      Programme p = new Programme();
      int i=0;
             
          if( tfNom.getText().length() <1 )
        {
        tfNom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        i=1;
        }else {
         p.setNomProgramme(tfNom.getText());
        }
           if( tfObjectif.getText().length() <1 )
              
        {
        tfObjectif.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        i=1;
        }else {
         p.setObjectifProgramme(tfObjectif.getText());
        }
       
        if( tfCategorie.getText().length() <1 )
        {
        tfCategorie.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        i=1;
        }else {
         p.setCategorieProgramme(tfCategorie.getText());
        }
       if( taDescription.getText().length() <1 )
        {
        taDescription.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        i=1;
        }else {
         p.setDescriptionProgramme(taDescription.getText());
        }
       
      
        
       // String Description = taDescription.getText();

     
        ProgrammeCRUD pc = new ProgrammeCRUD();
        pc.ajouterProgramme(p);
        if (i==0){
 Alert alert = new Alert(AlertType.CONFIRMATION ,"Votre Programme a ete ajouter"+tfNom.getText()+"", javafx.scene.control.ButtonType.OK);
 alert.showAndWait();
        }else{   Alert alert = new Alert(AlertType.ERROR ,"Votre  donner est vide "+tfNom.getText()+"", javafx.scene.control.ButtonType.OK);
 alert.showAndWait(); }
        RefreshTable();
    }

    @FXML
    private void update(ActionEvent event) {
        Programme pub = ProgTable.getSelectionModel().getSelectedItem();
        
        String Nom = tfNom.getText();
        String Objectif = tfObjectif.getText();
        String Categorie = tfCategorie.getText();
        String Description = taDescription.getText();

        Programme p = new Programme(Nom, Objectif, Description, Categorie);
        ProgrammeCRUD pc = new ProgrammeCRUD();
        pc.update(p);
       
        RefreshTable();
     
  
    }

    @FXML
    private void delete(ActionEvent event) {

        Programme pub = ProgTable.getSelectionModel().getSelectedItem();

        ProgTable.getItems().removeAll(ProgTable.getSelectionModel().getSelectedItem());

        ProgrammeCRUD service = new ProgrammeCRUD();
        service.delete(pub);
        Alert alert = new Alert(AlertType.CONFIRMATION ,"Aucun Programme avec IdProg ="+tfsearch.getText()+"", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();

    }

    private ObservableList<Programme> AfficherProgramme() {

        ProgrammeCRUD prog = new ProgrammeCRUD();
        List<Programme> p = prog.readAll();

        return FXCollections.observableList(p);

    }

    @FXML
    private void RefreshTable(ActionEvent event) {

        try {
            Programme.clear();

            String query = " SELECT * FROM programme";
            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Programme.add(new Programme(
                        rs.getString("nomProgramme"),
                        rs.getString("objectifProgramme"),
                        rs.getString("categorieProgramme"),
                        rs.getString("descriptionProgramme")));
                ProgTable.setItems(Programme);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AjouterprogrammeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void RefreshTable() {

        try {
            Programme.clear();

            String query = " SELECT * FROM programme";
            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Programme.add(new Programme(
                        rs.getString("nomProgramme"),
                        rs.getString("objectifProgramme"),
                        rs.getString("categorieProgramme"),
                        rs.getString("descriptionProgramme")));
                ProgTable.setItems(Programme);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AjouterprogrammeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Search(ActionEvent event)   {
        
    
        Connection cnx = new MyConnection().getCnx();
        
   String req = "SELECT * FROM Programme where  nomProgramme ='"+tfsearch.getText()+"' or objectifProgramme ='"+tfsearch.getText()+"'";
       int m=0;
        try {
             Statement st = cnx.createStatement();

            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {      
                
            tfNom.setText(rs.getString("nomProgramme"));
             tfObjectif.setText(rs.getString("objectifProgramme"));
              taDescription.setText(rs.getString("categorieProgramme"));
               tfCategorie.setText(rs.getString("descriptionProgramme"));
              
                  m=1;
            
            
            
            
        }
        } catch (SQLException e) {
                        e.printStackTrace();

        }
              
        
       if (m == 0) {
            Alert alert = new Alert(AlertType.ERROR ,"Aucun Programme avec IdProg ="+tfsearch.getText()+"", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
           
       
   
   
}}

    @FXML
    private void NavExercice(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu.fitnessny.gui3/ajouterexercice.fxml"));
        try {

            Parent root = loader.load();
            AjouterexericeController ew = loader.getController();

            ProgTable.getScene().setRoot(root);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        
        
    
    }



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import interfaces.Iproduit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import model.Employe;
import model.Produit;
import services.ServiceProduit;
import services.serviceEmployees;
import util.maConnexion;



/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class GereremployerController implements Initializable {
    serviceEmployees sp= new serviceEmployees();
  Connection cnx = maConnexion.getInstance().getCnx();
            PreparedStatement ps ;
    @FXML
    private TextField txnom;
    @FXML
    private TextField txprenom;
    @FXML
    private TextField txnum;
    @FXML
    private TextField txsalaire;
    @FXML
    private TextField txtype;
    @FXML
    private TableView<Employe> table_produit;
    @FXML
    private TextField txmail;
    @FXML
    private TableColumn<Employe,String> anom;
    @FXML
    private TableColumn<Employe,String> aprenom;
    @FXML
    private TableColumn<Employe,String> amail;
    @FXML
    private TableColumn<Employe,Integer> anum;
    @FXML
    private TableColumn<Employe,Float> asalaire;
    @FXML
    private TableColumn<Employe,String> atype;
    @FXML
    private TextField txt_searchmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afichier();
        
    }    
    ObservableList<Employe> listemp = FXCollections.observableArrayList();
        
    @FXML
    private void afichier() {
         
        try {
            Connection cnx = maConnexion.getInstance().getCnx();
            table_produit.getItems().clear();
            String req = "SELECT nom_emp, prenom_emp, mail, num_emp, salaire, type FROM Employe";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                listemp.add(new Employe(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getString(6)));
          
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            anom.setCellValueFactory(new PropertyValueFactory<Employe, String>("nom_emp"));
            aprenom.setCellValueFactory(new PropertyValueFactory<Employe, String>("prenom_emp"));
            amail.setCellValueFactory(new PropertyValueFactory<Employe, String>("mail"));
            anum.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("num_emp"));
            asalaire.setCellValueFactory(new PropertyValueFactory<Employe, Float>("salaire"));
            atype.setCellValueFactory(new PropertyValueFactory<Employe, String>("type"));
            table_produit.setItems(listemp);
    }



    @FXML
    private void addajout1(ActionEvent event) {
        String nom=txnom.getText();
        String prenom=txprenom.getText();
        String mail=txmail.getText();
        String num=txnum.getText();
        String salaire=txsalaire.getText();
        String type=txtype.getText();
            
         if (!nom.equals("")&&!prenom.equals("")&&!mail.equals("")&&!num.equals("")&&!salaire.equals("")&&!type.equals("")) {
             
                     sp.ajouterEmploye(new Employe(txnom.getText(), txprenom.getText(),txmail.getText(),Integer.parseInt(txnum.getText()),Float.parseFloat(txsalaire.getText()), txtype.getText()));
                     txnom.setText("");
                      txprenom.setText("");
                      txmail.setText("");
                      txnum.setText("");
                      txsalaire.setText("");
                      txtype.setText("");
                   
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION," employe ajouter  avec succes", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
            afichier();
        } else {
          // Alert alert = new Alert(Alert.AlertType.ERROR ,"Erreur de saisie  ="+txmail.getText()+"", javafx.scene.control.ButtonType.OK);
          //alert.showAndWait();
        }
    }

    @FXML
    private void searchemploye(MouseEvent event) {
         Connection cnx = maConnexion.getInstance().getCnx();
        String req = "SELECT * FROM Employe where  mail ='"+txt_searchmail.getText()+"'";
       int m=0;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {                
            txnom.setText(rs.getString("nom_emp"));
            txprenom.setText(rs.getString("prenom_emp"));
            txmail.setText(rs.getString("mail"));
            txnum.setText(rs.getString("num_emp"));
            txsalaire.setText(rs.getString("salaire"));
            txtype.setText(rs.getString("type"));   
                  m=1;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       if (m == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR ,"Aucun employe avec mail ="+txt_searchmail.getText()+"", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
    }
    }
    


    @FXML
    private void editemploye(MouseEvent event) {
        String catt=txnom.getText();
        String libb=txprenom.getText();
        String marc=txmail.getText();
        String pri=txnum.getText();
        String des=txsalaire.getText();
        String desp=txtype.getText();
        
           if (!catt.equals("")&&!libb.equals("")&&!marc.equals("")&&!pri.equals("")&&!des.equals("")&&!desp.equals("")) {
 sp.modifieremploye(new Employe(txt_searchmail.getText(),txnom.getText(), txprenom.getText(),Integer.parseInt(txnum.getText()),Float.parseFloat(txsalaire.getText()), txtype.getText()));
                     
                      txnom.setText("");
                      txprenom.setText("");
                      txmail.setText("");
                      txnum.setText("");
                      txsalaire.setText("");
                      txtype.setText("");
                     
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION," employe modifier  avec succes", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
            afichier();
        } else {
           Alert alert = new Alert(Alert.AlertType.ERROR ,"Erreur de modification  ="+txt_searchmail.getText()+"", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
        }
             
    }
    


    @FXML
    private void Suppemp(MouseEvent event) {
        String nom =txnom.getText();
        String prenom=txprenom.getText();
        String mail=txmail.getText();
        String num=   txnum.getText();
        String salaire= txsalaire.getText();
        String type=  txtype.getText();
    
      
         if (!nom.equals("")&&!prenom.equals("")&&!mail.equals("")&&!num.equals("")&&!salaire.equals("")&&!type.equals("")) {
              sp.suppemploye(new Employe(txnom.getText(), txprenom.getText(),txmail.getText(),Integer.parseInt(txnum.getText()),Float.parseFloat(txsalaire.getText()), txtype.getText()));
                      txnom.setText("");
                      txprenom.setText("");
                      txmail.setText("");
                      txnum.setText("");
                      txsalaire.setText("");
                      txtype.setText("");
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION," employe suprimé  avec succes", javafx.scene.control.ButtonType.OK);
          alert.showAndWait(); 
            afichier();
        } else {
           Alert alert = new Alert(Alert.AlertType.ERROR ,"Erreur de suprission  ="+txt_searchmail.getText()+"", javafx.scene.control.ButtonType.OK);
           alert.showAndWait();
        
         }
    }
}

    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptor.Uri.Factory.newValue;
import interfaces.Iproduit;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Abonement;
import model.Produit;
import services.ServiceAbonnement;
import services.ServiceProduit;
import util.maConnexion;


/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class AbonnementController implements Initializable {
   ServiceAbonnement sp= new ServiceAbonnement();
   Connection cnx = maConnexion.getInstance().getCnx();
            PreparedStatement ps ;
    @FXML
    private TableView<Abonement> table_abn;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef;
    @FXML
    private TableColumn<Abonement, String> nompro;
    @FXML
    private TableColumn<Abonement, String> prenompro;
    @FXML
    private TableColumn<Abonement, Date> datedpro;
    @FXML
    private TableColumn<Abonement, Date> datefinpro;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aficher();
    }    

    @FXML
    private void addanb(ActionEvent event) {
         String nom=txtnom.getText();
         String prenom= txtprenom.getText();
         Date ddate= Date.valueOf(dated.getValue());
         Date fdate= Date.valueOf(datef.getValue());
           
         if (!nom.equals("")&&!prenom.equals("")&&!ddate.equals("")&&!fdate.equals("")) {
             
            sp.ajouterabonnement(new Abonement(txtnom.getText(), txtprenom.getText(),Date.valueOf(dated.getValue()),Date.valueOf(datef.getValue())));
                     txtnom.setText("");
                      txtprenom.setText("");
                      
                       dated.setValue(ddate.toLocalDate());
                       dated.setValue(ddate.toLocalDate());
                     
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION," abonnement ajouter  avec succes", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
           aficher();
        } else {
           Alert alert = new Alert(Alert.AlertType.ERROR ,"Erreur de saisie  =", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
        }
        
        
    }

    ObservableList<Abonement> listprod = FXCollections.observableArrayList();


    @FXML
    private void aficher() {
        Connection cnx = maConnexion.getInstance().getCnx();
           table_abn.getItems().clear();
     String req = "SELECT * FROM abonement";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                listprod.add(new Abonement( rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4)));
          
            }
        
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        nompro.setCellValueFactory(new PropertyValueFactory<Abonement, String>("nom_emp"));
        prenompro.setCellValueFactory(new PropertyValueFactory<Abonement, String>("prenom_emp")); 
        datedpro.setCellValueFactory(new PropertyValueFactory<Abonement, Date>("dated")); 
        datefinpro.setCellValueFactory(new PropertyValueFactory<Abonement, Date>("datef")); 
              
        table_abn.setItems(listprod); 
    
    
    }
}

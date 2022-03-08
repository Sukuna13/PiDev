/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField loginid;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void login(ActionEvent event) {
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("../gui/Ajout.fxml"));
            Parent root=loader.load();
            username.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        Personne p=new Personne;
        p.setCin(Integer.parseInt(cin.getText()));
        p.setNom(nom.getText());
        p.setPrenom(prenom.getText());
        p.setDate(Date.valueOf(date.getValue()));
        PersonneService ps =new PerseonneService();
        ps.ajouter(p);
    }

    @FXML
    private void afficher(ActionEvent event) {
    }
    
}

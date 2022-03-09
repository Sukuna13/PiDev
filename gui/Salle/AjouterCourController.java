/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Salle;

import entities.CourSalle;
import fitnessny.entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import static java.time.LocalDate.now;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.CourSalleCRUD;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjouterCourController implements Initializable {

    @FXML
    private TextField nomCour;
    @FXML
    private Spinner<Integer> nbrTotal;
    @FXML
    private TextField info;
    @FXML
    private Button ajouterCour;
    @FXML
    private Button retour;
    @FXML
    private DatePicker date;
    @FXML
    private TextField minute;
    @FXML
    private TextField heure;

    String Chars = "(.*[A-z].*)";
    String number = "(.*[0-9].*)";
    String specialChar = "(.*[!,@,#,$,%,&,*,(,),’,+,-,.,,,/,:,;,<,=,>,?,[,],^,_,`,{,|,},§].*$)";

    /**
     * Initializes the controller class.
     */
    CourSalleCRUD cs = new CourSalleCRUD();
    public static Utilisateur connected ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 10);
        nbrTotal.setValueFactory(valueFactory);
    }

    @FXML
    private void ajouterCour(ActionEvent event) {
        if (nomCour.getText().isEmpty()
                || nbrTotal.getValue() == null
                || date.getValue() == null
                || minute.getText().isEmpty()
                || heure.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Information manquante !!!!");
            alert.showAndWait();
        } else if (date.getValue().isBefore(now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Date incorrecte !!!!");
            alert.showAndWait();
        } else if (heure.getText().toString().matches(specialChar) || heure.getText().toString().matches(Chars) || Integer.parseInt(heure.getText()) > 24 || Integer.parseInt(heure.getText()) < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Heure invalide !!!!");
            alert.showAndWait();
        } else if (minute.getText().toString().matches(specialChar) || minute.getText().toString().matches(Chars) || Integer.parseInt(minute.getText()) > 60 || Integer.parseInt(minute.getText()) < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Minute invalide !!!!");
            alert.showAndWait();
        } else {
            int nbrTotal0 = nbrTotal.getValue();
            Date date0 = Date.valueOf(date.getValue());
            String nomCour0 = nomCour.getText();
            String info0 = info.getText();
            Time tCour = new Time(Integer.parseInt(heure.getText()), Integer.parseInt(minute.getText()), 00);
            CourSalle cour = new CourSalle(connected.getId(), nomCour0, date0, tCour, nbrTotal0, info0);
            if (cs.testCourExsitant(cour) == false) {
                Notifications notificationBuilder = Notifications.create().title("Gestion Cour").text("Le Cour a été ajouté ").hideAfter(Duration.seconds(1)).position(Pos.CENTER);
                notificationBuilder.show();
                cs.ajouterCour(cour);
                GestionCourController.connected=connected;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionCour.fxml"));
                try {
                    Parent root = loader.load();
                    nomCour.getScene().setRoot(root);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Cour deja existant !!!!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        GestionCourController.connected=connected;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionCour.fxml"));
        try {
            Parent root = loader.load();
            nomCour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

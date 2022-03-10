/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Sportif;

import entities.Abonnement;
import entities.CourCoach;
import entities.CourSalle;
import fitnessny.entities.Utilisateur;
import fitnessny.service.GetService;
import static gui.Coach.GestionCoachController.connected;
import iEvent.ForumController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.MessagingException;
import services.CourCoachCRUD;
import services.CourSalleCRUD;
import utils.JavaMail;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class GestionSportifController implements Initializable {

    @FXML
    private Button cours;
    @FXML
    private Button coachs;
    @FXML
    private TableView<CourSalle> tablecoursalle;
    @FXML
    private TableView<CourCoach> tablecourcoach;
    @FXML
    private TableView<Abonnement> tableabonnement;
    @FXML
    private TableColumn<Abonnement, String> salle;
    @FXML
    private TableColumn<Abonnement, Date> datedebut;
    @FXML
    private TableColumn<Abonnement, Date> datefin;
    @FXML
    private TableColumn<CourSalle, String> nom;
    @FXML
    private TableColumn<CourSalle, Date> date;
    @FXML
    private TableColumn<CourSalle, Time> tCour;
    @FXML
    private TableColumn<CourSalle, Integer> nbrTotal;
    @FXML
    private TableColumn<CourSalle, Integer> nbrActuel;
    @FXML
    private TableColumn<CourSalle, String> info;
    @FXML
    private Button supprimerCour;
    @FXML
    private TableColumn<CourCoach, String> nomprenom;
    @FXML
    private TableColumn<CourCoach, Date> date1;
    @FXML
    private TableColumn<CourCoach, Time> heure;

    /**
     * Initializes the controller class.
     */
    CourSalleCRUD cs = new CourSalleCRUD();
    CourCoachCRUD cc = new CourCoachCRUD();
    public static Utilisateur connected ;
    @FXML
    private TableColumn<CourSalle, String> salle1;
    @FXML
    private TableColumn<CourCoach, String> etat;
    @FXML
    private Button supprimerreservation;
    @FXML
    private Button forum;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         GetService gs = new GetService();
        connected = gs.getByMail(connected.getMailAddress());
        System.out.println("gestion sportif"+connected);
        tableCoachListener();
        tableCourListener();
        afficherTableAbonnement();
        afficherTableReservationCoach();
        afficherTableCourSalle();
    }

    @FXML
    private void listecours(ActionEvent event) {
        ReserverCourSalleController.connected=connected;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverCourSalle.fxml"));
        try {
            Parent root = loader.load();
            cours.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void afficherTableAbonnement() {
        ObservableList<Abonnement> list = FXCollections.observableArrayList(cs.abonnement(connected));
        salle.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("nomSalle"));
        datedebut.setCellValueFactory(new PropertyValueFactory<Abonnement, Date>("datedebut"));
        datefin.setCellValueFactory(new PropertyValueFactory<Abonnement, Date>("datefin"));
        tableabonnement.setItems(list);
    }

    public void afficherTableCourSalle() {
        ObservableList<CourSalle> list = FXCollections.observableArrayList(cs.affichageReservationCourSalleEffectue(connected));
        nom.setCellValueFactory(new PropertyValueFactory<CourSalle, String>("nomCour"));
        salle1.setCellValueFactory(new PropertyValueFactory<CourSalle, String>("nomSalle"));
        date.setCellValueFactory(new PropertyValueFactory<CourSalle, Date>("date"));
        tCour.setCellValueFactory(new PropertyValueFactory<CourSalle, Time>("tCour"));
        nbrTotal.setCellValueFactory(new PropertyValueFactory<CourSalle, Integer>("nbrTotal"));
        nbrActuel.setCellValueFactory(new PropertyValueFactory<CourSalle, Integer>("nbrActuel"));
        info.setCellValueFactory(new PropertyValueFactory<CourSalle, String>("info"));
        tablecoursalle.setItems(list);
    }

    public void tableCourListener() {
        tablecoursalle.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                supprimerCour.setDisable(false);
            } else {
                supprimerCour.setDisable(true);
            }
        });
    }

    public void tableCoachListener() {
        tablecourcoach.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                supprimerreservation.setDisable(false);
            } else {
                supprimerreservation.setDisable(true);
            }
        });
    }

    @FXML
    private void supprimerReservationCour(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre choix : La reservation sera annulée!!!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            CourSalle cour = tablecoursalle.getSelectionModel().getSelectedItem();
            cs.supprimerReservationCourSalle(connected, cour);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionSportif.fxml"));
            try {
                Parent root = loader.load();
                cours.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void listeCoachsDisponibilites(ActionEvent event) {
        ListeCoachsDisponibilitesController.connected=connected;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listeCoachsDisponibilites.fxml"));
        try {
            Parent root = loader.load();
            cours.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void afficherTableReservationCoach() {
        ObservableList<CourCoach> list = FXCollections.observableArrayList(cc.afficherReservationEffectue(connected));
        System.out.println(list);
        nomprenom.setCellValueFactory(new PropertyValueFactory<CourCoach, String>("nomCoach"));
        etat.setCellValueFactory(new PropertyValueFactory<CourCoach, String>("etat"));
        date1.setCellValueFactory(new PropertyValueFactory<CourCoach, Date>("date"));
        heure.setCellValueFactory(new PropertyValueFactory<CourCoach, Time>("time"));
        tablecourcoach.setItems(list);
    }

    @FXML
    private void annulerReservation(ActionEvent event) throws MessagingException {
        CourCoach cour = tablecourcoach.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre choix : La reservation sera annulée!!!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println(cour.getEtat());
            System.out.println(cour.getEtat()=="Accepte");
            if (cour.getEtat() == "Accepte") {
                JavaMail.sendMail(cc.getEmailById(cour.getIdCoach()), "reservationAnnuléeParSportif", cour);
            }
            cc.annulerReservation(connected, cour.getDate(), cour.getTime(), cour.getIdCoach());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionSportif.fxml"));
            try {
                Parent root = loader.load();
                cours.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void forum(ActionEvent event) {
        ForumController.connected=connected;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/iEvent/forum.fxml"));
        try {
            Parent root = loader.load();
            cours.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

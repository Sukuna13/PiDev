/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Sportif;

import entities.CourCoach;
import fitnessny.entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.CourCoachCRUD;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ListeCoachsDisponibilitesController implements Initializable {

    @FXML
    private Button demandereservation;
    @FXML
    private TableView<CourCoach> table;
    @FXML
    private TableColumn<CourCoach, String> nomprenom;
    @FXML
    private TableColumn<CourCoach, Date> date;
    @FXML
    private TableColumn<CourCoach, Time> heure;
    @FXML
    private Button retour;
    @FXML
    private TextField recherche;
    @FXML
    private RadioButton pnom;
    @FXML
    private RadioButton pdate;
    @FXML
    private RadioButton pheure;
    /**
     * Initializes the controller class.
     */
    int searchtype = 0;
    ToggleGroup group = new ToggleGroup();
    CourCoachCRUD cc = new CourCoachCRUD();
   public static Utilisateur connected ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherTable();
        tableListener();
        pnom.setToggleGroup(group);
        pdate.setToggleGroup(group);
        pheure.setToggleGroup(group);
        pnom.setSelected(true);System.out.println("gestion sportif"+connected);
    }

    public void afficherTable() {
        ObservableList<CourCoach> list = FXCollections.observableArrayList(cc.afficherToutesLesDisponibilite());
        System.out.println(list);
        nomprenom.setCellValueFactory(new PropertyValueFactory<CourCoach, String>("nomCoach"));
        date.setCellValueFactory(new PropertyValueFactory<CourCoach, Date>("date"));
        heure.setCellValueFactory(new PropertyValueFactory<CourCoach, Time>("time"));
        table.setItems(list);
        FilteredList<CourCoach> filteredData = new FilteredList<>(list, b -> true);
        System.out.println(filteredData);
        recherche.setOnKeyPressed(e -> {
            recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super CourCoach>) cour -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if (searchtype == 0) {
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (cour.getNomCoach().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        }
                    }
                    if (searchtype == 2) {
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (String.valueOf(cour.getTime()).toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        }
                    }
                    if (searchtype == 1) {
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (String.valueOf(cour.getDate()).toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        }
                    }
                    return false;
                });
            });
            SortedList<CourCoach> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });
    }

    public void tableListener() {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                demandereservation.setDisable(false);
            } else {
                demandereservation.setDisable(true);
            }
        });
    }

    @FXML
    private void demandeReservation(ActionEvent event) {
        CourCoach cour = table.getSelectionModel().getSelectedItem();
        cour.setIdCoach(cc.findIdUserByName(cour.getNomCoach()));
        System.out.println(cour);
        if (cc.testCourExsitant(connected, cour)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez deja reservé pour ce cour !!!!");
            alert.showAndWait();
        } else {
            Notifications notificationBuilder = Notifications.create().title("Gestion Sportif").text("Votre demande a été envoyée ").hideAfter(Duration.seconds(1)).position(Pos.CENTER);
            notificationBuilder.show();
            cc.demandeReservation(connected, cour);
        }
    }

    @FXML
    private void retour(ActionEvent event) {
                GestionSportifController.connected=connected;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionSportif.fxml"));
        try {
            Parent root = loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void choice(ActionEvent event) {
        if (pnom.isSelected()) {
            searchtype = 0;
            recherche.setText("");
        } else if (pdate.isSelected()) {
            searchtype = 1;
            recherche.setText("");
        } else if (pheure.isSelected()) {
            searchtype = 2;
            recherche.setText("");
        }
    }
}

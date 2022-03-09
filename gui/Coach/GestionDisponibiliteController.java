/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Coach;

import static application.AdminHome.connected;
import entities.CourCoach;
import fitnessny.entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleButton;
import java.lang.Integer;
import java.sql.Date;
import java.sql.Time;
import static java.time.LocalDate.now;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
public class GestionDisponibiliteController implements Initializable {

    @FXML
    private Button confirmer;
    @FXML
    private ToggleButton H7;
    @FXML
    private ToggleButton H13;
    @FXML
    private ToggleButton H10;
    @FXML
    private ToggleButton H18;
    @FXML
    private ToggleButton H17;
    @FXML
    private ToggleButton H16;
    @FXML
    private ToggleButton H15;
    @FXML
    private ToggleButton H14;
    @FXML
    private ToggleButton H11;
    @FXML
    private ToggleButton H8;
    @FXML
    private ToggleButton H20;
    @FXML
    private ToggleButton H9;
    @FXML
    private ToggleButton H12;
    @FXML
    private ToggleButton H19;
    @FXML
    private Button retour;
    @FXML
    private TableView<CourCoach> table;
    @FXML
    private Button supprimer;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableColumn<CourCoach, Date> date;
    @FXML
    private TableColumn<CourCoach, Time> time;
    @FXML
    private TextField recherche;
    @FXML
    private RadioButton bydate;
    @FXML
    private RadioButton bytime;
    ToggleGroup group = new ToggleGroup();

    int searchtype = 0;
    CourCoachCRUD cc = new CourCoachCRUD();
    static Utilisateur connected ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        H7.setUserData(7);
        H8.setUserData(8);
        H9.setUserData(9);
        H10.setUserData(10);
        H11.setUserData(11);
        H12.setUserData(12);
        H13.setUserData(13);
        H14.setUserData(14);
        H15.setUserData(15);
        H16.setUserData(16);
        H17.setUserData(17);
        H18.setUserData(18);
        H19.setUserData(19);
        H20.setUserData(20);
        afficherTableAbonnement();
        tableCourListener();
        bytime.setToggleGroup(group);
        bydate.setToggleGroup(group);
        bytime.setSelected(true);
    }

    public void afficherTableAbonnement() {
        ObservableList<CourCoach> list = FXCollections.observableArrayList(cc.afficherDisponibilite(connected));
        System.out.println(list);
        date.setCellValueFactory(new PropertyValueFactory<CourCoach, Date>("date"));
        time.setCellValueFactory(new PropertyValueFactory<CourCoach, Time>("time"));
        table.setItems(list);
        FilteredList<CourCoach> filteredData = new FilteredList<>(list, b -> true);
        System.out.println(filteredData);
        recherche.setOnKeyPressed(e -> {
            recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super CourCoach>) cour -> {
                    if (searchtype == 0) {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (String.valueOf(cour.getTime()).toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        }
                        return false;
                    } else {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (String.valueOf(cour.getDate()).toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        }
                        return false;
                    }
                });
            });
            SortedList<CourCoach> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });
    }

    public void tableCourListener() {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                supprimer.setDisable(false);
            } else {
                supprimer.setDisable(true);
            }
        });
    }

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionCoach.fxml"));
        try {
            Parent root = loader.load();
            H7.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterDisponibilite(ActionEvent event) {
        List<String> tb = new ArrayList<>();
        if (H7.isSelected()) {
            tb.add(H7.getUserData().toString());
        }
        if (H8.isSelected()) {
            tb.add(H8.getUserData().toString());
        }
        if (H9.isSelected()) {
            tb.add(H9.getUserData().toString());
        }
        if (H10.isSelected()) {
            tb.add(H10.getUserData().toString());
        }
        if (H11.isSelected()) {
            tb.add(H11.getUserData().toString());
        }
        if (H12.isSelected()) {
            tb.add(H12.getUserData().toString());
        }
        if (H13.isSelected()) {
            tb.add(H13.getUserData().toString());
        }
        if (H14.isSelected()) {
            tb.add(H14.getUserData().toString());
        }
        if (H15.isSelected()) {
            tb.add(H15.getUserData().toString());
        }
        if (H16.isSelected()) {
            tb.add(H16.getUserData().toString());
        }
        if (H17.isSelected()) {
            tb.add(H17.getUserData().toString());
        }
        if (H18.isSelected()) {
            tb.add(H18.getUserData().toString());
        }
        if (H19.isSelected()) {
            tb.add(H19.getUserData().toString());
        }
        if (H20.isSelected()) {
            tb.add(H20.getUserData().toString());
        }
        if (datePicker.getValue() == null || datePicker.getValue().isBefore(now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Date incorrecte !!!!");
            alert.showAndWait();
        } else if (tb.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Selectionner une heure !!!!");
            alert.showAndWait();
        } else {
            Notifications notificationBuilder = Notifications.create().title("Gestion Coach").text("La reservation a été ajoutée ").hideAfter(Duration.seconds(1)).position(Pos.CENTER);
            notificationBuilder.show();
            Date date0 = Date.valueOf(datePicker.getValue());
            for (String time : tb) {
                if (!cc.testReservationExistante(connected, date0, new Time(Integer.parseInt(time), 0, 0))) {
                    cc.ajouterDisponibilite(connected, date0, new Time(Integer.parseInt(time), 0, 0));
                }
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionDisponibilite.fxml"));
            try {
                Parent root = loader.load();
                H7.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void supprimerDsiponibilite(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre choix : La selection sera supprimée!!!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Notifications notificationBuilder = Notifications.create().title("Gestion Coach").text("La disponibilitée a été supprimée ").hideAfter(Duration.seconds(1)).position(Pos.CENTER);
            notificationBuilder.show();
            CourCoach cour = table.getSelectionModel().getSelectedItem();
            cc.supprimerDisponibilite(connected, cour);
            afficherTableAbonnement();
        }
    }

    @FXML
    private void choice(ActionEvent event) {
        if (bytime.isSelected()) {
            searchtype = 0;
            recherche.setText("");
        } else if (bydate.isSelected()) {
            searchtype = 1;
            recherche.setText("");
        }
    }

}

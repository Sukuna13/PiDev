/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iEvent;

import entities.Event;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.EventCrud;

/**
 * FXML Controller class
 *
 * @author nahaw
 */
public class AddEventController implements Initializable {

    @FXML
    private AnchorPane EventScene_id;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef;
    @FXML
    private Button insererimg;
    
    @FXML
    public String imgurl;
    private URI imguriUri;
    String imgp;
    
    @FXML
    private TextField img_id;
    @FXML
    private ImageView imgview;
    @FXML
    private TextField nbrplaces;
    @FXML
    private TextField locationevent;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private TableView<?> Table_event;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_name;
    @FXML
    private TableColumn<?, ?> col_dated;
    @FXML
    private TableColumn<?, ?> col_datef;
    @FXML
    private TableColumn<?, ?> col_nbrplaces;
    @FXML
    private TableColumn<?, ?> col_location;
    @FXML
    private TableColumn<?, ?> col_img;
    
    
 EventCrud ec = new EventCrud();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_dated.setCellValueFactory(new PropertyValueFactory<>("date_dabut"));
        col_datef.setCellValueFactory(new PropertyValueFactory<>("date_fin"));  
        col_img.setCellValueFactory(new PropertyValueFactory<>("img"));
        col_nbrplaces.setCellValueFactory(new PropertyValueFactory<>("nbr_places")); 
        col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        
        //Table_event.setItems(ec.afficherEvent());
        Table_event.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    } 
//convertion :
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
//controle de saisie:
   public Boolean ValidateDate() {
        if ((datef.getValue().isBefore(dated.getValue())) | (dated.getValue().isAfter(java.time.LocalDate.now())) | (datef.getValue().isAfter(java.time.LocalDate.now()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
           
            alert.setContentText("Please Enter a valid date");
            alert.showAndWait();
            return false;
        }
        return true;
        
    }

     public Boolean ValidateFields() {
        if (name.getText().isEmpty() | dated.getValue().toString().isEmpty() | datef.getValue().toString().isEmpty() | nbrplaces.getText().isEmpty() | locationevent.getText().isEmpty()| img_id.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Into The Fields");
            alert.showAndWait();
            return false;
        }

        return true;

    }
    @FXML
    private void AjouterEvent(ActionEvent event) {
        String nomEvent = name.getText();
        LocalDate DateDebut = dated.getValue();
        LocalDate DateFin = datef.getValue();
        String img = img_id.getText();
        int nbrPlaces = Integer.parseInt(nbrplaces.getText());
        String location = locationevent.getText();
        
       if (ValidateFields() && ValidateDate()) {
            EventCrud ec = new EventCrud();
            Date dd = convertToDateViaSqlDate(DateDebut);
            Date df = convertToDateViaSqlDate(DateFin);
            Event e = new Event(nomEvent, dd, df,nbrPlaces, location,img);
            ec.ajouterEvent2(e);
            javafx.scene.Parent tableview = null;
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();

    } 
    clearFields();
    }
    
      private void clearFields() {
        id.clear();
        name.clear();
        dated.setValue(null);
        datef.setValue(null);
        nbrplaces.clear();
        locationevent.clear();
    }

    @FXML
    private void supprimerEvent(ActionEvent event) {
        ObservableList<Event> selectedRows, allRows;
        allRows = (ObservableList<Event>) Table_event.getItems();
        selectedRows = (ObservableList<Event>) Table_event.getSelectionModel().getSelectedItems();
        for (Event e : selectedRows) {
            allRows.remove(e);
            ec.supprimerEvent(e.getIdEvent());
    }}

    @FXML
    private void modifierEvent(ActionEvent event) {
        //recuperer
        String nomEvent = name.getText();
        LocalDate DateDebut = dated.getValue();
        LocalDate DateFin = datef.getValue();
        String img = img_id.getText();
        
        int nbrPlaces = Integer.parseInt(nbrplaces.getText());
        String location = locationevent.getText();
        
       
        //creer instance
        Date sd = convertToDateViaSqlDate(DateDebut);
        Date ed = convertToDateViaSqlDate(DateFin);
        Event e = new Event(nomEvent, sd, ed,nbrPlaces, location, img);

        //update event on the view:
        int l = Table_event.getSelectionModel().getSelectedIndex();
        //Table_event.getItems().set(l, e);
        ec.modifierEvent(e);
        clearFields();
    }

    @FXML
    private void showDetails(MouseEvent event) {
        Event e = (Event) Table_event.getSelectionModel().getSelectedItem();
        
        name.setText(e.getNomEvent());
        //dated.setValue(e.getDateDebut());
        //datef.setValue(e.getDateFin());
        //heured.setText(e.getHeureDebut());
        //heuref.setText(e.getHeureFin());
        locationevent.setText(e.getLocation());
        
    }

    @FXML
    private void getDateDebut(ActionEvent event) {
    }
    
    @FXML
    private void insererimg(ActionEvent event) throws FileNotFoundException {
        
        
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            imgurl = selectedfile.toURI().toString();
            String fileName = imgurl.substring(imgurl.lastIndexOf('/') + 1, imgurl.length());
            img_id.setText(fileName);
            Image image = new Image(imgurl);
            imgview.setImage(image);
            imguriUri = selectedfile.toURI();
            imgp = selectedfile.getName();
        }
    }  
    
}

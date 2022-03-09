/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iEvent;
import entities.Commentaire;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import services.CommentaireCrud;
/**
 * FXML Controller class
 *
 * @author nahaw
 */
public class ReviewController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Label category;
    @FXML
    private Label rating;
    @FXML
    private Label content;
    @FXML
    private TextField contenufield;
    @FXML
    private Button send;
    @FXML
    private Button cancel;
    @FXML
    private Rating stars;
    @FXML
    private TextField categoryfield;

    /**
     * Initializes the controller class.
     */
    CommentaireCrud cc = new CommentaireCrud();
    List<Commentaire> cm = cc.afficherCommentaireCrud();
    @FXML
    private Button Retour;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        stars.ratingProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               title.setText("Rating"+newValue);
            }           
        });
    }    
 public Boolean ValidateFields() {
        if (category.getText().isEmpty() | content.getText().isEmpty() | rating.getText().isEmpty()) {
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
    private void AddReview(ActionEvent AddReview) throws IOException {
        String cat= categoryfield.getText();
        String con= contenufield.getText();
       if (ValidateFields()) {
           
        Commentaire c = new Commentaire(cat,con);
        cc.ajouterCommentaire2(c);
        ObservableList<Commentaire> datalist= FXCollections.observableArrayList(cm);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText("Review added with succes");
        alert.showAndWait();
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("review.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)AddReview.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
       }  clearFields();
    }
       
 private void clearFields() {
        categoryfield.clear();
        contenufield.clear();
    }
    @FXML
    private void cancel (ActionEvent cancel) throws IOException {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Review.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)cancel.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("forum.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    
}

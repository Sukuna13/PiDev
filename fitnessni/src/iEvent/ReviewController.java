/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iEvent;

import entities.Commentaire;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        stars.ratingProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               title.setText("Rating"+newValue);
            }
            
        });
    }    

    @FXML
    private void AddReview(ActionEvent AddReview) throws IOException {
        String cat= categoryfield.getText();
        String con= contenufield.getText();
  
        CommentaireCrud cc = new CommentaireCrud();
        //Commentaire c = new Commentaire(cat,con);
        //cc.insertReviewPST(c);      
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText("Review added with succes");
        alert.showAndWait();
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("review.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)AddReview.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();}
       

    @FXML
    private void cancelButton(ActionEvent cancel) throws IOException {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Review.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)cancel.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
}

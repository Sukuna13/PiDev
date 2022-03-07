package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fitnessny.entities.Utilisateur;
import fitnessny.service.GetService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

public class LoginController implements Initializable{
	
	GetService gs;
	
	@FXML
	private Hyperlink inscrire;
	
	@FXML
	private TextField adresseMail;
	
	@FXML
	private TextField mdp;
	
	
	
	
	
	
	public void login(ActionEvent e) {
		
		gs=new GetService();
		//tester s'il existe
		if(gs.isexist(adresseMail.getText(), mdp.getText())) {
			Utilisateur connected = gs.getByMailAndPwd(adresseMail.getText(), mdp.getText());
			System.out.println(connected);
			//tester s'il est bloqué
			if(connected.getUnbloc()==null) {
			
			//entrer dans la page admin
			if(gs.getByMailAndPwd(adresseMail.getText(), mdp.getText()).getWhoami().equals("Administrateur")
					||gs.getByMailAndPwd(adresseMail.getText(), mdp.getText()).getWhoami().equals("Super")){
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
				try {
					Parent root  = loader.load();
					AdminHome hc = loader.getController();
					
					hc.setTantque(connected, gs.getByMailAndPwd(adresseMail.getText(), mdp.getText()).getNom());
					System.out.println(connected.getNom());
					
					
					//pour ne pas instancier une autre scene
					adresseMail.getScene().setRoot(root);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			//entrer à la page du coach
			else if(gs.getByMailAndPwd(adresseMail.getText(), mdp.getText()).getWhoami().equals("Coach")){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("CoachHome.fxml"));
				try {
					Parent root  = loader.load();
					CoachHomeController hc = loader.getController();
					//pour ne pas instancier une autre scene
					adresseMail.getScene().setRoot(root);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			
			else if(gs.getByMailAndPwd(adresseMail.getText(), mdp.getText()).getWhoami().equals("Sportif")){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("SportifHome.fxml"));
				try {
					Parent root  = loader.load();
					SportifHomeController hc = loader.getController();
					//pour ne pas instancier une autre scene
					adresseMail.getScene().setRoot(root);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			} 
			else if(gs.getByMailAndPwd(adresseMail.getText(), mdp.getText()).getWhoami().equals("Gérant")){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("GerantHome.fxml"));
				try {
					Parent root  = loader.load();
					GérantHomeController hc = loader.getController();
					//pour ne pas instancier une autre scene
					adresseMail.getScene().setRoot(root);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			
			
		
				
			}
			
			
	else {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Bloqué");
				alert.setHeaderText("Vous êtes bloqué(e)! ");
				alert.setContentText("Raison: "+connected.getBlocRaison()+ ", durée: jusqu'à "+connected.getUnbloc());

				alert.showAndWait();
			}
			
			
			
		}else {
			//alerte pour la case d'adresse mail
			adresseMail.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(adresseMail).setDelay(Duration.seconds(0)).play();
    		
			//alerte pour la case du mot de passe
    		mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
    		
		}
		
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	
public void sendMail(ActionEvent e) {
		
		
		
	}


public void inscrire(ActionEvent e) {
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
	try {
		Parent root  = loader.load();
		//pour ne pas instancier une autre scene
		adresseMail.getScene().setRoot(root);
		
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	
}
	
	

}

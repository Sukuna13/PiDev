package application;

import java.net.URL;
import java.util.ResourceBundle;

import fitnessny.entities.Utilisateur;
import fitnessny.service.UpdateService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ResetPassword implements Initializable{
	
	
	@FXML
	public  Text mdpcheck;
	
	@FXML
	public  Text newmdpcheck;
	
	@FXML
	private TextField newmdp;
	
	@FXML
	private TextField newmdpretap;
	
	private String mailaddress="";
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	
	
	public void resetMdp(ActionEvent e) {
		
		
		
		 boolean isValid = true;
         if (newmdp.getText().toString().length() > 15 || newmdp.getText().toString().length() < 8)
         {
        	 newmdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
	    		new animatefx.animation.Shake(newmdp).setDelay(Duration.seconds(0)).play();
	    		mdpcheck.setText("Longueur invalide ! [8-15] ");
                 isValid = false;
         }
         String upperCaseChars = "(.*[A-Z].*)";
         if (!newmdp.getText().toString().matches(upperCaseChars ))
         {
        	 newmdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
	    		new animatefx.animation.Shake(newmdp).setDelay(Duration.seconds(0)).play();
	    		mdpcheck.setText("Insérer du majuscule");
                 isValid = false;
         }
         String lowerCaseChars = "(.*[a-z].*)";
         if (!newmdp.getText().toString().matches(lowerCaseChars ))
         {
        	 newmdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
	    		new animatefx.animation.Shake(newmdp).setDelay(Duration.seconds(0)).play();
	    		mdpcheck.setText("Insérer du minuscule");
                 isValid = false;
         }
         String numbers = "(.*[0-9].*)";
         if (!newmdp.getText().toString().matches(numbers ))
         {
        	 newmdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
	    		new animatefx.animation.Shake(newmdp).setDelay(Duration.seconds(0)).play();
	    		mdpcheck.setText("Insérer des chiffres");
                 isValid = false;
         }
         String specialChars = "(.*[@,#,$,%].*$)";
         if (!newmdp.getText().toString().matches(specialChars ))
         {
        	 newmdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
	    		new animatefx.animation.Shake(newmdp).setDelay(Duration.seconds(0)).play();
	    		mdpcheck.setText("Insérer des caractères spéciaux (@,#,$,%)");
                 isValid = false;
         }
         
         if(isValid && !newmdp.getText().toString().equals(newmdpretap.getText().toString())) {
        	 mdpcheck.setText("");
        	 newmdpcheck.setText("Resaisir correctement");
        	 newmdpretap.setStyle("-fx-border-color:red; -fx-border-width:2px;");
	    		new animatefx.animation.Shake(newmdpretap).setDelay(Duration.seconds(0)).play();
         	 newmdp.setStyle(null);
         }
         
 	 
		if(isValid && newmdp.getText().toString().equals(newmdpretap.getText().toString())) {
			UpdateService us = new UpdateService();
			Utilisateur user = new Utilisateur();
			System.out.println("mail:"+mailaddress);
			user.setPassword(newmdp.getText().toString());
			user.setMailAddress(mailaddress);
			us.updateUserpwd(user);

		}
		
		
		
	}
	
	
	
	public void setMailAddress(String address) {
		this.mailaddress=address;
	}

}

package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import fitnessny.configr.MyConnection;
import fitnessny.entities.Utilisateur;
import fitnessny.service.CreateService;
import fitnessny.service.GetService;
import fitnessny.service.UpdateService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class UpdateUser implements Initializable{
	
	
	static Utilisateur connected;
	
	@FXML
	private Text exists;
   
	@FXML
	private Text telControll;
	@FXML
	private TextField nom;
	@FXML
	private TextField prenom;
	@FXML
	private DatePicker datenais;
	@FXML
	private TextField adressemail;
	@FXML
	private TextField mdp;
	
	
	@FXML
	private TextField numtel;
	@FXML
	private TextField adresse;
	
	@FXML
	private Text adressenonvalide;
	
	int nbValid;
	
    
    @FXML
    public void updateIt(ActionEvent event)
{
    	if(nom.getText().length()==0 ) {
    		nom.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(nom).setDelay(Duration.seconds(0)).play();
    	}else {
    		nbValid++;
    		nom.setStyle(null);
    	}
    	
    	if(!isValidEmailAddress(adressemail.getText()) && adressemail.getText().length()!=0) {
    		adressenonvalide.setText("Adresse non valide !");
    		adressemail.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(adressemail).setDelay(Duration.seconds(0)).play();
    		System.out.println(adressemail.getText()+"not valid");
    	}else {
    		adressenonvalide.setText("");
    		adressemail.setStyle(null);
    		nbValid++;

    	}
    	
    	if(adressemail.getText().length()==0) {
    		adressemail.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(adressemail).setDelay(Duration.seconds(0)).play();
    		System.out.println(adressemail.getText()+"not valid");
    	}else {
    		nbValid++;
    		adressemail.setStyle(null);
    	}
    	
    	 
    		if(adresse.getText().length()==0) {
    		adresse.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(adresse).setDelay(Duration.seconds(0)).play();
    	}else {
    		nbValid++;
    		adresse.setStyle(null);
    	}
    	
    	
    		
    		
    		
    		
    		
    		
    		
    		
//    		
//    	 if(mdp.getText().length()==0) {
//    		mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
//    		new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
//    	}else {
//    		mdp.setStyle(null);
//    	}
    	 
    	 //controle de saisie de mot de passe
    	 boolean isValid = true;
            if (mdp.getText().toString().length() > 15 || mdp.getText().toString().length() < 8)
            {
            	mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
	    		new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
            	passwordctrl.setText("Longueur invalide ! [8-15] ");
                    isValid = false;
            }
            String upperCaseChars = "(.*[A-Z].*)";
            if (!mdp.getText().toString().matches(upperCaseChars ))
            {
            	mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
	    		new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
            	passwordctrl.setText("Insérer du majuscule");
                    isValid = false;
            }
            String lowerCaseChars = "(.*[a-z].*)";
            if (!mdp.getText().toString().matches(lowerCaseChars ))
            {
            	mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
	    		new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
            	passwordctrl.setText("Insérer du minuscule");
                    isValid = false;
            }
            String numbers = "(.*[0-9].*)";
            if (!mdp.getText().toString().matches(numbers ))
            {
            	mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
	    		new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
            	passwordctrl.setText("Insérer des chiffres");
                    isValid = false;
            }
            String specialChars = "(.*[@,#,$,%].*$)";
            if (!mdp.getText().toString().matches(specialChars ))
            {
            	mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
	    		new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
            	passwordctrl.setText("Insérer des caractères spéciaux (@,#,$,%)");
                    isValid = false;
            }
            
            if(isValid) {
            	passwordctrl.setText("");
            	mdp.setStyle(null);
            	nbValid++;
            }
    	 
    	 
    	 
    	 
    	 
    	 
    	 

    	 if(datenais.getValue()==null) {
    		 datenais.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(datenais).setDelay(Duration.seconds(0)).play();
    	}else {
    		datenais.setStyle(null);
    		nbValid++;
    	}
    	 
    	
    	 
    	 
    	 
    	 if(prenom.getText().length()==0) {
    		 prenom.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(prenom).setDelay(Duration.seconds(0)).play();
    	}else {
    		prenom.setStyle(null);
    		nbValid++;
    	}
    	 
    	 
    	 

    	 
    	 Boolean valid=true;
    		if(numtel.getText().length()!=8) {
    			numtel.setStyle("-fx-border-color:red; -fx-border-width:2px;");
	    		new animatefx.animation.Shake(numtel).setDelay(Duration.seconds(0)).play();
    			telControll.setText("Longueur invalide !");
    			
    			try {
					Long.parseLong(numtel.getText().toString());
				} catch (NumberFormatException e) {
					telControll.setText("Numéro invalide ");
				}
    		}else if (numtel.getText().length()==8){
    			try {
					Long.parseLong(numtel.getText().toString());
				} catch (NumberFormatException e) {
					numtel.setStyle("-fx-border-color:red; -fx-border-width:2px;");
		    		new animatefx.animation.Shake(numtel).setDelay(Duration.seconds(0)).play();
					telControll.setText("Numéro invalide ");
					valid =false;
				}
    			if (valid) {
    				telControll.setText("");
		    		numtel.setStyle(null);
		    		nbValid++;
    			}
    		}
    	
    	 
    	 
    	
    	if (nbValid==8) {
    			
	    	connected.setNom(nom.getText());
	    	connected.setPrenom(prenom.getText()) ;
	    	connected.setMailAddress(adressemail.getText()) ;
	    	connected.setPassword(mdp.getText());
	    	connected.setNumTel(numtel.getText()); 
	    	connected.setAdresse(adresse.getText()); 
	    	connected.setDateNaissance(Date.valueOf(datenais.getValue()));
	    	
	    	 
			UpdateService us = new UpdateService();
			GetService getsv= new GetService();
			
    		
    		exists.setText("");
    
    	//getting connection
		MyConnection cnx=MyConnection.getInstance();
		us.updateUser(connected);
		if(connected.getWhoami().equals("Administrateur")) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
		try {
			Parent root  = loader.load();
			AdminHome hc = loader.getController();
			hc.setTantque(connected, connected.getNom());
			//pour ne pas instancier une autre scene
			nom.getScene().setRoot(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}}}
    
    }
			

    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.connected=AdminHome.connected;
		System.out.println("init mlou"+this.connected);
		this.nom.setText(connected.getNom());
		this.prenom.setText(connected.getPrenom());
		this.datenais.setValue(this.connected.getDateNaissance().toLocalDate());
		System.out.println("init flkhr"+this.connected);

		nbValid=0;
		
	}
	
	
	




	 public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
	
	
	
	
	String tantque;
	public void setTantque(Utilisateur user, String nom) {
		this.connected=user;
		this.tantque=nom;
		System.out.println("from settq"+this.connected);

	}
	
	
	@FXML
	private Text passwordctrl;
	
	 
	
public void gotoadminlist(ActionEvent e) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListAdmins.fxml"));
		try {
			Parent root  = loader.load();
			AdminListAdmins hc = loader.getController();
			hc.setTantque(connected,connected.getNom());
			//pour ne pas instancier une autre scene
			nom.getScene().setRoot(root);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	

public void gotogerantlist(ActionEvent e) {
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListGerant.fxml"));
	try {
		Parent root  = loader.load();
		AdminListGerantCtrl hc = loader.getController();
		hc.setTantque(connected, connected.getNom());
		//pour ne pas instancier une autre scene
		nom.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}


public void gotosportiflist(ActionEvent e) {
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListSportif.fxml"));
	try {
		Parent root  = loader.load();
		AdminListSportifs hc = loader.getController();
		hc.setTantque(connected,connected.getNom());
		//pour ne pas instancier une autre scene
		nom.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}


public void gotocoachlist(ActionEvent e) {
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListCoach.fxml"));
	try {
		Parent root  = loader.load();
		AdminListCoachsCtrl hc = loader.getController();
		hc.setTantque(connected ,connected.getNom());
		//pour ne pas instancier une autre scene
		nom.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}



public void afficherUsers(ActionEvent e) {
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
	try {
		Parent root  = loader.load();
		AdminHome hc = loader.getController();
		hc.setTantque(connected, connected.getNom());

		//hc.setTantque(this.tantque.getText().toString());

		//pour ne pas instancier une autre scene
		nom.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}

	
	
	
	
	
}
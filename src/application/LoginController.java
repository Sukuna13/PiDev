package application;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
import javafx.scene.text.Text;
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
	
	@FXML
	private Text setmailText;
	
	
	
	
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
	InscriptionController ic = new InscriptionController();
	
	if(adresseMail.getText().toString().equals("")) {
		setmailText.setText("Saisir votre addresse");
	}else if(!ic.isValidEmailAddress(adresseMail.getText().toString())){
		setmailText.setText("Saisir une adresse valide");

	}
	
	else {
	
	String to = adresseMail.getText(); 
    String myaccountEmail="skander.kefi@esprit.tn" ; 
    String password ="213JMT2132" ; 
     Properties properties = new Properties();    
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
  
    Session session = Session.getInstance(properties, new Authenticator() {
    
    
protected PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(myaccountEmail, password);
      
   }

  });
    
      try {
       Message message = new MimeMessage(session) ;
                  
       message.setFrom(new InternetAddress(myaccountEmail));
      
       message.setRecipient(Message.RecipientType.TO, new InternetAddress(to) );
       
       message.setSubject("Récupérer votre mot de passe");
       
       //message.setText("Votre cours est ajoutée avec succ  ");
       
       String htmlCode = "<h2> Voici votre code confirmation </h2>"+LoginController.generateCodeToResetPwd() ; 
       message.setContent(htmlCode,"text/html");
       
       Transport.send(message);
          System.out.println("succes");
          
   } catch (Exception ex) {
	   System.out.println("teb3athech");
      // Logger.getLogger(JavaMainUtil.class.getName()).log(Level.SEVERE, null, ex);
   
   }
	}

   //email.clear();  
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
	


public static String generateCodeToResetPwd() {
// create a string of uppercase and lowercase characters and numbers
String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
String numbers = "0123456789";

// combine all strings
String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

// create random string builder
StringBuilder sb = new StringBuilder();

// create an object of Random class
Random random = new Random();

// specify length of random string
int length = 10;

for(int i = 0; i < length; i++) {

  // generate random index number
  int index = random.nextInt(alphaNumeric.length());

  // get character specified by index
  // from the string
  char randomChar = alphaNumeric.charAt(index);

  // append the character to string builder
  sb.append(randomChar);
}

String randomString = sb.toString();
return randomString;
}




	

}

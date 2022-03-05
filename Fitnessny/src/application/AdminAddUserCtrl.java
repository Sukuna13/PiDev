package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import fitnessny.configr.MyConnection;
import fitnessny.entities.Utilisateur;
import fitnessny.service.CreateService;
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

public class AdminAddUserCtrl  implements Initializable
{
	   
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
	
    
    @FXML
    public void saveit(ActionEvent event)
{
    	if(nom.getText().length()==0 ) {
    		nom.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(nom).setDelay(Duration.seconds(0)).play();
    	}else {
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
    	}
    	
    	if(adressemail.getText().length()==0) {
    		adressemail.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(adressemail).setDelay(Duration.seconds(0)).play();
    		System.out.println(adressemail.getText()+"not valid");
    	}else {
    		adressemail.setStyle(null);
    	}
    	
    	 
    		if(adresse.getText().length()==0) {
    		adresse.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(adresse).setDelay(Duration.seconds(0)).play();
    	}else {
    		adresse.setStyle(null);
    	}
    	
    	
    	 if(mdp.getText().length()==0) {
    		mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
    	}else {
    		mdp.setStyle(null);
    	}
    	 
    	 
    	 

    	 if(datenais.getValue()==null) {
    		 datenais.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(datenais).setDelay(Duration.seconds(0)).play();
    	}else {
    		datenais.setStyle(null);
    	}
    	 
    	 
    	
    	
    	
    	 
    	 
    	 
    	 if(prenom.getText().length()==0) {
    		 prenom.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(prenom).setDelay(Duration.seconds(0)).play();
    	}else {
    		prenom.setStyle(null);
    	}
    	 
    	 
    	 
    	 
    	 if(numtel.getText().length()==0) {
    		 numtel.setStyle("-fx-border-color:red; -fx-border-width:2px;");
    		new animatefx.animation.Shake(numtel).setDelay(Duration.seconds(0)).play();
    	}else {
    		numtel.setStyle(null);
    	}
    	 
    	 
    	
    	if (nom.getText().length()!=0 &&
    			adresse.getText().length()!=0 && mdp.getText().length()!=0){
    		
    			
//    		
//    		
//    		
// 		   // Recipient's email ID needs to be mentioned.
//         String to = "kefiskander99@gmail.com";
//
//         // Sender's email ID needs to be mentioned
//         String from = "skander.kefi@esprit.tn";
//
//         // Assuming you are sending email from through gmails smtp
//         String host = "smtp.gmail.com";
//
//         // Get system properties
//         Properties properties = System.getProperties();
//
//         // Setup mail server
//         properties.put("mail.smtp.host", host);
//         properties.put("mail.smtp.port", "465");
//         properties.put("mail.smtp.ssl.enable", "true");
//         properties.put("mail.smtp.auth", "true");
//
//         // Get the Session object.// and pass username and password
//         Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//
//             protected PasswordAuthentication getPasswordAuthentication() {
//
//                 return new PasswordAuthentication("skander.kefi@esprit.tn", "213JMT2132");
//
//             }
//
//         });
//
//         // Used to debug SMTP issues
//         session.setDebug(true);
//
//         try {
//             // Create a default MimeMessage object.
//             MimeMessage message = new MimeMessage(session);
//
//             // Set From: header field of the header.
//             message.setFrom(new InternetAddress(from));
//
//             // Set To: header field of the header.
//             message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//             // Set Subject: header field
//             message.setSubject("This is the Subject Line!");
//
//             // Now set the actual message
//             message.setText("This is actual message");
//
//             System.out.println("sending...");
//             // Send message
//             Transport.send(message);
//             System.out.println("Sent message successfully....");
//         } catch (MessagingException mex) {
//             mex.printStackTrace();
//         }
    		
    		
    		
    		
    		
    	String name = nom.getText();
    	String prenoom = prenom.getText();
    	String mailadress = adressemail.getText();
    	String motdp = mdp.getText();
    	String whoamii = "Administrateur";
    	String tel = numtel.getText();
    	String adress = adresse.getText();

    	Date datenaissance = Date.valueOf(datenais.getValue());
    	
    	
    	Utilisateur user = new Utilisateur(name, prenoom, adress, tel, motdp, mailadress, datenaissance, whoamii,null,null);
    	//getting connection
		MyConnection cnx=MyConnection.getInstance();
		CreateService gs = new CreateService();
		gs.addUser(user);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
		try {
			Parent root  = loader.load();
			AdminHome hc = loader.getController();
			
			//pour ne pas instancier une autre scene
			nom.getScene().setRoot(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    
    }}
    

    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	



	 public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
	
	
	
	
	
	public void toLogin(ActionEvent e) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
		try {
			Parent root  = loader.load();
			//pour ne pas instancier une autre scene
			nom.getScene().setRoot(root);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	Utilisateur connected;
	@FXML
	private Text tantque;
	public void setTantque( Utilisateur connected, String tantque) {
		this.connected=connected;
		this.tantque.setText(tantque); 
	}
	
	
	
public void gotoadminlist(ActionEvent e) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListAdmins.fxml"));
		try {
			Parent root  = loader.load();
			AdminListAdmins hc = loader.getController();
			hc.setTantque(connected,this.tantque.getText().toString());
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
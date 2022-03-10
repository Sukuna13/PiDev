package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fitnessny.entities.Utilisateur;
import fitnessny.service.CreateService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CodeConfirmation implements Initializable{
	
	@FXML
	private TextField confirmationField;
	
	@FXML
	private Text confirmationText;

	
	int timetosend ;
	public static String confirmatinCode="";
	public String mailaddress="";

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		timetosend=0;
		
	}
	
	
	public static Utilisateur verifyingUser;
	public static Boolean isfrominscription=false;
	public static Boolean isfromlogin=false;

	
	@FXML
	public void checkConfirmationCode(ActionEvent e) {
		System.out.println("confcoddeeee"+confirmatinCode);
		timetosend++;
		
		if(timetosend<4 && !confirmationField.getText().toString().equals(confirmatinCode)) {
			this.confirmationText.setText("Vérifier le code !");
		}else if (timetosend>=4 && !confirmationField.getText().toString().equals(confirmatinCode)
				&& isfromlogin) {
			isfromlogin=false;
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
				try {
					Parent root  = loader.load();
					LoginController cc = loader.getController();
					
					//pour ne pas instancier une autre scene
					confirmationField.getScene().setRoot(root);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			
			
		}
		else if (timetosend>=4 && !confirmationField.getText().toString().equals(confirmatinCode)
				&& isfrominscription) {
			isfrominscription=false;
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
				try {
					Parent root  = loader.load();
					InscriptionController cc = loader.getController();
					
					//pour ne pas instancier une autre scene
					confirmationField.getScene().setRoot(root);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			
			
		}
		
		
		else if(confirmationField.getText().toString().equals(confirmatinCode)
				&& isfrominscription){
			
			
			
			if(verifyingUser.getWhoami().equals("Super") || verifyingUser.getWhoami().equals("Administrateur")) {
				isfrominscription=false;
				AdminHome.connected=verifyingUser;
				CreateService gs = new CreateService();
				gs.addUser(verifyingUser);
				System.out.println("added");
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
				try {
					Parent root  = loader.load();
					AdminHome rp = loader.getController();
					System.out.println("mail:"+mailaddress);
					//pour ne pas instancier une autre scene
					confirmationField.getScene().setRoot(root);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			
			}
		}
		
		
		
		else if(confirmationField.getText().toString().equals(confirmatinCode)
				&& isfromlogin){
			
			isfromlogin=false;
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
				try {
					Parent root  = loader.load();
					ResetPassword rp = loader.getController();
					System.out.println("mail:"+mailaddress);
					rp.setMailAddress(mailaddress);
					//pour ne pas instancier une autre scene
					confirmationField.getScene().setRoot(root);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			
			
		}
		
		
	}




	public void setConfirmatinCode(String confirmatinCode) {
		this.confirmatinCode=confirmatinCode;
	}
	
	
	
	public void setMailAddress(String address) {
		this.mailaddress=address;
	}
	
	
	
	
	public void retour(ActionEvent e) {
		
		if(isfrominscription) {
			isfrominscription=false;
				 FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
					try {
						Parent root  = loader.load();
						InscriptionController cc = loader.getController();
						
						//pour ne pas instancier une autre scene
						confirmationField.getScene().setRoot(root);
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				
		
		}
			
		else {
			isfromlogin=false;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			try {
				Parent root  = loader.load();
				LoginController cc = loader.getController();
				
				//pour ne pas instancier une autre scene
				confirmationField.getScene().setRoot(root);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
		}
			
		}
		
		
	
	
	
	
	
	

}

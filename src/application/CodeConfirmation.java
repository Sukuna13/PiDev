package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
	public String confirmatinCode="";
	public String mailaddress="";

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		timetosend=0;
		
	}
	
	
	
	@FXML
	public void checkConfirmationCode(ActionEvent e) {
		System.out.println("confcoddeeee"+confirmatinCode);
		timetosend++;
		
		if(timetosend<4 && !confirmationField.getText().toString().equals(confirmatinCode)) {
			this.confirmationText.setText("Vérifier le code !");
		}else if (timetosend>=4 && !confirmationField.getText().toString().equals(confirmatinCode)) {
			
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
				try {
					Parent root  = loader.load();
					LoginController cc = loader.getController();
					
					//pour ne pas instancier une autre scene
					confirmationField.getScene().setRoot(root);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			
			
		}else if(confirmationField.getText().toString().equals(confirmatinCode)){
			
			
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
	
	

}

package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import fitnessny.entities.Utilisateur;
import fitnessny.service.DeleteService;
import fitnessny.service.GetService;
import fitnessny.service.UpdateService;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class AdminListAdmins implements Initializable{
	
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private Text userType;
	
	
	
	
	@FXML
	private Text denied;
	
	
	@FXML
	private Text raisonText;

	@FXML
	private Text periodText;
	
	@FXML
	private Text blocError;
	
	@FXML
	private  ChoiceBox<String> blocChoice;
	private String[] whyBlocking = {"takfer barcha","jabri","nattar","frère"};

	
	@FXML 
	private ChoiceBox<String> blocDate;
	private String[] BlockingPeriod = {"1 Jour","7 Jours","15 Jours","1 mois"};

	
	@FXML 
	private TextField blocField;

	int whatdobloc=0;
	
	
	@FXML
	private TextField deleteinput;
	
	@FXML 
	private Hyperlink suprimerField;
	
	@FXML 
	private Hyperlink blocLink;
	
	@FXML
	private Text tantque;
	
	@FXML
	private TableView<Utilisateur> adminTable;
	
	@FXML
	private TableColumn<Utilisateur, Integer> adminId;
	
	@FXML
	private TableColumn<Utilisateur, String> adminName;
	
	@FXML
	private TableColumn<Utilisateur, String> adminprenom;
	
	@FXML
	private TableColumn<Utilisateur, String> adminmailaddress;
	
	
	ObservableList<Utilisateur> users = afficherAdmins();
	
	@FXML
	private TextField search;
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		blocChoice.getItems().addAll(whyBlocking);
		blocDate.getItems().addAll(BlockingPeriod);
		
		
		whatdobloc=0;
		blocChoice.setVisible(false);
		blocDate.setVisible(false);
		blocField.setVisible(false);
		
		
		

		
		whatdo=0;
		deleteinput.setVisible(false);
		
		adminId.setCellValueFactory(new  PropertyValueFactory<Utilisateur,Integer>("id"));
		adminName.setCellValueFactory(new  PropertyValueFactory<Utilisateur,String>("nom"));
		adminprenom.setCellValueFactory(new  PropertyValueFactory<Utilisateur,String>("prenom"));
		adminmailaddress.setCellValueFactory(new  PropertyValueFactory<Utilisateur,String>("mailAddress"));
		adminTable.setItems(users);
		
		
		
		ObservableList data =  adminTable.getItems();
		search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
		            if (oldValue != null && (newValue.length() < oldValue.length())) {
		            	adminTable.setItems(data);
		            }
		            String value = newValue.toLowerCase();
		            ObservableList<Utilisateur> subentries = FXCollections.observableArrayList();

		            long count = adminTable.getColumns().stream().count();
		            for (int i = 0; i < adminTable.getItems().size(); i++) {
		                for (int j = 0; j < count; j++) {
		                    String entry = "" + adminTable.getColumns().get(j).getCellData(i);
		                    if (entry.toLowerCase().contains(value)) {
		                        subentries.add(adminTable.getItems().get(i));
		                        break;
		                    }
		                }
		            }
		            adminTable.setItems(subentries);
		        });
		
		
		
		System.out.println("bloob: "+connected.getRetrievedImage());
		InputStream inputstreatm;
		try {
			inputstreatm = connected.getRetrievedImage().getBinaryStream();
			Image image = new Image(inputstreatm);
			imageView.setImage(image);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public ObservableList<Utilisateur> afficherAdmins(){
		GetService gs = new GetService();
		
		List<Utilisateur> usersList = gs.displayAllUsers();
		
		List admins= usersList.stream().filter(e->e.getWhoami().equals("Administrateur")).collect(Collectors.toList());
		System.out.println(admins);
		
		
		return FXCollections.observableList(admins);
	}
	
public void gotoadminlist(ActionEvent e) {
	
		AdminListAdmins.connected=connected;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListAdmins.fxml"));
		try {
			Parent root  = loader.load();
			AdminListAdmins hc = loader.getController();
			//pour ne pas instancier une autre scene
			hc.setTantque(connected, this.tantque.getText().toString());
			adminTable.getScene().setRoot(root);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	

public void gotogerantlist(ActionEvent e) {
	
	AdminListGerantCtrl.connected=connected;
	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListGerant.fxml"));
	try {
		Parent root  = loader.load();
		AdminListGerantCtrl hc = loader.getController();
		hc.setTantque(connected, this.tantque.getText().toString());
		//pour ne pas instancier une autre scene
		adminTable.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}


public void gotosportiflist(ActionEvent e) {
	
	AdminListSportifs.connected=connected;
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListSportif.fxml"));
	try {
		Parent root  = loader.load();
		AdminListSportifs hc = loader.getController();
		hc.setTantque(connected, this.tantque.getText().toString());
		//pour ne pas instancier une autre scene
		adminTable.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}


public void gotocoachlist(ActionEvent e) {
	
	AdminListCoachsCtrl.connected=connected;
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListCoach.fxml"));
	try {
		Parent root  = loader.load();
		AdminListCoachsCtrl hc = loader.getController();
		hc.setTantque(connected, this.tantque.getText().toString());
		//pour ne pas instancier une autre scene
		adminTable.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}

public void afficherUsers(ActionEvent e) {
	
	AdminHome.connected=connected;
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
	try {
		Parent root  = loader.load();
		AdminHome hc = loader.getController();
		hc.setTantque(connected, connected.getNom());

		//hc.setTantque(this.tantque.getText().toString());

		//pour ne pas instancier une autre scene
		adminTable.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}


static Utilisateur connected;
public void setTantque( Utilisateur connected, String tantque) {
	this.connected=connected;
	this.tantque.setText(tantque+" "+connected.getPrenom()); 
	this.userType.setText(connected.getWhoami());
}


@FXML
private Text notsuper;
public void addAdmin(ActionEvent e) {
	if(connected.getWhoami().equals("Super")) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdmnAddUserCtrl.fxml"));
		try {
			Parent root  = loader.load();
			AdminAddUserCtrl hc = loader.getController();
			hc.setTantque(connected, connected.getNom());
			//hc.setTantque(this.tantque.getText().toString());
			//pour ne pas instancier une autre scene
			adminTable.getScene().setRoot(root);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
	}else {
		notsuper.setText("Permission refusée !");
	}
	
}

boolean intid=true;
int whatdo;
public void deleteAdmin(ActionEvent e) {
	
	
	DeleteService ds = new DeleteService();
	GetService gs = new GetService();
	whatdo++;
	if((whatdo%2)!=0) {
		deleteinput.setVisible(true);
	}
	else  
	{
		if(deleteinput.getText().length()!=0) {
			try {
				Integer.parseInt(deleteinput.getText().toString());
			} catch (NumberFormatException e2) {
				intid=false;
			}
			
			if(intid && gs.isexist(Integer.parseInt(deleteinput.getText().toString()))) {
		ds.deleteUtilisateurById(Integer.parseInt(deleteinput.getText().toString()));
		
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListAdmins.fxml"));
		try {
			Parent root  = loader.load();
			AdminListAdmins hc = loader.getController();
			hc.setTantque(connected, connected.getNom());
			//pour ne pas instancier une autre scene
			adminTable.getScene().setRoot(root);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		System.out.println("mrigl !");
		deleteinput.setVisible(false);
		deleteinput.setText("");
		deleteinput.setText("");
		}else if(!intid) {
			deleteinput.setText("invalide !");
			whatdo++;
		}
			else {
				deleteinput.setText("Inexistant !");
				whatdo++;
			}
		}else {
			deleteinput.setText("saisissez Id");
			whatdo++;
		}
	}
	
	
}


boolean intid1=true;

public void BlocAdmin(ActionEvent e) {
	
	if (connected.getWhoami().equals("Administrateur")) {
		denied.setText("Permission refusée ! ");
		
	}else {
	
	
	
	UpdateService ds = new UpdateService();
	GetService gs = new GetService();
	whatdobloc++;
	if((whatdobloc%2)!=0) {
		blocField.setVisible(true);
		blocDate.setVisible(true);
		blocChoice.setVisible(true);
		raisonText.setText("Raison");
		periodText.setText("Période");

	}
	else  
	{
		if(blocField.getText().length()!=0 && blocChoice!=null && blocDate!=null ) {
			try {
				Integer.parseInt(blocField.getText().toString());
			} catch (NumberFormatException e2) {
				intid1=false;
			}
			
			if(intid1 && gs.isexist(Integer.parseInt(blocField.getText().toString()))) {
				
				
				Utilisateur blocked = gs.getById(Integer.parseInt(blocField.getText().toString()));
				blocked.setBlocRaison(blocChoice.getValue().toString());
				
				
				Date todaysDate = new Date(new java.util.Date().getTime());

				
		        int futureDay =0;
		        if (blocDate.getValue().toString().equals("1 Jour")) {
		        	futureDay = futureDay +1;
				}else if (blocDate.getValue().toString().equals("7 Jours")) {
		        	futureDay = futureDay +7;

				}else if (blocDate.getValue().toString().equals("15 Jours")) {
		        	futureDay = futureDay +15;

				}else if (blocDate.getValue().toString().equals("1 mois")) {
		        	futureDay = futureDay +30;

				}

		        
		        Date unblockDate = this.addDays(todaysDate, futureDay);
				blocked.setUnbloc(unblockDate);
				ds.updateUser(blocked);
		
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListAdmins.fxml"));
		try {
			Parent root  = loader.load();
			AdminListAdmins hc = loader.getController();
			hc.setTantque(connected, connected.getNom());
			//pour ne pas instancier une autre scene
			adminTable.getScene().setRoot(root);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		System.out.println("blocked !");
		blocField.setVisible(false);
		blocChoice.setVisible(false);
		blocDate.setVisible(false);
		raisonText.setText("");
		periodText.setText("");
		blocField.setText("");
		blocError.setText("");
		}else if(!intid) {
			blocError.setText("invalide !");
			whatdo++;
		}
			else {
				blocError.setText("Inexistant !");
				whatdo++;
			}
		}else {
			blocError.setText("Obligatoires ! ");
			whatdo++;
		}
	}
	
	
	}
	
}
	


public void toUpdate(ActionEvent e) {
	
	UpdateUser.connected=connected;
	FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateUser.fxml"));
	try {
		
		Parent root  = loader.load();
		UpdateUser us = loader.getController();
		us.setTantque(this.connected ,this.connected.getNom());
		System.out.println("***********************from to update method "+this.connected);

		//pour ne pas instancier une autre scene
		adminTable.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
	
	
	
}






public void logout(ActionEvent e) {
	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	alert.setTitle("Déconnexion");
	alert.setContentText("Êtes-vous sûre");
	ButtonType okButton = new ButtonType("Oui", ButtonData.YES);
	ButtonType noButton = new ButtonType("Non", ButtonData.YES.NO);
	ButtonType cancelButton = new ButtonType("Fermer", ButtonData.CANCEL_CLOSE);
	alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
	alert.showAndWait().ifPresent(type -> {
	        if (type == okButton) {
	        	
	        	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	        	try {
	        		
	        		Parent root  = loader.load();
	        		LoginController us = loader.getController();

	        		//pour ne pas instancier une autre scene
	        		adminTable.getScene().setRoot(root);
	        	} catch (IOException e2) {
	        		e2.printStackTrace();
	        	}
	        	
	        	
	        } else if (type == noButton) {
	        } else {
	        }
	});
	
	
	
	
	
}







//add days for blocked user
public static Date addDays(Date date, int days) {
  Calendar c = Calendar.getInstance();
  c.setTime(date);
  c.add(Calendar.DATE, days);
  return new Date(c.getTimeInMillis());
}






}

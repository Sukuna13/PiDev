package application;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fitnessny.entities.Utilisateur;
import fitnessny.service.DeleteService;
import fitnessny.service.GetService;
import fitnessny.service.UpdateService;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class AdminHome implements Initializable{
	
	
	
	
	@FXML
	private Text raisonText;

	@FXML
	private Text periodText;
	
	@FXML
	private Text blocError;
	
	public static Utilisateur connected;
	
	
	
	@FXML
	private TableView<Utilisateur> coachTable;
	
	@FXML
	private  ChoiceBox<String> blocChoice;
	private String[] whyBlocking = {"takfer barcha","jabri","nattar","frère"};

	
	@FXML 
	private ChoiceBox<String> blocDate;
	private String[] BlockingPeriod = {"1 Jour","7 Jours","15 Jours","1 mois"};

	
	@FXML 
	private TextField blocField;
	
	@FXML
	private TableColumn<Utilisateur, Integer> coachId;
	
	@FXML
	private TableColumn<Utilisateur, String> coachName;
	
	@FXML
	private TableColumn<Utilisateur, String> coachprenom;
	
	@FXML
	private TableColumn<Utilisateur, String> coachmailaddress;
	
	@FXML
	private TextField deleteField;
	
	@FXML
	private ImageView imageView;
	
	
	ObservableList<Utilisateur> users = afficherUsers();
	

	
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
		deleteField.setVisible(false);
		
		coachId.setCellValueFactory(new  PropertyValueFactory<Utilisateur,Integer>("id"));
		coachName.setCellValueFactory(new  PropertyValueFactory<Utilisateur,String>("nom"));
		coachprenom.setCellValueFactory(new  PropertyValueFactory<Utilisateur,String>("prenom"));
		coachmailaddress.setCellValueFactory(new  PropertyValueFactory<Utilisateur,String>("mailAddress"));
		coachTable.setItems(users);
		
		
		
		ObservableList data =  coachTable.getItems();
		search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
		            if (oldValue != null && (newValue.length() < oldValue.length())) {
		            	coachTable.setItems(data);
		            }
		            String value = newValue.toLowerCase();
		            ObservableList<Utilisateur> subentries = FXCollections.observableArrayList();

		            long count = coachTable.getColumns().stream().count();
		            for (int i = 0; i < coachTable.getItems().size(); i++) {
		                for (int j = 0; j < count; j++) {
		                    String entry = "" + coachTable.getColumns().get(j).getCellData(i);
		                    if (entry.toLowerCase().contains(value)) {
		                        subentries.add(coachTable.getItems().get(i));
		                        break;
		                    }
		                }
		            }
		            coachTable.setItems(subentries);
		        });
		
		GetService gs = new GetService();
		connected = gs.getByMail(connected.getMailAddress());
		
		if(connected.getRetrievedImage()!=null) {
		System.out.println("bloob: "+connected.getRetrievedImage());
		InputStream inputstreatm;
		try {
			inputstreatm = connected.getRetrievedImage().getBinaryStream();
			Image image = new Image(inputstreatm);
			imageView.setImage(image);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
		
	}
	
	
	public ObservableList<Utilisateur> afficherCoachs(){
		GetService gs = new GetService();
		
		List<Utilisateur> usersList = gs.displayAllUsers();
		
		List coachs= usersList.stream().filter(e->e.getWhoami().equals("Coach")).collect(Collectors.toList());
		System.out.println(coachs);
		
		return FXCollections.observableList(coachs);
	}
	
	
	
	
	public ObservableList<Utilisateur> afficherSportifs(){
		GetService gs = new GetService();
		
		List<Utilisateur> usersList = gs.displayAllUsers();
		List sportifs= usersList.stream().filter(e->e.getWhoami().equals("Sportifs")).collect(Collectors.toList());
		System.out.println(sportifs);
		
		
		return FXCollections.observableList(usersList);
	}
	
	public  ObservableList<Utilisateur> afficherGerant(){
		GetService gs = new GetService();
		
		List<Utilisateur> usersList = gs.displayAllUsers();
		List gerants= usersList.stream().filter(e->e.getWhoami().equals("Gérant")).collect(Collectors.toList());
		System.out.println("from afficher greant"+gerants);
		
		
		return FXCollections.observableList(gerants);
	}
	
	
	public ObservableList<Utilisateur> afficherUsers(){
		GetService gs = new GetService();
		
		List<Utilisateur> usersList = gs.displayAllUsers();
		
		
		return FXCollections.observableList(usersList);
	}


	
	@FXML
	private Text tantque;
	@FXML
	private Text userType;
	
	public void setTantque( Utilisateur connected, String tantque) {
		this.connected=connected;
		this.tantque.setText(connected.getNom()+" "+connected.getPrenom()); 
		this.userType.setText(connected.getWhoami());
		
	}
	
	
	boolean intid=true;

	int whatdo;
	@FXML
	private Text saisissezId;
public void deleteAdmin(ActionEvent e) {
	DeleteService ds = new DeleteService();
	GetService gs = new GetService();
	whatdo++;
	if((whatdo%2)!=0) {
		deleteField.setVisible(true);
	}
	else  
	{
		if(deleteField.getText().length()!=0) {
			try {
				Integer.parseInt(deleteField.getText().toString());
			} catch (NumberFormatException e2) {
				intid=false;
			}
			
			if(intid && gs.isexist(Integer.parseInt(deleteField.getText().toString()))) {
		ds.deleteUtilisateurById(Integer.parseInt(deleteField.getText().toString()));
		
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
		try {
			Parent root  = loader.load();
			AdminHome hc = loader.getController();
			hc.setTantque(connected, connected.getNom());
			//pour ne pas instancier une autre scene
			coachTable.getScene().setRoot(root);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		deleteField.setVisible(false);
		deleteField.setText("");
		saisissezId.setText("");
		}else if(!intid) {
			saisissezId.setText("invalide !");
			whatdo++;
		}
			else {
				saisissezId.setText("Inexistant !");
				whatdo++;
			}
		}else {
			saisissezId.setText("saisissez Id");
			whatdo++;
		}
	}
		
	}

int whatdobloc;
boolean intid1=true;


public void BlocAdmin(ActionEvent e) {
	System.out.println("enter method");
	
	
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
	{	System.out.println("bloc field"+blocField.getText().toString());
	System.out.println("blocchoice"+blocChoice);
	System.out.println(blocDate);

		if(blocField.getText().toString().length()!=0 && blocChoice!=null && blocDate!=null ) {
			System.out.println("all not null");

			try {
				Integer.parseInt(blocField.getText().toString());
			} catch (NumberFormatException e2) {
				intid1=false;
			}
			
			if(intid1 && gs.isexist(Integer.parseInt(blocField.getText().toString()))) {
				System.out.println("existe et id nn null");

				
			if(	gs.getById(Integer.parseInt(blocField.getText().toString())).getWhoami().equals("Administrateur")
					|| gs.getById(Integer.parseInt(blocField.getText().toString())).getWhoami().equals("Super")) {
				System.out.println("super ou admin");

				if(connected.getWhoami().equals("Super")) {
					System.out.println("super");

					
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
					System.out.println("updated");

			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
			try {
				Parent root  = loader.load();
				AdminHome hc = loader.getController();
				hc.setTantque(connected, connected.getNom());
				//pour ne pas instancier une autre scene
				coachTable.getScene().setRoot(root);
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
			
					
					
					
					
				}else {
					blocError.setText("Permission refusée !");
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Permission");
					alert.setHeaderText("Perimission refusé! ");
					alert.setContentText("Raison : Vous n'êtes pas un super administrateur!");

					alert.showAndWait();
				}
				
					
					
			}else {
				
				
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
				System.out.println("updated");

		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
		try {
			Parent root  = loader.load();
			AdminHome hc = loader.getController();
			hc.setTantque(connected, connected.getNom());
			//pour ne pas instancier une autre scene
			coachTable.getScene().setRoot(root);
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
		
				

				
				
				
				
				
			}
				
				
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
	
	
public void gotoadminlist(ActionEvent e) {
		
		AdminListAdmins.connected=connected;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListAdmins.fxml"));
		try {
			Parent root  = loader.load();
			AdminListAdmins hc = loader.getController();
			hc.setTantque(connected,this.tantque.getText().toString());
			//pour ne pas instancier une autre scene
			coachTable.getScene().setRoot(root);
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
		hc.setTantque(connected, connected.getNom());
		//pour ne pas instancier une autre scene
		coachTable.getScene().setRoot(root);
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
		hc.setTantque(connected,connected.getNom());
		//pour ne pas instancier une autre scene
		coachTable.getScene().setRoot(root);
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
		hc.setTantque(connected ,connected.getNom());
		//pour ne pas instancier une autre scene
		coachTable.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}



//add days for blocked user
public static Date addDays(Date date, int days) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.DATE, days);
    return new Date(c.getTimeInMillis());
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
		coachTable.getScene().setRoot(root);
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
	        		coachTable.getScene().setRoot(root);
	        	} catch (IOException e2) {
	        		e2.printStackTrace();
	        	}
	        	
	        	
	        } else if (type == noButton) {
	        } else {
	        }
	});
	
	
	
	
	
}













}

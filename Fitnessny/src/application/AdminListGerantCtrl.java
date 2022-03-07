package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class AdminListGerantCtrl implements Initializable{
	
	
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

	
	
	@FXML
	private  TextField deleteinput;
	
	
	Utilisateur connected;
	
	@FXML
	private TextField search;
	
	@FXML 
	private Hyperlink supprimeLink;
	
	
	
	@FXML
	private Text tantque;
	
	
	@FXML
	private TableView<Utilisateur> gerantTable;
	
	@FXML
	private TableColumn<Utilisateur, Integer> gerantId;
	
	@FXML
	private TableColumn<Utilisateur, String> gerantName;
	
	@FXML
	private TableColumn<Utilisateur, String> gerantprenom;
	
	@FXML
	private TableColumn<Utilisateur, String> gerantmailaddress;
	
	
	ObservableList<Utilisateur> users = afficherCoachs();
	


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
		
		
		gerantId.setCellValueFactory(new  PropertyValueFactory<Utilisateur,Integer>("id"));
		gerantName.setCellValueFactory(new  PropertyValueFactory<Utilisateur,String>("nom"));
		gerantprenom.setCellValueFactory(new  PropertyValueFactory<Utilisateur,String>("prenom"));
		gerantmailaddress.setCellValueFactory(new  PropertyValueFactory<Utilisateur,String>("mailAddress"));
		gerantTable.setItems(users);
		
		
		ObservableList data =  gerantTable.getItems();
		search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
		            if (oldValue != null && (newValue.length() < oldValue.length())) {
		            	gerantTable.setItems(data);
		            }
		            String value = newValue.toLowerCase();
		            ObservableList<Utilisateur> subentries = FXCollections.observableArrayList();

		            long count = gerantTable.getColumns().stream().count();
		            for (int i = 0; i < gerantTable.getItems().size(); i++) {
		                for (int j = 0; j < count; j++) {
		                    String entry = "" + gerantTable.getColumns().get(j).getCellData(i);
		                    if (entry.toLowerCase().contains(value)) {
		                        subentries.add(gerantTable.getItems().get(i));
		                        break;
		                    }
		                }
		            }
		            gerantTable.setItems(subentries);
		        });
		
	}
	
	
	
	public ObservableList<Utilisateur> afficherCoachs(){
		GetService gs = new GetService();
		
		List<Utilisateur> usersList = gs.displayAllUsers();
		
		List coachs= usersList.stream().filter(e->e.getWhoami().equals("Gérant")).collect(Collectors.toList());
		System.out.println(coachs);
		
		
		return FXCollections.observableList(coachs);
	}
	
public void gotoadminlist(ActionEvent e) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListAdmins.fxml"));
		try {
			Parent root  = loader.load();
			AdminListAdmins hc = loader.getController();
			hc.setTantque(connected, connected.getNom());

			//pour ne pas instancier une autre scene
			gerantTable.getScene().setRoot(root);
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
		gerantTable.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}


public void gotosportiflist(ActionEvent e) {
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListSportif.fxml"));
	try {
		Parent root  = loader.load();
		AdminListSportifs hc = loader.getController();
		hc.setTantque(connected, connected.getNom());

		//pour ne pas instancier une autre scene
		gerantTable.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}


public void gotocoachlist(ActionEvent e) {
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListCoach.fxml"));
	try {
		Parent root  = loader.load();
		AdminListCoachsCtrl hc = loader.getController();
		hc.setTantque(connected, connected.getNom());

		//pour ne pas instancier une autre scene
		gerantTable.getScene().setRoot(root);
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

		
		//pour ne pas instancier une autre scene
		gerantTable.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}

public void setTantque(Utilisateur connected, String tantque) {
	this.connected=connected;
	this.tantque.setText(tantque);
}


boolean intid=true;
int whatdo;
public void deleteGerant(ActionEvent e) {
	
	
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
		
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListGerant.fxml"));
		try {
			Parent root  = loader.load();
			AdminListGerantCtrl hc = loader.getController();
			hc.setTantque(connected, connected.getNom());
			//pour ne pas instancier une autre scene
			gerantTable.getScene().setRoot(root);
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
	{
		
		
		System.out.println("bloc field"+blocField.getText().toString());
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
				
				
				if(gs.getById(Integer.parseInt(blocField.getText().toString())).getWhoami().equals("Gérant")) {
					
					
					
					System.out.println(" coach existe et id nn null ");

					
					
					
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

			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListGerant.fxml"));
			try {
				Parent root  = loader.load();
				AdminListGerantCtrl hc = loader.getController();
				hc.setTantque(connected, connected.getNom());
				//pour ne pas instancier une autre scene
				gerantTable.getScene().setRoot(root);
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
					
					blocError.setText("Inexistant !");
					whatdo++;
				}
			
				
			}
			
			
			else if(!intid) {
				blocError.setText("invalide !");
				whatdo++;}
				
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
	
	









//add days for blocked user
public static Date addDays(Date date, int days) {
  Calendar c = Calendar.getInstance();
  c.setTime(date);
  c.add(Calendar.DATE, days);
  return new Date(c.getTimeInMillis());
}







}

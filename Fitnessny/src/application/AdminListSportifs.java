package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import fitnessny.entities.Utilisateur;
import fitnessny.service.DeleteService;
import fitnessny.service.GetService;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class AdminListSportifs implements Initializable{
	
	@FXML
	private  TextField deleteinput;
	
	@FXML 
	private Hyperlink supprimeLink;
	
	
	Utilisateur connected;
	
	@FXML
	private TextField search;
	
	
	
	@FXML 
	private Hyperlink blocField;
	
	
	
	
//	@FXML 
//	private TextField blocField;
	
	@FXML
	private Text tantque;
	
	
	@FXML
	private TableView<Utilisateur> sportifTable;
	
	@FXML
	private TableColumn<Utilisateur, Integer> sportifId;
	
	@FXML
	private TableColumn<Utilisateur, String> sportifName;
	
	@FXML
	private TableColumn<Utilisateur, String> sportifprenom;
	
	@FXML
	private TableColumn<Utilisateur, String> sportifmailaddress;
	
	
	ObservableList<Utilisateur> users = affichersportif();
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		whatdo=0;
		deleteinput.setVisible(false);
		
		
		sportifId.setCellValueFactory(new  PropertyValueFactory<Utilisateur,Integer>("id"));
		sportifName.setCellValueFactory(new  PropertyValueFactory<Utilisateur,String>("nom"));
		sportifprenom.setCellValueFactory(new  PropertyValueFactory<Utilisateur,String>("prenom"));
		sportifmailaddress.setCellValueFactory(new  PropertyValueFactory<Utilisateur,String>("mailAddress"));
		sportifTable.setItems(users);
		
		
		
		ObservableList data =  sportifTable.getItems();
		search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
		            if (oldValue != null && (newValue.length() < oldValue.length())) {
		            	sportifTable.setItems(data);
		            }
		            String value = newValue.toLowerCase();
		            ObservableList<Utilisateur> subentries = FXCollections.observableArrayList();

		            long count = sportifTable.getColumns().stream().count();
		            for (int i = 0; i < sportifTable.getItems().size(); i++) {
		                for (int j = 0; j < count; j++) {
		                    String entry = "" + sportifTable.getColumns().get(j).getCellData(i);
		                    if (entry.toLowerCase().contains(value)) {
		                        subentries.add(sportifTable.getItems().get(i));
		                        break;
		                    }
		                }
		            }
		            sportifTable.setItems(subentries);
		        });
		
		
	}
	
	
	
	public ObservableList<Utilisateur> affichersportif(){
		GetService gs = new GetService();
		
		List<Utilisateur> usersList = gs.displayAllUsers();
		
		List coachs= usersList.stream().filter(e->e.getWhoami().equals("Sportif")).collect(Collectors.toList());
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
			sportifTable.getScene().setRoot(root);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	

public void gotogerantlist(ActionEvent e) {
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListCoach.fxml"));
	try {
		Parent root  = loader.load();
		AdminListCoachsCtrl hc = loader.getController();
		hc.setTantque(connected, connected.getNom());
		//pour ne pas instancier une autre scene
		sportifTable.getScene().setRoot(root);
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
		sportifTable.getScene().setRoot(root);
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
		sportifTable.getScene().setRoot(root);
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
		sportifTable.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}
public void setTantque(Utilisateur connected, String tantque) {
	this.connected=connected;
	this.tantque.setText(tantque); 
}



public void showDelete(ActionEvent e) {
	
}

boolean intid=true;
int whatdo;
public void deleteSportif(ActionEvent e) {
	
	
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
		
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListSportif.fxml"));
		try {
			Parent root  = loader.load();
			AdminListSportifs hc = loader.getController();
			hc.setTantque(connected, connected.getNom());
			//pour ne pas instancier une autre scene
			sportifTable.getScene().setRoot(root);
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


}

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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class AdminListCoachsCtrl implements Initializable{
	
	@FXML
	private  TextField deleteinput;
	
	Utilisateur connected;
	
	@FXML
	private Hyperlink Hyperlink;
	
	
	@FXML
	private Hyperlink supprimeLink;
	
	@FXML 
	private TextField search;

	@FXML
	private Text tantque;
	
	
	@FXML
	private TableView<Utilisateur> coachTable;
	
	@FXML
	private TableColumn<Utilisateur, Integer> coachId;
	
	@FXML
	private TableColumn<Utilisateur, String> coachName;
	
	@FXML
	private TableColumn<Utilisateur, String> coachprenom;
	
	@FXML
	private TableColumn<Utilisateur, String> coachmailaddress;
	
	
	ObservableList<Utilisateur> users = afficherCoachs();
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		whatdo=0;
		deleteinput.setVisible(false);
		
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
		
	}
	
	
	
	public ObservableList<Utilisateur> afficherCoachs(){
		GetService gs = new GetService();
		
		List<Utilisateur> usersList = gs.displayAllUsers();
		
		List coachs= usersList.stream().filter(e->e.getWhoami().equals("Coach")).collect(Collectors.toList());
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
			coachTable.getScene().setRoot(root);
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
		coachTable.getScene().setRoot(root);
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
		coachTable.getScene().setRoot(root);
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
		coachTable.getScene().setRoot(root);
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
		coachTable.getScene().setRoot(root);
	} catch (IOException e2) {
		e2.printStackTrace();
	}
}


public void setTantque(Utilisateur connected , String tantque) {
	this.connected=connected;
	this.tantque.setText(tantque); 
}



boolean intid=true;
int whatdo;
public void deleteCoach(ActionEvent e) {
	
	
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
		
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListCoach.fxml"));
		try {
			Parent root  = loader.load();
			AdminListCoachsCtrl hc = loader.getController();
			hc.setTantque(connected, connected.getNom());
			//pour ne pas instancier une autre scene
			coachTable.getScene().setRoot(root);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fitnessny.gui3;

import edu.fitnessny.entities.Exercice;
import edu.finessny.services.ExerciceCRUD;
import edu.finessny.services.ProgrammeCRUD;
import edu.finessny.utils.MyConnection;
import edu.fitnessny.entities.Programme;
import edu.fitnessny.gui2.AjouterprogrammeController;
import javafx.event.EventHandler;

import java.io.IOException;
import static java.lang.Math.log;
import static java.lang.StrictMath.log;
import javafx.collections.FXCollections;
import java.net.URL;
import static java.nio.file.Files.list;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rocky
 */
public class AfficherExerciceController implements Initializable {

    @FXML
    private TableView<Exercice> TabExe;
    @FXML
    private TableColumn<Exercice, String> nomExercice;
    @FXML
    private TableColumn<Exercice, Integer> nbrSerie;
    @FXML
    private TableColumn<Exercice, Integer> nbrRep;
    @FXML
    private TableColumn<Exercice, String> Categorie;
    @FXML
    private TableColumn<Exercice, String> Description;

    //private TableColumn<Exercice, String> Action;
    ObservableList<Exercice> Exercice = AfficherExercice();
    @FXML
    private Button ajouterexe;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnRefresh;

    @FXML
    private Pagination pagination;
    private final static int dataSize = 30;
    private final static int rowPerPage = 5;
    @FXML
    private TextField tfFilter;
    @FXML
    private Button btntoprogramme;
    @FXML
    private Button btnExe;
    private Stage stage;
    private Scene scene;
    private Parent root;
//    private AnchorPane 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // nom.setCellValueFactory(new PropertyValueFactory<Programme, String>("nomProgramme"));
        // idExercice.setCellValueFactory(new PropertyValueFactory<Exercice, String>("idExercice"));
        nomExercice.setCellValueFactory(new PropertyValueFactory<Exercice, String>("nomExercice"));
        nomExercice.setCellFactory(TextFieldTableCell.forTableColumn());
        nomExercice.setOnEditCommit(new EventHandler<CellEditEvent<Exercice, String>>() {
            @Override
            public void handle(CellEditEvent<Exercice, String> event) {
                Exercice ex = event.getRowValue();
                ex.setNomExercice(event.getNewValue());
            }
        });
        nbrRep.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("nbrRepetition"));
        nbrSerie.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("nbrSerie"));

        Categorie.setCellValueFactory(new PropertyValueFactory<Exercice, String>("categorieExercice"));
        Categorie.setCellFactory(TextFieldTableCell.forTableColumn());
        Description.setCellValueFactory(new PropertyValueFactory<Exercice, String>("descriptionExercice"));
        Description.setCellFactory(TextFieldTableCell.forTableColumn());
        TabExe.setItems(Exercice);
        TabExe.setEditable(true);
        pagination.setPageFactory(this::createPage);

//       
//        FilteredList <Exercice>  filteredData = new FilteredList<>(Exercice, b -> true);
//        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
//			
//            filteredData.setPredicate((Exercice) -> {
//				// If filter text is empty, display all persons.
//								
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//				
//				// Compare first name and last name of every person with filter text.
//				String lowerCaseFilter = newValue.toLowerCase();
//				
//				if (Exercice.getNomExercice().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//					return true; // Filter matches first name.
//				}
//        
//				
//				     else  
//				    	 return false; // Does not match.
//			});
//		});
//        
//        		SortedList<Exercice> sortedData = new SortedList<>(filteredData);
//                      	sortedData.comparatorProperty().bind(TabExe.comparatorProperty());
//                        
//                        TabExe.setItems(sortedData);
        // TODO
    }

    private ObservableList<Exercice> AfficherExercice() {

        ExerciceCRUD ex = new ExerciceCRUD();
        List<Exercice> exer = ex.afficherExercice();

        return FXCollections.observableList(exer);

    }

    @FXML
    public void AjouterExe(ActionEvent e) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterexercice.fxml"));
        try {

            Parent root = loader.load();
            AjouterexericeController ew = loader.getController();

            TabExe.getScene().setRoot(root);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void ModifierExercice(ActionEvent event) {
        Exercice ex = TabExe.getSelectionModel().getSelectedItem();

        String nom = nomExercice.getText();
        //Integer.parseInt(tfserie.getText())

        int repitition = nbrRep.getCellData(ex);
        int serie = nbrSerie.getCellData(ex);

        String Cat = Categorie.getText();
        String Desc = Description.getText();

        ex.setNomExercice(nom);
        //ex.setNbrRepetition(repitition);
        // ex.setNbrSerie(serie);
        ex.setCategorieExercice(Cat);
        ex.setDescriptionExercice(Desc);

        ExerciceCRUD exe = new ExerciceCRUD();
        exe.modifierExercice(ex);
        TabExe.refresh();

        List<Exercice> exe1 = exe.afficherExercice();

        nomExercice.setCellValueFactory(new PropertyValueFactory<Exercice, String>("nomExercice"));
        nbrRep.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("nbrRepetition"));
        nbrSerie.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("nbrSerie"));
        Categorie.setCellValueFactory(new PropertyValueFactory<Exercice, String>("categorieExercice"));
        Description.setCellValueFactory(new PropertyValueFactory<Exercice, String>("descriptionExercice"));
        TabExe.setItems(Exercice);

    }

    @FXML
    private void SupprimerExercice(ActionEvent event) {

        Exercice pub = TabExe.getSelectionModel().getSelectedItem();
        TabExe.getItems().removeAll(TabExe.getSelectionModel().getSelectedItem());

        ExerciceCRUD ex = new ExerciceCRUD();
        ex.supprimerExercice1(pub);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Votre Exercice a ete supprime ", javafx.scene.control.ButtonType.OK);
        alert.showAndWait();

//         Programme pub = ProgTable.getSelectionModel().getSelectedItem();
//
//        ProgTable.getItems().removeAll(ProgTable.getSelectionModel().getSelectedItem());
//
//        ProgrammeCRUD service = new ProgrammeCRUD();
//        service.delete(pub);
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION ,"Aucun Programme avec IdProg ="+tfsearch.getText()+"", javafx.scene.control.ButtonType.OK);
//            alert.showAndWait();
    }

//    private void RefreshTable() {
//
//        try {
//            Exercice.clear();
//
//            String query = " SELECT * FROM exercice";
//            Statement st = new MyConnection().getCnx().createStatement();
//            ResultSet rs = st.executeQuery(query);
//
//            while (rs.next()) {
//                Exercice.add(new Exercice(
//                        rs.getString("nomProgramme"),
//                        rs.getString("objectifProgramme"),
//                        rs.getString("categorieProgramme"),
//                        rs.getString("descriptionProgramme")));
//                TabExe.setItems(Exercice);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(AjouterprogrammeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    @FXML
    private void RefreshTable(ActionEvent event) {

        try {
            Exercice.clear();

            String query = " SELECT * FROM Exercice";
            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Exercice.add(new Exercice(
                        rs.getString("nomExercice"),
                        rs.getInt("nbrSerie"),
                        rs.getInt("nbrRepetition"),
                        rs.getString("descriptionExercice"),
                        rs.getString("categorieExercice")));
                TabExe.setItems(Exercice);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AjouterprogrammeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowPerPage;
        int toIndex = Math.min(fromIndex + rowPerPage, Exercice.size());
        TabExe.setItems(FXCollections.observableList(Exercice.subList(fromIndex, toIndex)));
        return null;
    }

    @FXML
    private void NavProgramme(ActionEvent event) throws IOException {
        
   root = FXMLLoader.load(getClass().getResource("/edu/fitnessny/gui2/ajouterprogramme.fxml"));

  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

}

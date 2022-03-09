/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Employe;
import services.serviceEmployees;


/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField tfsupp;
    @FXML
    private TableView<Employe> resourceslist;
    private serviceEmployees sr;
    @FXML
    private TextField tfsearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
            // afficher les elements initialement:
            sr = new serviceEmployees();

            TableColumn<Employe, String> titleCol = new TableColumn<>("nom_emp");
            titleCol.setCellValueFactory(new PropertyValueFactory<>("nom_emp"));

            TableColumn<Employe, String> descriptionCol = new TableColumn<>("prenom_emp");
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("prenom_emp"));

           /* TableColumn<Employe, String> pathCol = new TableColumn<>("Path");
            pathCol.setCellValueFactory(new PropertyValueFactory<>("path"));

            TableColumn<Employe, String> pathModule = new TableColumn<>("Module");
            pathModule.setCellValueFactory(new PropertyValueFactory<>("module"));
*/
            //y'affichili fl module fl path !!!!!!!!!!!!!!!!!!
            //preparation de la structure
            resourceslist.getColumns().add(titleCol);
            resourceslist.getColumns().add(descriptionCol);
            

            //affichage
            resourceslist.getItems().addAll(sr.afficherEmploye());
            
        

            //Rech avancée +Title
           // serviceEmployees sr2 = serviceEmployees
           /*
            ObservableList<Employe> resslist2 = FXCollections.observableArrayList(sr2.Read());

            FilteredList<Ressources> filteredData = new FilteredList<>(resslist2, b -> true);
            tfsearch.textProperty().addListener(((observable, oldValue, newValue) -> {

                filteredData.setPredicate(e -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (e.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            }));
            SortedList<Ressources> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(resourceslist.comparatorProperty());
            resourceslist.setItems(sortedData);

//            
//            
       
*/
             }
    

    @FXML
    private void showRESOURCES(MouseEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "ressources/FXMLDocument.fxml"
                    )
            );

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //this accesses the window.
            stage.setScene(
                    new Scene(loader.load())
            );
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /*
    public void exportExcel() throws FileNotFoundException, IOException, SQLException{
        Connection cnx = MYDB.getInstance().getConnection();
        String query = "select p.*,t.nom_tournoi,e.nom_equipe from tournoi t ,equipe e,participants_tournoi p WHERE (p.id_tournoi=t.id_tournoi) AND (p.id_equipe=e.id_equipe)";
         PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Détails Activités");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("id_tournoi");
            header.createCell(1).setCellValue("nom_tournoi");
            header.createCell(2).setCellValue("id_equipe");
            header.createCell(3).setCellValue("nom_equipe");
            
            int index = 1;
            while(rs.next()){
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(String.valueOf(rs.getInt("id_tournoi")));
                row.createCell(1).setCellValue(rs.getString("nom_tournoi"));
                row.createCell(2).setCellValue(String.valueOf(rs.getInt("id_equipe")));
                row.createCell(3).setCellValue(rs.getString("nom_equipe"));
                index++;
            }
            
            FileOutputStream file = new FileOutputStream("Détails Activités.xlsx");
            wb.write(file);
            file.close();
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Exportation effectuée!!!");
            alert.showAndWait();
            pst.close();
            rs.close();
            
            File myfFile = new File("C:/Users/ASUS/Documents/NetBeansProjects/valorantESport/Détails Activités.xlsx");
            Desktop.getDesktop().open(myfFile);
    } 
    */

}

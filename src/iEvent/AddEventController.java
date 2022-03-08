/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iEvent;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import entities.Event;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import outils.Myconnection;
import services.EventCrud;
import sun.rmi.transport.Transport;

/**
 * FXML Controller class
 *
 * @author nahaw
 */
public class AddEventController implements Initializable {

    @FXML
    private AnchorPane EventScene_id;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef;
    @FXML
    private Button insererimg;
    
    public String imgurl;
    private URI imguriUri;
    String imgp;
    
    @FXML
    private TextField img_id;
    @FXML
    private ImageView imgview;
    @FXML
    private TextField nbrplaces;
    @FXML
    private TextField locationevent;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private TableView<Event> Table_event;
    @FXML
    private TableColumn<Event, Integer> col_id;
    @FXML
    private TableColumn<Event, String> col_name;
    @FXML
    private TableColumn<Event, Date> col_dated;
    @FXML
    private TableColumn<Event, Date> col_datef;
    @FXML
    private TableColumn<Event, Integer> col_nbrplaces;
    @FXML
    private TableColumn<Event, String> col_location;
    @FXML
    private TableColumn<Event, String> col_img;
    
    
 EventCrud ec = new EventCrud();
 List<Event> ev= ec.afficherEventCrud();
    @FXML
    private TextField searchField;
    @FXML
    private Text searchText;
    @FXML
    private Button pdf;
    private Connection cnx;
//    @FXML
//    private Button Statistics;
    Button[] Statistics = new Button[100];
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(ev);
        // TODO
        col_id.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
        col_dated.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        col_datef.setCellValueFactory(new PropertyValueFactory<>("DateFin"));  
        col_img.setCellValueFactory(new PropertyValueFactory<>("img"));
        col_nbrplaces.setCellValueFactory(new PropertyValueFactory<>("nbrPlaces")); 
        col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        ObservableList<Event> datalist= FXCollections.observableArrayList(ev);
        Table_event.setItems(datalist);
       // Table_event.setItems(ec.afficherEvent());
        Table_event.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    } 
//convertion :
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
//controle de saisie:
   public Boolean ValidateDate() {
        if ((datef.getValue().isBefore(dated.getValue())) | (dated.getValue().isBefore(java.time.LocalDate.now())) | (datef.getValue().isBefore(java.time.LocalDate.now()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a valid date");
            alert.showAndWait();
            return false;
        }
        return true;
        
    }

     public Boolean ValidateFields() {
        if (name.getText().isEmpty() | dated.getValue().toString().isEmpty() | datef.getValue().toString().isEmpty() | nbrplaces.getText().isEmpty() | locationevent.getText().isEmpty()| img_id.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Into The Fields");
            alert.showAndWait();
            return false;
        }

        return true;

    }
    @FXML
    private void AjouterEvent(ActionEvent event) {
        String nomEvent = name.getText();
        LocalDate DateDebut = dated.getValue();
        LocalDate DateFin = datef.getValue();
        String img = img_id.getText();
        int nbrPlaces = Integer.parseInt(nbrplaces.getText());
        String location = locationevent.getText();
        
       if (ValidateFields() && ValidateDate()) {
            EventCrud ec = new EventCrud();
            Date dd = convertToDateViaSqlDate(DateDebut);
            Date df = convertToDateViaSqlDate(DateFin);
            Event e = new Event(nomEvent, dd, df,nbrPlaces, location,img);
            ec.ajouterEvent2(e);
           
//            javafx.scene.Parent tableview = null;
//            Scene sceneview = new Scene(tableview);
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            window.setScene(sceneview);
//            window.show();
 ObservableList<Event> datalist= FXCollections.observableArrayList(ev);
Table_event.setItems(datalist);

    } 
    clearFields();
    }
    
    private void clearFields() {
        id.clear();
        name.clear();
        dated.setValue(null);
        datef.setValue(null);
        nbrplaces.clear();
        locationevent.clear();
    }

    @FXML
    private void supprimerEvent(ActionEvent event) {
        ObservableList<Event> selectedRows, allRows;
        allRows = (ObservableList<Event>) Table_event.getItems();
        selectedRows = (ObservableList<Event>) Table_event.getSelectionModel().getSelectedItems();
        for (Event e : selectedRows) {
            allRows.remove(e);
            ec.supprimerEvent(e.getIdEvent());
    }
    }

    @FXML
    private void modifierEvent(ActionEvent event) {
        //recuperer
        int idEvent = Integer.parseInt(id.getText());
        String nomEvent = name.getText();
        LocalDate DateDebut = dated.getValue();
        LocalDate DateFin = datef.getValue();
        String img = img_id.getText();
        System.out.println("dd");
        int nbrPlaces = Integer.parseInt(nbrplaces.getText());
        String location = locationevent.getText();
        
        //creer instance
        Date sd = convertToDateViaSqlDate(DateDebut);
        Date ed = convertToDateViaSqlDate(DateFin);
        Event e = new Event(idEvent ,nomEvent, sd, ed, nbrPlaces, location, img);
        //update event on the view:
        int l = Table_event.getSelectionModel().getSelectedIndex();
        //Table_event.getItems().set(l, e);
        ec.modifierEvent(e);
        clearFields();
    }

    @FXML
    private void showDetails(MouseEvent event) {
        
        Event e = (Event) Table_event.getSelectionModel().getSelectedItem();
        DateFormat dff=new SimpleDateFormat("yyyy-MM-dd");
        String dateToString = dff.format(e.getDateDebut());
        LocalDate date = LocalDate.parse(dateToString);
       
        String dateToString1 = dff.format(e.getDateFin());
        LocalDate date1 = LocalDate.parse(dateToString1);
        
        id.setText(String.valueOf(e.getIdEvent()));
        name.setText(e.getNomEvent());
        dated.setValue(date);
        datef.setValue(date1);
        nbrplaces.setText(String.valueOf(e.getNbrPlaces()));
        locationevent.setText(e.getLocation());
        img_id.setText(e.getImg());
    }
    
    @FXML
    private void insererimg(ActionEvent event) {
        
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            imgurl = selectedfile.toURI().toString();
            String fileName = imgurl.substring(imgurl.lastIndexOf('/') + 1, imgurl.length());
            img_id.setText(fileName);
            Image image = new Image(imgurl);
            imgview.setImage(image);
            imguriUri = selectedfile.toURI();
            imgp = selectedfile.getName();
        }
    } 
    
    EventCrud ecm = new EventCrud(); 
    
    
    public void afficher_Event(List<Event> ltt)
    {
    
        ObservableList<Event> datalist1 = FXCollections.observableArrayList(ltt);
        System.out.println(datalist1.size());
        col_id.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
        col_dated.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        col_datef.setCellValueFactory(new PropertyValueFactory<>("DateFin"));  
        col_img.setCellValueFactory(new PropertyValueFactory<>("img"));
        col_nbrplaces.setCellValueFactory(new PropertyValueFactory<>("nbrPlaces")); 
        col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        Table_event.setItems(datalist1);
   
    }
     
     
     private void printLog(TextArea textArea, List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textArea.appendText(file.getAbsolutePath() + "\n");
        }
    }

     /*  private void openFile(File file) {
        try {
            this.desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();*/


     /* @FXML
    private void chercher() {
      EventCrud ev = new EventCrud();
      le = ev.recherche(searchField.getText()); 
      System.out.println(searchField.getText()); 
      Table_event.setEditable(true);
      afficher_Event(le);
    }
*/

    
    @FXML
    private void chercher(ActionEvent event) {
        List<Event> le = ecm.afficherEventCrud();
        EventCrud ev = new EventCrud();
      le = ev.recherche(searchField.getText()); 
      System.out.println(searchField.getText()); 
      Table_event.setEditable(true);
      afficher_Event(le);
    }

    @FXML
    private void pdf(ActionEvent event) throws ClassNotFoundException, ClassNotFoundException, SQLException, IOException, URISyntaxException, DocumentException {
           
        cnx = Myconnection.getinstance().getcnx();
        try {
                 PreparedStatement pt = cnx.prepareStatement("select * from event");
                 ResultSet rs = pt.executeQuery();
            
                       /* Step-2: Initialize PDF documents - logical objects */

                       Document my_pdf_report = new Document(){};
                       
                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
                       
                              my_pdf_report.open();  
//                             my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("c:/6.png");
//                            my_pdf_report.add(img);
                             my_pdf_report.add(new Paragraph("                                                       Events\n"
                                     + "                                                        "));
                             
                             my_pdf_report.addCreationDate();
              
                       
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(5);
                             
                       //create a cell object
                       PdfPCell table_cell;
                       
                       
                                       table_cell=new PdfPCell(new Phrase("name"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("date de début"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("date de fin"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                        table_cell=new PdfPCell(new Phrase("nombre de Places"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                        table_cell=new PdfPCell(new Phrase("location"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                     
                                       
                                       

                                      while(rs.next()){
                                      
                                       String nom= rs.getString("nomEvent");
                                       table_cell=new PdfPCell(new Phrase(nom));
                                       my_report_table.addCell(table_cell);
                                       String adresse=rs.getString("DateDebut");
                                       table_cell=new PdfPCell(new Phrase(adresse));
                                       my_report_table.addCell(table_cell);
                                       String DateFin=rs.getString("DateFin");
                                       table_cell=new PdfPCell(new Phrase(DateFin));
                                       my_report_table.addCell(table_cell);
                                       String nbrPlaces=String.valueOf(rs.getInt("nbrPlaces"));
                                       table_cell=new PdfPCell(new Phrase(nbrPlaces));
                                       my_report_table.addCell(table_cell);
                                       String location=rs.getString("location");
                                       table_cell=new PdfPCell(new Phrase(location));
                                       my_report_table.addCell(table_cell);                
                       }
                       /* Attach report table to PDF */
                       
                       my_pdf_report.add(my_report_table); 
                       
                       System.out.println(my_pdf_report);
                       my_pdf_report.close();
                       JOptionPane.showMessageDialog(null, "impression effectuée");

                       /* Close all DB related objects */
                       rs.close();
                       pt.close(); 
                       cnx.close();               


       } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       }

    }
    
    
    
    @FXML
             private void showStat()
    {
        try {
            
           
        FXMLLoader statevent= new FXMLLoader(getClass().getResource("lineChart.fxml"));
            Parent root = statevent.load();
            Scene scene1 = new Scene(root);
           Stage statStage = new Stage();
            
            statStage.setTitle("Hello World!");
            statStage.setScene(scene1);
            statStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
        
     }
    
    
    
     
}

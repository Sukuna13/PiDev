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
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import outils.Myconnection;
import services.EventCrud;
import sun.rmi.transport.Transport;
import java.net.Authenticator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;
//import org.jxmapviewer.JXMapViewer;
//import org.jxmapviewer.OSMTileFactoryInfo;
//import org.jxmapviewer.cache.FileBasedLocalCache;
//import org.jxmapviewer.input.CenterMapListener;
//import org.jxmapviewer.input.PanKeyListener;
//import org.jxmapviewer.input.PanMouseInputListener;
//import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
//import org.jxmapviewer.viewer.DefaultTileFactory;
//import org.jxmapviewer.viewer.GeoPosition;
//import org.jxmapviewer.viewer.TileFactoryInfo;
//import org.jxmapviewer.viewer.WaypointPainter;

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
    
    public String adresse;
    EventCrud ec = new EventCrud();
    List<Event> ev= ec.afficherEventCrud();
    
    @FXML
    private TextField searchField;
    @FXML
    private Text searchText;
    private Connection cnx;
//    @FXML
//    private Button Statistics;
   // @FXML
   // Button Statistics = new Button[100];
    @FXML
    private Button Refresh;
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
   
//   @FXML
//      private void mapopen(ActionEvent event) throws InterruptedException
//    {
//        Process p ;
//        map();
//        
//     }
//   public void map() {
//        // Create a TileFactoryInfo for OSM
//        TileFactoryInfo info = new OSMTileFactoryInfo();
//        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
//        tileFactory.setThreadPoolSize(8);
//
//        // Setup local file cache
//        File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
//        tileFactory.setLocalCache(new FileBasedLocalCache(cacheDir, false));
//
//        // Setup JXMapViewer
//        JXMapViewer mapViewer = new JXMapViewer();
//        mapViewer.setTileFactory(tileFactory);
//
//        GeoPosition local1 = new GeoPosition(36.36702378263096, 10.537600024580636);
//        GeoPosition local2 = new GeoPosition(37.28810234852007, 9.872579697358955);
//        GeoPosition local3 = new GeoPosition(36.89941166959323, 10.189593871798582);
//        GeoPosition local4 = new GeoPosition(52.652663251555616, 12.984945514235399);
//        GeoPosition local5 = new GeoPosition(48.86255390816682, 2.34050780653153);
//
//        // Set the focus
//        mapViewer.setZoom(10);
//        mapViewer.setAddressLocation(local1);
//
//        // Add interactions
//        MouseInputListener mia = new PanMouseInputListener(mapViewer);
//        mapViewer.addMouseListener(mia);
//        mapViewer.addMouseMotionListener(mia);
//        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
//        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
//        mapViewer.addKeyListener(new PanKeyListener(mapViewer));
//
//        // Create waypoints from the geo-positions
//        Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>(Arrays.asList(
//                new SwingWaypoint("Retrouvez nous Dans cet evenement le 17/03/2022", local1),
//                new SwingWaypoint("Retrouvez nous Dans cet evenement le 30/03/2022", local2),
//                new SwingWaypoint("Retrouvez nous Dans cet evenement le 04/04/2022", local3),
//                new SwingWaypoint("Retrouvez nous Dans cet evenement le 20/04/2022", local4),
//                new SwingWaypoint("Retrouvez nous Dans cet evenement le 30/04/2022", local5)));
//
//        // Set the overlay painter
//        WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
//        swingWaypointPainter.setWaypoints(waypoints);
//        mapViewer.setOverlayPainter(swingWaypointPainter);
//
//        // Add the JButtons to the map viewer
//        for (SwingWaypoint w : waypoints) {
//            mapViewer.add(w.getButton());
//        }
//
//        // Display the viewer in a JFrame
//        JFrame frame = new JFrame("Events Map");
//        frame.getContentPane().add(mapViewer);
//        frame.setSize(800, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
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
            ObservableList<Event> datalist= FXCollections.observableArrayList(ev);
            Table_event.setItems(datalist);
    } 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
            try {
                Parent root = loader.load();
                locationevent.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
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
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
            try {
                Parent root = loader.load();
                locationevent.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

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
                             my_pdf_report.add(new Paragraph("                                                           Events\n"
                                     + "                                                        "));
                             
                             my_pdf_report.addCreationDate();
              
                       
                       //we have five columns in our table
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
                                       String DateDebut=rs.getString("DateDebut");
                                       table_cell=new PdfPCell(new Phrase(DateDebut));
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
            
            statStage.setTitle("Satistique Event par mois");
            statStage.setScene(scene1);
            statStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
        
     }  
   /*   
    public void sendMail(String recepient) throws Exception {
        
        String adresse= RecupAdd(); 
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "nahawand.manai@esprit.tn";
        //Your gmail password
        String password = "khalil22";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        //Prepare email message
        
        Message message = prepareMessage(session, myAccountEmail, adresse);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String adresse) throws SQLException, javax.mail.MessagingException {
        MimeMessage mail=new MimeMessage(session); 
        MimeMultipart multipart = new MimeMultipart("related");
        MimeBodyPart htmlPart = new MimeBodyPart();

        try {
  

multipart.addBodyPart(htmlPart);

MimeBodyPart imgPart=new MimeBodyPart();
String path = "../images/valo.jpeg";
DataSource ds=new FileDataSource(path);
imgPart.setDataHandler(new DataHandler(ds));    
imgPart.setHeader("Content-ID","the-img-1");
multipart.addBodyPart(imgPart);

mail.setContent(multipart);

mail.setSentDate(new Date());
mail.setHeader("X-Mailer", "ALS Notifier Build 1.0.0.10");
            //Message message = new MimeMessage(session);
            mail.setFrom(new InternetAddress(myAccountEmail));
            mail.setRecipient(Message.RecipientType.BCC, new InternetAddress("nahawandmanai98@gmail.com"));
            mail.setSubject("New EVENTS Fitnessni");
            String htmlCode = " <h1>Check our new events\n</h1><h2>De la part de l'équipe Black Water</h2>";
            mail.setContent(htmlCode, "text/html");
         
            return (Message) mail;
            
            
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
        }
        return null;
    }    */   

    @FXML
    private void Refresh(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
            try {
                Parent root = loader.load();
                locationevent.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

    }
             
}

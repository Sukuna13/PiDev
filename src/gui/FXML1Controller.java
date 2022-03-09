/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.text.Document;
import util.maConnexion;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class FXML1Controller implements Initializable {
    private Parent fxml;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void interfaceemployés(MouseEvent event) {

        try {
            fxml= FXMLLoader.load(getClass().getResource("gereremployer.fxml"));
            root.getChildren ().removeAll ();
            root.getChildren ().setAll (fxml);
            
            } catch (IOException ex) {
            Logger.getLogger(FXML1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void interfaceproduits(MouseEvent event) {
                try {
            fxml= FXMLLoader.load(getClass().getResource("FXML2.fxml"));
            root.getChildren ().removeAll ();
            root.getChildren ().setAll (fxml);
            
            } catch (IOException ex) {
            Logger.getLogger(FXML1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void quitter(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void tableaudebord(MouseEvent event) {
         try {
            fxml= FXMLLoader.load(getClass().getResource("statistiques.fxml"));
            root.getChildren ().removeAll ();
            root.getChildren ().setAll (fxml);
            
            } catch (IOException ex) {
            Logger.getLogger(FXML1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   /* 
    ///// PDF

  
    @FXML
    private void exportPDF(ActionEvent event) throws ClassNotFoundException, ClassNotFoundException, SQLException, IOException, URISyntaxException, DocumentException {
       try {
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection cnx = maConnexion.getInstance().getCnx();
                     PreparedStatement ps ;
                // Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/connect_sport", "root", "");
                 // PreparedStatement pt = con.prepareStatement("select * from magasin");
                
                        Step-2: Initialize PDF documents - logical objects 

                       Document my_pdf_report = new Document(){};
                       
                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
                       
                              my_pdf_report.open();  
//                             my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("c:/6.png");
//                            my_pdf_report.add(img);
                             my_pdf_report.add(new Paragraph("Magasins"));
                             my_pdf_report.addCreationDate();
              
                       
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(3);
                             
                       //create a cell object
                       PdfPCell table_cell;
                       
                       
                                       table_cell=new PdfPCell(new Phrase(" nom"));
                                      table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("adresse"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("nombrebloc"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       
                                       

                                      while(rs.next()){
                                      
                                       String nom= rs.getString("nom");
                                       table_cell=new PdfPCell(new Phrase(nom));
                                       my_report_table.addCell(table_cell);
                                       String adresse=rs.getString("adresse");
                                       table_cell=new PdfPCell(new Phrase(adresse));
                                       my_report_table.addCell(table_cell);
                                       String nombrebloc=rs.getString("nombrebloc");
                                       table_cell=new PdfPCell(new Phrase(nombrebloc));
                                       my_report_table.addCell(table_cell);
                                       
                                        
                       }
                        Attach report table to PDF 
                       
                       my_pdf_report.add(my_report_table); 
                       
                       System.out.println(my_pdf_report);
                       my_pdf_report.close();
                       JOptionPane.showMessageDialog(null, "impression effectuée");

                        Close all DB related objects 
                       rs.close();
                       pt.close(); 
                       con.close();               


       } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       }


    }
    }

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
            );-
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */

 /*public void exportExcel() throws FileNotFoundException, IOException, SQLException{
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
    } */

    @FXML
    private void pdf1(MouseEvent event) throws SQLException, FileNotFoundException, IOException {
       
        Connection cnx = maConnexion.getInstance().getCnx();
        String query = "select nom_emp,prenom_emp,num_emp from employe";
         PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("employé");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Nom");
            header.createCell(1).setCellValue("Prenom");
           
            header.createCell(2).setCellValue("Numero");
           
            
            int index = 1;
            while(rs.next()){
                index+=1;
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("nom_emp"));
                row.createCell(1).setCellValue(rs.getString("prenom_emp"));
                
                row.createCell(2).setCellValue(rs.getInt("num_emp"));
                
            }
            
            FileOutputStream file = new FileOutputStream("employé.xlsx");
            wb.write(file);
            file.close();
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information employé");
            alert.setHeaderText(null);
            alert.setContentText("Exportation effectuée!!!");
            alert.showAndWait();
            pst.close();
            rs.close();
            
            File myfFile = new File("C:/Users/infoevo/Desktop/PRODUIT/employé.xlsx");
            Desktop.getDesktop().open(myfFile);
    }

    @FXML
    private void abonnement(MouseEvent event) {
         try {
            fxml= FXMLLoader.load(getClass().getResource("abonnement.fxml"));
            root.getChildren ().removeAll ();
            root.getChildren ().setAll (fxml);
            
            } catch (IOException ex) {
            Logger.getLogger(FXML1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    



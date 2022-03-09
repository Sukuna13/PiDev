/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import interfaces.Iproduit;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Produit;
import services.ServiceProduit;
import util.maConnexion;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FXML2Controller implements Initializable {
 Iproduit sp= new ServiceProduit();
  Connection cnx = maConnexion.getInstance().getCnx();
            PreparedStatement ps ;
    @FXML
    private TextField txt_searchId;
    @FXML
    private TextField txt_lib;
    @FXML
    private TextField txt_prix;
    @FXML
    private TextField txt_desc;
    @FXML
    private TextField txt_mar;
    @FXML
    private TextField txt_quan;
    @FXML
    private TextField txt_not;
    @FXML
    private Label lab_url;
    @FXML
    private TableView<Produit> table_produit;
    @FXML
    private TableColumn<Produit, Integer> id_pr;
    @FXML
    private TableColumn<Produit, String> cate_pro;
    @FXML
    private TableColumn<Produit, String> lib_pro;
    @FXML
    private TableColumn<Produit, String> mar_pro;
    @FXML
    private TableColumn<Produit, Float> prix_pro;
    @FXML
    private TableColumn<Produit, String> desc_pro;
    @FXML
    private TableColumn<Produit, String> disp_pro;
    @FXML
    private TableColumn<Produit, Float> note_pro;
    @FXML
    private TableColumn<Produit, Integer> qua_prod;
    @FXML
    private ImageView image_produit;
    @FXML
    private TextField txt_cat;
    @FXML
    private TextField txt_desp;
    @FXML
    private TextField txt_img;
    @FXML
    private ImageView icon_imprt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afichier();
        remplircateg();
   
     // ObservableList<String> list = FXCollections.observableArrayList("Jeux","Hardware");
      // cd_catt.setItems(list);
    }  
    
   
ObservableList<Produit> listprod = FXCollections.observableArrayList();
    @FXML
    private void afichier() {
       
       
        Connection cnx = maConnexion.getInstance().getCnx();
           table_produit.getItems().clear();
     String req = "SELECT * FROM produit";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                listprod.add(new Produit(rs.getInt("id_prod"), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getFloat(9), rs.getInt(10)));
          
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
        
        
         id_pr.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id_prod"));
        cate_pro.setCellValueFactory(new PropertyValueFactory<Produit, String>("categorie_prod")); 
         lib_pro.setCellValueFactory(new PropertyValueFactory<Produit, String>("libelle")); 
            mar_pro.setCellValueFactory(new PropertyValueFactory<Produit, String>("marque")); 
              prix_pro.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix")); 
                 desc_pro.setCellValueFactory(new PropertyValueFactory<Produit, String>("description_prod")); 
                 disp_pro.setCellValueFactory(new PropertyValueFactory<Produit, String>("disponibilite")); 
                  note_pro.setCellValueFactory(new PropertyValueFactory<Produit, Float>("note")); 
                   qua_prod.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantite")); 
                         table_produit.setItems(listprod);
    }

    
    private void remplircateg() {
       
       
        
   // String s =  cd_catt.getSelectionModel().getSelectedItem().toString();
     
    }

    private void remplirdispon() {
        
        Connection cnx = maConnexion.getInstance().getCnx();
        String req = "SELECT `disponibilite` FROM `produit` ";
        List<String> prom = new ArrayList<String>();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                prom.add(rs.getString("disponibilite"));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //cd_disp.setItems(FXCollections.observableArrayList(prom));
        //cd_disp.setItems(prom);
    }

    @FXML
    private void addajout(ActionEvent event) {
        
        String     catt=txt_cat.getText();
         String    libb=      txt_lib.getText();
        String    marc=     txt_mar.getText();
          String   pri=   txt_prix.getText();
        String   des= txt_desc.getText();
        String   desp=  txt_desp.getText();
         String   nt=  txt_not.getText();
           String quan = txt_quan.getText();       
         if (!catt.equals("")&&!libb.equals("")&&!marc.equals("")&&!pri.equals("")&&!des.equals("")&&!desp.equals("")&&! nt.equals("")&&! quan.equals("")) {
             
                      sp.ajouterproduit(new Produit(txt_cat.getText(), txt_lib.getText(),txt_mar.getText(),Float.parseFloat(txt_prix.getText()), txt_desc.getText(),txt_img.getText(),txt_desp.getText(),Float.parseFloat(txt_not.getText()),Integer.parseInt(txt_quan.getText())));
                     txt_cat.setText("");
                      txt_lib.setText("");
                      txt_mar.setText("");
                      txt_prix.setText("");
                      txt_desc.setText("");
                      txt_img.setText("");
                      txt_desp.setText("");
                      txt_not.setText("");
                      txt_quan.setText("");
           Alert alert = new Alert(AlertType.CONFIRMATION," produit ajouter  avec succes", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
            afichier();
        } else {
           Alert alert = new Alert(AlertType.ERROR ,"Erreur de saisie  ="+txt_searchId.getText()+"", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
        }

    }
        
        
        
        
        
    

    @FXML
    private void Selected(MouseEvent event) {
    }

    @FXML
    private void searchproduit() {
    
       Connection cnx = maConnexion.getInstance().getCnx();
        String req = "SELECT * FROM produit where  id_prod ='"+txt_searchId.getText()+"'";
       int m=0;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {                
            txt_cat.setText(rs.getString("categorie_prod"));
             txt_lib.setText(rs.getString("libelle"));
              txt_mar.setText(rs.getString("marque"));
               txt_prix.setText(rs.getString("prix"));
               txt_desc.setText(rs.getString("description_prod"));
                 txt_desp.setText(rs.getString("disponibilite"));
                 txt_not.setText(rs.getString("note"));
                  txt_quan.setText(rs.getString("quantite"));
                  m=1;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       if (m == 0) {
            Alert alert = new Alert(AlertType.ERROR ,"Aucun produit avec id_prod ="+txt_searchId.getText()+"", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
           
        
    
    
}}

    @FXML
    private void editproduit(MouseEvent event) {
        
           String     catt=txt_cat.getText();
         String    libb=      txt_lib.getText();
        String    marc=     txt_mar.getText();
          String   pri=   txt_prix.getText();
        String   des= txt_desc.getText();
        String   desp=  txt_desp.getText();
         String   nt=  txt_not.getText();
           String quan = txt_quan.getText();       
         if (!catt.equals("")&&!libb.equals("")&&!marc.equals("")&&!pri.equals("")&&!des.equals("")&&!desp.equals("")&&! nt.equals("")&&! quan.equals("")) {
             
             sp.modifierproduit(new Produit(Integer.parseInt(txt_searchId.getText()),txt_cat.getText(), txt_lib.getText(),txt_mar.getText(),Float.parseFloat(txt_prix.getText()), txt_desc.getText(),txt_img.getText(),txt_desp.getText(),Float.parseFloat(txt_not.getText()),Integer.parseInt(txt_quan.getText() )));
                     
                      txt_cat.setText("");
                      txt_lib.setText("");
                      txt_mar.setText("");
                      txt_prix.setText("");
                      txt_desc.setText("");
                      txt_img.setText("");
                      txt_desp.setText("");
                      txt_not.setText("");
                      txt_quan.setText("");
           Alert alert = new Alert(AlertType.CONFIRMATION," produit modifier  avec succes", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
            afichier();
        } else {
           Alert alert = new Alert(AlertType.ERROR ,"Erreur de modifier  ="+txt_searchId.getText()+"", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
        }

    }

    @FXML
    private void Suppproduit(MouseEvent event) {
        
        
        
     String     catt=txt_cat.getText();
         String    libb=      txt_lib.getText();
        String    marc=     txt_mar.getText();
          String   pri=   txt_prix.getText();
        String   des= txt_desc.getText();
        String   desp=  txt_desp.getText();
         String   nt=  txt_not.getText();
           String quan = txt_quan.getText();       
         if (!catt.equals("")&&!libb.equals("")&&!marc.equals("")&&!pri.equals("")&&!des.equals("")&&!desp.equals("")&&! nt.equals("")&&! quan.equals("")) {
              sp.suppproduit(new Produit(Integer.parseInt(txt_searchId.getText()),txt_cat.getText(), txt_lib.getText(),txt_mar.getText(),Float.parseFloat(txt_prix.getText()), txt_desc.getText(),txt_img.getText(),txt_desp.getText(),Float.parseFloat(txt_not.getText()),Integer.parseInt(txt_quan.getText() )));
                     
            
                      txt_cat.setText("");
                      txt_lib.setText("");
                      txt_mar.setText("");
                      txt_prix.setText("");
                      txt_desc.setText("");
                      txt_img.setText("");
                      txt_desp.setText("");
                      txt_not.setText("");
                      txt_quan.setText("");
           Alert alert = new Alert(AlertType.CONFIRMATION," produit suprimé  avec succes", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
            afichier();
        } else {
           Alert alert = new Alert(AlertType.ERROR ,"Erreur de suprission  ="+txt_searchId.getText()+"", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
        }

    }
 /* public void showimage(){
     
         Connection cnx = maConnexion.getInstance().getCnx();
         String req = "SELECT image_prod FROM produit";
         
         byte byteImg[];
         Blob blob;
         try {
         Statement st = cnx.createStatement();
         ResultSet rs = st.executeQuery(req);
         blob=rs.getBlob("image_prod");
         byteImg=blob.getBytes (1, (int) blob.length ());
          Image img =new Image (new ByteArrayInputStream (byteImg),image_produit.getFitWidth(),image_produit.getFitHeight(),true,true);
         image_produit.setImage(img); 
     } catch (SQLException ex) {
          ex.printStackTrace();
              }
}*/
  
    @FXML
    private void importimage() {
         byte byteImg[];
        Blob blob;
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Images files","*.png","*.jpg"));
        File f= fc.showOpenDialog(null);
        if(f!=null){
            lab_url.setText(f.getAbsolutePath());
            Image imag = new Image(f.toURI().toString(),image_produit.getFitWidth(),image_produit.getFitHeight(),true,true);
            
             image_produit.setImage(imag);
            
        }
        
        
        
        
    }
}
        
        
        
        
    


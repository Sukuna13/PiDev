/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Plats;
import edu.esprit.services.CreateService;
import edu.esprit.services.DeleteService;
import edu.esprit.services.ReadService;
import edu.esprit.utils.MyConnection;
import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class MainClass {
    
    
     
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/market.fxml"));
        primaryStage.setTitle("foods Marker");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
     public static void main (String[]args)  {
      launch(args);
    MyConnection mc =  MyConnection.getInstance();
    
        // CreateService cs = new CreateService();
       //  cs.ajouterBesoin(new besoin_sportif(1, 12.2f, 52.4f, 200f, 412.0f));
    
        // ReadService rs = new ReadService();
     //   System.out.println( rs.displayAllBesoins());
        
        
         //DeleteService ds = new DeleteService();
         //ds.deleteAllPlats();
         
        
    }
    
}

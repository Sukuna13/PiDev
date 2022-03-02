/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Plats;
import edu.esprit.entities.caractérestiques_sportif;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class UpdateService {
 
    Connection cnx ; 
    public UpdateService() {
        cnx=MyConnection.getInstance().getCnx();
        
    }
    
    public void UpdateService(Plats b){
    
        String req = "UPDATE Plats  SET id_plats =?, id_sportif=? WHERE id_plats =?";
        
        try {
          PreparedStatement pst = cnx.prepareStatement(req);
               
               pst.setInt(1,b.getId_plats());
               pst.setInt(2,b.getId_sportif());
               
        
          
          pst.executeUpdate();
          System.out.println("Updated SUCCESSFULLY !!");          
        }
        catch(SQLException e){
        e.printStackTrace();
        }
    }
    
     public void UpdateService(caractérestiques_sportif C){
         
        String req = "UPDATE caractérestiques_sportif  SET id_caractérestiques =?, id_sportif=?, taille_sportif=?, poid_sportif=?, age_sportif=?, sexe_sportif=?, objectif_nutrition =?, Bmi_sportif=?, besoin_bmi=?, besoin_protein=?, besoin_carbs=?, besoin_calories=?, besoin_fat=?  WHERE id_caractérestiques =?";
        
        try {
          PreparedStatement pst = cnx.prepareStatement(req);
               
                pst.setInt(1,C.getId_caractérestiques()); 
                pst.setInt(2,C.getId_sportif());
                pst.setFloat(3,C.getTaille_sportif());
                pst.setFloat(4,C.getPoid_sportif());
                pst.setInt(5,C.getAge_sportif());
                pst.setString(6,C.getSexe_sportif());
                pst.setString(7,C.getObjectif_nutrition());
                pst.setInt(8,C.getBmi_sportif());
                pst.setInt(9,C.getBesoin_bmi());
                pst.setInt(10,C.getBesoin_protein());
                pst.setInt(11,C.getBesoin_carbs());
                pst.setInt(12,C.getBesoin_calories());
                pst.setInt(13,C.getBesoin_fat());
               
        
          
          pst.executeUpdate();
          System.out.println("Updated SUCCESSFULLY !!");          
        }
        catch(SQLException e){
        e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
}

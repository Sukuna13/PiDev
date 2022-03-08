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
    
        String req = "UPDATE Plat  SET idPlat =?, idSportif=? WHERE id_plat =?";
        
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
         
        String req = "UPDATE caracteristiquesportif  SET idCaracterisitque =?, idSportif=?, tailleSportif=?, poidSportif=?, ageSportif=?, sexe=?, objectifNutrition=?, bmiSportif=?, besoinProteine=?, besoinCarb=?, besoinCalories=?, besoinFat=?  WHERE idCaracterisitque =?";
        
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
               
                pst.setInt(9,C.getBesoin_protein());
                pst.setInt(10,C.getBesoin_carbs());
                pst.setInt(11,C.getBesoin_calories());
                pst.setInt(12,C.getBesoin_fat());
               
        
          
          pst.executeUpdate();
          System.out.println("Updated SUCCESSFULLY !!");          
        }
        catch(SQLException e){
        e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
}

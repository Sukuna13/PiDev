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
import java.util.List;

/**
 *
 * @author HP
 */
public class CreateService {
    Connection cnx ; 

    public CreateService() {
        cnx=MyConnection.getInstance().getCnx();
    }
    
    
    
    public void ajouterPlats(Plats B){
    
        String requete = "INSERT INTO Plat( idPlat, idSportif) VALUES(?,?) "; 
        try {
               PreparedStatement pst = cnx.prepareStatement(requete);
               pst.setInt(1,B.getId_sportif());
               pst.setInt(2,B.getId_plats());
               
               pst.executeUpdate();
               System.err.println("added successfully !!!!!");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    
    }
    
    public void ajoutercaractérestiques (caractérestiques_sportif C){
        
        String requete ="INSERT INTO caracteristiquesportif(idCaracterisitque, idSportif, tailleSportif, poidSportif, ageSportif, sexe, objectifNutrition, bmiSportif, besoinProteine, besoinCarb, besoinCalories, besoinFat) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
                PreparedStatement pst =cnx.prepareStatement(requete);
             
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
                 System.err.println("added successfully !!!!!");
                
                
                
        
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    
    }
   
   
    
}

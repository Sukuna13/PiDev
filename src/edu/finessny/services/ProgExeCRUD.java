/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.finessny.services;

import edu.finessny.utils.MyConnection;
import edu.fitnessny.entities.Exercice;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author rocky
 */
public class ProgExeCRUD {
    
    
     public void ajouterExercice(Exercice e) {
        try {
            String requete2 = "INSERT INTO exercice (nomExercice,nbrSerie,nbrRepetition,descriptionExercice)" + " VALUES (?,?,?,?)";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
            pst.setString(1, e.getNomExercice());
       
            pst.executeUpdate();
            System.out.println(" Votre Exercice  est Ajoutee ++ ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class DeleteService {
     Connection cnx;

    public DeleteService() {
        cnx=MyConnection.getInstance().getCnx();
    }
             
    public void deleteAllPlats(){
        String req ="DELETE FROM Plat";
        
        try {
             PreparedStatement pst = cnx.prepareStatement(req);
             pst.executeUpdate();
             System.out.print("deleted successfully");
        } catch (SQLException e) {
            System.err.print(e.getMessage());
        }
    }
    
    
    public void deleteAllcaractérestiques(){
        String req ="DELETE FROM caracteristiquesportif";
        
        try {
             PreparedStatement pst = cnx.prepareStatement(req);
             pst.executeUpdate();
             System.out.print("deleted successfully");
        } catch (SQLException e) {
            System.err.print(e.getMessage());
        }
    }
            
    
}

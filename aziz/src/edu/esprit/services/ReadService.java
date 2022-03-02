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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class ReadService {
    
    
    Connection cnx;

    public ReadService() {
        cnx=MyConnection.getInstance().getCnx();
        
    }
    

    
    public List<Plats> displayAllPlats(){
        List<Plats> plats =new ArrayList<>();
        try {
            String req ="SELECT * FROM Plats";
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next()){
                Plats plat = new Plats();
                plat.setId_plats(rs.getInt(1));
                plat.setId_sportif(rs.getInt(2));
                plats.add(plat);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
        }
    return plats;
    }
    
    
    
    public List<caractérestiques_sportif> displayAllcaractérestiques(){
        List<caractérestiques_sportif> caractérestiques =new ArrayList<>();
        try {
            String req ="SELECT * FROM caractérestiques_sportif";
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next()){
                caractérestiques_sportif caractérestique = new caractérestiques_sportif();
                caractérestique.setId_caractérestiques(rs.getInt(1));
                caractérestique.setId_caractérestiques(rs.getInt(2));
                caractérestiques.add(caractérestique);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
        }
    return caractérestiques ;
    }
    
    
}

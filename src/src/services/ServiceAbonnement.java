/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Abonement;
import model.Produit;
import util.maConnexion;

/**
 *
 * @author infoevo
 */
public class ServiceAbonnement {
    
    Connection cnx = maConnexion.getInstance().getCnx();

    
    public void ajouterabonnement(Abonement s) {
         String req = "insert into Abonement(nom_emp,prenom_emp,dated,datef) values(?,?,?,?)";

          try {
            PreparedStatement ps = cnx.prepareStatement(req);         
    
    
            ps.setString(1, s.getNom_emp());
            ps.setString(2, s.getPrenom_emp());
            ps.setDate(3, s.getDated());
            ps.setDate(4, s.getDatef());
            ps.execute();
            System.out.println("2 : produit ajoutee avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
}
        public List<Abonement> afficherabonnement() {
       List<Abonement> prod = new ArrayList<>();
        
        String req = "SELECT * FROM Abonement";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                prod.add(new Abonement(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4)));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return prod; }
}

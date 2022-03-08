/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import gui.GereremployerController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employe;
import model.Produit;
import util.maConnexion;

/**
 *
 * @author infoevo
 */
public class serviceEmployees {
    Connection cnx = maConnexion.getInstance().getCnx();

    public void ajouterEmploye(Employe s) {
         String req = "INSERT INTO Employe (nom_emp,prenom_emp,mail,num_emp,salaire,type) VALUES(?,?,?,?,?,?)";

          try {
            PreparedStatement ps = cnx.prepareStatement(req);         

          ps.setString(1, s.getNom_emp());
	  ps.setString(2, s.getPrenom_emp());
          ps.setString(3, s.getMail());
          ps.setInt(4, s.getNum_emp()); 
          ps.setFloat(5, s.getSalaire());
          ps.setString(6, s.getType());

            ps.execute();
            System.out.println("2 :employe ajoutee avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    

    }

   
    public List<Employe> afficherEmploye() {
       List<Employe> prod = new ArrayList<>();
        
        String req = "SELECT * FROM Employe";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                prod.add(new Employe(rs.getInt(1), rs.getString(2), rs.getString (3),rs.getString (4), rs.getInt(5),rs.getInt(6),rs.getString (7)));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return prod; }
    
    
    


 
    public void modifieremploye(Employe s) {
        
        GereremployerController gec = new GereremployerController();
            String req7 = "UPDATE Employe SET nom_emp=?, prenom_emp=?,mail=?, num_emp=?, salaire=?, type=? WHERE id_emp=?";
            try{
            PreparedStatement ps = cnx.prepareStatement(req7);
            
            
            ps.setString(1, s.getNom_emp());
            ps.setString(2, s.getPrenom_emp());
            ps.setString(3, s.getMail());
            ps.setInt(4, s.getNum_emp());
            ps.setFloat(5, s.getSalaire());
            ps.setString(6, s.getType());
            ps.setInt(7, gec.id);
            ps.executeUpdate();
            
            /*try {
            String req = "UPDATE employe SET `nom_emp`='" +s.getNom_emp()
            + "',`prenom_emp`='" +s.getPrenom_emp()
            + "',`num_emp`='" + s.getNum_emp()
            + "',`salaire`='" +  +s.getSalaire()
            + "',`type`='" + s.getType()
            + "' where mail=" + s.getMail();
            Statement ps = cnx.createStatement();
            ps.executeUpdate(req);
            System.out.println("Done. Employe bien modifier ");
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
            */
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }       

    
    public void suppemploye(Employe s) {
        try {
            PreparedStatement ps = cnx.prepareStatement("DELETE FROM Employe WHERE  mail = ?");
            
            ps.setString(1,s.getMail());
            
            
            if (ps.executeUpdate() > 0)
                System.out.println(" l'employe de l'email  = "+s+" deleted successfully.");
            else
                System.out.println("Record not found.");
        } catch (SQLException ex) {
            Logger.getLogger(serviceEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
      }   

    }
   


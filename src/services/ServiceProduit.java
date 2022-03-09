/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Iproduit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Produit;
import util.maConnexion;

/**
 *
 * @author MSI
 */
public class ServiceProduit implements Iproduit {
 Connection cnx = maConnexion.getInstance().getCnx();

    @Override
    public void ajouterproduit(Produit s) {
         String req = "insert into produit(id_prod,categorie_prod,libelle,marque,prix,description_prod,image_prod,disponibilite,note,quantite) values(?,?,?,?,?,?,?,?,?,?)";

          try {
            PreparedStatement ps = cnx.prepareStatement(req);         
    
    
            ps.setInt(1, s.getId_prod());
            ps.setString(2, s.getCategorie_prod());
            ps.setString(3, s.getLibelle());
            ps.setString(4, s.getMarque());
            ps.setFloat(5, s.getPrix());
             ps.setString(6, s.getDescription_prod());
            ps.setString(7, s.getImage_prod());
            ps.setString(8, s.getDisponibilite());
            ps.setFloat(9, s.getNote());
            ps.setInt(10, s.getQuantite());

            ps.execute();
            System.out.println("2 : produit ajoutee avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    

    }

    @Override
    public List<Produit> afficherproduit() {
       List<Produit> prod = new ArrayList<>();
        
        String req = "SELECT * FROM produit";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                prod.add(new Produit(rs.getInt("id_prod"), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getFloat(9), rs.getInt(10)));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return prod; }
    
    
    


    @Override
    public void modifierproduit(Produit s) {
          try{
        String req = "UPDATE produit SET   `categorie_prod`='" +s.getCategorie_prod()
                                             + "',`libelle`='" +s.getLibelle()
                                              + "',`marque`='" + s.getMarque()
                                            + "',`prix`='" +  +s.getPrix()
                                               + "',`description_prod`='" + s.getDescription_prod()
                                                + "',`image_prod`='" + s.getImage_prod()
                                              + "',`disponibilite`='" + s.getDisponibilite()
                                                 + "',`note`='" + s.getNote()
                                                   + "',`quantite`='" + s.getQuantite()
                                                   + "' where id_prod=" + s.getId_prod() ;  
          Statement ps = cnx.createStatement();
    ps.executeUpdate(req);
        System.out.println("Done. STOCK bien modifier ");
        }catch(SQLException e){
        System.out.println(e.getMessage());
    
    }       }

    @Override
    public void suppproduit(Produit s) {
        try (PreparedStatement ps = cnx.prepareStatement("DELETE FROM produit WHERE  id_prod = ?")
) {
    ps.setInt(1,s.getId_prod());

    if (ps.executeUpdate() > 0)
        System.out.println("stock with id_prod = "+s+" deleted successfully.");
    else
        System.out.println("Record not found.");
}       catch (SQLException ex) {
          ex.printStackTrace();        }   

}

    public void ajouterproduit1(Produit s) {
         String req = "insert into produit (categorie_prod,libelle,marque,prix,description_prod,disponibilite,note,quantite) values(?,?,?,?,?,?,?,?)";

          try {
            PreparedStatement ps = cnx.prepareStatement(req);         
    
    
           
            ps.setString(1, s.getCategorie_prod());
            ps.setString(2, s.getLibelle());
            ps.setString(3, s.getMarque());
            ps.setFloat(4, s.getPrix());
             ps.setString(5, s.getDescription_prod());
            ps.setString(6, s.getDisponibilite());
            ps.setFloat(7, s.getNote());
            ps.setInt(8, s.getQuantite());

            ps.execute();
            System.out.println("2 : produit ajoutee avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
}

    @Override
    public List<Produit> filtrerproduit() {
         List<Produit> prod = new ArrayList<>();
        
        String req = "SELECT * FROM produit where  quantite > 100 ";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                prod.add(new Produit(rs.getInt("id_prod"), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getFloat(9), rs.getInt(10)));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return prod; }
    
}
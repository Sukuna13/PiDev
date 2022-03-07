/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import outils.Myconnection;

/**
 *
 * @author nahaw
 */
public class CommentaireCrud {
   Connection cnx; 
   
    public CommentaireCrud() {
        
          cnx = Myconnection.getinstance().getcnx();
    
    }
   
    public void ajouterCommentaire() {
        try {
            String requete ="INSERT INTO Commentaire (idCommentaire,idForum) VALUES (5,2,'ghhjs')";

            Statement st = new Myconnection().getcnx().createStatement(); 
            st.executeUpdate(requete); //update pour les requetes qui mettent à jour la base et on a aussi querry
            System.out.println("Commentaire ajoutée avec succès");
            
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
        }
    }
    
    public void ajouterCommentaire2(Commentaire c) {
        try {
            String requete2 = "INSERT INTO Commentaire (idForum,message) VALUES (?,?)"; //requete dynamique
            //pst = new Myconnection().getcnx().prepareStatement(requete2);
            PreparedStatement pst = cnx.prepareStatement(requete2);     
            pst.setInt(1,c.getIdForum()); 
            pst.setString(2,c.getMessage()); 
            pst.executeUpdate(); 
            System.out.println ("Votre commentaire a été ajoutée"); 
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    } 
     /**
     *
     * @return 
     */
    
     public List<Commentaire> afficherCommentaireCrud (){
       
        List<Commentaire> myList = new ArrayList<> ();
        try {
            String requete3 = "SELECT * FROM Commentaire ";
            //Statement st = new Myconnection().getcnx().createStatement();
            Statement st = cnx.createStatement(); 
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()){
                Commentaire c = new Commentaire(); 
                c.setIdCommentaire(rs.getInt(1)); 
                c.setIdForum(rs.getInt(2)); 
                c.setMessage(rs.getString(3));
                myList.add(c); 
            } 
           
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
         return myList;
    }

    public List<Commentaire> afficherCommentaire() {
        
            List<Commentaire> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM Commentaire";
            //Statement st = new Myconnection().getcnx().createStatement(); 
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
       return myList;
              
    } 
    
 public void modifierCommentaire(Commentaire c) {
        try{ 
        String req = "UPDATE Commentaire SET idForum=?,message=?  WHERE idCommentaire= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1,c.getIdForum());
        ps.setString(2,c.getMessage());
        ps.setInt(3,c.getIdCommentaire());
        System.out.println("Modification réalisée");
        ps.executeUpdate(); 
        System.out.println("Une ligne modifiée dans la table");
       }
       catch(SQLException ex){
            System.err.println(ex.getMessage());
       }
    }

    
    public void supprimerCommentaire(int idCommentaire) {
            try{
            String req = "DELETE FROM Commentaire WHERE idCommentaire = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression faite");
            ps.setInt(1,idCommentaire);
            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table");
       }
       catch(SQLException ex){
           System.err.println(ex.getMessage());
       }
    }   
}

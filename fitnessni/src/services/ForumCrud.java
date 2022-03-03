/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
//table commentaire contient comentaire et id sportif et reliée à un id Forum 
import entities.Forum;
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
public class ForumCrud {
  connection cnx; 
   private Statement sta;
    public void ajouterForum() {
        try {
            String requete ="INSERT INTO forum (idForum,Sujet,id) VALUES (2,'les avantages',12)";

            Statement st = new Myconnection().getcnx().createStatement(); 
            st.executeUpdate(requete); //update pour les requete qui mettent à jour la base et on a aussi querry
            System.out.println("forum ajoutée avec succès");
            
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
        }
    }
    
    public void ajouterForum2(Forum f) {
        //PreparedStatement pst = null;
        try {
            String req = "insert into Forum (Sujet,id) VALUES (?,?) ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1,f.getSujet()); 
            pst.setInt(2,f.getId());  

            pst.executeUpdate(req);
            System.out.println ("Votre forum a été ajoutée"); 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
            /*String requete2 = "INSERT INTO forum (Sujet,idCoach,idSalle,idSportif) VALUES (?,?,?,?)"; //requete dynamique
          // pst = new Myconnection().getcnx().prepareStatement(requete2);

            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1,f.getSujet());
            pst.setInt(2,f.getIdCoach());
            pst.setInt(3,f.getIdSalle());
            pst.setInt(4,f.getIdSportif());
           
            pst.executeUpdate(); 
            System.out.println ("Votre evenement a été ajoutée"); 
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    } */
     /**
     *
     * @return 
     */
   public List<Forum> afficherForumCrud (){
       
        List<Forum> myList = new ArrayList<> ();
        try {
            String requete3 = "SELECT * FROM forum ";
            //Statement st = new Myconnection().getcnx().createStatement();
            Statement st = cnx.createStatement(); 
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()){
                Forum f = new Forum(); 
                f.setIdForum(rs.getInt(1)); 
                f.setSujet(rs.getString("Sujet")); 
                f.setId(rs.getInt(2)); 
                
                myList.add(f); 
                
            } 
           
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
         return myList;
    }

    public List<Forum> afficherForum() {
        
            List<Forum> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM forum";
            //Statement st = new Myconnection().getcnx().createStatement(); 
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
       return myList;
              
    } 
    
 public void modifierForum(Forum f) {
       
        //PreparedStatement ps = null;
        try{
        
        String req = "UPDATE forum SET Sujet = ?, id=? WHERE idForum= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
       
        ps.setString(1,f.getSujet());
        ps.setInt(2,f.getId());
        ps.setInt(3,f.getIdForum());
      
        
        System.out.println("Modification réalisée");
        ps.executeUpdate();
      
        System.out.println("Une ligne modifiée dans la table");
       }
       catch(SQLException ex){
            System.err.println(ex.getMessage());
       }
               
    }

    
    public void supprimerForum(int code_forum) {
      
             try{
            String req = "DELETE FROM forum WHERE idForum = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression faite");
            ps.setInt(1,code_forum);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table");
       }
       catch(SQLException ex){
           System.err.println(ex.getMessage());
       }
             
    }  
    }

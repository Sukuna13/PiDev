/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import outils.Myconnection;
import java.sql.Connection;
import java.sql.Statement;


/**
 *
 * @author nahaw
 */
public class EventCrud {
    
 static Connection cnx;
 private Statement st;
    public EventCrud() {
        
      cnx = Myconnection.getinstance().getcnx();
    
    }

    public void ajouterEvent() {
        try {
            String requete ="INSERT INTO event (nomEvent,DateDebut,DateFin,heureDebut,heureFin,location) VALUES ('running competition','10/06/2022','10/06/2022',8,12,'ariana')";
//Statement st = new Myconnection().getcnx().createStatement();
            PreparedStatement pt = cnx.prepareStatement(requete); 
            pt.executeUpdate(requete); //update pour les requete qui mettent à jour la base et on a aussi querry
            System.out.println("evenement ajoutée avec succès");
         
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
        }
    }
    
    public void ajouterEvent2(Event e) {
        try {
            String requete2 = "INSERT INTO event (nomEvent,DateDebut,DateFin,heureDebut,heureFin,location) VALUES (?,?,?,?,?,?)"; //requete dynamique
            //pst = new Myconnection().getcnx().prepareStatement(requete2);
            PreparedStatement pst = cnx.prepareStatement(requete2); 
            
            pst.setString(1,e.getNomEvent()); 
            pst.setDate(2,e.getDateDebut());
            pst.setDate(3,e.getDateFin());
            pst.setInt(4,e.getHeureDebut());
            pst.setInt(5,e.getHeureFin());
            pst.setString(6,e.getLocation());
            
            pst.executeUpdate(); 
            System.out.println ("Votre evenement a été ajoutée"); 
              
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        } 
    }
    
     public List<Event> afficherEventCrud (){
       
        List<Event> myList = new ArrayList<> ();
        try {
            String requete3 = "SELECT * FROM event ";
            PreparedStatement pt = cnx.prepareStatement(requete3);
            //Statement st = cnx2.createStatement(); 
            ResultSet rs = pt.executeQuery();
            while (rs.next()){
                Event e = new Event(); 
                e.setIdEvent(rs.getInt(1)); 
                e.setNomEvent(rs.getString(2)); 
                e.setDateDebut(rs.getDate(3));
                e.setDateFin(rs.getDate(4));
                e.setHeureDebut(rs.getInt(5));
                e.setHeureFin(rs.getInt(6));
                e.setLocation(rs.getString(7));
                myList.add(e); 
            } 
           
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
         return myList;
    }

    public List<Event> afficherEvent() {
        
            List<Event> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM event";
            PreparedStatement ps = cnx.prepareStatement(requete3); 
            //Statement st = cnx2.createStatement();
            ResultSet rs = ps.executeQuery();
            
        } catch (SQLException rs) {
           System.err.println(rs.getMessage());
        }
       return myList;
              
    }
    
    public void modifierEvent(Event e) {
        
        try{
        
        String req = "UPDATE event SET nomEvent = ?, DateDebut=?, DateFin = ?, heureDebut= ?,heureFin = ?, location=?  WHERE idEvent= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
       
        ps.setString(1,e.getNomEvent());
        ps.setDate(2,e.getDateDebut());
        ps.setDate(3,e.getDateFin());
        ps.setInt(4,e.getHeureDebut());
        ps.setInt(5,e.getHeureFin());
        ps.setString(6,e.getLocation());
        ps.setInt(7,e.getIdEvent());
        
        System.out.println("Modification réalisée");
        ps.executeUpdate();
      
        System.out.println("Une ligne modifiée dans la table");
       }
       catch(SQLException ex){
            System.err.println(ex.getMessage());
       }
               
    }
    
    public void supprimerEvent(int idEvent) {
        
        try{
                String Q = "DELETE FROM event WHERE idEvent=" + idEvent;
            st = cnx.createStatement();
            st.executeUpdate(Q);
            
            /*String req = "DELETE FROM event WHERE idEvent = '?'";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression faite");
            ps.setInt(1,idEvent);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMEE dans la table");*/
       }
       catch(SQLException ex){
           System.err.println(ex.getMessage());
       }      
    }  
}

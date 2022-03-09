/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.finessny.services;


import edu.finessny.utils.MyConnection;
import edu.fitnessny.entities.Exercice;
import edu.fitnessny.entities.Programme;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author rocky
 */
public class ExerciceCRUD {

    public void ajouterExercice(Exercice e) {
        try {
            String requete2 = "INSERT INTO exercice (nomExercice,nbrSerie,nbrRepetition,descriptionExercice,categorieExercice)" + " VALUES (?,?,?,?,?)";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
            pst.setString(1, e.getNomExercice());
            pst.setInt(2, e.getNbrSerie());
            pst.setInt(3, e.getNbrRepetition());
            pst.setString(4, e.getDescriptionExercice());
               pst.setString(5, e.getCategorieExercice());
            pst.executeUpdate();
            System.out.println(" Votre Exercice  est Ajoutee ++ ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public List<Exercice> afficherExercice() {
        List<Exercice> myList = new ArrayList<>();

        try {
            String requete3 = "SELECT * FROM Exercice";
            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Exercice e = new Exercice();

                e.setNomExercice(rs.getString("nomExercice"));
                e.setNbrRepetition(rs.getInt("nbrRepetition"));
                e.setNbrSerie(rs.getInt("nbrSerie"));
                e.setDescriptionExercice(rs.getString("descriptionExercice"));
                e.setCategorieExercice(rs.getString("categorieExercice"));

                myList.add(e);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;
    }
    //**************** Supprimee*******************
     public boolean  supprimerExercice1(Exercice pub) {
    try {
            String requete4 = "DELETE FROM `exercice` WHERE `exercice`.`idExercice` = ? ";
             PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete4);
            pst.setInt(1, pub.getIdExercice());
            pst.executeUpdate();
            System.out.println("Exercice  supprimer avec succés");
            return true;
        } catch (SQLException ex) {
            System.err.println (ex.getMessage()); 
            return false;
            
                    }    }
//    public void supprimerExercice(){
//             try {
//            String requete4 = "DELETE FROM Exercice WHERE idExercice= ?";
//            Statement st = new MyConnection().getCnx().createStatement();
//            st.executeUpdate(requete4);
//            System.out.println("Exercice  supprimer avec succés");
//        } catch (SQLException ex) {
//            System.err.println (ex.getMessage());      
//                    }
//         }
//    
//      public void supprimerExercice1(){
//             try {
//            String requete4 = "DELETE FROM Exercice WHERE idExercice=? ";
//            Statement st = new MyConnection().getCnx().createStatement();
//            st.executeUpdate(requete4);
//            System.out.println("Exercice  supprimer avec succés");
//        } catch (SQLException ex) {
//            System.err.println (ex.getMessage());      
//                    }
//         }
    
     public boolean  modifierExercice(Exercice e){
             try {
//                 ,NbrRepetition=? ,nbrSerie= ? 
            String requete5 = "Update exercice SET nomExercice= ? ,nbrRepetition=? ,nbrSerie= ? , descriptionExercice = ? ,categorieExercice = ?  where idExercice = ?  ;";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete5);
            pst.setString(1, e.getNomExercice());
            pst.setInt(2, e.getNbrSerie());
            pst.setInt(3, e.getNbrRepetition());
            pst.setString(4, e.getDescriptionExercice());
            pst.setString(5, e.getCategorieExercice());
            pst.executeUpdate();
            System.out.println("Exercice modifier avec succés");
            return true ;
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());  
            return false ;
                    }
         }
    
//    String requete2 = "INSERT INTO exercice (nomExercice,nbrSerie,nbrRepetition,descriptionExercice,categorieExercice)" + " VALUES (?,?,?,?,?)";
//            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
//            pst.setString(1, e.getNomExercice());
//            pst.setInt(2, e.getNbrSerie());
//            pst.setInt(3, e.getNbrRepetition());
//            pst.setString(4, e.getDescriptionExercice());
//               pst.setString(5, e.getCategorieExercice());
//            pst.executeUpdate();
//            System.out.println(" Votre Exercice  est Ajoutee ++ ");

   

}

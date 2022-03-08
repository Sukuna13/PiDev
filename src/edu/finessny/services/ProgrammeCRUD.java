/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.finessny.services;
import com.mysql.cj.conf.PropertyKey;
import edu.finessny.utils.MyConnection;
import edu.fitnessny.entities.Programme;

import edu.fitnessny.entities.Exercice;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import edu.finessny.services.ExerciceCRUD;
import java.sql.Connection;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author rocky
 */
public class ProgrammeCRUD {

    public void ajouterProgramme(Programme p) {
        try {
            String requete2 = "INSERT INTO programme (nomProgramme,objectifProgramme,descriptionProgramme,categorieProgramme)" + " VALUES (?,?,?,?)";

            //  String requete8 = " UPDATE `programme` SET `idExercice` = '2' WHERE `programme`.`idProgramme` = 14"
            String requete8 = " UPDATE `programme`p , `exercice`e SET p.idExercice = e.idExercice  WHERE p.objectifProgramme = 'perdre du poids' AND e.categorieExercice = p.categorieProgramme ";
            String requete9 = " UPDATE `programme`p , `exercice`e SET p.idExercice = e.idExercice  WHERE p.objectifProgramme = 'rester en forme' AND e.categorieExercice = p.categorieProgramme ";
            String requete10 = " UPDATE `programme`p , `exercice`e SET p.idExercice = e.idExercice  WHERE p.objectifProgramme = 'se muscle' AND e.categorieExercice = p.categorieProgramme ";

            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
            PreparedStatement pst1 = new MyConnection().getCnx().prepareStatement(requete8);
            PreparedStatement pst2 = new MyConnection().getCnx().prepareStatement(requete9);
            PreparedStatement pst3 = new MyConnection().getCnx().prepareStatement(requete10);
            pst.setString(1, p.getNomProgramme());
            pst.setString(2, p.getObjectifProgramme());
            pst.setString(3, p.getDescriptionProgramme());
            pst.setString(4, p.getCategorieProgramme());
            //  pst.setString(3, p.getdescriptionProgramme());   pst1.setInt(1, e.getIdExercice() );

            pst.executeUpdate();
            pst1.executeUpdate();
            pst2.executeUpdate();
            pst3.executeUpdate();
            System.out.println(" Votre Programme  est Ajoutee ++ ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Programme> afficherProgramme() {
        List<Programme> myList = new ArrayList<>();
        List<Exercice> myList1 = new ArrayList<>();

        try {
            //    String requete7 = "select nomExercice ,nbrRepetition ,nbrSerie ,descriptionExercice   from exercice e,programme p WHERE e.idExercice=p.idExercice;";

            String requete3 = "SELECT * FROM programme p , exercice e where p.idExercice =e.idExercice";
            // String requete6 = "SELECT * FROM programme p , exercice e where p.idExercice =e.idExercice";

            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete3);

            System.out.println("\n");
            while (rs.next()) {

                Programme p = new Programme();

                p.setNomProgramme(rs.getString("nomProgramme"));
                p.setObjectifProgramme(rs.getString("objectifProgramme"));
                p.setDescriptionProgramme(rs.getString("descriptionProgramme"));
                p.setCategorieProgramme(rs.getString("categorieProgramme"));

//                Exercice e = new Exercice();
//                e.setNomExercice(rs.getString("nomExercice"));
//                e.setNbrRepetition(rs.getInt("nbrRepetition"));
//                e.setNbrSerie(rs.getInt("nbrSerie"));
//                e.setDescriptionExercice(rs.getString("descriptionExercice"));
//                e.setCategorieExercice(rs.getString("categorieExercice"));
//                
                myList.add(p);
                //myList1.add(e);
//ArrayList<String> a = new ArrayList<String>();

//    all.addAll(myList);
//     all.addAll(myList1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;

    }

    public List<Programme> afficherProgrammeAll() {
        List<Programme> myList = new ArrayList<>();

        try {
            //    String requete7 = "select nomExercice ,nbrRepetition ,nbrSerie ,descriptionExercice   from exercice e,programme p WHERE e.idExercice=p.idExercice;";

            String requete3 = "SELECT * FROM programme";
            // String requete6 = "SELECT * FROM programme p , exercice e where p.idExercice =e.idExercice";

            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete3);

            System.out.println("\n");
            while (rs.next()) {

                Programme p = new Programme();

                p.setNomProgramme(rs.getString("nomProgramme"));
                p.setObjectifProgramme(rs.getString("objectifProgramme"));
                p.setDescriptionProgramme(rs.getString("descriptionProgramme"));
                p.setCategorieProgramme(rs.getString("categorieProgramme"));

//                Exercice e = new Exercice();
//                e.setNomExercice(rs.getString("nomExercice"));
//                e.setNbrRepetition(rs.getInt("nbrRepetition"));
//                e.setNbrSerie(rs.getInt("nbrSerie"));
//                e.setDescriptionExercice(rs.getString("descriptionExercice"));
//                e.setCategorieExercice(rs.getString("categorieExercice"));
//                
                myList.add(p);
                //myList1.add(e);
//ArrayList<String> a = new ArrayList<String>();

//    all.addAll(myList);
//     all.addAll(myList1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;

    }

    /*    public List<Client> afficherClients() {
        List<Client> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM Client";
            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Client c = new Client();
                c.setId_client(rs.getInt(1));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setPrenom(rs.getString("Date_nais"));
                myList.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;
    }
     */
    public boolean delete(Programme p) {
        String req = "DELETE FROM `programme` WHERE `programme`.`nomProgramme` = ? ";
        try {
            
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(req);
            pst.setString(1, p.getNomProgramme());
            pst.execute();
            System.out.println("publication Supprimé");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProgrammeCRUD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    } 
    public void modifierProgramme() {
        try {
            String requete5 = "Update programme SET nomProgramme='Chaaaa' Where idProgramme='3'";
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete5);
            System.out.println("Programme modifier avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void update(Programme p) {
try {
            String requete2 = "UPDATE programme SET nomProgramme=?,objectifProgramme=?,descriptionProgramme=?,categorieProgramme=? ,idExercice=? where idProgramme=?";

            //  String requete8 = " UPDATE `programme` SET `idExercice` = '2' WHERE `programme`.`idProgramme` = 14"
//            String requete8 = " UPDATE `programme`p , `exercice`e SET p.idExercice = e.idExercice  WHERE p.objectifProgramme = 'perdre du poids' AND e.categorieExercice = p.categorieProgramme ";
//            String requete9 = " UPDATE `programme`p , `exercice`e SET p.idExercice = e.idExercice  WHERE p.objectifProgramme = 'rester en forme' AND e.categorieExercice = p.categorieProgramme ";
//            String requete10 = " UPDATE `programme`p , `exercice`e SET p.idExercice = e.idExercice  WHERE p.objectifProgramme = 'se muscle' AND e.categorieExercice = p.categorieProgramme ";

            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
//            PreparedStatement pst1 = new MyConnection().getCnx().prepareStatement(requete8);
//            PreparedStatement pst2 = new MyConnection().getCnx().prepareStatement(requete9);
//            PreparedStatement pst3 = new MyConnection().getCnx().prepareStatement(requete10);
            pst.setString(1, p.getNomProgramme());
            pst.setString(2, p.getObjectifProgramme());
            pst.setString(3, p.getDescriptionProgramme());
            pst.setString(4, p.getCategorieProgramme());
            pst.setInt(5, p.getIdExercice());
             pst.setInt(6, p.getIdProgramme());
            //  pst.setString(3, p.getdescriptionProgramme());   pst1.setInt(1, e.getIdExercice() );

            pst.executeUpdate();
//            pst1.executeUpdate();
//            pst2.executeUpdate();
//            pst3.executeUpdate();
            System.out.println(" Votre Programme  est Modifier ++ ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public ObservableList<Programme> readAll() {
        ObservableList<Programme> publications = FXCollections.observableArrayList();
        String req = "SELECT * FROM `programme`";

        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();

            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Programme p = new Programme();

                p.setNomProgramme(rs.getString("nomProgramme"));
                p.setObjectifProgramme(rs.getString("objectifProgramme"));
                p.setDescriptionProgramme(rs.getString("descriptionProgramme"));
                p.setCategorieProgramme(rs.getString("categorieProgramme"));

                publications.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProgrammeCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return publications;

    }
    
//        private void searchproduit() {
//   
//       Connection cnx = MyConnection.getInstance().getCnx();
//        String req = "SELECT * FROM produit where  id_prod ='"+txt_searchId.getText()+"'";
//       int m=0;
//        try {
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(req);
//            if (rs.next()) {                
//            txt_cat.setText(rs.getString("categorie_prod"));
//             txt_lib.setText(rs.getString("libelle"));
//              txt_mar.setText(rs.getString("marque"));
//               txt_prix.setText(rs.getString("prix"));
//               txt_desc.setText(rs.getString("description_prod"));
//                 txt_desp.setText(rs.getString("disponibilite"));
//                 txt_not.setText(rs.getString("note"));
//                  txt_quan.setText(rs.getString("quantite"));
//                  m=1;
//            }
//           
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//           
//           
//           
//        }
//       if (m == 0) {
//            Alert alert = new Alert(AlertType.ERROR ,"Aucun produit avec id_prod ="+txt_searchId.getText()+"", javafx.scene.control.ButtonType.OK);
//            alert.showAndWait();
//           
//       
//   
//   
//}}
    
    
    
    
    
    
}

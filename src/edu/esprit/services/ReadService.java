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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
/**
 *
 * @author HP
 */
public class ReadService {

    Connection cnx;

    public ReadService() {
        cnx = MyConnection.getInstance().getCnx();

    }

    public List<Plats> displayAllPlats() {
        List<Plats> plats = new ArrayList<>();
        try {
            String req = "SELECT * FROM Plat";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
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

    public List<caractérestiques_sportif> displayAllcaractérestiques() {
        List<caractérestiques_sportif> caractérestiques = new ArrayList<>();
        try {
            String req = "SELECT * FROM caracteristiquesportif";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                caractérestiques_sportif caractérestique = new caractérestiques_sportif();
                caractérestique.setId_caractérestiques(rs.getInt(1));
                caractérestique.setId_caractérestiques(rs.getInt(2));
                caractérestiques.add(caractérestique);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage() + "fd");
        }
        return caractérestiques;
    }

   

    
    
    /**/
       public ObservableList<caractérestiques_sportif> displayAllcaractérestiqueso() {
               ObservableList<caractérestiques_sportif> caractérestiques = FXCollections.observableArrayList();

        try {
            String req = "SELECT * FROM caracteristiquesportif";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                caractérestiques_sportif caractérestique = new caractérestiques_sportif();
                caractérestique.setId_caractérestiques(rs.getInt(1));
                caractérestique.setTaille_sportif(rs.getFloat(3));
                caractérestique.setPoid_sportif(rs.getFloat(4));
                caractérestique.setAge_sportif(rs.getInt(5));
                caractérestique.setBmi_sportif(rs.getInt(8));
                caractérestique.setSexe_sportif(rs.getString(6));
                caractérestique.setObjectif_nutrition(rs.getString("objectifNutrition"));
                caractérestique.setBesoin_protein(rs.getInt(9));
                caractérestique.setBesoin_carbs(rs.getInt(10));
                caractérestique.setBesoin_calories(rs.getInt(11));
                caractérestique.setBesoin_fat(rs.getInt(12));
                caractérestiques.add(caractérestique);
                //System.out.println("c bon");
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage() + "fd");
        }
        return caractérestiques;
    }

}

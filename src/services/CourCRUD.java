/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cour;
import entities.Salle;
import entities.Sportif;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author Asus
 */
public class CourCRUD {

    static Connection cnx;

    public CourCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterCour(Cour cour) {
        try {
            String req = "INSERT INTO planning(idSalle,nom,date,time,nbrTotal,info,nbrActuel) VALUES (?,?,?,?,?,?,0) ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getIdSalle());
            pst.setString(2, cour.getNom());
            pst.setDate(3, cour.getDate());
            pst.setTime(4, cour.getTime());
            pst.setInt(5, cour.getNbrTotal());
            pst.setString(6, cour.getInfo());
            pst.executeUpdate();
            String req2 = "CREATE TABLE " + cour.getIdSalle() + cour.getNom() + cour.getDate().getDay() + cour.getDate().getMonth() + cour.getDate().getYear()
                    + cour.getTime().getHours() + cour.getTime().getMinutes() + cour.getTime().getSeconds() + " (id INT PRIMARY KEY,nom VARCHAR(255))";
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            pst2.executeUpdate();
            System.out.println("Cour ajouté!!!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerCour(Cour cour) {
        try {
            String req = "DELETE FROM planning WHERE idSalle= ? AND nom= ? AND date= ? AND time= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getIdSalle());
            pst.setString(2, cour.getNom());
            pst.setDate(3, cour.getDate());
            pst.setTime(4, cour.getTime());
            pst.executeUpdate();
            String req2 = "DROP TABLE " + cour.getIdSalle() + cour.getNom() + cour.getDate().getDay() + cour.getDate().getMonth() + cour.getDate().getYear()
                    + cour.getTime().getHours() + cour.getTime().getMinutes() + cour.getTime().getSeconds();
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            pst2.executeUpdate();
            System.out.println("Cour supprimé!!!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void ajouterParticipation(Sportif s, Cour cour) {
        try {
            String req3 = "SELECT nbrActuel FROM planning WHERE idSalle= ? AND nom= ? AND date= ? AND time=?";
            PreparedStatement pst3;
            pst3 = cnx.prepareStatement(req3);
            pst3.setInt(1, cour.getIdSalle());
            pst3.setString(2, cour.getNom());
            pst3.setDate(3, cour.getDate());
            pst3.setTime(4, cour.getTime());
            ResultSet rs = pst3.executeQuery();
            while (rs.next()) {
                cour.setNbrActuel(rs.getInt(1));
                System.out.println(cour.getNbrActuel());
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (cour.getNbrActuel() >= cour.getNbrTotal()) {
            System.out.println("Impossible cour complet!!!!");
        } else {
            try {
                String req2 = "UPDATE planning SET nbrActuel= ? WHERE idSalle= ? AND nom= ? AND date= ? AND time= ?";
                PreparedStatement pst2 = cnx.prepareStatement(req2);
                cour.setNbrActuel(cour.getNbrActuel() + 1);
                pst2.setInt(1, cour.getNbrActuel());
                pst2.setInt(2, cour.getIdSalle());
                pst2.setString(3, cour.getNom());
                pst2.setDate(4, cour.getDate());
                pst2.setTime(5, cour.getTime());
                pst2.executeUpdate();
                String req = "INSERT INTO " + cour.getIdSalle() + cour.getNom() + cour.getDate().getDay() + cour.getDate().getMonth() + cour.getDate().getYear()
                        + cour.getTime().getHours() + cour.getTime().getMinutes() + cour.getTime().getSeconds() + " VALUES (?,?)";
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, s.getId());
                pst.setString(2, s.getNom());
                pst.executeUpdate();
                System.out.println("Reservation ajouté");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public void supprimerParticipation(Sportif s, Cour cour) {
        try {
            String req3 = "SELECT * FROM " + cour.getIdSalle() + cour.getNom() + cour.getDate().getDay() + cour.getDate().getMonth() + cour.getDate().getYear()
                    + cour.getTime().getHours() + cour.getTime().getMinutes() + cour.getTime().getSeconds() + " WHERE id= ? AND nom= ?";
            PreparedStatement pst3 = cnx.prepareStatement(req3);
            pst3.setInt(1, s.getId());
            pst3.setString(2, s.getNom());
            ResultSet rs = pst3.executeQuery();
            if (rs.first() == false) {
                System.out.println("Information invalide!!!!");
            } else {
                try {
                    String req4 = "SELECT nbrActuel FROM planning WHERE idSalle= ? AND nom= ? AND date= ? AND time=?";
                    PreparedStatement pst4 = cnx.prepareStatement(req4);
                    pst4.setInt(1, cour.getIdSalle());
                    pst4.setString(2, cour.getNom());
                    pst4.setDate(3, cour.getDate());
                    pst4.setTime(4, cour.getTime());
                    ResultSet rs1 = pst4.executeQuery();
                    while (rs1.next()) {
                        cour.setNbrActuel(rs1.getInt(1));
                        System.out.println(cour.getNbrActuel());
                    }
                    String req = "DELETE FROM " + cour.getIdSalle() + cour.getNom() + cour.getDate().getDay() + cour.getDate().getMonth() + cour.getDate().getYear()
                            + cour.getTime().getHours() + cour.getTime().getMinutes() + cour.getTime().getSeconds() + " WHERE id= ? AND nom= ?";
                    PreparedStatement pst = cnx.prepareStatement(req);
                    pst.setInt(1, s.getId());
                    pst.setString(2, s.getNom());
                    pst.executeUpdate();
                    String req2 = "UPDATE planning SET nbrActuel= ? WHERE idSalle= ? AND nom= ? AND date= ? AND time= ?";
                    PreparedStatement pst2 = cnx.prepareStatement(req2);
                    cour.setNbrActuel(cour.getNbrActuel() - 1);
                    pst2.setInt(1, cour.getNbrActuel());
                    pst2.setInt(2, cour.getIdSalle());
                    pst2.setString(3, cour.getNom());
                    pst2.setDate(4, cour.getDate());
                    pst2.setTime(5, cour.getTime());
                    pst2.executeUpdate();
                    System.out.println("Participation supprimé!!!!");
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<Cour> afficherPlanning(Salle s) {
        List<Cour> planning = new ArrayList<>();
        try {
            String req = "SELECT * FROM planning WHERE idSalle= ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, s.getId());
            ResultSet rs = pst.executeQuery();
            Cour cour = new Cour();
            while (rs.next()) {
                cour.setIdSalle(rs.getInt(1));
                cour.setNom(rs.getString(2));
                cour.setDate(rs.getDate(3));
                cour.setTime(rs.getTime(4));
                cour.setNbrTotal(rs.getInt(5));
                cour.setInfo(rs.getString(6));
                cour.setNbrActuel(rs.getInt(7));
                planning.add(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return planning;
    }
}

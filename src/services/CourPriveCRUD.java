/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Coach;
import entities.CourPrive;
import entities.Sportif;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author Asus
 */
public class CourPriveCRUD {

    static Connection cnx;

    public CourPriveCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterDisponibilite(Coach c, Date date, Time t1, Time t2) {
        if (t1.after(t2)) {
            System.out.println("Erreur Time");
        } else {
            try {
                String req = "INSERT INTO disponibiliteCoach VALUES (?,?,?,?) ";
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, c.getId());
                pst.setDate(2, date);
                pst.setTime(3, t1);
                pst.setTime(4, t2);
                pst.executeUpdate();
                System.out.println("Disponibilité ajouté!!!!");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public void supprimerDisponibilite(Coach c, Date date, Time t1, Time t2) {
        try {
            String req = "DELETE FROM disponibiliteCoach WHERE idCoach= ? AND date= ? AND tDebut= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, c.getId());
            pst.setDate(2, date);
            pst.setTime(3, t1);
            pst.executeUpdate();
            System.out.println("La disponibilité n'exite pas/plus!!!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<CourPrive> afficherDisponibilite(Coach c) {
        List<CourPrive> cp = new ArrayList<>();
        try {
            String req = "SELECT * FROM disponibiliteCoach WHERE idCoach= ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, c.getId());
            ResultSet rs = pst.executeQuery();
            CourPrive courprive = new CourPrive();
            while (rs.next()) {
                courprive.setIdCoach(rs.getInt(1));
                courprive.setDate(rs.getDate(2));
                courprive.settDebut(rs.getTime(3));
                courprive.settFin(rs.getTime(4));
                cp.add(courprive);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return cp;
    }

    public void demandeReservation(Coach c, Date date, Time t1, Time t2, Sportif s) {
        try {
            String req = "SELECT * FROM disponibiliteCoach WHERE idCoach= ? AND date= ? AND tDebut= ? AND tFin= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, c.getId());
            pst.setDate(2, date);
            pst.setTime(3, t1);
            pst.setTime(4, t2);
            ResultSet rs = pst.executeQuery();
            if (rs.first() == false) {
                System.out.println("Information invalide!!!!");
            } else {
                String req2 = "INSERT INTO reservationCoach VALUES (?,?,?,?,?,? )";
                PreparedStatement pst2 = cnx.prepareStatement(req2);
                pst2.setInt(1, c.getId());
                pst2.setInt(5, s.getId());
                pst2.setDate(2, date);
                pst2.setTime(3, t1);
                pst2.setTime(4, t2);
                pst2.setString(6, "En Attente");
                pst2.executeUpdate();
                System.out.println("Demande de reservation envoyé!!!!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public List<CourPrive> afficherReservation(Coach c) {
        List<CourPrive> cp = new ArrayList<>();
        try {
            String req = "SELECT * FROM reservationCoach WHERE idCoach= ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, c.getId());
            ResultSet rs = pst.executeQuery();
            CourPrive courprive = new CourPrive();
            while (rs.next()) {
                courprive.setIdCoach(rs.getInt(1));
                courprive.setDate(rs.getDate(2));
                courprive.settDebut(rs.getTime(3));
                courprive.settFin(rs.getTime(4));
                courprive.setEtat(rs.getString(6));
                courprive.setIdParticipant(rs.getInt(5));
                cp.add(courprive);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return cp;
    }
    public void traiterDemande(Coach c, Date date, Time t1, Time t2, Sportif s, String rep) {
        try {
            String req = "SELECT * FROM reservationCoach WHERE idCoach= ? AND date= ? AND tDebut= ? AND tFin= ? AND idParticipant= ? AND etat='En Attente'";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, c.getId());
            pst.setDate(2, date);
            pst.setTime(3, t1);
            pst.setTime(4, t2);
            pst.setInt(5, s.getId());
            ResultSet rs = pst.executeQuery();
            if (rs.first() == false) {
                System.out.println("Information invalide!!!!");
            } else {
                /* rep sera accepter ou refuser*/
                if (rep.equals("accepter")) {
                    String req1 = "UPDATE reservationCoach SET etat='accepté' WHERE idCoach= ? AND date= ? AND tDebut= ? AND tFin= ? AND idParticipant= ? AND etat='En Attente'";
                    PreparedStatement pst1 = cnx.prepareStatement(req1);
                    pst1.setInt(1, c.getId());
                    pst1.setDate(2, date);
                    pst1.setTime(3, t1);
                    pst1.setTime(4, t2);
                    pst1.setInt(5, s.getId());
                    pst1.executeUpdate();
                    String req3="DELETE FROM disponibiliteCoach WHERE idCoach= ? AND date= ? AND tDebut= ? AND tFin= ? ";
                    PreparedStatement pst3 = cnx.prepareStatement(req3);
                    pst3.setInt(1, c.getId());
                    pst3.setDate(2, date);
                    pst3.setTime(3, t1);
                    pst3.setTime(4, t2);
                    pst3.executeUpdate();
                    System.out.println("accepté!!!!");
                }else if (rep.equals("refuser")){
                String req2 = "DELETE FROM reservationCoach WHERE idCoach= ? AND date= ? AND tDebut= ? AND tFin= ? AND idParticipant= ? AND etat='En Attente'";
                    PreparedStatement pst2 = cnx.prepareStatement(req2);
                    pst2.setInt(1, c.getId());
                    pst2.setDate(2, date);
                    pst2.setTime(3, t1);
                    pst2.setTime(4, t2);
                    pst2.setInt(5, s.getId());
                    pst2.executeUpdate();
                    System.out.println("refusé!!!!");
            }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void annulerReservation(Coach c, Date date, Time t1, Time t2, Sportif s) {
        try {
            String req = "DELETE FROM reservationCoach WHERE idCoach= ? AND date= ? AND tDebut= ? AND tFin= ? AND idParticipant= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
                    pst.setInt(1, c.getId());
                    pst.setDate(2, date);
                    pst.setTime(3, t1);
                    pst.setTime(4, t2);
                    pst.setInt(5, s.getId());
                    pst.executeUpdate();
                    System.out.println("Cette reservation n'existe pas/plus!!!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

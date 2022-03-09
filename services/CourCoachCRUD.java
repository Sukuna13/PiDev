/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CourCoach;
import fitnessny.entities.Utilisateur;
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
public class CourCoachCRUD {

    static Connection cnx;

    public CourCoachCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterDisponibilite(Utilisateur coach, Date date, Time time) {
        try {
            String req = "INSERT INTO disponibilitecoach (idCoach,date,time) VALUES (?,?,?) ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, coach.getId());
            pst.setDate(2, date);
            pst.setTime(3, time);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean testReservationExistante(Utilisateur coach, Date date, Time time) {
        try {
            String req = "SELECT * FROM disponibilitecoach WHERE idCoach= ? AND Date= ? AND Time=?";
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, coach.getId());
            pst.setDate(2, date);
            pst.setTime(3, time);
            ResultSet rs = pst.executeQuery();
            return rs.first();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public List<CourCoach> afficherDisponibilite(Utilisateur coach) {
        List<CourCoach> disponibilite = new ArrayList<>();
        try {
            String req = "SELECT * FROM disponibilitecoach WHERE idCoach= ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, coach.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourCoach cour = new CourCoach();
                cour.setIdCour(rs.getInt(1));
                cour.setIdCoach(rs.getInt(2));
                cour.setDate(rs.getDate(3));
                cour.setTime(rs.getTime(4));
                disponibilite.add(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return disponibilite;
    }

    public void supprimerDisponibilite(Utilisateur coach, CourCoach cour) {
        try {
            String req = "DELETE FROM disponibilitecoach where date=? AND time=? AND idCoach=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setDate(1, cour.getDate());
            pst.setTime(2, cour.getTime());
            pst.setInt(3, coach.getId());
            pst.executeUpdate();
            String req1 = "DELETE FROM reservationCoach WHERE idCoach= ? AND date= ? AND time= ?  ";
            PreparedStatement pst1 = cnx.prepareStatement(req1);
            pst1.setInt(1, coach.getId());
            pst1.setDate(2, cour.getDate());
            pst1.setTime(3, cour.getTime());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<CourCoach> afficherToutesLesDisponibilite() {
        List<CourCoach> disponibilite = new ArrayList<>();
        try {
            String req = "SELECT * FROM disponibilitecoach ";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourCoach cour = new CourCoach();
                cour.setIdCour(rs.getInt(1));
                cour.setIdCoach(rs.getInt(2));
                cour.setDate(rs.getDate(3));
                cour.setTime(rs.getTime(4));
                String req1 = "SELECT concat(concat(nom,\" \"),prenom) as nom FROM utilisateur where id=?";
                PreparedStatement pst1 = cnx.prepareStatement(req1);
                pst1.setInt(1, cour.getIdCoach());
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    cour.setNomCoach(rs1.getString(1));
                }
                disponibilite.add(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return disponibilite;
    }

    public void demandeReservation(Utilisateur sportif, CourCoach cour) {

        try {

            String req = "INSERT INTO reservationCoach(idCoach,date,time,idParticipant,etat) VALUES (?,?,?,?,? )";
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getIdCoach());
            pst.setDate(2, cour.getDate());
            pst.setTime(3, cour.getTime());
            pst.setInt(4, sportif.getId());
            pst.setString(5, "En Attente");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourCoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int findIdUserByName(String s) {
        try {
            String[] split = s.split(" ", 0);
            String req = "SELECT id FROM utilisateur where nom=? AND prenom=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, split[0]);
            pst.setString(2, split[1]);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            };
        } catch (SQLException ex) {
            Logger.getLogger(CourCoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<CourCoach> afficherReservationEffectue(Utilisateur sportif) {
        List<CourCoach> disponibilite = new ArrayList<>();
        try {
            String req = "SELECT * FROM reservationcoach WHERE idParticipant= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, sportif.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourCoach cour = new CourCoach();
                cour.setIdCour(rs.getInt(1));
                cour.setIdCoach(rs.getInt(2));
                cour.setDate(rs.getDate(3));
                cour.setTime(rs.getTime(4));
                cour.setEtat(rs.getString(6));
                String req1 = "SELECT concat(concat(nom,\" \"),prenom) as nom FROM utilisateur where id=?";
                PreparedStatement pst1 = cnx.prepareStatement(req1);
                pst1.setInt(1, cour.getIdCoach());
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    cour.setNomCoach(rs1.getString(1));
                }
                disponibilite.add(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return disponibilite;
    }

    public boolean testCourExsitant(Utilisateur sportif, CourCoach cour) {
        try {
            String req = "SELECT * FROM reservationcoach WHERE idCoach= ? AND idParticipant= ? AND Date=? AND time=?";
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getIdCoach());
            pst.setInt(2, sportif.getId());
            pst.setDate(3, cour.getDate());
            pst.setTime(4, cour.getTime());
            ResultSet rs = pst.executeQuery();;
            return rs.first();
        } catch (SQLException ex) {
            Logger.getLogger(CourSalleCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<CourCoach> getDemandes(Utilisateur coach,String s) {
        List<CourCoach> demandes = new ArrayList<>();
        try {
            String req = "SELECT * FROM reservationcoach WHERE idCoach=? AND etat=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, coach.getId());
            pst.setString(2, s);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourCoach cour = new CourCoach();
                cour.setIdCoach(rs.getInt(2));
                cour.setDate(rs.getDate(3));
                cour.setTime(rs.getTime(4));
                cour.setIdParticipant(rs.getInt(5));
                cour.setEtat(rs.getString(6));
                String req1 = "SELECT concat(concat(nom,\" \"),prenom) as nom FROM utilisateur where id=?";
                PreparedStatement pst1 = cnx.prepareStatement(req1);
                pst1.setInt(1, cour.getIdParticipant());
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    cour.setNomCoach(rs1.getString(1));
                }
                demandes.add(cour);
                System.out.println(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return demandes;
    }

    public void annulerReservationCoach(Utilisateur coach, Date date, Time time, int id) {
        try {
            String req = "DELETE FROM reservationcoach WHERE idCoach= ? AND date= ? AND time= ?  AND idParticipant= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, coach.getId());
            pst.setDate(2, date);
            pst.setTime(3, time);
            pst.setInt(4, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void accepterReservation(Utilisateur coach, Date date, Time time, int id) {
        try {
            String req = "UPDATE reservationCoach SET etat='Accepte' WHERE idCoach= ? AND date= ? AND time= ?  AND idParticipant= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, coach.getId());
            pst.setDate(2, date);
            pst.setTime(3, time);
            pst.setInt(4, id);
            pst.executeUpdate();
            String req1 = "DELETE FROM disponibiliteCoach WHERE idCoach= ? AND date= ? AND time= ? ";
            PreparedStatement pst1 = cnx.prepareStatement(req1);
            pst1.setInt(1, coach.getId());
            pst1.setDate(2, date);
            pst1.setTime(3, time);
            pst1.executeUpdate();
            String req2 = "DELETE FROM reservationCoach WHERE idCoach= ? AND date= ? AND time= ? AND idParticipant<>?";
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            pst2.setInt(1, coach.getId());
            pst2.setDate(2, date);
            pst2.setTime(3, time);
            pst2.setInt(4, id);
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
     public void annulerReservation(Utilisateur sportif, Date date, Time time, int id) {
        try {
            String req = "DELETE FROM reservationCoach WHERE idCoach= ? AND date= ? AND time= ? AND idParticipant= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
                    pst.setInt(1, id);
                    pst.setDate(2, date);
                    pst.setTime(3, time);
                    pst.setInt(4, sportif.getId());
                    pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   public String getEmailById(int id){
        try {
            String req = "SELECT adresseMail FROM utilisateur WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                return rs.getString(1).toString();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourCoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
   }
}

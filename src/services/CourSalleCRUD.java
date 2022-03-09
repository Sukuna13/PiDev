/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Abonnement;
import entities.CourSalle;
import fitnessny.entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author Asus
 */
public class CourSalleCRUD {

    static Connection cnx;

    public CourSalleCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public boolean testCourExsitant(CourSalle cour) {
        try {
            String req = "SELECT * FROM courssalle WHERE idSalle= ? AND nomCour= ? AND Date=? AND tCour=?";
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getIdSalle());
            pst.setString(2, cour.getNomCour());
            pst.setDate(3, cour.getDate());
            pst.setTime(4, cour.getTCour());
            ResultSet rs = pst.executeQuery();;
            return rs.first();
        } catch (SQLException ex) {
            Logger.getLogger(CourSalleCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void ajouterCour(CourSalle cour) {
        try {
            String req = "INSERT INTO CoursSalle(idSalle,nomCour,date,tCour,nbrTotal,info,nbrActuel) VALUES (?,?,?,?,?,?,0) ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getIdSalle());
            pst.setString(2, cour.getNomCour());
            pst.setDate(3, cour.getDate());
            pst.setTime(4, cour.getTCour());
            pst.setInt(5, cour.getNbrTotal());
            pst.setString(6, cour.getInfo());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<CourSalle> afficherCoursSalle(Utilisateur salle) {
        List<CourSalle> planning = new ArrayList<>();
        try {
            String req = "SELECT * FROM courssalle WHERE idSalle= ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, salle.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourSalle cour = new CourSalle();
                cour.setIdCour(rs.getInt(1));
                cour.setIdSalle(rs.getInt(2));
                cour.setNomCour(rs.getString(3));
                cour.setDate(rs.getDate(4));
                cour.setTCour(rs.getTime(5));
                cour.setNbrTotal(rs.getInt(6));
                cour.setInfo(rs.getString(7));
                cour.setNbrActuel(rs.getInt(8));
                planning.add(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return planning;
    }

    public void supprimerCour(CourSalle cour) {
        try {
            String req = "DELETE FROM reservationcoursalle where idCour=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getIdCour());
            pst.executeUpdate();
            String req2 = "DELETE FROM courssalle WHERE idCour=?";
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            pst2.setInt(1, cour.getIdCour());
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Abonnement> abonnement(Utilisateur sportif) {
        List<Abonnement> abonnements = new ArrayList<>();
        try {
            String req = "SELECT * FROM abonnement WHERE idUser= ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, sportif.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Abonnement abonnement = new Abonnement();
                abonnement.setIdSalle(rs.getInt(1));
                abonnement.setIdUtilisateur(rs.getInt(2));
                abonnement.setDatedebut(rs.getDate(3));
                abonnement.setDatefin(rs.getDate(4));
                abonnement.setNomSalle(getNomSalleById(abonnement.getIdSalle()));
                abonnements.add(abonnement);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return abonnements;
    }

    public String getNomSalleById(int id) {
        String nom = "";
        try {
            String req = "SELECT DISTINCT nomSalle FROM salle WHERE idSalle= ?";
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nom = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourSalleCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }

    public List<CourSalle> afficherReservationPossible(Utilisateur sportif) {
        List<CourSalle> reservationPossible = new ArrayList<>();
//        System.out.println(abonnement(sportif));
        for (Abonnement abonnement : abonnement(sportif)) {
            try {
//                System.out.println(sportif.getId()+" "+abonnement.getDatedebut()+" "+abonnement.getDatefin());
                String req = "SELECT * FROM courssalle WHERE idSalle= ? AND (Date BETWEEN (?) AND (?) )";
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, abonnement.getIdSalle());
                pst.setDate(2, abonnement.getDatedebut());
                pst.setDate(3, abonnement.getDatefin());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    CourSalle cour = new CourSalle();
                    cour.setIdCour(rs.getInt(1));
                    cour.setIdSalle(rs.getInt(2));
                    cour.setNomCour(rs.getString(3));
                    cour.setDate(rs.getDate(4));
                    cour.setTCour(rs.getTime(5));
                    cour.setNbrTotal(rs.getInt(6));
                    cour.setInfo(rs.getString(7));
                    cour.setNbrActuel(rs.getInt(8));
                    cour.setNomSalle(getNomSalleById(cour.getIdSalle()));
                    reservationPossible.add(cour);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return reservationPossible;
    }

    public boolean testCourComplet(CourSalle cour) {
        String req = "SELECT nbrActuel FROM courssalle WHERE idCour=?";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getIdCour());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                if (rs.getInt(1) >= cour.getNbrTotal()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public boolean testDejaParticipant(Utilisateur sportif, CourSalle cour) {
        try {
            String req = "SELECT * FROM reservationcoursalle WHERE idCour= ? ";
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getIdCour());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(4) == sportif.getId()) {
                    System.out.println("truuuee");
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("falsseee");
        return false;
    }

    public void reserverCourSalle(Utilisateur sportif, CourSalle cour) {
        try {
            String req = "UPDATE courssalle SET nbrActuel= ? WHERE idCour= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            cour.setNbrActuel(cour.getNbrActuel() + 1);
            pst.setInt(1, cour.getNbrActuel());
            pst.setInt(2, cour.getIdCour());
            pst.executeUpdate();
            String req1 = "INSERT INTO  reservationcoursalle (idSalle,idCour,idParticipant)  VALUES (?,?,?)";
            PreparedStatement pst1 = cnx.prepareStatement(req1);
            pst1.setInt(1, cour.getIdSalle());
            pst1.setInt(2, cour.getIdCour());
            pst1.setInt(3, sportif.getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<CourSalle> affichageReservationCourSalleEffectue(Utilisateur sportif) {
        List<CourSalle> reservationPossible = new ArrayList<>();
        try {
            String req = "SELECT * FROM courssalle WHERE idCour IN (SELECT idCour from reservationcoursalle WHERE idParticipant= ? )";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, sportif.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourSalle cour = new CourSalle();
                cour.setIdCour(rs.getInt(1));
                cour.setIdSalle(rs.getInt(2));
                cour.setNomCour(rs.getString(3));
                cour.setDate(rs.getDate(4));
                cour.setTCour(rs.getTime(5));
                cour.setNbrTotal(rs.getInt(6));
                cour.setInfo(rs.getString(7));
                cour.setNbrActuel(rs.getInt(8));
                cour.setNomSalle(getNomSalleById(cour.getIdSalle()));
                reservationPossible.add(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reservationPossible;
    }

    public void supprimerReservationCourSalle(Utilisateur sportif, CourSalle cour) {
        try {
            String req = "DELETE FROM reservationcoursalle WHERE  idCour= ? AND idParticipant= ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getIdCour());
            pst.setInt(2, sportif.getId());
            pst.executeUpdate();
            String req1 = "UPDATE courssalle SET nbrActuel=nbrActuel-1 WHERE idCour= ?";
            PreparedStatement pst1 = cnx.prepareStatement(req1);
            pst1.setInt(1, cour.getIdCour());;
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}

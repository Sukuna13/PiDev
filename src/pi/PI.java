/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import entities.Coach;
import entities.Cour;
import entities.Salle;
import entities.Sportif;
import java.sql.Date;
import java.sql.Time;
import services.CourCRUD;
import services.CourPriveCRUD;

/**
 *
 * @author Asus
 */
public class PI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CourCRUD cr = new CourCRUD();
        Date date = new Date(2022 - 1900, 2, 12);
        Date date2 = new Date(2022 - 1900, 5, 9);
        Time time = new Time(9, 00, 00);
        Time time2 = new Time(10, 00, 00);
        Time time3=new Time(17,30,00);
        Time time4 = new Time(18, 30, 00);
        Time time5 = new Time(20, 00, 00);
        Salle sa1 = new Salle(12, "gym");
        Salle sa2 = new Salle(10, "arene");
        Cour c1 = new Cour(12, "Cross1", date, time, 3);
        Cour c2 = new Cour(12, "CrossFit", date2, time2, 2, "Cour Avancé");
        Cour c3 = new Cour(10, "Cross1", date, time, 3);
        Cour c4 = new Cour(10, "CrossFit", date2, time3, 2, "Cour Avancé");
        Sportif s1 = new Sportif(12, "ibrahim");
        Sportif s2 = new Sportif(15, "ibrahim");
        Sportif s3 = new Sportif(19, "ibrahim");
        Sportif s4 = new Sportif(10, "ibrahim");
        cr.ajouterCour(c1);
        cr.ajouterCour(c2);
        cr.ajouterCour(c3);
        cr.ajouterCour(c4);
//        cr.supprimerCour(c1);
//        cr.supprimerCour(c2);
        cr.ajouterParticipation(s1, c1);
        cr.ajouterParticipation(s2, c1);
        cr.ajouterParticipation(s3, c1);
        cr.ajouterParticipation(s4, c1);
        cr.ajouterParticipation(s3, c2);
        cr.ajouterParticipation(s4, c2);
        System.out.println(cr.afficherPlanning(sa1));
//        cr.supprimerParticipation(s4, c1);
        Coach co1 = new Coach(1);
        Coach co2 = new Coach(2);
        Coach co3 = new Coach(3);
        CourPriveCRUD crp = new CourPriveCRUD();
//        crp.ajouterDisponibilite(co1, date, time, time2);
//        crp.ajouterDisponibilite(co1, date, time3, time4);
//        crp.ajouterDisponibilite(co2, date2, time3, time4);
//        crp.ajouterDisponibilite(co3, date, time3, time4);
//        System.out.println(crp.afficherDisponibilite(co1));
//        crp.supprimerDisponibilite(co2, date2, time3, time4);
//        crp.supprimerDisponibilite(co2, date, time3, time4);
//        System.out.println(crp.afficherDisponibilite(co2));
//        crp.demandeReservation(co1, date, time3, time4, s4);
//        crp.demandeReservation(co1,  date, time, time2, s4);
//        crp.demandeReservation(co3, date, time3, time4, s4);
//        System.out.println(crp.afficherReservation(co3));
//        System.out.println(crp.afficherReservation(co1));
//        crp.traiterDemande(co3, date, time3, time4, s4, "refuser");
        crp.traiterDemande(co1, date, time3, time4, s4, "accepter");
//            crp.annulerReservation(co1, date, time, time2, s4);
    }

}

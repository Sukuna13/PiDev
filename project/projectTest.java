/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import entities.CourSalle;
import entities.User;
import java.sql.Date;
import java.sql.Time;
import services.CourSalleCRUD;

/**
 *
 * @author Asus
 */
public class projectTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        User spf1 = new User(1, "SPORTIF");
        User spf2 = new User(2, "SPORTIF");
        User spf3 = new User(3, "SPORTIF");
        User sal1 = new User(4, "SALLE");
        User sal2 = new User(5, "SALLE");
        User sal3 = new User(6, "SALLE");
        CourSalle c1 = new CourSalle("cour1", "crosss", new Date(2022 - 1900, 02, 26), new Time(10, 0, 0), 4, 2);
        CourSalle c2 = new CourSalle("cour1", "hiiittt", new Date(2022 - 1900, 02, 26), new Time(9, 0, 0), 5, 3);
        CourSalle c3 = new CourSalle("cour2", "crosss", new Date(2022 - 1900, 02, 26), new Time(10, 0, 0), 5, 2);
        CourSalle c4 = new CourSalle("cour1", "muscuu", new Date(2022 - 1900, 02, 26), new Time(11, 0, 0), 6, 3);
        CourSalleCRUD cr = new CourSalleCRUD();
        cr.ajouterCour(c3);
        cr.ajouterCour(c3);
        cr.ajouterCour(c2);
        cr.ajouterCour(c1);
        cr.ajouterCour(c4);
//        cr.supprimerCour(c3);
//        System.out.println(cr.afficherCoursSalle(sal2));
//        System.out.println(cr.afficherCoursSalle(sal2));
//        System.out.println(cr.afficherCoursSalle(sal3));
//        cr.ajouterParticipation(sal3, c4);
//        cr.ajouterParticipation(spf1, c4);
//        cr.ajouterParticipation(spf1, c4);
//        cr.ajouterParticipation(spf3, c4);
//        cr.supprimerParticipation(sal3, c4);
        System.out.println(cr.afficherReservationSportif(spf2));
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestMain;

import entities.CourSalle;
import fitnessny.entities.Utilisateur;
import java.sql.Date;
import java.sql.Time;
import javax.mail.MessagingException;
import services.CourCoachCRUD;
import services.CourSalleCRUD;
import utils.JavaMail;



/**
 *
 * @author Asus
 */
public class CRUDMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MessagingException {
        CourSalleCRUD cs=new CourSalleCRUD();
//        Utilisateur connected = new Utilisateur(4, "salle", "bensalle");
        CourSalle cour=new CourSalle(4, "aaaaaa",new Date(2022 - 1900, 02, 26),new Time(12,00,00), 12,"AAA");
//        cs.ajouterCour(cour);
//        System.out.println(cs.testCourExsitant(cour));
//        System.out.println(cs.afficherCoursSalle(connected));
//cs.supprimerCour(cour);
//Utilisateur connected = new Utilisateur(2, "ibrahim", "reguigui");
//Abonnement abonnement=new Abonnement(5, 2, new Date(2022,02,02), new Date(2022,04,02));
//        System.out.println(cs.getNomSalleByAbonnement(abonnement));;
//        System.out.println(cs.abonnement(connected));
//System.out.println(cs.afficherReservationPossible(connected));
//System.out.println(cs.testCourComplet(cour));
//System.out.println(cs.affichageReservationCourSalleEffectue(connected));
 CourCoachCRUD cc = new CourCoachCRUD();
//        System.out.println(cc.findIdCoachByName("AAAAAA BBBBBB"));
//    JavaMail.sendMail("ibrahim.reguigui@esprit.tn","brahim.br24@gmail.com");
    }
    
}

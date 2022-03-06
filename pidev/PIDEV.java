/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import entities.Abonnement;
import entities.CourSalle;
import entities.Utilisateur;
import java.sql.Date;
import java.sql.Time;
import services.CourSalleCRUD;

/**
 *
 * @author Asus
 */
public class PIDEV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CourSalleCRUD cs=new CourSalleCRUD();
//        Utilisateur connected = new Utilisateur(4, "salle", "bensalle");
        CourSalle cour=new CourSalle(4, "aaaaaa",new Date(2022 - 1900, 02, 26),new Time(12,00,00), 12,"AAA");
//        cs.ajouterCour(cour);
//        System.out.println(cs.testCourExsitant(cour));
//        System.out.println(cs.afficherCoursSalle(connected));
//cs.supprimerCour(cour);
Utilisateur connected = new Utilisateur(2, "ibrahim", "reguigui");
Abonnement abonnement=new Abonnement(5, 2, new Date(2022,2,2), new Date(2022,2,2));
        System.out.println(cs.getNomSalleByAbonnement(abonnement));;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fintessny.tests;

import edu.finessny.services.ExerciceCRUD;
import edu.finessny.services.ProgrammeCRUD;
import edu.finessny.utils.MyConnection;
import edu.fitnessny.entities.Exercice;
import edu.fitnessny.entities.Programme;

/**
 *
 * @author rocky
 */
public class MainClass {

    public static void main(String[] args) {
        //***************** Connexion *******************//
        MyConnection mc = new MyConnection();
        //***************** Connexion *******************//

        //***************** Exercice *******************//
//        ExerciceCRUD exe = new ExerciceCRUD();
//       Exercice e = new Exercice("ex11", 10, 5, "sans machine ","cardio");
//         Exercice e1 = new Exercice("ex12", 4, 10, "avec machine ","cardio");
//        exe.ajouterExercice(e);
//          exe.ajouterExercice(e1);
//       System.out.println("Ajout avec succeeeee ");
        
        
       //   exe.supprimerExercice() ;
      //  exe.modifierExercice() ;
    //   System.out.println(exe.afficherExercice());
        //***************** Exercice *******************//
        
        //***************** Programme ********************//
    ProgrammeCRUD pcd = new ProgrammeCRUD();
 // Programme p2 = new Programme("P3", "rester en forme", "Dez lahdidd","bras");
//  Exercice e = new Exercice(1);
 //  pcd.ajouterProgramme(p2);
    System.out.println(pcd.afficherProgramme());
        //   pcd.supprimerProgramme();
        // System.out.println(pcd.afficherProgramme());
        // pcd.modifierProgramme();
        //  System.out.println(pcd.afficherProgramme());
        //***************** Programme ********************//
    }
}

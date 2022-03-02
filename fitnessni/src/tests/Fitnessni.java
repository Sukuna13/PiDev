/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import outils.Myconnection;
import services.EventCrud;  
import services.ForumCrud;
import services.CommentaireCrud;
import entities.Event;
import entities.Forum;
import entities.Commentaire; 
import java.sql.Date;
import java.text.DateFormat;


/**
 *
 * @author nahaw
 */
public class Fitnessni {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Myconnection mc = Myconnection.getinstance(); 
        System.out.println(mc);
        //Myconnection mc = new Myconnection(); //pour la connexion avec la base la 1ere fois 
        //Myconnection.getinstance();
     
        //Myconnection mc2 = Myconnection.getinstance(); 
        //System.out.println(mc.hashCode()+"-"+mc2.hashCode());
        
        System.out.println(java.time.LocalDate.now());
      
        EventCrud ecd = new EventCrud(); 
        Event e; 
        ForumCrud fcd = new ForumCrud(); 
        Forum f;
        CommentaireCrud ccd = new CommentaireCrud(); 
        Commentaire c; 

        /////////////////////////// to test Ajouter event methode /////////////////////////// 
         /*
         String dte = "2007-07-07";
         Date dt =Date.valueOf(dte);
         String dte2 = "2008-08-08";
         Date dt2 =Date.valueOf(dte2);
         e= new Event(10,"natation",dt,dt2,3,7,9); 
         ecd.ajouterEvent2(e);
         */
        /////////////////////////to test modifier event methode /////////////////////////// 
        /*
         String dte = "2010-10-10";
         Date dt =Date.valueOf(dte);
         String dte2 = "2011-11-11";
         Date dt2 =Date.valueOf(dte2);
         Event e2 = new Event("running",dt,dt2,1,10,50);
         e2.setIdEvent(20);
         ecd.modifierEvent(e2);
        */ 
        
        ///////////////////////// to test supprimer methode /////////////////////////
        /* ecd.supprimerEvent(20);*/
        //////////////////////////////////////////////////////////////////////////
        //System.out.println(ecd.afficherEventCrud()); 
        
        
        /////////////////////////to test Ajouter forum methode /////////////////////////// 
/*
        f= new Forum("coach",2,1,14); 
        fcd.ajouterForum2(f);
*/       
        /////////////////////////to test modifier forum methode /////////////////////////// 
/*        Forum f2 = new Forum("salle",10,2,4);
        f2.setIdForum(22);
        fcd.modifierForum(f2);
*/   
        ///////////////////////// to test supprimer methode /////////////////////////
       // fcd.supprimerForum(2);
        //////////////////////////////////////////////////////////////////////////
        
       // System.out.println(fcd.afficherForumCrud()); 
        
        /////////////////////////// to test Ajouter commentaire methode /////////////////////////// 
        /*
        c= new Commentaire(10); 
        ccd.ajouterCommentaire2(c);
        */
         /////////////////////////to test modifier commentaire methode /////////////////////////// 
        /*
         Commentaire c3 = new Commentaire(10);
         c3.setIdCommentaire(10);
         ccd.modifierCommentaire(c3);
        */
   
        ///////////////////////// to test supprimer methode /////////////////////////
        //ccd.supprimerCommentaire(2);
        //////////////////////////////////////////////////////////////////////////
        //System.out.println(ccd.afficherCommentaireCrud()); 
        
    }   
}

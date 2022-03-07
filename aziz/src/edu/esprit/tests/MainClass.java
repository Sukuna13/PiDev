/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Plats;
import edu.esprit.services.CreateService;
import edu.esprit.services.DeleteService;
import edu.esprit.services.ReadService;
import edu.esprit.utils.MyConnection;

/**
 *
 * @author HP
 */
public class MainClass {
    
     public static void main (String[]args) {
    
    MyConnection mc =  MyConnection.getInstance();
    
         CreateService cs = new CreateService();
       //  cs.ajouterBesoin(new besoin_sportif(1, 12.2f, 52.4f, 200f, 412.0f));
    
         ReadService rs = new ReadService();
     //   System.out.println( rs.displayAllBesoins());
        
        
         DeleteService ds = new DeleteService();
         ds.deleteAllPlats();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit;

import java.util.List;
import services.ServiceProduit;
import model.Produit;

/**
 *
 * @author MSI
 */
public class Gestionproduit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Produit s = new Produit("Hardware","d","t",2.f,"df","null","En Stock",7.f,3);
         Produit s3 = new Produit(1,"Jeux","jk","t",2.f,"df","gh","En Stock",7.f,3);
         ServiceProduit sp = new ServiceProduit();
         // sp.ajouterproduit(s);
      // sp.ajouterproduit1(s);
        
        //sp.modifierproduit(s3);
        
        
       //sp.suppproduit(s3);
        
        //Ajouter
     //System.out.println( sp.filtrerproduit());
        
        //Afficher
   //  System.out.println( sp.afficherproduit());
    }

    }
    


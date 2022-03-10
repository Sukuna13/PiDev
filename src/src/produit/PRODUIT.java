/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produit;

import model.Employe;
import services.serviceEmployees;

/**
 *
 * @author MSI
 */
public class PRODUIT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Employe e1 = new Employe(1111,"wejden","rejeb","@mail",12345,120.0F,"coach");
        serviceEmployees se =new serviceEmployees();
        System.out.println(se.rechercherEmploye("wejden@esprit.tn"));
        se.modifieremploye(e1);
        se.rechercherEmploye("@mail");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Employe;
import model.Produit;

/**
 *
 * @author infoevo
 */
public interface Iemployees {
    public void ajouterEmploye(Employe s);
    //public void ajouterproduit1(Produit s);
    public List<Employe> afficherEmploye();
    public void modifieremploye(Employe s);
    public void suppemploye(Employe s);
    //public List<Produit> filtrerproduit();
    
}

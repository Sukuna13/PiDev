 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author HP
 */
public class caractérestiques_sportif {
    
    private int id_caractérestiques; 
    private int id_sportif;
    private float taille_sportif; 
    private float poid_sportif; 
    private int age_sportif; 
    private String sexe_sportif;
    private String objectif_nutrition; 
    private int Bmi_sportif;
    
    private int besoin_bmi;
    private int besoin_protein;
    private int besoin_carbs;
    private int besoin_calories;
    private int besoin_fat;
            

    public caractérestiques_sportif(int id_caractérestiques, int id_sportif, float taille_sportif, float poid_sportif, int age_sportif, String sexe_sportif, String objectif_nutrition, int Bmi_sportif, int besoin_bmi, int besoin_protein, int besoin_carbs, int besoin_calories, int besoin_fat) {
        this.id_caractérestiques = id_caractérestiques;
        this.id_sportif = id_sportif;
        this.taille_sportif = taille_sportif;
        this.poid_sportif = poid_sportif;
        this.age_sportif = age_sportif;
        this.sexe_sportif = sexe_sportif;
        this.objectif_nutrition = objectif_nutrition;
        this.Bmi_sportif = Bmi_sportif;
        this.besoin_bmi = besoin_bmi;
        this.besoin_protein = besoin_protein;
        this.besoin_carbs = besoin_carbs;
        this.besoin_calories = besoin_calories;
        this.besoin_fat = besoin_fat;
        
    }

   

    public caractérestiques_sportif(float taille_sportif, float poid_sportif, int age_sportif, String sexe_sportif, String objectif_nutrition, int Bmi_sportif, int besoin_bmi, int besoin_protein, int besoin_carbs, int besoin_calories, int besoin_fat) {
        this.id_sportif = id_sportif;
        this.taille_sportif = taille_sportif;
        this.poid_sportif = poid_sportif;
        this.age_sportif = age_sportif;
        this.sexe_sportif = sexe_sportif;
        this.objectif_nutrition = objectif_nutrition;
        this.Bmi_sportif = Bmi_sportif;
        this.besoin_bmi = besoin_bmi;
        this.besoin_protein = besoin_protein;
        this.besoin_carbs = besoin_carbs;
        this.besoin_calories = besoin_calories;
        this.besoin_fat = besoin_fat;
    }

    public caractérestiques_sportif() {
    }

    public caractérestiques_sportif(float taille_sportif, float poid_sportif, int age_sportif) {
        this.taille_sportif = taille_sportif;
        this.poid_sportif = poid_sportif;
        this.age_sportif = age_sportif;
    }

    public caractérestiques_sportif(float taille_sportif, float poid_sportif, int age_sportif, String sexe_sportif, String objectif_nutrition) {
        this.taille_sportif = taille_sportif;
        this.poid_sportif = poid_sportif;
        this.age_sportif = age_sportif;
        this.sexe_sportif = sexe_sportif;
        this.objectif_nutrition = objectif_nutrition;
    }

   
   
    
    public int getId_caractérestiques() {
        return id_caractérestiques;
    }

    public void setId_caractérestiques(int id_caractérestiques) {
        this.id_caractérestiques = id_caractérestiques;
    }

    public int getId_sportif() {
        return id_sportif;
    }

    public void setId_sportif(int id_sportif) {
        this.id_sportif = id_sportif;
    }

    public float getTaille_sportif() {
        return taille_sportif;
    }

    public void setTaille_sportif(float taille_sportif) {
        this.taille_sportif = taille_sportif;
    }

    public float getPoid_sportif() {
        return poid_sportif;
    }

    public void setPoid_sportif(float poid_sportif) {
        this.poid_sportif = poid_sportif;
    }

    public int getAge_sportif() {
        return age_sportif;
    }

    public void setAge_sportif(int age_sportif) {
        this.age_sportif = age_sportif;
    }

    public String getSexe_sportif() {
        return sexe_sportif;
    }

    public void setSexe_sportif(String sexe_sportif) {
        this.sexe_sportif = sexe_sportif;
    }

    public String getObjectif_nutrition() {
        return objectif_nutrition;
    }

    public void setObjectif_nutrition(String objectif_nutrition) {
        this.objectif_nutrition = objectif_nutrition;
    }

    public int getBmi_sportif() {
        return Bmi_sportif;
    }

    public void setBmi_sportif(int Bmi_sportif) {
        this.Bmi_sportif = Bmi_sportif;
    }

    public int getBesoin_bmi() {
        return besoin_bmi;
    }

    public void setBesoin_bmi(int besoin_bmi) {
        this.besoin_bmi = besoin_bmi;
    }

    public int getBesoin_protein() {
        return besoin_protein;
    }

    public void setBesoin_protein(int besoin_protein) {
        this.besoin_protein = besoin_protein;
    }

    public int getBesoin_carbs() {
        return besoin_carbs;
    }

    public void setBesoin_carbs(int besoin_carbs) {
        this.besoin_carbs = besoin_carbs;
    }

    public int getBesoin_calories() {
        return besoin_calories;
    }

    public void setBesoin_calories(int besoin_calories) {
        this.besoin_calories = besoin_calories;
    }

    public int getBesoin_fat() {
        return besoin_fat;
    }

    public void setBesoin_fat(int besoin_fat) {
        this.besoin_fat = besoin_fat;
    }

    @Override
    public String toString() {
        return "caract\u00e9restiques_sportif{" + "id_caract\u00e9restiques=" + id_caractérestiques + ", id_sportif=" + id_sportif + ", taille_sportif=" + taille_sportif + ", poid_sportif=" + poid_sportif + ", age_sportif=" + age_sportif + ", sexe_sportif=" + sexe_sportif + ", objectif_nutrition=" + objectif_nutrition + ", Bmi_sportif=" + Bmi_sportif + ", besoin_bmi=" + besoin_bmi + ", besoin_protein=" + besoin_protein + ", besoin_carbs=" + besoin_carbs + ", besoin_calories=" + besoin_calories + ", besoin_fat=" + besoin_fat + '}';
    }

    
    
    
    
    
    
    

    
    
    
   
    
    
    
}

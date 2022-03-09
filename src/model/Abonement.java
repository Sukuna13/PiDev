/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author infoevo
 */
public class Abonement {
    
   
    private String nom_emp;
    private String prenom_emp;
    private Date dated;
    private Date datef;

    public Abonement(String nom_emp, String prenom_emp, Date dated, Date datef) {
        this.nom_emp = nom_emp;
        this.prenom_emp = prenom_emp;
        this.dated = dated;
        this.datef = datef;
    }

 

    public String getNom_emp() {
        return nom_emp;
    }

    public String getPrenom_emp() {
        return prenom_emp;
    }

    public Date getDated() {
        return dated;
    }

    public Date getDatef() {
        return datef;
    }



    public void setNom_emp(String nom_emp) {
        this.nom_emp = nom_emp;
    }

    public void setPrenom_emp(String prenom_emp) {
        this.prenom_emp = prenom_emp;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public void setDatef(Date datef) {
        this.datef = datef;
    }


   
    
    
    
    
}

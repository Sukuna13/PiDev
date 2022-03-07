/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author infoevo
 */
public class Employe {
    private int id_emp;
    private String nom_emp;
    private String prenom_emp;
    private String mail;
    private int num_emp;
    private float salaire;
    private String type;

    public Employe() {
        super();
    }

    public Employe(int id_emp, String nom_emp, String prenom_emp, String mail, int num_emp, float salaire, String type) {
        this.id_emp = id_emp;
        this.nom_emp = nom_emp;
        this.prenom_emp = prenom_emp;
        this.mail = mail;
        this.num_emp = num_emp;
        this.salaire = salaire;
        this.type = type;
    }

    public Employe(String nom_emp, String prenom_emp, String mail, int num_emp, float salaire, String type) {
        this.nom_emp = nom_emp;
        this.prenom_emp = prenom_emp;
        this.mail = mail;
        this.num_emp = num_emp;
        this.salaire = salaire;
        this.type = type;
    }


    public int getId_emp() {
        return id_emp;
    }

    public String getNom_emp() {
        return nom_emp;
    }

    public String getPrenom_emp() {
        return prenom_emp;
    }

    public int getNum_emp() {
        return num_emp;
    }

    public float getSalaire() {
        return salaire;
    }

    public String getType() {
        return type;
    }

    public String getMail() {
        return mail;
    }
    
    

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public void setNom_emp(String nom_emp) {
        this.nom_emp = nom_emp;
    }

    public void setPrenom_emp(String prenom_emp) {
        this.prenom_emp = prenom_emp;
    }

    public void setNum_emp(int num_emp) {
        this.num_emp = num_emp;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    
 
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Abonnement {
    private int idSalle,idUtilisateur;
    private Date dateDebut,datefin;
    private String nomSalle;

    

    public Abonnement() {
    }

    public Abonnement(int idSalle, int idUtilisateur, Date dateDebut, Date datefin) {
        this.idSalle = idSalle;
        this.idUtilisateur = idUtilisateur;
        this.dateDebut = dateDebut;
        this.datefin = datefin;
    }

    public Abonnement(int idSalle, int idUtilisateur, Date dateDebut, Date datefin,String nomSalle) {
        this.idSalle = idSalle;
        this.idUtilisateur = idUtilisateur;
        this.dateDebut = dateDebut;
        this.datefin = datefin;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public Date getDatedebut() {
        return dateDebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setDatedebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "idSalle=" + idSalle + ", idUtilisateur=" + idUtilisateur + ", dateDebut=" + dateDebut + ", datefin=" + datefin + ", nomSalle=" + nomSalle + '}';
    }

    
    
}

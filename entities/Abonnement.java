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
    private Date dadtedebut,datefin;

    public Abonnement() {
    }

    public Abonnement(int idSalle, int idUtilisateur, Date dadtedebut, Date datefin) {
        this.idSalle = idSalle;
        this.idUtilisateur = idUtilisateur;
        this.dadtedebut = dadtedebut;
        this.datefin = datefin;
    }

    

    public int getIdSalle() {
        return idSalle;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public Date getDadtedebut() {
        return dadtedebut;
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

    public void setDadtedebut(Date dadtedebut) {
        this.dadtedebut = dadtedebut;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "idSalle=" + idSalle + ", idUtilisateur=" + idUtilisateur + ", dadtedebut=" + dadtedebut + ", datefin=" + datefin + '}';
    }
    
}

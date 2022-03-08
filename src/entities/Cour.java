/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class Cour {
    private String nom,info=" ";
    private Date date;
    private Time time;
    private int idSalle,nbrActuel,nbrTotal;
    
   

    public Cour() {
    }
    public Cour(int idSalle,String nom, Date date, Time time,int nbrTotal) {
        this.nbrTotal = nbrTotal;
        this.idSalle = idSalle;
        this.nom = nom;
        this.date = date;
        this.time = time;
    }
    public Cour(int idSalle,String nom, Date date, Time time,int nbrTotal, String info) {
        this.nbrTotal = nbrTotal;
        this.idSalle = idSalle;
        this.nom = nom;
        this.info = info;
        this.date = date;
        this.time = time;
    }

  
    public void setNbrActuel(int nbrActuel) {
        this.nbrActuel = nbrActuel;
    }

    public void setNbrTotal(int nbrTotal) {
        this.nbrTotal = nbrTotal;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getNbrActuel() {
        return nbrActuel;
    }

    public int getNbrTotal() {
        return nbrTotal;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public String getNom() {
        return nom;
    }

    public String getInfo() {
        return info;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Cour{" + "nom=" + nom + ", info=" + info + ", date=" + date + ", time=" + time + ", idSalle=" + idSalle + ", nbrActuel=" + nbrActuel + ", nbrTotal=" + nbrTotal + '}';
    }


    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cour other = (Cour) obj;
        if (this.idSalle != other.idSalle) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }

    
}

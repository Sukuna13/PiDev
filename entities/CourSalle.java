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
public class CourSalle {

    private String nomCour, info;
    private Date date;
    private Time tCour;
    private int idCour, idSalle, nbrActuel, nbrTotal;

    public CourSalle() {
    }

    public CourSalle(int idSalle, String nomCour, Date date, Time tCour, int nbrTotal, String info) {
        this.nomCour = nomCour;
        this.info = info;
        this.date = date;
        this.tCour = tCour;
        this.idSalle = idSalle;
        this.nbrTotal = nbrTotal;
    }

    public String getNomCour() {
        return nomCour;
    }

    public String getInfo() {
        return info;
    }

    public Date getDate() {
        return date;
    }

    public Time getTCour() {
        return tCour;
    }

    public int getIdCour() {
        return idCour;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public int getNbrActuel() {
        return nbrActuel;
    }

    public int getNbrTotal() {
        return nbrTotal;
    }

    public void setNomCour(String nomCour) {
        this.nomCour = nomCour;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTCour(Time tCour) {
        this.tCour = tCour;
    }

    public void setIdCour(int idCour) {
        this.idCour = idCour;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public void setNbrActuel(int nbrActuel) {
        this.nbrActuel = nbrActuel;
    }

    public void setNbrTotal(int nbrTotal) {
        this.nbrTotal = nbrTotal;
    }

    @Override
    public String toString() {
        return "CourSalle{" + "nomCour=" + nomCour + ", info=" + info + ", date=" + date + ", tCour=" + tCour + ", idCour=" + idCour + ", idSalle=" + idSalle + ", nbrActuel=" + nbrActuel + ", nbrTotal=" + nbrTotal + '}';
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Asus
 */
public class CourPrive {
    private int idCoach;
    private Date date;
    private Time tDebut,tFin;
    private String etat="En attente";
    private int idParticipant;

    public CourPrive() {
    }

    public CourPrive(int idCoach, Date date, Time tDebut, Time tFin) {
        this.idCoach = idCoach;
        this.date = date;
        this.tDebut = tDebut;
        this.tFin = tFin;
    }

    public CourPrive(Date date, Time tDebut, Time tFin) {
        this.date = date;
        this.tDebut = tDebut;
        this.tFin = tFin;
    }

    public int getIdCoach() {
        return idCoach;
    }

    public Date getDate() {
        return date;
    }

    public Time gettDebut() {
        return tDebut;
    }

    public Time gettFin() {
        return tFin;
    }

    public String getEtat() {
        return etat;
    }

    public void setIdCoach(int idCoach) {
        this.idCoach = idCoach;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void settDebut(Time tDebut) {
        this.tDebut = tDebut;
    }

    public void settFin(Time tFin) {
        this.tFin = tFin;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setIdParticipant(int idParticipant) {
        this.idParticipant = idParticipant;
    }

    public int getIdParticipant() {
        return idParticipant;
    }

    @Override
    public String toString() {
        return "CourPrive{" + "idCoach=" + idCoach + ", date=" + date + ", tDebut=" + tDebut + ", tFin=" + tFin + ", etat=" + etat + '}';
    }
    
}

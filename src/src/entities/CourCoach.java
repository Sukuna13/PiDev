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
public class CourCoach {

    private Date date;
    private Time time;
    private String etat = "En attente",nomCoach=" ";
    private int idParticipant;
    private int idCoach, idCour;

    public int getIdCour() {
        return idCour;
    }

    public CourCoach(int idCour, int idCoach, Date date, Time time) {
        this.date = date;
        this.time = time;
        this.idCoach = idCoach;
        this.idCour = idCour;
    }

    public CourCoach(Date date, Time time, String nomCoach, int idCoach, int idCour) {
        this.date = date;
        this.time = time;
        this.nomCoach = nomCoach;
        this.idCoach = idCoach;
        this.idCour = idCour;
    }

    public void setNomCoach(String nomCoach) {
        this.nomCoach = nomCoach;
    }

    public String getNomCoach() {
        return nomCoach;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setIdCour(int idCour) {
        this.idCour = idCour;
    }

    public Time getTime() {
        return time;
    }

    public CourCoach() {
    }

    public CourCoach(int idCoach, Date date, Time time) {
        this.idCoach = idCoach;
        this.date = date;
        this.time = time;
    }

    public CourCoach(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    public int getIdCoach() {
        return idCoach;
    }

    public Date getDate() {
        return date;
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
        return "CourCoach{" + "date=" + date + ", time=" + time + ", etat=" + etat + ", nomCoach=" + nomCoach + ", idParticipant=" + idParticipant + ", idCoach=" + idCoach + ", idCour=" + idCour + '}';
    }

   

}

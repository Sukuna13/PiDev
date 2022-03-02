/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
//import java.util.Date;

import java.sql.Date;


/**
 *
 * @author nahaw
 */
public class Event {
  private int idEvent;    
  private String nomEvent;
  private Date DateDebut;    
  private Date DateFin; 
  private int heureDebut; 
  private int heureFin;
  private int idSalle; 
  
  public Event() {
      
    }

    public Event(int idEvent, String nomEvent, Date DateDebut, Date DateFin, int heureDebut, int heureFin, int idSalle) {
        this.idEvent = idEvent;
        this.nomEvent = nomEvent;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.idSalle = idSalle;
    }

    public Event(String nomEvent, Date DateDebut, Date DateFin, int heureDebut, int heureFin, int idSalle) {
        this.nomEvent = nomEvent;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.idSalle = idSalle;
    }

 
    public int getIdEvent() {
        return idEvent;
    }

    public String getNomEvent() {
        return nomEvent;
    }


    public Date getDateDebut() {
        return DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdEvent(int id_event) {
        this.idEvent = id_event;
    }

    public void setNomEvent(String nom_event) {
        this.nomEvent = nom_event;
    }

    public void setDateDebut(Date date_debut) {
        this.DateDebut = date_debut;
    }

    public void setDateFin(Date date_fin) {
        this.DateFin = date_fin;
    }

    public void setHeureDebut(int heure_debut) {
        this.heureDebut = heure_debut;
    }

    public void setHeureFin(int heure_fin) {
        this.heureFin = heure_fin;
    }

    public void setIdSalle(int id_salle) {
        this.idSalle = id_salle;
    }

    @Override
    public String toString() {
        return "evenement{" + "idEvent=" + idEvent + ", nomEvent=" + nomEvent + ", DateDebut=" + DateDebut + ", DateFin=" + DateFin + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", idSalle=" + idSalle + '}';
    }
}
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
  private int nbrPlaces; 
  private String location; 
  private String img; 
  
  public Event() {
      
    }

    public Event(int idEvent, String nomEvent, Date DateDebut, Date DateFin, int nbrPlaces, String location, String img) {
        this.idEvent = idEvent;
        this.nomEvent = nomEvent;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.nbrPlaces = nbrPlaces;
        this.location = location;
        this.img = img;
    }

    public Event(String nomEvent, Date DateDebut, Date DateFin, int nbrPlaces, String location, String img) {
        this.nomEvent = nomEvent;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.nbrPlaces = nbrPlaces;
        this.location = location;
        this.img = img;
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

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public String getLocation() {
        return location;
    }

    public String getImg() {
        return img;
    }
    

    public void setIdEvent(int id_event) {
        this.idEvent = id_event;
    }

    public void setNomEvent(String nom_event) {
        this.nomEvent = nom_event;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }
  
    public void setLocation(String location) {
        this.location = location;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Event{" + "idEvent=" + idEvent + ", nomEvent=" + nomEvent + ", DateDebut=" + DateDebut + ", DateFin=" + DateFin + ", nbrPlaces=" + nbrPlaces + ", location=" + location + ", img=" + img + '}';
    }
    
  
}
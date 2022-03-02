/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author nahaw
 */
public class Forum {
  private int idForum;
  private String Sujet;  
  private int idCoach;
  private int idSalle;
  private int idSportif;
  
  public Forum() {
      
    }

    public Forum(int idForum, String Sujet, int idCoach, int idSalle, int idSportif) {
        this.idForum = idForum;
        this.Sujet = Sujet;
        this.idCoach = idCoach;
        this.idSalle = idSalle;
        this.idSportif = idSportif;
    }

    public Forum(String Sujet, int idCoach, int idSalle, int idSportif) {
        this.Sujet = Sujet;
        this.idCoach = idCoach;
        this.idSalle = idSalle;
        this.idSportif = idSportif;
    }

    public int getIdForum() {
        return idForum;
    }

    public String getSujet() {
        return Sujet;
    }

    public int getIdCoach() {
        return idCoach;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public int getIdSportif() {
        return idSportif;
    }
    public void setIdForum(int idForum) {
        this.idForum = idForum;
    }

    public void setSujet(String Sujet) {
        this.Sujet = Sujet;
    }

    public void setIdCoach(int idCoach) {
        this.idCoach = idCoach;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public void setIdSportif(int idSportif) {
        this.idSportif = idSportif;
    }

    @Override
    public String toString() {
        return "Forum{" + "idForum=" + idForum + ", Sujet=" + Sujet + ", idCoach=" + idCoach + ", idSalle=" + idSalle + ", idSportif=" + idSportif + '}';
    }




}


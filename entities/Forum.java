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
  private int id;
  
  public Forum() {
      
    }

    public Forum(int idForum, String Sujet, int id) {
        this.idForum = idForum;
        this.Sujet = Sujet;
        this.id = id;
    }

    public Forum(String Sujet, int id) {
        this.Sujet = Sujet;
        this.id = id;
    }

    public int getIdForum() {
        return idForum;
    }

    public String getSujet() {
        return Sujet;
    }

    public int getId() {
        return id;
    }
   
    public void setIdForum(int idForum) {
        this.idForum = idForum;
    }

    public void setSujet(String Sujet) {
        this.Sujet = Sujet;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Forum{" + "idForum=" + idForum + ", Sujet=" + Sujet + ", id=" + id + '}';
    }
}


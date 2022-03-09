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
public class Commentaire {
   private int idCommentaire; 
   private String categorie; 
   private String message; 

    public Commentaire() {
    }

    public Commentaire(int idCommentaire, String categorie, String message) {
        this.idCommentaire = idCommentaire;
        this.categorie = categorie;
        this.message = message;
    }

    public Commentaire(String categorie, String message) {
        this.categorie = categorie;
        this.message = message;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getMessage() {
        return message;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", categorie=" + categorie + ", message=" + message + '}';
    }
}


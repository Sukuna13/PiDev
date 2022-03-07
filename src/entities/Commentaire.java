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
   private int idForum; 
   private String message; 

    public Commentaire() {
    }
   

    public Commentaire(int idCommentaire, int idForum, String message) {
        this.idCommentaire = idCommentaire;
        this.idForum = idForum;
        this.message = message; 
    }

    public Commentaire(int idForum, String message) {
        this.idForum = idForum;
        this.message = message;
    }

 

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public int getIdForum() {
        return idForum;
        
    }

    public String getMessage() {
        return message;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public void setIdForum(int idForum) {
        this.idForum = idForum;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", idForum=" + idForum + ", message=" + message + '}';
    }
}


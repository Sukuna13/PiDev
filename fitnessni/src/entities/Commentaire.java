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

    public Commentaire() {
    }
   

    public Commentaire(int idCommentaire, int idForum) {
        this.idCommentaire = idCommentaire;
        this.idForum = idForum;
    }

    public Commentaire(int idForum) {
        this.idForum = idForum;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public int getIdForum() {
        return idForum;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public void setIdForum(int idForum) {
        this.idForum = idForum;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", idForum=" + idForum + '}';
    }
   
}


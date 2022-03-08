/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MSI
 */
public class Produit {
    private int id_prod;
     private String categorie_prod;
     private String libelle ;
     private String marque ;
     private float prix	;
     private String description_prod;
     private String image_prod;
     private String disponibilite;	
      private float note;
      private int quantite;	

    public Produit() {
    }

    public Produit(int id_prod, String categorie_prod, String libelle, String marque, float prix, String description_prod, String image_prod, String disponibilite, float note, int quantite) {
        this.id_prod = id_prod;
        this.categorie_prod = categorie_prod;
        this.libelle = libelle;
        this.marque = marque;
        this.prix = prix;
        this.description_prod = description_prod;
        this.image_prod = image_prod;
        this.disponibilite = disponibilite;
        this.note = note;
        this.quantite = quantite;
    }

    public Produit(String categorie_prod, String libelle, String marque, float prix, String description_prod, String image_prod, String disponibilite, float note, int quantite) {
        this.categorie_prod = categorie_prod;
        this.libelle = libelle;
        this.marque = marque;
        this.prix = prix;
        this.description_prod = description_prod;
        this.image_prod = image_prod;
        this.disponibilite = disponibilite;
        this.note = note;
        this.quantite = quantite;
    }

    public Produit( String categorie_prod, String libelle, String marque, float prix, String description_prod, String disponibilite, float note, int quantite) {
        
        this.categorie_prod = categorie_prod;
        this.libelle = libelle;
        this.marque = marque;
        this.prix = prix;
        this.description_prod = description_prod;
        this.disponibilite = disponibilite;
        this.note = note;
        this.quantite = quantite;
    }

    public Produit(String categorie_prod) {
        this.categorie_prod = categorie_prod;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getCategorie_prod() {
        return categorie_prod;
    }

    public void setCategorie_prod(String categorie_prod) {
        this.categorie_prod = categorie_prod;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription_prod() {
        return description_prod;
    }

    public void setDescription_prod(String description_prod) {
        this.description_prod = description_prod;
    }

    public String getImage_prod() {
        return image_prod;
    }

    public void setImage_prod(String image_prod) {
        this.image_prod = image_prod;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_prod=" + id_prod + ", categorie_prod=" + categorie_prod + ", libelle=" + libelle + ", marque=" + marque + ", prix=" + prix + ", description_prod=" + description_prod + ", image_prod=" + image_prod + ", disponibilite=" + disponibilite + ", note=" + note + ", quantite=" + quantite + '}';
    }

    
}

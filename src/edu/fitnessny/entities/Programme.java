/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fitnessny.entities;

/**
 *
 * @author rocky
 */
public class Programme {
    private int idProgramme;
    private String nomProgramme; 
    private String objectifProgramme;
    private String descriptionProgramme;
    private String categorieProgramme;
    private int idExercice;

    @Override
    public String toString() {
        return "Programme{" + "idProgramme=" + idProgramme + ", nomProgramme=" + nomProgramme + ", objectifProgramme=" + objectifProgramme + ", descriptionProgramme=" + descriptionProgramme + ", categorieProgramme=" + categorieProgramme + ", idExercice=" + idExercice + '}';
    }

    public Programme() {
    }
      public Programme(int i) {

    }

    public int getIdProgramme() {
        return idProgramme;
    }

    public void setIdProgramme(int idProgramme) {
        this.idProgramme = idProgramme;
    }

    public String getNomProgramme() {
        return nomProgramme;
    }

    public void setNomProgramme(String nomProgramme) {
        this.nomProgramme = nomProgramme;
    }

    public String getObjectifProgramme() {
        return objectifProgramme;
    }

    public void setObjectifProgramme(String objectifProgramme) {
        this.objectifProgramme = objectifProgramme;
    }

    public String getDescriptionProgramme() {
        return descriptionProgramme;
    }

    public void setDescriptionProgramme(String descriptionProgramme) {
        this.descriptionProgramme = descriptionProgramme;
    }

    public String getCategorieProgramme() {
        return categorieProgramme;
    }

    public void setCategorieProgramme(String categorieProgramme) {
        this.categorieProgramme = categorieProgramme;
    }

    public int getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(int idExercice) {
        this.idExercice = idExercice;
    }

    public Programme(int idProgramme, String nomProgramme, String objectifProgramme, String descriptionProgramme, String categorieProgramme, int idExercice) {
        this.idProgramme = idProgramme;
        this.nomProgramme = nomProgramme;
        this.objectifProgramme = objectifProgramme;
        this.descriptionProgramme = descriptionProgramme;
        this.categorieProgramme = categorieProgramme;
        this.idExercice = idExercice;
    }

    public Programme(String nomProgramme, String objectifProgramme, String descriptionProgramme, String categorieProgramme, int idExercice) {
        this.nomProgramme = nomProgramme;
        this.objectifProgramme = objectifProgramme;
        this.descriptionProgramme = descriptionProgramme;
        this.categorieProgramme = categorieProgramme;
        this.idExercice = idExercice;
    }

    public Programme(String nomProgramme, String objectifProgramme, String descriptionProgramme, String categorieProgramme) {
        this.nomProgramme = nomProgramme;
        this.objectifProgramme = objectifProgramme;
        this.descriptionProgramme = descriptionProgramme;
        this.categorieProgramme = categorieProgramme;
    }

    public Programme(int idProgramme, String nomProgramme, String objectifProgramme, String descriptionProgramme, String categorieProgramme) {
        this.idProgramme = idProgramme;
        this.nomProgramme = nomProgramme;
        this.objectifProgramme = objectifProgramme;
        this.descriptionProgramme = descriptionProgramme;
        this.categorieProgramme = categorieProgramme;
    }
    


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fitnessny.entities;

/**
 *
 * @author rocky
 */
public class Exercice {

    private int idExercice;
    private String nomExercice;
    private int nbrSerie;
    private int nbrRepetition;
    private String descriptionExercice;
    private String categorieExercice;

    public Exercice(String string, String string0, String string1, String string2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public String toString() {
        return "Exercice{" + "idExercice=" + idExercice + ", nomExercice=" + nomExercice + ", nbrSerie=" + nbrSerie + ", nbrRepetition=" + nbrRepetition + ", descriptionExercice=" + descriptionExercice + ", categorieExercice=" + categorieExercice + '}';
    }

    public Exercice() {
    }

    public Exercice(int i) {

    }

    public int getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(int idExercice) {
        this.idExercice = idExercice;
    }

    public String getNomExercice() {
        return nomExercice;
    }

    public void setNomExercice(String nomExercice) {
        this.nomExercice = nomExercice;
    }

    public int getNbrSerie() {
        return nbrSerie;
    }

    public void setNbrSerie(int nbrSerie) {
        this.nbrSerie = nbrSerie;
    }

    public int getNbrRepetition() {
        return nbrRepetition;
    }

    public void setNbrRepetition(int nbrRepetition) {
        this.nbrRepetition = nbrRepetition;
    }

    public String getDescriptionExercice() {
        return descriptionExercice;
    }

    public void setDescriptionExercice(String descriptionExercice) {
        this.descriptionExercice = descriptionExercice;
    }

    public String getCategorieExercice() {
        return categorieExercice;
    }

    public void setCategorieExercice(String categorieExercice) {
        this.categorieExercice = categorieExercice;
    }

    public Exercice(String nomExercice, int nbrSerie, int nbrRepetition, String descriptionExercice, String categorieExercice) {
        this.nomExercice = nomExercice;
        this.nbrSerie = nbrSerie;
        this.nbrRepetition = nbrRepetition;
        this.descriptionExercice = descriptionExercice;
        this.categorieExercice = categorieExercice;
    }

    public Exercice(int idExercice, String nomExercice, int nbrSerie, int nbrRepetition, String descriptionExercice, String categorieExercice) {
        this.idExercice = idExercice;
        this.nomExercice = nomExercice;
        this.nbrSerie = nbrSerie;
        this.nbrRepetition = nbrRepetition;
        this.descriptionExercice = descriptionExercice;
        this.categorieExercice = categorieExercice;
    }

}

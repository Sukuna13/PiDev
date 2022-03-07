/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author HP
 */
public class Plats {
    private int id_plats;
    private int id_sportif;

    public Plats(int id_plats, int id_sportif) {
        this.id_plats = id_plats;
        this.id_sportif = id_sportif;
    }

    public Plats(int id_sportif) {
        this.id_sportif = id_sportif;
    }

    public Plats() {
    }

   
    
    public int getId_plats() {
        return id_plats;
    }

    public void setId_plats(int id_plats) {
        this.id_plats = id_plats;
    }

    public int getId_sportif() {
        return id_sportif;
    }

    public void setId_sportif(int id_sportif) {
        this.id_sportif = id_sportif;
    }

    @Override
    public String toString() {
        return "Plats{" + "id_plats=" + id_plats + ", id_sportif=" + id_sportif + '}';
    }
    
    
    
}

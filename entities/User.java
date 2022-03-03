/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Asus
 */
public class User {
    private int id ;
    private String niveau;

    public User(int id, String niveau) {
        this.id = id;
        this.niveau = niveau;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", niveau=" + niveau + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getId() {
        return id;
    }

    public String getNiveau() {
        return niveau;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class maConnexion {
    
    
     final static String URL = "jdbc:mysql://127.0.0.1:3306/gestion_salle";
    final static String USERNAME = "root";
    final static String PWD = "";
    
    //var
    //Singleton : 1
    static maConnexion instance = null;
    private Connection cnx;
    
    //Constructeur
    //Singleton : 2 (private)
    private maConnexion() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("Connexion etablie avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //Getters 
    public Connection getCnx() {
        return cnx;
    }
    //Singleton : 3
    public static maConnexion getInstance() {
        if (instance == null) {
            instance = new maConnexion();
        }
        return instance;
}}

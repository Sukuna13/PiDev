/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.finessny.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rocky
 */
public class MyConnection {
    
    
    public String url = "jdbc:mysql://localhost:3306/fitnessni";
    public String login = "root";
    public String pwd = "";
    Connection cnx ;

    public MyConnection() {
        try {
          cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion Etablie ! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public Connection  getCnx() {
return cnx ; 
}
}

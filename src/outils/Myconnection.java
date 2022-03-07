/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author nahaw
 */
public class Myconnection {

    /*public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    public String url ="jdbc:mysql://localhost:3306/fitnessni"; 
    public String login="root"; 
    public String pwd=""; 
    Connection cnx; 
    public static Myconnection instance; 
    
    public Myconnection(){
      try { 
        cnx =    DriverManager.getConnection(url, login, pwd);
        System.out.println("connexion etablie!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


   public Connection getcnx() {
        return cnx ;     
    }
   public static Myconnection getinstance(){
        if (instance == null) {
            instance = new Myconnection ();  
        }
        return instance; 
    }  
}

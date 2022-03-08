/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */


module Fitnessny {
    
    requires javafx.controls;
    requires javafx.fxml;
	
	//requires AnimateFX;
	//requires java.mail;
	


    requires mysql.connector.java;
    requires java.sql;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;
    requires recaptchav2.java;
    requires http;
    requires commons.io;
    
   
    
    opens edu.fitnessny.entities ;
    opens edu.fitnessny.gui2 to javafx.graphics, javafx.fxml;
    opens edu.fitnessny.gui3 to javafx.graphics, javafx.fxml;
   
    

}
   

module Fitnessny {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires AnimateFX;
	requires java.mail;
	requires spring.security.crypto;
	requires mysql.connector.java;
  
    requires junit5;
	requires junit;
	requires mockito.all;
	
	requires transitive org.apiguardian.api;
    requires transitive org.junit.platform.commons;
    requires transitive org.opentest4j;
	requires log4j;
	requires org.junit.jupiter.api;

    
    
	opens application to javafx.graphics, javafx.fxml;
	opens fitnessny.entities to javafx.base;

}

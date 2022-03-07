module Fitnessny {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires AnimateFX;
	requires java.mail;
	requires spring.security.crypto;
	
	
	opens application to javafx.graphics, javafx.fxml;
	opens fitnessny.entities to javafx.base;

}

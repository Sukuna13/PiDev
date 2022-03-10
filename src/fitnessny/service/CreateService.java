package fitnessny.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;  
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import fitnessny.configr.MyConnection;

import fitnessny.entities.Utilisateur;
public class CreateService {
	
	Connection cnx;
	public CreateService() {
		cnx=MyConnection.getInstance().getCnx();
	}

	public void addUser(Utilisateur user)  {
		
		
		String req="INSERT INTO Utilisateur (nom,prenom,adresse,dateNaissance,adresseMail,motDePasse,whoami,numTel,blocRaison,unbloc,image) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement psst=cnx.prepareStatement(req);
			psst.setString(1, user.getNom());
			psst.setString(2, user.getPrenom());
			psst.setString(3, user.getAdresse());
			psst.setDate(4, user.getDateNaissance());
			psst.setString(5, user.getMailAddress());
			psst.setString(6, user.getPassword());
			psst.setString(7, user.getWhoami());
			psst.setString(8, user.getNumTel());
			psst.setString(9, user.getBlocRaison());
			psst.setDate(10, user.getUnbloc());


			
			if(user.getImage()!=null) {
				psst.setBinaryStream(11, user.getImage(),user.getImage().available());
				}else {
				psst.setBinaryStream(11, user.getImage());

				}

			

			psst.executeUpdate();
			System.out.println("added successfully !!!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	
	
	
	

		

}

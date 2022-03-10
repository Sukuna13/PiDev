package fitnessny.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCrypt;

import application.ResetPassword;
import fitnessny.configr.MyConnection;
import fitnessny.entities.Utilisateur;
import javafx.util.Duration;


public class GetService {

	Connection cnx;
	public GetService() {
		cnx=MyConnection.getInstance().getCnx();
	}
//	
	public List<Utilisateur> displayAllUsers(){
		List<Utilisateur> admins=new ArrayList<>();
		try {
		String req = "SELECT * FROM Utilisateur";
		Statement st = cnx.createStatement();
		ResultSet rs = st.executeQuery(req);
		while (rs.next()) {
			Utilisateur admin = new Utilisateur();
			admin.setId(rs.getInt(1));
			admin.setNom(rs.getString(2));
			admin.setPrenom(rs.getString(3));
			admin.setAdresse(rs.getString(4));
			admin.setDateNaissance(rs.getDate(5));
			admin.setMailAddress(rs.getString(6));
			admin.setPassword(rs.getString(7));
			admin.setWhoami(rs.getString(8));
			admin.setNumTel(rs.getString(9));
			admin.setBlocRaison(rs.getString(10));
			admin.setUnbloc(rs.getDate(11));
			admin.setRetrievedImage(rs.getBlob(12));




			admins.add(admin);
		}

		
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return admins;
	}
	
	public Boolean isexist(String mailAddress, String password) {
		
		Utilisateur utilisateur = null;
		List<Utilisateur> users = new ArrayList<>();
		users = displayAllUsers();
		for (Utilisateur user : users) {
			if (user.getMailAddress().equals(mailAddress) && checkpassword(password, user.getPassword())) 
			{utilisateur=user;}
		}
		
		
		if(utilisateur==null) {
			return false;
		}else {
			return true;
		}
			
		}
	
	
	

	public Boolean isexistByMail(String mailAddress) {
		
		Utilisateur utilisateur = null;
		List<Utilisateur> users = new ArrayList<>();
		users = displayAllUsers();
		for (Utilisateur user : users) {
			if (user.getMailAddress().equals(mailAddress) ) {utilisateur=user;}
		}
		
		
		if(utilisateur==null) {
			return false;
		}else {
			return true;
		}
			
		}
	
	
	
public Boolean isexist(int id) {
		
		Utilisateur utilisateur = null;
		List<Utilisateur> users = new ArrayList<>();
		users = displayAllUsers();
		for (Utilisateur user : users) {
			if (user.getId()==id) {utilisateur=user;}
		}
		
		
		if(utilisateur==null) {
			return false;
		}else {
			return true;
		}
			
		}
	
	
public Utilisateur getByMailAndPwd(String mailAddress, String password) {
		
		Utilisateur utilisateur = null;
		List<Utilisateur> users = new ArrayList<>();
		users = displayAllUsers();
		for (Utilisateur user : users) {
			if (user.getMailAddress().equals(mailAddress) && checkpassword(password, user.getPassword()))
			{utilisateur=user;}
		}
		
		
		return utilisateur;
			
		}



public Utilisateur getByMail(String mailAddress) {
	
	Utilisateur utilisateur = null;
	List<Utilisateur> users = new ArrayList<>();
	users = displayAllUsers();
	for (Utilisateur user : users) {
		if (user.getMailAddress().equals(mailAddress))
		{utilisateur=user;}
	}
	
	return utilisateur;
		
	}






public Utilisateur getById(int id) {
	
	Utilisateur utilisateur = null;
	List<Utilisateur> users = new ArrayList<>();
	users = displayAllUsers();
	for (Utilisateur user : users) {
		if (user.getId()==id) {utilisateur=user;}
	}
	
	return utilisateur;
		
	}



	private boolean checkpassword(String candidate_password, String stored_hash) {
		if (BCrypt.checkpw(candidate_password, stored_hash))
		    return true;
		else
		    return false;
		
	}
	
	
	
	
	
	public static String generateCodeToResetPwd() {
		// create a string of uppercase and lowercase characters and numbers
		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";

		// combine all strings
		String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		int length = 10;

		for(int i = 0; i < length; i++) {

		  // generate random index number
		  int index = random.nextInt(alphaNumeric.length());

		  // get character specified by index
		  // from the string
		  char randomChar = alphaNumeric.charAt(index);

		  // append the character to string builder
		  sb.append(randomChar);
		}

		String randomString = sb.toString();
		return randomString;
		}



	
	
		
	

}

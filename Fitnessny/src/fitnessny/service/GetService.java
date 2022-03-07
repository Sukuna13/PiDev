package fitnessny.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

import fitnessny.configr.MyConnection;
import fitnessny.entities.Utilisateur;


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
			if (user.getMailAddress().equals(mailAddress) ) {utilisateur=user;}
		}
		//&& checkpassword(password, user.getPassword())
		
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
			if (user.getMailAddress().equals(mailAddress) ) {utilisateur=user;}
		}
		//&& checkpassword(password, user.getPassword())
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
	
	
	
	
	
	
	
	
	
		
	

}

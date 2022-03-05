package fitnessny.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




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
			if (user.getMailAddress().equals(mailAddress) && user.getPassword().equals(password)) {utilisateur=user;}
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
			if (user.getMailAddress().equals(mailAddress) && user.getPassword().equals(password)) {utilisateur=user;}
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
		
	
//	
////	public Admin displayAdminById(int id){
////		 Admin admin=new Admin();
////		try {
////		String req = "SELECT * FROM Admin WHERE id=?";
////		PreparedStatement st = cnx.prepareStatement(req);
////		st.setInt(1, id);
////		ResultSet rs = st.executeQuery(req);
////			admin.setId(rs.getInt(1));
////			admin.setNom(rs.getString(2));
////			admin.setAdresse(rs.getString(3));
////			admin.setNumTel(rs.getString(4));
////			
////		
////		}catch (Exception e) {
////			System.err.println(e.getMessage());
////		}
////		return admin;
////	}
//	
//	
//	
//	
//	public List<Coach> displayAllCoachs(){
//		List<Coach> coachs=new ArrayList<>();
//		try {
//		String req = "SELECT * FROM coach";
//		Statement st = cnx.createStatement();
//		ResultSet rs = st.executeQuery(req);
//		while (rs.next()) {
//			Coach coach = new Coach();
//			coach.setId(rs.getInt(1));
//			coach.setNom(rs.getString(2));
//			coach.setAdresse(rs.getString(3));
//			coach.setNumTel(rs.getString(4));
//			coachs.add(coach);
//		}
//		
//		}catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//		return coachs;
//	}
//	
//	
//	
//	public List<EmployeSalle> displayAllEmployees(){
//		List<EmployeSalle> empls=new ArrayList<>();
//		try {
//		String req = "SELECT * FROM EmployeSalle";
//		Statement st = cnx.createStatement();
//		ResultSet rs = st.executeQuery(req);
//		while (rs.next()) {
//			EmployeSalle employeSalle = new EmployeSalle();
//			employeSalle.setId(rs.getInt(1));
//			employeSalle.setNom(rs.getString(2));
//			employeSalle.setAdresse(rs.getString(3));
//			employeSalle.setNumTel(rs.getString(4));
//			empls.add(employeSalle);
//		}
//		
//		}catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//		return empls;
//	}
//	
//	
//	
//	public List<Gerant> displayAllGerant(){
//		List<Gerant> gerants=new ArrayList<>();
//		try {
//		String req = "SELECT * FROM Gerant";
//		Statement st = cnx.createStatement();
//		ResultSet rs = st.executeQuery(req);
//		while (rs.next()) {
//			Gerant gerant = new Gerant();
//			gerant.setId(rs.getInt(1));
//			gerant.setNom(rs.getString(2));
//			gerant.setAdresse(rs.getString(3));
//			gerant.setNumTel(rs.getString(4));
//			gerants.add(gerant);
//		}
//		
//		}catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//		return gerants;
//	}
//	
//	
//	
//	public List<Sportif> displayAllSportif(){
//		List<Sportif> sportifs=new ArrayList<>();
//		try {
//		String req = "SELECT * FROM Sportif";
//		Statement st = cnx.createStatement();
//		ResultSet rs = st.executeQuery(req);
//		while (rs.next()) {
//			Sportif sportif = new Sportif();
//			sportif.setId(rs.getInt(1));
//			sportif.setNom(rs.getString(2));
//			sportif.setAdresse(rs.getString(3));
//			sportif.setNumTel(rs.getString(4));
//			sportifs.add(sportif);
//		}
//		
//		}catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//		return sportifs;
//	}
}

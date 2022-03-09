package fitnessny.service;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCrypt;

import fitnessny.configr.MyConnection;

import fitnessny.entities.Utilisateur;

public class UpdateService {
	
	
	Connection cnx;
	public UpdateService() {
		cnx=MyConnection.getInstance().getCnx();
	}
	
	






	public void updateUser(Utilisateur user) {
		String req = "UPDATE Utilisateur SET nom=? , prenom=? , adresse=? , dateNaissance=? , adresseMail=? , motDePasse=? , numTel=? , whoami=? , blocRaison=? , unbloc=? WHERE id=?";
		try {
			PreparedStatement psst=cnx.prepareStatement(req);
			psst.setString(1, user.getNom());
			psst.setString(2, user.getPrenom());
			psst.setString(3, user.getAdresse());
			psst.setDate(4, user.getDateNaissance());
			psst.setString(5, user.getMailAddress());
			psst.setString(6, user.getPassword());
			psst.setString(7, user.getNumTel());
			psst.setString(8, user.getWhoami());
			psst.setString(9, user.getBlocRaison());
			psst.setDate(10, user.getUnbloc());
			psst.setInt(11, user.getId());

			
			

			psst.executeUpdate();
			System.out.println("updated successfully !!!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	


public void updateUserpwd(Utilisateur user) {
	String req = "UPDATE Utilisateur SET motDePasse=? WHERE adresseMail=?";
	try {
		PreparedStatement psst=cnx.prepareStatement(req);
		psst.setString(1, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		psst.setString(2, user.getMailAddress());
	
		
		
		

		psst.executeUpdate();
		System.out.println("updated successfully !!!!!");
	} catch (SQLException e) {
		e.printStackTrace();
	}

	
}}




//	
//	public void updateSportif(Sportif sportif) {
//		String req = "UPDATE Sportif SET nom=?, adresse=?, numTel=? WHERE id=?";
//		try {
//			PreparedStatement psst=cnx.prepareStatement(req);
//			psst.setString(1, sportif.getNom());
//			psst.setString(2, sportif.getAdresse());
//			psst.setString(3, sportif.getNumTel());
//			psst.setInt(4, sportif.getId());
//
//
//			psst.executeUpdate();
//			System.out.println("updated successfully !!!!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		
//	}
//	
//	
//	public void updateCoach(Coach coach) {
//		String req = "UPDATE coach SET nom=?, adresse=?, numTel=? WHERE id=?";
//		try {
//			PreparedStatement psst=cnx.prepareStatement(req);
//			psst.setString(1, coach.getNom());
//			psst.setString(2, coach.getAdresse());
//			psst.setString(3, coach.getNumTel());
//			psst.setInt(4, coach.getId());
//
//
//			psst.executeUpdate();
//			System.out.println("updated successfully !!!!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		
//	}
//	
//	
//	
//	public void updateGerant(Gerant gerant) {
//		String req = "UPDATE Gerant SET nom=?, adresse=?, numTel=? WHERE id=?";
//		try {
//			PreparedStatement psst=cnx.prepareStatement(req);
//			psst.setString(1, gerant.getNom());
//			psst.setString(2, gerant.getAdresse());
//			psst.setString(3, gerant.getNumTel());
//			psst.setInt(4, gerant.getId());
//
//
//			psst.executeUpdate();
//			System.out.println("updated successfully !!!!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		
//	}
//	
//	
//	
//	public void updateEmployeeSalle(EmployeSalle employeSalle) {
//		String req = "UPDATE EmployeSalle SET nom=?, adresse=?, numTel=? WHERE id=?";
//		try {
//			PreparedStatement psst=cnx.prepareStatement(req);
//			psst.setString(1, employeSalle.getNom());
//			psst.setString(2, employeSalle.getAdresse());
//			psst.setString(3, employeSalle.getNumTel());
//			psst.setInt(4, employeSalle.getId());
//
//
//			psst.executeUpdate();
//			System.out.println("updated successfully !!!!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		
//	}
//
//}
//
//

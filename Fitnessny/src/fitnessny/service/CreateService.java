package fitnessny.service;

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

	public void addUser(Utilisateur user) {
		
		
		String req="INSERT INTO Utilisateur (nom,prenom,adresse,dateNaissance,adresseMail,motDePasse,whoami,numTel,blocRaison,unbloc) VALUES(?,?,?,?,?,?,?,?,?,?)";
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




			psst.executeUpdate();
			System.out.println("added successfully !!!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}}
		
//		public void addCoach(Coach coach) {
//			
//			String req="INSERT INTO coach (nom,adresse,numTel,prénom,dateNaissance,isheprivate) VALUES(?,?,?,?,?,?)";
//			try {
//				PreparedStatement psst=cnx.prepareStatement(req);
//				psst.setString(1, coach.getNom());
//				psst.setString(2, coach.getAdresse());
//				psst.setString(3, coach.getNumTel());
//				psst.setString(4, coach.getPrenom());
//				psst.setDate(5, coach.getDateNaissance());
//				psst.setBoolean(6, coach.isHePrivate());
//
//				psst.executeUpdate();
//				System.out.println("added successfully !!!!!");
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//		}
//			
//			public void addGerant(Gerant gerant) {
//				
//				String req="INSERT INTO Gerant (nom,prénom,numTel,adresse,dateNaissance) VALUES(?,?,?,?,?)";
//				try {
//					PreparedStatement psst=cnx.prepareStatement(req);
//					psst.setString(1, gerant.getNom() );
//					psst.setString(2, gerant.getPrenom());
//					psst.setString(3, gerant.getNumTel());
//					psst.setString(4, gerant.getAdresse());
//					psst.setDate(5, gerant.getDateNaissance());
//
//					psst.executeUpdate();
//					System.out.println("added successfully !!!!!");
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			
//	public void addEmployee(EmployeSalle employee) {
//				
//				String req="INSERT INTO EmployeSalle (nom,prénom,numTel,adresse,dateNaissance) VALUES(?,?,?,?,?)";
//				try {
//					PreparedStatement psst=cnx.prepareStatement(req);
//					psst.setString(1, employee.getNom() );
//					psst.setString(2, employee.getPrenom());
//					psst.setString(3, employee.getNumTel());
//					psst.setString(4, employee.getAdresse());
//					psst.setDate(5, employee.getDateNaissance());
//
//					psst.executeUpdate();
//					System.out.println("added successfully !!!!!");
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//				
//				
//				public void addSportif(Sportif sportif) {
//					
//					String req="INSERT INTO Sportif (nom,adresse,numTel,prénom,dateNaissance) VALUES(?,?,?,?,?)";
//					
//					try {
//						PreparedStatement psst=cnx.prepareStatement(req);
//						psst.setString(1, sportif.getNom());
//						psst.setString(2, sportif.getAdresse());
//						psst.setString(3, sportif.getNumTel());
//						psst.setString(4, sportif.getPrenom());
//						psst.setDate(5, sportif.getDateNaissance());
//						psst.executeUpdate();
//						System.out.println("success");
//						System.out.println("added successfully !!!!!");
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//		
//	}
//	
//	
	
}

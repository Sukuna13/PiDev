package fitnessny.service;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.SQLException;


import fitnessny.configr.MyConnection;

public class DeleteService {
	
	
	
	Connection cnx;
	public DeleteService() {
		cnx=MyConnection.getInstance().getCnx();
	}
	
	//deleting all admins
	public void deleteAllAdmin() {
		String req="DELETE FROM Admin";
		try {
			PreparedStatement psst=cnx.prepareStatement(req);
			psst.executeUpdate();
			System.out.println("deleted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUtilisateurById(int id) {
		String req = "DELETE FROM Utilisateur WHERE id=?";
		try {
			PreparedStatement psst=cnx.prepareStatement(req);
			psst.setInt(1,id);
			psst.executeUpdate();
			System.out.println("deleted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	//deleting all coachs
	public void deleteAllCoachs() {
		String req="DELETE FROM coach";
		try {
			PreparedStatement psst=cnx.prepareStatement(req);
			psst.executeUpdate();
			System.out.println("deleted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//delete coach by his id
	public void deleteCoachById(int id) {
		String req = "DELETE FROM coach WHERE id=?";
		try {
			PreparedStatement psst=cnx.prepareStatement(req);
			psst.setInt(1,id);
			psst.executeUpdate();
			System.out.println("deleted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	//deleting all sportifs
	public void deleteAllSportif() {
		String req="DELETE FROM Sportif";
		try {
			PreparedStatement psst=cnx.prepareStatement(req);
			psst.executeUpdate();
			System.out.println("deleted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//delete sportif by id
	public void deleteSportifById(int id) {
		String req = "DELETE FROM Sportif WHERE id=?";
		try {
			PreparedStatement psst=cnx.prepareStatement(req);
			psst.setInt(1,id);
			psst.executeUpdate();
			System.out.println("deleted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	//deleting all gerant
	public void deleteAllGerant() {
		String req="DELETE FROM Gerant";
		try {
			PreparedStatement psst=cnx.prepareStatement(req);
			psst.executeUpdate();
			System.out.println("deleted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void deletegerantById(int id) {
		String req = "DELETE FROM Gerant WHERE id=?";
		try {
			PreparedStatement psst=cnx.prepareStatement(req);
			psst.setInt(1,id);
			psst.executeUpdate();
			System.out.println("deleted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	//deleting all employees
	public void deleteAllEmployees() {
		String req="DELETE FROM EmployeSalle";
		try {
			PreparedStatement psst=cnx.prepareStatement(req);
			psst.executeUpdate();
			System.out.println("deleted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteEMployeeById(int id) {
		String req = "DELETE FROM EmployeSalle WHERE id=?";
		try {
			PreparedStatement psst=cnx.prepareStatement(req);
			psst.setInt(1,id);
			psst.executeUpdate();
			System.out.println("deleted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	

}

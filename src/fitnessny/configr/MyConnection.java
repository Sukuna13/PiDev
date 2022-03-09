package fitnessny.configr;

import java.sql.*;

public class MyConnection {

	public String url="jdbc:mysql://localhost:3306/lastfitnessni";
	public String login="root";
	public String pwd="";
	Connection cnx;
	public static MyConnection instance;
	private MyConnection() {
		try {
			cnx = DriverManager.getConnection(url, login, pwd);
			System.out.println("okay");
			
		} catch (SQLException e) {
			
			System.err.println("pas de cnx bd !"+e.getMessage());
		}
		
	}
	
	public Connection getCnx() {
		return cnx;
	}
	
	public static MyConnection getInstance() {
		if(instance==null) {
			instance=new MyConnection();
		}
		return instance;
	}
}

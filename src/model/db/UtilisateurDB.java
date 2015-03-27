package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import model.Utilisateur;
import model.db.exception.DatabaseAccessError;

public class UtilisateurDB {
	
	private static Map<String,Utilisateur> users;
	private final static String login = "root";
	private final static String pass = "";
	private final static String url = "jdbc:mysql://localhost/reseauentreprise";
	private static Connection con = null;
	
	static {
		users = new LinkedHashMap<String, Utilisateur>();
		try {
			initializeUsersList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void initializeUsersList() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");//.newInstance();
			try {
				con = DriverManager.getConnection(url, login, pass);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch(ClassNotFoundException cnfe) {
			System.err.println("Error loading : " + cnfe);
		}
		ResultSet rs = null;
		PreparedStatement ps = null;
		ps =  con.prepareStatement("SELECT * FROM utilisateur");	
		rs = ps.executeQuery();
		while (rs.next()) {
			Utilisateur u = new Utilisateur(rs.getString("usr_nom"), rs.getString("usr_prenom"), rs.getString("usr_email"),rs.getString("usr_password"));
			u.setTitre(rs.getString("usr_titre"));
			u.setLieuDeTravail(rs.getString("usr_nom"));
			u.setPoste(rs.getString("usr_poste"));
			u.setNumFix(rs.getString("usr_fixe"));
			u.setNumPortable(rs.getString("usr_portable"));
			
			users.put(rs.getString("usr_email"), u);
		}	
	}
	
	public static boolean checkLogin(String login,String password) throws DatabaseAccessError{
		Utilisateur u = users.get(login);
		if(u == null) 
			return false;
		return u.getPassword().equals(password);
	}
	
	public static Utilisateur getUtilisateur(String login) throws DatabaseAccessError {
		Utilisateur u = users.get(login);
		return u;
	}

	public static void AddUtilisateur(Utilisateur us) throws SQLException {
		con = DriverManager.getConnection(url, login, pass);
		
		PreparedStatement ps =  con.prepareStatement(
				  "INSERT INTO utilisateur (usr_email, usr_nom, usr_prenom, usr_password) "
				+ "VALUES (?, ?, ?, ?)");
		ps.setString(1, us.getNom());
		ps.setString(2, us.getPrenom());
		ps.setString(3, us.getEmail());
		ps.setString(4, us.getPassword());
		ps.execute();
		ps.close();
		users.put(us.getEmail(), us);
	}
}

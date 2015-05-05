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
	//Liste des utilisateurs
	private static Map<String,Utilisateur> users;
	//Informations liées à la connexion
	private final static String login = "root";
	private final static String pass = "";
	private final static String url = "jdbc:mysql://localhost/reseauentreprise";
	private static Connection con = null;
	
	static {
		//On instancie la map
		try {
			initializeUsersList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Fonction qui réinitialise la liste avec tous les utilisateurs present dans la base de données
	public static void initializeUsersList() throws SQLException {
		users = new LinkedHashMap<String, Utilisateur>();
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
		ps =  con.prepareStatement("SELECT `usr_id`, `usr_email`, `usr_nom`, `usr_prenom`, `usr_password`, `usr_titre`, `usr_lieutaf`, `usr_portable`, `usr_fixe`, `usr_dispo`, Date_format(`usr_naissance`,'%m/%d/%Y') FROM utilisateur");	
		rs = ps.executeQuery();
		while (rs.next()) {
			Utilisateur u = new Utilisateur(rs.getString("usr_nom"), rs.getString("usr_prenom"), rs.getString("usr_email"),rs.getString("usr_password"));
			u.setId(rs.getInt("usr_id"));
			u.setTitre(rs.getString("usr_titre"));
			u.setLieuDeTravail(rs.getString("usr_lieutaf"));
			u.setNumFix(rs.getString("usr_fixe"));
			u.setNumPortable(rs.getString("usr_portable"));
			u.setDispo(rs.getInt("usr_dispo"));
			//Dans la base de données nous avons les dates sous la formes AAAA-MM-JJ 
			//et nous les convertissons pour les avoirs de la forme MM/JJ/AAAA
			String date =rs.getString(11);
			u.setDate(date);
			users.put(rs.getString("usr_email"), u);
		}	
	}
	
	public static boolean loginIsFree(String login) {
		boolean libre = true;
		Utilisateur u = users.get(login);
		if (u != null) {
			libre = false;
		}
		return libre;
	}
	
	public static boolean checkLogin(String login,String password) throws DatabaseAccessError {
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
				  "INSERT INTO utilisateur (usr_email, usr_nom, usr_prenom, usr_password, usr_titre, usr_lieutaf, usr_portable, usr_fixe, usr_dispo, usr_naissance) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		ps.setString(1, us.getEmail());
		ps.setString(2, us.getNom());
		ps.setString(3, us.getPrenom());
		ps.setString(4, us.getPassword());
		ps.setString(5, "");
		ps.setString(6, "");
		ps.setString(7, "");
		ps.setString(8, "");
		ps.setInt(9, 0);
		ps.setString(10, us.getDate());
		ps.execute();
		ps.close();
		initializeUsersList();
	}

	public static void UpdateUtilisateur(Utilisateur us) throws SQLException {
		con = DriverManager.getConnection(url, login, pass);
		
		PreparedStatement ps =  con.prepareStatement(
				"UPDATE utilisateur"
				+ " SET usr_email = ?, "
				+ " usr_nom = ?,"
				+ " usr_prenom = ?,"
				+ " usr_password = ?, "
				+ " usr_titre = ?,"
				+ " usr_lieutaf = ?,"
				+ "	usr_portable = ?,"
				+ " usr_fixe = ?,"
				+ " usr_dispo = ?,"
				+ " usr_naissance = ? "
				+ " WHERE usr_id = ?");
		ps.setString(1, us.getEmail());
		ps.setString(2, us.getNom());
		ps.setString(3, us.getPrenom());
		ps.setString(4, us.getPassword());
		ps.setString(5, us.getTitre());
		ps.setString(6, us.getLieuDeTravail());
		ps.setString(7, us.getNumPortable());
		ps.setString(8, us.getNumFix());
		ps.setInt(9, us.getDispo());
		ps.setString(10, us.getDate());
		ps.setInt(11, us.getId());
		ps.execute();
		ps.close();
		initializeUsersList();
	}
	
	public static void supUtilisateur(int id) throws SQLException {
		con = DriverManager.getConnection(url, login, pass);
		//On suprime tous ce qui concerne les personalites de l'utilisateurs
		PersonaliteDB.supFromUser(id);
		//On supprime l'utilisateur de la liste
		PreparedStatement ps =  con.prepareStatement("DELETE FROM utilisateur WHERE usr_id = ?");
		ps.setInt(1, id);
		ps.execute();
		ps.close();
		initializeUsersList();
	}
	
	public static Utilisateur getUtilisateurFromId(int id) throws DatabaseAccessError {
		Utilisateur u = null;
		for (Utilisateur us :users.values()){
			if (id == us.getId()) {
				u = us;
			}
		}
		return u;
	}
}

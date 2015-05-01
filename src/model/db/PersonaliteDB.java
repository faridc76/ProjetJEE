package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.Personalite;
import model.Utilisateur;
import model.db.exception.DatabaseAccessError;

public class PersonaliteDB {
	//Liste des utilisateurs
	private final static int COMPETENCE_CODE = 1;
	private final static int PASSION_CODE = 2;
	
	private static Map<Integer, Personalite> personalites;
	//Informations liées à la connexion
	private final static String login = "root";
	private final static String pass = "";
	private final static String url = "jdbc:mysql://localhost/reseauentreprise";
	private static Connection con = null;
		
	static {
		try {
			initializePersonalitesList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Collection<Personalite> getPersonalites () {
		return personalites.values();
	}
	
	//La fonction qui remplie la liste de toutes les personalites
	private static void initializePersonalitesList() throws SQLException {
		//On instancie la map
		personalites = new LinkedHashMap<Integer, Personalite>();
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
		ps =  con.prepareStatement("SELECT * FROM personalite");	
		rs = ps.executeQuery();
		while (rs.next()) {
			Personalite p = null;
			Utilisateur u = null;
			try {
				u = UtilisateurDB.getUtilisateurFromId(rs.getInt("prs_usr_id"));
			} catch (DatabaseAccessError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p = new Personalite(rs.getString("prs_nom"), rs.getInt("prs_lvl"), getType(rs.getInt("prs_tps_id")), rs.getString("prs_desc"), u);
			p.setId(rs.getInt("prs_id"));
			personalites.put(p.getId(), p);	
		}
	}
	
	//Fonction qui retourne le type de personalite en fonction de son code
	public static String getType(int type) {
		if (type == COMPETENCE_CODE) {
			return "Compétence";
		} else if(type == PASSION_CODE) {
			return "Passion";
		}
		return "Autre";
	}
	
	//Fonction qui retourne le code de personalite en fonction de son type
	public static int getCode(String type) {
		if (type == "Compétence") {
			return COMPETENCE_CODE;
			
		} else if(type == "Passion") {
			return PASSION_CODE;
		}
		return 0;
	}

	//Fonction qui retourne la list de toute les personalité d'un utilisateur rentré en parametre
	public static List<Personalite> getUserPersonalites(String user) throws DatabaseAccessError {
		List<Personalite> l = new ArrayList<Personalite>();
		for (Personalite p : personalites.values()) {
			if (p.getUser().getEmail().equals(user)) {
				l.add(p);
			}
		}
		return l;
	}
	
	//Fonction qui met à jour une personalité
	public static void updatePersonalite(Personalite p) throws SQLException {
		con = DriverManager.getConnection(url, login, pass);
		PreparedStatement ps =  con.prepareStatement(
				"UPDATE personalite"
				+ " SET prs_nom = ?, "
				+ " prs_lvl = ?,"
				+ " prs_desc = ? "
				+ " WHERE prs_id = ?");
		ps.setString(1, p.getNom());
		ps.setInt(2, p.getNiveau());
		ps.setString(3, p.getDescription());
		ps.setInt(4, p.getId());
		ps.execute();
		ps.close();
		initializePersonalitesList();
	}

	public static void addPersonalite(Personalite p) throws SQLException {
		con = DriverManager.getConnection(url, login, pass);
		PreparedStatement ps =  con.prepareStatement(
				  "INSERT INTO personalite (prs_nom, prs_lvl, prs_desc, prs_usr_id, prs_tps_id) "
				+ "VALUES (?, ?, ?, ?, ?)");
		ps.setString(1, p.getNom());
		ps.setInt(2, p.getNiveau());
		ps.setString(3, p.getDescription());
		ps.setInt(4, p.getUser().getId());
		ps.setInt(5, getCode(p.getType()));
		ps.execute();
		ps.close();
		initializePersonalitesList();
	}
	
	//On supprime une personalite en fonction de son id
	public static void supPersonalite(int id) throws SQLException {
		con = DriverManager.getConnection(url, login, pass);
		PreparedStatement ps =  con.prepareStatement("DELETE FROM personalite WHERE prs_id = ?");
		ps.setInt(1, id);
		ps.execute();
		ps.close();
		initializePersonalitesList();
	}	
	
	//On supprime toute les personalites d'un utilisateur
	public static void supFromUser(int id) throws SQLException {
		con = DriverManager.getConnection(url, login, pass);
		PreparedStatement ps =  con.prepareStatement("DELETE FROM personalite WHERE prs_usr_id = ?");
		ps.setInt(1, id);
		ps.execute();
		ps.close();
		initializePersonalitesList();
	}

	public static Utilisateur getUserFromPersonalite(int id) throws DatabaseAccessError {
		return personalites.get(id).getUser();
	}
	
	public static Personalite getPersonalite(int id) throws DatabaseAccessError {
		return personalites.get(id);
	}
	
	
	
}
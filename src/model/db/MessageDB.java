package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Message;
import model.Utilisateur;
import model.db.exception.DatabaseAccessError;
/**
 * 
 * @author alexandre.deneuve and farid.chouakria
 * Version 1.0
 * date: 2015-05-11 
 *
 */
public class MessageDB {
	//Liste des message pour un utilisateur
	private static List<Message> userMessages;
	//Informations li�es � la connexion
	private final static String login = "root";
	private final static String pass = "";
	private final static String url = "jdbc:mysql://localhost/reseauentreprise";
	private static Connection con = null;
	
	
	//La fonction qui remplie la liste de toutes les personalites
	public static List<Message> initializeUserMessageList(Utilisateur us) throws SQLException {
		//On instancie la map
		userMessages = new LinkedList<Message>();
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
		ps =  con.prepareStatement("SELECT * FROM message WHERE msg_rcp_id = " + us.getId());	
		rs = ps.executeQuery();
		while (rs.next()) {
			Utilisateur emmeteur = null;
			Utilisateur recepteur = null;
			Message m = null;
			try {
				emmeteur = UtilisateurDB.getUtilisateurFromId(rs.getInt("msg_emt_id"));
				recepteur = us;
			} catch (DatabaseAccessError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m = new Message(emmeteur, recepteur, rs.getString("msg_titre"), rs.getString("msg_contenu"));
			m.setId(rs.getInt("msg_id"));
			m.setDate(rs.getString("msg_date"));
			m.setLu(rs.getString("msg_statut"));
			userMessages.add(m);	
		}
		return userMessages;
	}


	public static int nombreNonLu(int idUsers)   throws SQLException {
		int nombreNonLu = 0;
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
		ps =  con.prepareStatement("SELECT COUNT(*) FROM message WHERE msg_rcp_id = " + idUsers + " and msg_statut = 'Non lu'" );
		rs = ps.executeQuery();
		if (rs.next()) {
		
			nombreNonLu = rs.getInt(1);	
		}
		return nombreNonLu;
	}


	public static void sendMessage(Message m) throws SQLException {
		con = DriverManager.getConnection(url, login, pass);
		PreparedStatement ps =  con.prepareStatement(
				  "INSERT INTO message (msg_emt_id, msg_rcp_id, msg_date, msg_titre, msg_contenu, msg_statut) "
				+ "VALUES (?, ?, CURRENT_DATE(), ?, ?, ?)");
		ps.setInt(1, m.getEmetteur().getId());
		ps.setInt(2, m.getRecepteur().getId());
		ps.setString(3, m.getTitre());
		ps.setString(4, m.getMessage());
		ps.setString(5, m.getLu());
		ps.execute();
		ps.close();
	}		
	
	public static void supMessage(int messageId, Utilisateur us) throws SQLException {
		con = DriverManager.getConnection(url, login, pass);
		PreparedStatement ps =  con.prepareStatement("DELETE FROM message WHERE msg_id = ?");
		ps.setInt(1, messageId);
		ps.execute();
		ps.close();
		initializeUserMessageList(us);
	}
	
	public static void updateStatut(int idMessage, String statut, Utilisateur User) throws SQLException {
		con = DriverManager.getConnection(url, login, pass);
		PreparedStatement ps =  con.prepareStatement("UPDATE message SET msg_statut = ? WHERE msg_id = ? AND msg_statut != ?");
		System.out.println("statut = " + statut);
		System.out.println("id = " + idMessage);
		ps.setString(1, statut);
		ps.setInt(2, idMessage);
		//On ne fait pas évoluer le statut d'un message au quel on a déja répondu
		ps.setString(3, "Répondu");
		ps.execute();
		ps.close();
		initializeUserMessageList(User);
	} 
	
	public static Message getMessageFromId(int idMessage)  throws SQLException {
		Message m = null;
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
		ps =  con.prepareStatement("SELECT * FROM message WHERE msg_id = " + idMessage);	
		rs = ps.executeQuery();
		if (rs.next()) {
			Utilisateur emmeteur = null;
			Utilisateur recepteur = null;
			try {
				emmeteur = UtilisateurDB.getUtilisateurFromId(rs.getInt("msg_emt_id"));
				recepteur = UtilisateurDB.getUtilisateurFromId(rs.getInt("msg_rcp_id"));;
			} catch (DatabaseAccessError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m = new Message(emmeteur, recepteur, rs.getString("msg_titre"), rs.getString("msg_contenu"));
			m.setId(rs.getInt("msg_id"));
			m.setDate(rs.getString("msg_date"));
			m.setLu(rs.getString("msg_statut"));	
		}
		return m;
	}


	public static void supFromUser(int id) throws SQLException {
		con = DriverManager.getConnection(url, login, pass);
		PreparedStatement ps =  con.prepareStatement("DELETE FROM message WHERE msg_emt_id = ? OR msg_rcp_id = ?");
		ps.setInt(1, id);
		ps.setInt(2, id);
		ps.execute();
		ps.close();
	}
	
	
}
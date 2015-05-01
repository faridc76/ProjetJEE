package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Message;
import model.Personalite;
import model.Utilisateur;
import model.db.exception.DatabaseAccessError;

public class MessageDB {
	//Liste des message pour un utilisateur
	private static List<Message> userMessages;
	//Informations liées à la connexion
	private final static String login = "root";
	private final static String pass = "";
	private final static String url = "jdbc:mysql://localhost/reseauentreprise";
	private static Connection con = null;
	
	
	//La fonction qui remplie la liste de toutes les personalites
	public static void initializeUserMessageList(Utilisateur us) throws SQLException {
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
			m = new Message(emmeteur, recepteur, rs.getString("msg_contenu"));
			m.setLu(rs.getString("msg_statut"));
			userMessages.add(m);	
		}
	}
		
	
}
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Message;
import model.Utilisateur;
import model.db.MessageDB;

/**
 * 
 * @author alexandre.deneuve and farid.chouakria
 * Version 1.0
 * date: 2015-05-11 
 *
 */
@WebServlet("/Message")
public class RequestMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		Utilisateur us = (Utilisateur) session.getAttribute("user");
		//On remet otherUser à null pour que RequestSendMessageServlet comprenne que c'est une réponse
		session.setAttribute("otherUser", null);
		
	
		//On crée une variable tableau dans la quelle on va enregistrer du html pour qui sera introduit dans un jsp
		String table = "";
		//On crée une liste de message
		List<Message> userMessages;
		try {
			//On récupere tous les message de l'utilisateur
			userMessages = MessageDB.initializeUserMessageList(us);
			//On boucle sur la liste des messages
			for (Message m : userMessages) {
				//On crée des lignes d'un tableau html et on entre les infos dans chaque colonne
				table = table + "<tr>";
				//Nom prenom de l'emeteur
				table = table + "<th>" + m.getEmetteur().getPrenom() + " " + m.getEmetteur().getNom() + "</th>";
				//titre du message
				table = table + "<th>" + m.getTitre() + "</th>";
				//date du message
				table = table + "<th>" + m.getDate() + "</th>";
				//Le contenu du message dans un textarea
				table = table + "<th><textarea class='form-control' rows='2' readonly='true'>" + m.getMessage() + "</textarea></th>";
				//Le statut du message
				table = table + "<th>" + m.getLu() + "</th>";
				//Ainsi que deux bouttons, un pour repondre au message l'autre pour supprimer le message
				table = table + "<th><div class='btn-group'>"
						+ "<button class='glyphicon glyphicon-share-alt btn btn-success'" 
						+ "href='#envoyerMessage' data-toggle='modal' value='" + m.getId() + "' onClick='idValue(this.value)'></button>"
						+ "<button href='#suppressionMessage' class='glyphicon glyphicon-remove btn btn-danger'"
						+ " data-toggle='modal' value='" + m.getId() + "' onClick='idValue(this.value)'></button>"
						+ "</div></th>";
				table = table + "</tr>";
				//On met a jout le statut du message (une la page des messages ouverte on considere que tous les messages sont lu)
				MessageDB.updateStatut(m.getId(), "lu", us);
			}
			//On ajoute la table de message à la session pour la recuperer dans un jsp
			session.setAttribute("userMessagesTable", table);
			//L'attribut non lu de la ssion est mi à vide
			session.setAttribute("nonLu", "");
		} catch (SQLException e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirection vers la messagerie
			resp.sendRedirect("messageBox.jsp");
		}
		
	}
	public void test (String f) {
		System.out.println(f);
	}

}

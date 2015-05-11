package controller;

import java.io.IOException;
import java.sql.SQLException;

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
@WebServlet("/SendMessage")
public class RequestSendMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		//On recupere les élements du message
		String titre = req.getParameter("titreMessage");
		String message = req.getParameter("inputMessage");
		
		Utilisateur us = (Utilisateur) session.getAttribute("user");
		Utilisateur otherUser = (Utilisateur) session.getAttribute("otherUser");
		String lien = "/Projet/ShowProfil";
		try {
			//si otherUser == null cela veut dire que ce n'est pas un nouveau message mais une réponse
			if(otherUser == null) {
				lien = "/Projet/Message";
				int idMessage = Integer.parseInt(req.getParameter("id"));
				//On r�cup�re le message auquel nous allons r�pondre
				Message receptMessage = MessageDB.getMessageFromId(idMessage);
				//Nous mettons dans otherUser la personne qui nous a �crit (c'est � dire celle � qui nous allons r�pondre);
				otherUser = receptMessage.getEmetteur();
				//On met le message comme r�pondu
				MessageDB.updateStatut(idMessage, "Répondu", us);
			}
			//On cr�e un objet avec le nouveau message
			Message m = new Message(us, otherUser, titre, message);
			//On envoi le message 
			MessageDB.sendMessage(m);
			
			session.setAttribute("compte", "Le message a bien été envoyé à " + otherUser.getPrenom() + " " + otherUser.getNom());
		} catch (SQLException | NumberFormatException e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirecton
			resp.sendRedirect(lien);
		}
		
	}

}

package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Message;
import model.Personalite;
import model.Utilisateur;
import model.db.MessageDB;
import model.db.PersonaliteDB;
import model.db.UtilisateurDB;
import model.db.exception.DatabaseAccessError;

@WebServlet("/SendMessage")
public class RequestSendMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
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
				//On récupère le message auquel nous allons répondre
				Message receptMessage = MessageDB.getMessageFromId(idMessage);
				//Nous mettons dans otherUser la personne qui nous a écrit (c'est à dire celle à qui nous allons répondre);
				otherUser = receptMessage.getEmetteur();
				//On met le message comme répondu
				MessageDB.updateStatut(idMessage, "Répondu", us);
			}
			//On crée un objet avec le nouveau message
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

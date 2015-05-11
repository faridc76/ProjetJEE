package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utilisateur;
import model.db.MessageDB;
/**
 * 
 * @author alexandre.deneuve and farid.chouakria
 * Version 1.0
 * date: 2015-05-11 
 *
 */
@WebServlet("/DeleteMessage")
public class RequestDeleteMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		Utilisateur us = (Utilisateur) session.getAttribute("user");
		//Récupere l'id du message appartir des parametres
		int idMessage = Integer.parseInt(req.getParameter("id"));
		
		try {
			//on supprime le message 
			MessageDB.supMessage(idMessage, us);
		} catch (SQLException e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirection vers le servlet Message
			resp.sendRedirect("/Projet/Message");
		}
	}

}

package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import model.Personalite;
import model.Utilisateur;
import model.db.PersonaliteDB;
import model.db.exception.DatabaseAccessError;

@WebServlet("/ShowProfil")
public class RequestShowProfilServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		Utilisateur us = (Utilisateur) session.getAttribute("user");
		String login = us.getEmail();
		//On ajoute dans la session la disponibilité de l'utilisateur
		session.setAttribute("checked", us.getDispo());
		String table = "";
		try {
			List<Personalite> userPersonalites = PersonaliteDB.getUserPersonalites(login);
			for (Personalite p : userPersonalites) {
				table = table + "<tr>";
				table = table + "<th id='perso_"+ p.getId() + "'>" + p.getNom() + "</th><br>";
				table = table + "<th>" + p.getType() + "</th>";
				table = table + "<th>" + p.getNiveau() + "</th>";
				table = table + "<th><button class='glyphicon glyphicon-share-alt btn btn-primary'" 
						+ "href='/Projet/ShowProfil' data-toggle='modal'></button></th>";
				table = table + "</tr>";
			}
			session.setAttribute("userPersonalitesTable", table);
		} catch (DatabaseAccessError e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirecton
			RequestDispatcher dispatcher = req.getRequestDispatcher("profil.jsp");
			dispatcher.forward(req, resp);
		}

		
	}

}

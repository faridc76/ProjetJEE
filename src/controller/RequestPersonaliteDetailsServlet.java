package controller;

import java.io.IOException;

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


@WebServlet("/PersonaliteDetails")
public class RequestPersonaliteDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		int personaliteId = Integer.parseInt(req.getParameter("personalitesID"));
		Utilisateur us;
		Personalite p;
		try {
			us = (Utilisateur) session.getAttribute("user");
			p = PersonaliteDB.getPersonalite(personaliteId);
			
			session.setAttribute("name", us.getPrenom() + " " + us.getNom());
		} catch (DatabaseAccessError e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		}
		
	}

}

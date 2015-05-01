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

import model.Personalite;
import model.Utilisateur;
import model.db.PersonaliteDB;

@WebServlet("/AddPersonalite")
public class RequestAddPersonaliteServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		String nom = req.getParameter("nom");
		//On convertie le niveau de compétence en int
		int lvl = Integer.parseInt(req.getParameter("niveau"));
		String desc = req.getParameter("descriptif");
		//On convertie le int envoyer en parametre e String avec la methode getType de la classe PersonaliteDB
		String type = PersonaliteDB.getType(Integer.parseInt(req.getParameter("type")));
		Utilisateur us = (Utilisateur) session.getAttribute("user");
		Personalite p = new Personalite(nom, lvl, type, desc, us);
		try {
			PersonaliteDB.addPersonalite(p);
		} catch (SQLException e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirecton
			resp.sendRedirect("/Projet/ShowProfil");
		}
		
	}

}

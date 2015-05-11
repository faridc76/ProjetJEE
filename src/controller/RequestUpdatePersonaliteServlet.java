package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Personalite;
import model.Utilisateur;
import model.db.PersonaliteDB;
/**
 * 
 * @author alexandre.deneuve and farid.chouakria
 * Version 1.0
 * date: 2015-05-11 
 *
 */
@WebServlet("/UpdatePersonalite")
public class RequestUpdatePersonaliteServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		//On recupere l'utilisateur
		Utilisateur us = (Utilisateur) session.getAttribute("user");
		//on recupere l'id de la personalite qu'on a convertie en int
		int idPersonalite = Integer.parseInt(req.getParameter("id"));
		//On recupere le nouveau nom
		String nom = req.getParameter("nomPersonalite");
		//On convertie le niveau de compétence en int
		int lvl = Integer.parseInt(req.getParameter("niveauPersonalite"));
		//La desciption
		String desc = req.getParameter("descriptifPersonalite");
		//Le type, si c'est une compétence ou une personalité
		String type = req.getParameter("typePersonalite");
		//On crée un objet avec la personalite
		Personalite p = new Personalite(nom, lvl, type, desc, us);
		//On indique l'id de la personalité
		p.setId(idPersonalite);
		try {
			//On met à jour les informations
			PersonaliteDB.updatePersonalite(p);
		} catch (SQLException e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirection
			resp.sendRedirect("/Projet/ShowProfil");
		}
	}

}

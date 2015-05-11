package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.PersonaliteDB;
/**
 * 
 * @author alexandre.deneuve and farid.chouakria
 * Version 1.0
 * date: 2015-05-11 
 *
 */
@WebServlet("/DeletePersonalite")
public class RequestDeletePersonaliteServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		//On recupere l'id de la personalité appartir des parametres
		int idPersonalite = Integer.parseInt(req.getParameter("id"));
		
		try {
			//On supprime la personalité
			PersonaliteDB.supPersonalite(idPersonalite);
		} catch (SQLException e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirection vers ShowProfil
			resp.sendRedirect("/Projet/ShowProfil");
		}
	}

}

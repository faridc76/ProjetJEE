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
import model.db.UtilisateurDB;
/**
 * 
 * @author alexandre.deneuve and farid.chouakria
 * Version 1.0
 * date: 2015-05-11 
 *
 */
@WebServlet("/DeleteUser")
public class RequestDeleteUserRequest extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		
		//On recupere l'utilisateur appartir de la session
		Utilisateur us = (Utilisateur) session.getAttribute("user");
		
		try {
			//On supprime l'utilisateur
			UtilisateurDB.supUtilisateur(us.getId());
			//On invalide la session
			session.invalidate();
		} catch (SQLException e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirection vers l'index
			resp.sendRedirect("index.jsp");
		}
	}

}

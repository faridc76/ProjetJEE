package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utilisateur;
import model.db.UtilisateurDB;
import model.db.exception.DatabaseAccessError;
/**
 * 
 * @author alexandre.deneuve and farid.chouakria
 * Version 1.0
 * date: 2015-05-11 
 *
 */
@WebServlet("/LoginServlet") 
public class RequestLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		//On recupere le login et le mot de passe entrée en parametre
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		boolean loginOk = false;
		try {
			//On test si il existe un compte avec le login et le mot de passe
			loginOk = UtilisateurDB.checkLogin(login, password);
			//S'il existe bien un compte
			if (loginOk) {
				Utilisateur user = null;
				//On recupere l'utilisateur en fonction du login
				user = UtilisateurDB.getUtilisateur(login);
				//On ajoute les informations dans la session
				session.setAttribute("user", user);
				session.setAttribute("name", user.getPrenom() + " " + user.getNom());
			} else {
				//On affiche un message d'erreur disant indiquant que les informations sont éronné
				session.setAttribute("error", "login ou mot de passe incorrect");
			}
		
		} catch (DatabaseAccessError e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirection vers l'index
			resp.sendRedirect("index.jsp");
		}	
	}
}

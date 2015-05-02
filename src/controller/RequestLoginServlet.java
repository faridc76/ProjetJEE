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
import model.db.UtilisateurDB;
import model.db.exception.DatabaseAccessError;

@WebServlet("/LoginServlet") 
public class RequestLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		boolean loginOk = false;
		try {
			loginOk = UtilisateurDB.checkLogin(login, password);
			if (loginOk) {
				Utilisateur user = null;
				user = UtilisateurDB.getUtilisateur(login);
				session.setAttribute("user", user);
				session.setAttribute("name", user.getPrenom() + " " + user.getNom());
				MessageDB.initializeUserMessageList(user);
				if (MessageDB.isNonLu()) {
					session.setAttribute("newMessage", "color:blue");
				} else {
					session.setAttribute("newMessage", "color:grey");
				}
				
			} else {
				session.setAttribute("error", "login ou mot de passe incorrect");
			}
		
		} catch (DatabaseAccessError e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} catch (SQLException e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirecton
			resp.sendRedirect("index.jsp");
		}	
	}
}

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

@WebServlet("/SignUpServlet")
public class RequestSignUpServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Si on arrive la c'est que le la mot de passe et la confirmation de mot de passe sont identique
		//que le mail est bien de la forme aaaa@bbbb.com
		boolean free = false;
		HttpSession session = req.getSession(true);
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		try {
			free = UtilisateurDB.loginIsFree(email);
			if (free) {
				Utilisateur us = new Utilisateur(nom, prenom, email, password);
				UtilisateurDB.AddUtilisateur(us);
				session.setAttribute("compte", "Le compte a bien été créé, merci de vous connecter");
			} else {
				session.setAttribute("error", "Il y a déjà un compte avec cet E-mail");
			}
		} catch (SQLException e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirecton
			resp.sendRedirect("index.jsp");
		}
	}

}
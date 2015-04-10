package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utilisateur;
import model.db.UtilisateurDB;
import model.db.exception.DatabaseAccessError;

@WebServlet("/RequestUpdateProfilServlet")
public class RequestUpdateProfilServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		String login = (String) session.getAttribute("login");
		try {
			Utilisateur us = UtilisateurDB.getUtilisateur(login);
		} catch (DatabaseAccessError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String dateDeNaissance = req.getParameter("dateDeNaissance");
		String lieuDeTaf = req.getParameter("lieuDeTaf");
		String numFix = req.getParameter("numFixe");
		String numPortable = req.getParameter("numPortable");
		String disponibilite = req.getParameter("dispo");
		
		Utilisateur us;
		try {
			us = UtilisateurDB.getUtilisateur(login);
			us.setNom(nom);
			us.setPrenom(prenom);
			us.setPassword(password);
			us.setDate(dateDeNaissance);
			us.setLieuDeTravail(lieuDeTaf);
			us.setNumFix(numFix);
			us.setNumPortable(numPortable);
			us.setNumPortable(numPortable);
			us.setDispo(disponibilite);
			UtilisateurDB.UpdateUtilisateur(us);
		} catch (DatabaseAccessError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}

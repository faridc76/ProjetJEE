package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utilisateur;
import model.db.UtilisateurDB;

@WebServlet("/SaveUpdate")
public class RequestSaveUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Si on arrive la c'est que le la mot de passe et la confirmation de mot de passe sont identique
		//que le mail est bien de la forme aaaa@bbbb.com
		boolean free = false;
		HttpSession session = req.getSession(true);
		String newEmail = req.getParameter("email");
		try {
			free = UtilisateurDB.loginIsFree(newEmail);
			Utilisateur us = (Utilisateur) session.getAttribute("user");
			String oldEmail = ((Utilisateur) session.getAttribute("user")).getEmail();
			//Le changement d'information s'effectue si le nouveau mail est libre
			//Ou si l'ancien email est le meme que le nouveau
			if (oldEmail.equals(newEmail) || free) {
				us.setEmail(newEmail);
				UtilisateurDB.initializeUsersList();
				session.setAttribute("compte", "Le compte a bien été mis à jour");
			} else {
				session.setAttribute("error", "Impossible de modifier l'email car un compte existe déjà avec ce mail");
			}
			
			us.setNom(req.getParameter("nom"));
			us.setPrenom(req.getParameter("prenom"));
			us.setPassword(req.getParameter("password"));
			us.setTitre(req.getParameter("titre"));
			us.setLieuDeTravail(req.getParameter("lieuDeTravail"));
			us.setNumFix(req.getParameter("numeroFixe"));
			us.setNumPortable(req.getParameter("numeroPortable"));
			us.setDispo(Integer.parseInt(req.getParameter("radio")));

			//On decoupe la date qui est mm/jj/aaaa en {m, j, a}
			String date[] = req.getParameter("dateDeNaissance").split("/");
			//On l'ecrit ensuite dans la forme aaaa-mm-jj pour que mysql comprenne
			us.setDate(date[2] + "-" + date[0] + "-" + date [1]);
			UtilisateurDB.UpdateUtilisateur(us);
			
			//On modifie le nom qui s'affiche
			session.setAttribute("name", us.getPrenom() + " " + us.getNom());
		} catch (SQLException e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirecton
			resp.sendRedirect("/Projet/ShowProfil");
		}
	}

}

package controller;

import java.io.IOException;
import java.util.List;

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

/**
 * 
 * @author alexandre.deneuve and farid.chouakria
 * Version 1.0
 * date: 2015-05-11 
 *
 */
@WebServlet("/ShowOtherProfil")
public class RequestShowOtherProfilServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		//On recupere l'id de la personalité
		int personaliteId = Integer.parseInt(req.getParameter("id"));
		Utilisateur us;
		try {
			//On recupere l'utilisateur de la personalité
			us = PersonaliteDB.getUserFromPersonalite(personaliteId);
			//On indique dans la session quelle est l'utilisateur du profil visitéé
			session.setAttribute("otherUser", us);
			//On crée un objet html avec les informations ur chaque personalité de lutilisateur
			//Objet qui sera retrouvé dans un jsp
			String table = "";
			List<Personalite> userPersonalites = PersonaliteDB.getUserPersonalites(us.getEmail());
			for (Personalite p : userPersonalites) {
				table = table + "<tr>";
				table = table + "<th>" + p.getNom() + "</th>";
				table = table + "<th>" + p.getType() + "</th>";
				table = table + "<th>" + p.getNiveau() + "</th>";
				table = table + "<th><textarea class='form-control' rows='2' readonly='true'>" + p.getDescription() + "</textarea></th>";
				table = table + "</tr>";
			}
			session.setAttribute("otherUserPersonalitesTable", table);	
		} catch (DatabaseAccessError e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			//On redirige
			resp.sendRedirect("autre-profil.jsp");
		}
		
	}

}

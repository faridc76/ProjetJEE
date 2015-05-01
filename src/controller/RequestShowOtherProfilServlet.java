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
import model.db.UtilisateurDB;
import model.db.exception.DatabaseAccessError;


@WebServlet("/ShowOtherProfil")
public class RequestShowOtherProfilServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		int personaliteId = Integer.parseInt(req.getParameter("id"));
		Utilisateur us;
		String lien = "";
		String profil = "";
		try {
			us = PersonaliteDB.getUserFromPersonalite(personaliteId);
			session.setAttribute("otherUser", us);
			if (us.getId() == ((Utilisateur)session.getAttribute("user")).getId()) {
				lien = "profil.jsp";
				profil = "userPersonalitesTable";
			} else {
				lien = "autre-profil.jsp";
				profil = "otherUserPersonalitesTable";
			}
			
			String table = "";
			List<Personalite> userPersonalites = PersonaliteDB.getUserPersonalites(us.getEmail());
			for (Personalite p : userPersonalites) {
				table = table + "<tr>";
				table = table + "<th><a href=\"PersonaliteDetails?personalitesID=" + p.getId() + "\">" + p.getNom() + "</a></th>";
				table = table + "<th>" + p.getType() + "</th>";
				table = table + "<th>" + p.getNiveau() + "</th>";
				table = table + "</tr>";
			}
			session.setAttribute(profil, table);	
		} catch (DatabaseAccessError e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			resp.sendRedirect(lien);
		}
		
	}

}

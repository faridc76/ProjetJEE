package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/ShowProfil")
public class RequestShowProfilServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		Utilisateur us = (Utilisateur) session.getAttribute("user");
		String login = us.getEmail();
		//On ajoute dans la session la disponibilit� de l'utilisateur
		session.setAttribute("checked", us.getDispo());
		String table = "";
		try {
			List<Personalite> userPersonalites = PersonaliteDB.getUserPersonalites(login);
			for (Personalite p : userPersonalites) {
				table = table + "<tr>";
				table = table + "<th id='nomPersonalite_"+ p.getId() + "'>" + p.getNom() + "</th><br>";
				table = table + "<th id='typePersonalite_" + p.getId() + "'>" + p.getType() + "</th>";
				table = table + "<th id='niveauPersonalite_"+ p.getId() + "'>" + p.getNiveau() + "</th>";
				table = table + "<th id='descriptifPersonalite_"+ p.getId() + "'><textarea class='form-control' rows='2' readonly='true'>" + p.getDescription() + "</textarea></th>";
				table = table + "<th><div class='btn-group'>"
						+ "<button class='glyphicon glyphicon-share-alt btn btn-primary'" 
						+ "href='#modifierPersonalite'  value='" + p.getId() + "' id='modifierPersonaliteButton' onClick='idValue(this.value)'"
						+ "data-toggle='modal'></button>"
						+ "<button href='#suppressionPersonalite' class='glyphicon glyphicon-remove btn btn-danger'"
						+ "data-toggle='modal' value='" + p.getId() + "' onClick='idValue(this.value)'></button>"
						+ "</div></th>";
				table = table + "</tr>";
			}
			session.setAttribute("userPersonalitesTable", table);
		} catch (DatabaseAccessError e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirecton
			RequestDispatcher dispatcher = req.getRequestDispatcher("profil.jsp");
			dispatcher.forward(req, resp);
		}

		
	}

}

package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;






import model.Message;
import model.Personalite;
import model.Utilisateur;
import model.db.MessageDB;
import model.db.PersonaliteDB;
import model.db.exception.DatabaseAccessError;

@WebServlet("/Message")
public class RequestMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		Utilisateur us = (Utilisateur) session.getAttribute("user");
		//On remet otherUser à null pour que RequestSendMessageServlet comprenne que c'est une réponse
		session.setAttribute("otherUser", null);
		
		//On ajoute dans la session la disponibilité de l'utilisateur
		
		String table = "";
		List<Message> userMessages;
		try {
			userMessages = MessageDB.initializeUserMessageList(us);
			for (Message m : userMessages) {
				table = table + "<tr>";
				table = table + "<th>" + m.getEmetteur().getPrenom() + " " + m.getEmetteur().getNom() + "</th>";
				table = table + "<th>" + m.getTitre() + "</th>";
				table = table + "<th>" + m.getDate() + "</th>";
				table = table + "<th>" + m.getMessage() + "</th>";
				table = table + "<th>" + m.getLu() + "</th>";
				table = table + "<th><div class='btn-group'>"
						+ "<button class='glyphicon glyphicon-share-alt btn btn-success'" 
						+ "href='#envoyerMessage' data-toggle='modal' value='" + m.getId() + "' onClick='idValue(this.value)'></button>"
						+ "<a class='glyphicon glyphicon-remove btn btn-danger' href='/Projet/DeleteMessage?id=" + m.getId() + "'>"
						+ "</a></div></th>";
				table = table + "</tr>";
				MessageDB.updateStatut(m.getId(), "lu", us);
			}
			session.setAttribute("userMessagesTable", table);
			session.setAttribute("nonLu", "");
		} catch (SQLException e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirecton
			resp.sendRedirect("messageBox.jsp");
		}
		
	}
	public void test (String f) {
		System.out.println(f);
	}

}

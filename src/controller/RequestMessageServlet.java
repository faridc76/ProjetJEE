package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Message;
import model.Utilisateur;
import model.db.MessageDB;


@WebServlet("/Message")
public class RequestMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		Utilisateur us = (Utilisateur) session.getAttribute("user");
		//On remet otherUser � null pour que RequestSendMessageServlet comprenne que c'est une r�ponse
		session.setAttribute("otherUser", null);
		
		//On ajoute dans la session la disponibilit� de l'utilisateur
		
		String table = "";
		List<Message> userMessages;
		try {
			userMessages = MessageDB.initializeUserMessageList(us);
			for (Message m : userMessages) {
				table = table + "<tr>";
				table = table + "<th>" + m.getEmetteur().getPrenom() + " " + m.getEmetteur().getNom() + "</th>";
				table = table + "<th>" + m.getTitre() + "</th>";
				table = table + "<th>" + m.getDate() + "</th>";
				table = table + "<th><textarea class='form-control' rows='2' readonly='true'>" + m.getMessage() + "</textarea></th>";
				table = table + "<th>" + m.getLu() + "</th>";
				table = table + "<th><div class='btn-group'>"
						+ "<button class='glyphicon glyphicon-share-alt btn btn-success'" 
						+ "href='#envoyerMessage' data-toggle='modal' value='" + m.getId() + "' onClick='idValue(this.value)'></button>"
						+ "<button href='#suppressionMessage' class='glyphicon glyphicon-remove btn btn-danger'"
						+ " data-toggle='modal' value='" + m.getId() + "' onClick='idValue(this.value)'></button>"
						+ "</div></th>";
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

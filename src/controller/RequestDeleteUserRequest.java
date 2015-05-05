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

@WebServlet("/DeleteUser")
public class RequestDeleteUserRequest extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		Utilisateur us = (Utilisateur) session.getAttribute("user");
		try {
			System.out.println("id : " + us.getId());
			UtilisateurDB.supUtilisateur(us.getId());
			session.invalidate();
		} catch (SQLException e) {
			session.setAttribute("error", e.toString());
			e.printStackTrace();
		} finally {
			// redirecton
			resp.sendRedirect("index.jsp");
		}
	}

}

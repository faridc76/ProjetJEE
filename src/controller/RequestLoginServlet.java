package controller;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/RequestLoginServlet") 
public class RequestLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession(true);
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		
		boolean loginOk = false;
		try {
			loginOk = UtilisateurDB.checkLogin(login, password);
		} catch (DatabaseAccessError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (loginOk) {
			Utilisateur us = null;
			try {
				us = UtilisateurDB.getUtilisateur(login);
			} catch (DatabaseAccessError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// create session
			
			session.setAttribute("login", login);
			session.setAttribute("name", us.getPrenom() + " " + us.getNom());
			RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
			dispatcher.forward(req, resp);
		} else {
			out.println("nok");
			RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
			dispatcher.forward(req, resp);
		}
		
	}

}

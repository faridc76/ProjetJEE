package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 * @author alexandre.deneuve and farid.chouakria
 * Version 1.0
 * date: 2015-05-11 
 *
 */
@WebServlet("/LogoutServlet")
public class RequestLogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = -5524413147809785195L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//On recupere la session
		HttpSession session = req.getSession();
		//On l'invalide
		session.invalidate();
		//On redirige vers l'index
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, resp);
	}
}

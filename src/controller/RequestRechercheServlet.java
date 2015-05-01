package controller;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Personalite;
import model.Utilisateur;
import model.db.PersonaliteDB;



@WebServlet("/Recherche")
public class RequestRechercheServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String json = "{\"sEcho\":\"1\",\"iTotalRecords\":\"1\",\"iTotalDisplayRecords\":\"1\",\"aaData\":[";
		Collection<Personalite> personalites = PersonaliteDB.getPersonalites();
		//Un compteur pour savoir quand mettre une virgule car la forme doit etre {[ [], []... ]}
		int i = 0;
		for (Personalite perso : personalites) {
			Utilisateur us = perso.getUser();
			if (i > 0) {
				json = json + ",";
			}
			json = json + "[";
			json = json + "\"<a href='/Projet/ShowOtherProfil?id=" + perso.getId() + "'>" + us.getPrenom() + " " + us.getNom() + "</a>\",";
			json = json + "\"" + us.getTitre() + "\",";
			json = json + "\"" + us.getEmail() + "\",";
			json = json + "\"" + perso.getType() + "\",";
			json = json + "\"" + perso.getNom() + "\",";
			json = json + "\"" + perso.getNiveau() + "\",";
			json = json + "\"" + us.disponibilite() + "\",";
			json = json + "\"" + us.getLieuDeTravail() + "\"";
			json = json + "]";
			i++;
		}
		json = json + "]}";
		PrintWriter out = resp.getWriter();
		out.println(json);
	}
}

package controller;

import java.io.IOException;


import java.util.Collection;

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

/**
 * 
 * @author alexandre.deneuve and farid.chouakria
 * Version 1.0
 * date: 2015-05-11 
 *
 */

@WebServlet("/Recherche")
public class RequestRechercheServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//On crée un String qui contiendra des éléments JSON
		String json="[";
		//On recupere la liste des personalites
		Collection<Personalite> personalites = PersonaliteDB.getPersonalites();
		//Un compteur pour savoir quand mettre une virgule car la forme doit etre {[ [], []... ]}
		int i = 0;
		HttpSession session = req.getSession(true);
		//On recupere l'utilisateur
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		//On boucle sur toute les personalites
		for (Personalite perso : personalites) {
			//Si l'id de la personne a qui appartien la personalité est differente de celle de lutilisateur on l'affiche
			//En effet nous ne voulons pas afficher dans le tableau de recherche les personalites de l'utilisateur en cours
			if (perso.getUser().getId() != user.getId()) {
				Utilisateur us = perso.getUser();
				//Si ce n'est pas la premiere personalité dans notre JSON alors on ajoute une virgule
				if (i > 0) {
					json = json + ",";
				}
				//On ajoute toute les infos dans le json
				json = json + "[";
				//Le nom prenom avec un lien pour acceder à la fiche de l'utilisateur
				json = json + "\"<a href='/Projet/ShowOtherProfil?id=" + perso.getId() + "'>" + us.getPrenom() + " " + us.getNom() + "</a>\",";
				//Le titre de l'utilisateur
				json = json + "\"" + us.getTitre() + "\",";
				//Le type de personalité compétence ou passion
				json = json + "\"" + perso.getType() + "\",";
				//le nom de la compétence
				json = json + "\"" + perso.getNom() + "\",";
				//Le niveau de la personalité
				json = json + "\"" + perso.getNiveau() + "\",";
				//La disponibilité de l'utilisateur
				json = json + "\"" + us.disponibilite() + "\",";
				//Le lieu de travail de l'utilisateur
				json = json + "\"" + us.getLieuDeTravail() + "\",";
				//Des détails sur la personalité experience ou autre
				json = json + "\"" + perso.getDescription() + "\"";
				json = json + "]";
				i++;
			}
		}
		json = json + "]";
		//On ajoute la personalité à la session pour pouvoir l'utiliser dans un jsp
		session.setAttribute("json", json);
		//On redirige vers la page de recherche
		RequestDispatcher dispatcher = req.getRequestDispatcher("/recherche.jsp");
		dispatcher.forward(req, resp);
		//resp.sendRedirect("/Projet/recherche.jsp");

	}
}

package model;

/**
 * 
 * @author alexandre.deneuve and farid.chouakria
 * Version 1.0
 * date: 2015-05-11 
 *
 */

public class Message {
	private Utilisateur emetteur;
	private Utilisateur recepteur;
	private String message;
	private String lu;
	private String titre;
	private String date;
	private int id;

	/**
	 * 
	 * @param emetteur = emetteur du message
	 * @param recepteur = le destinataire
	 * @param titre = le titre du message
	 * @param message = le contenu
	 */
	public Message (Utilisateur emetteur, Utilisateur recepteur, String titre, String message) {
		this.setEmetteur(emetteur);
		this.setRecepteur(recepteur);
		this.setMessage(message);
		this.setTitre(titre);
		setDate("0000-00-00");
		setLu("Non lu");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Utilisateur getEmetteur() {
		return emetteur;
	}

	public void setEmetteur(Utilisateur emetteur) {
		this.emetteur = emetteur;
	}

	public Utilisateur getRecepteur() {
		return recepteur;
	}

	public void setRecepteur(Utilisateur recepteur) {
		this.recepteur = recepteur;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isLu() {
		return (lu.equals("non lu"));
	}
	
	public String getLu() {
		return lu;
	}

	public void setLu(String string) {
		this.lu = string;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}

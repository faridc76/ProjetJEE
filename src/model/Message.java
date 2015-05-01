package model;

public class Message {
	private Utilisateur emetteur;
	private Utilisateur recepteur;
	private String message;
	private String lu;

	public Message (Utilisateur emetteur, Utilisateur recepteur, String message) {
		this.setEmetteur(emetteur);
		this.setRecepteur(recepteur);
		this.setMessage(message);
		setLu("Non lu");
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

}

package model;

/**
 * 
 * @author alexandre.deneuve and farid.chouakria
 * Version 1.0
 * date: 2015-05-11 
 *
 */
public class Utilisateur {

	private String nom;
	private String prenom;
	private String email;
	private String password;
	
	private int id;
	private String titre;
	private String lieuDeTravail;
	private String poste;
	private String numPortable;
	private String numFix;
	private String date;
	private int dispo;
	
	/**
	 * 
	 * @param nom = nom de l'utilisateur
	 * @param prenom = prenom de lutilisateur
	 * @param email = l'email de l'utilisateur
	 * @param password = le mot de passe de l'utilisateur
	 */
	public Utilisateur(String nom, String prenom, String email, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		titre = "";
		lieuDeTravail = "";
		poste = "";
		numPortable = "";
		numFix = "";
		setDate("1980-01-01");
		setDispo(0);
		setId(0);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getLieuDeTravail() {
		return lieuDeTravail;
	}

	public void setLieuDeTravail(String lieuDeTravail) {
		this.lieuDeTravail = lieuDeTravail;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public String getNumPortable() {
		return numPortable;
	}

	public void setNumPortable(String numPortable) {
		this.numPortable = numPortable;
	}

	public String getNumFix() {
		return numFix;
	}

	public void setNumFix(String numFix) {
		this.numFix = numFix;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getDispo() {
		return dispo;
	}

	public void setDispo(int dispo) {
		this.dispo = dispo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String disponibilite() {
		switch (getDispo()) {
		case 0:
            return "Disponible";
        case 1:
            return "Peu disponible";
        case 2:
            return "Indisponible";
		}
		return "Non indiqu�";
	}


}

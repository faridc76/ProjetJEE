package model;

public class Personalite {
	private String nom;
	private int niveau;
	private String description;
	private String type;
	private Utilisateur user;
	private int id;
	
	public Personalite(String nom, int niveau, String type, String description, Utilisateur user) {
		this.setNom(nom);
		this.setNiveau(niveau);
		this.setType(type);
		this.description = description;
		this.setUser(user);
		setId(0);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	

	
}

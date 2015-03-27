package model;

public class Personalite {
	private String nom;
	private int niveau;
	private String description;
	private int id;
	private int type;
	
	public Personalite(String nom, int niveau, int type) {
		this.setNom(nom);
		this.setNiveau(niveau);
		this.setType(type);
		setDescription("");
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
}

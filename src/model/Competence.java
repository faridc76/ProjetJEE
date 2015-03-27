package model;

public class Competence extends Personalite {
	
	private final static int COMPETENCE_CODE = 1;
	
	public Competence(String nom, int niveau) {
		super(nom, niveau, COMPETENCE_CODE);
	}

}

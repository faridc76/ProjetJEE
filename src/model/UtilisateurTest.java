package model;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author alexandre.deneuve and farid.chouakria
 * Version 1.0
 * date: 2015-05-11 
 *
 */
public class UtilisateurTest {

	@Test
	public void testUtilisateur() {
		Utilisateur us = new Utilisateur("Nom", "Prenom", "email@mail.fr", "password");
		// Nous testons les param�tres
		assertEquals("Nom", us.getNom());
		assertEquals("Prenom", us.getPrenom());
		assertEquals("email@mail.fr", us.getEmail());
		assertEquals("password", us.getPassword());
		//Nous testons que les autres informations sont bien rentr� par d�faut
		assertEquals("1980-01-01", us.getDate());
		assertEquals(0, us.getDispo());
		assertEquals(0, us.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUtilisateurSansEmail() {
		Utilisateur us = new Utilisateur("Nom", "Prenom", "email@mail.fr", "password");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUtilisateurSansPassword() {
		Utilisateur us = new Utilisateur("Nom", "Prenom", "email@mail.fr", "password");
	}


	@Test
	public void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTitre() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLieuDeTravail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPoste() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNumPortable() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNumFix() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDispo() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisponibilite() {
		fail("Not yet implemented");
	}

}

package fr.isika.cda28.projet1.Annuaire;

import java.io.IOException;

public class LanceurTest {
	public static void main(String[] args) throws IOException {
		// Création d'un objet Annuaire
		Annuaire annuaire = new Annuaire();
		Stagiaire stagiaire1 = new Stagiaire("BOB*****************", "Marley**************", "26**", "CDA28*******", 2024);
		Stagiaire stagiaire2 = new Stagiaire("WACIM***************", "MERINI**************", "2***", "CDA48*******", 2048);
		Noeud stagiaire = new Noeud(stagiaire1, -1, -1);
		Noeud stagiaire22 = new Noeud(stagiaire2, 1, -1);
		annuaire.ajouterStagiaire(stagiaire);
		annuaire.ajouterStagiaire(stagiaire22);

		
		System.out.println(stagiaire);
		System.out.println(stagiaire22);

		annuaire.positionCurseur();
		
		// L'annuaire contient maintenant tous les stagiaires
		// Tu peux maintenant utiliser l'annuaire pour d'autres opérations
	}
}

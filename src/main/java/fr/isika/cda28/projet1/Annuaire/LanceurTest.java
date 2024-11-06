package fr.isika.cda28.projet1.Annuaire;

import java.io.IOException;

public class LanceurTest {
	public static void main(String[] args) throws IOException {
		// Création d'un objet Annuaire
		Annuaire annuaire = new Annuaire();
		Stagiaire stagiaire1 = new Stagiaire("BOB", "Marley", "26", "CDA28", 2024);
		Noeud stagiaire = new Noeud(stagiaire1, -1, -1);
		annuaire.ajouterStagiaire(stagiaire);
		
		
		System.out.println(stagiaire);
		// L'annuaire contient maintenant tous les stagiaires
		// Tu peux maintenant utiliser l'annuaire pour d'autres opérations
	}
}

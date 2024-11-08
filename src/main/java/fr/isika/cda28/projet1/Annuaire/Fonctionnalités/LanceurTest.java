package fr.isika.cda28.projet1.Annuaire.Fonctionnalités;

import java.io.IOException;

public class LanceurTest {
	public static void main(String[] args) throws IOException {
		// Création d'un objet Annuaire
		Annuaire annuaire = new Annuaire();
		Stagiaire stagiaire1 = new Stagiaire("BOB", "Marley", "26", "CDA28", 2024);
		Stagiaire stagiaire2 = new Stagiaire("WACIM", "MERINI", "2", "CDA48", 2048);
		Stagiaire stagiaire3 = new Stagiaire("ARITH", "DAVID", "2", "CDA48", 2048);
		Noeud stagiaire = new Noeud(stagiaire1, -1, -1);
		Noeud stagiaire22 = new Noeud(stagiaire2, -1, -1);
		Noeud stagiaire23 = new Noeud(stagiaire3, -1, -1);
		annuaire.ajouterStagiaire(stagiaire);
		annuaire.ajouterStagiaire(stagiaire22);
		annuaire.ajouterStagiaire(stagiaire23);
		Stagiaire stagiaire11 = new Stagiaire("Durand", "Alice", "75", "Informatique", 2022);
		Stagiaire stagiaire28 = new Stagiaire("Martin", "Bob", "34", "Commerce", 2021);
		Stagiaire stagiaire31 = new Stagiaire("Bernard", "Celine", "13", "Gestion", 2023);
		Stagiaire stagiaire4 = new Stagiaire("Alonso", "David", "69", "Marketing", 2020);
		Stagiaire stagiaire5 = new Stagiaire("Zola", "Eve", "92", "Droit", 2022);

		// Ajout des stagiaires dans l'annuaire
		annuaire.ajouterStagiaire(new Noeud(stagiaire11, -1, -1));
		annuaire.ajouterStagiaire(new Noeud(stagiaire28, -1, -1));
		annuaire.ajouterStagiaire(new Noeud(stagiaire31, -1, -1));
		annuaire.ajouterStagiaire(new Noeud(stagiaire4, -1, -1));
		annuaire.ajouterStagiaire(new Noeud(stagiaire5, -1, -1));
		annuaire.supprimerStagiaire(stagiaire22);
//		annuaire.afficherListeOrdreAlphabetique();
		
		System.out.println("Est ce que DAVID est présent dans l'annuaire ? : "
				+ annuaire.rechercherStagiaire("WACIM"));
//		for (int i = 0; i < 4; i++) {
//			annuaire.getRaf().seek(i * Noeud.TAILLE_NOEUD_OCTET);
//			System.out.println(annuaire.noeud.lireNoeud(annuaire.getRaf()));
//		}

//		System.out.println(stagiaire);
//		System.out.println(stagiaire22);
//		System.out.println(stagiaire23);

		// L'annuaire contient maintenant tous les stagiaires
		// Tu peux maintenant utiliser l'annuaire pour d'autres opérations
	}
}

package fr.isika.cda28.projet1.Annuaire.BackEnd;

import java.util.List;

public class LanceurTest {
	public static void main(String[] args) throws Exception {
		// Création d'un objet Annuaire
		Annuaire annuaire = new Annuaire();
		GestionDesFichiers fichiers = new GestionDesFichiers();
		fichiers.chargerStagiairesDepuisFichier();
		
		Noeud potin = new Noeud(new Stagiaire("POTIN", "Thomas", "75","ATOD 21", 2014), -1,-1);
		Noeud roignant = new Noeud(new Stagiaire("ROIGNANT", "Pierre-Yves", "77","ATOD 26 CP", 2015), -1,-1);
		
		
//		Stagiaire stagiaire1 = new Stagiaire("BOB", "Marley", "26", "CDA28", 2024);
//		Stagiaire stagiaire2 = new Stagiaire("WACIM", "MERINI", "2", "CDA48", 2048);
//		Stagiaire stagiaire3 = new Stagiaire("ARITH", "DAVID", "2", "CDA48", 2048);
//		Noeud stagiaire = new Noeud(stagiaire1, -1, -1);
//		Noeud stagiaire22 = new Noeud(stagiaire2, -1, -1);
//		Noeud stagiaire23 = new Noeud(stagiaire3, -1, -1);
//		annuaire.ajouterStagiaire(stagiaire);
//		annuaire.ajouterStagiaire(stagiaire22);
//		annuaire.ajouterStagiaire(stagiaire23);
//		Stagiaire stagiaire11 = new Stagiaire("Durand", "Alice", "75", "Informatique", 2022);
//		Stagiaire stagiaire28 = new Stagiaire("Martin", "Bob", "34", "Commerce", 2021);
//		Stagiaire stagiaire31 = new Stagiaire("Bernard", "Celine", "13", "Gestion", 2023);
//		Stagiaire stagiaire4 = new Stagiaire("Alonso", "David", "69", "Marketing", 2020);
//		Stagiaire stagiaire5 = new Stagiaire("Zola", "Eve", "92", "Droit", 2022);
//
//		// Ajout des stagiaires dans l'annuaire
//		annuaire.ajouterStagiaire(new Noeud(stagiaire11, -1, -1));
//		annuaire.ajouterStagiaire(new Noeud(stagiaire28, -1, -1));
//		annuaire.ajouterStagiaire(new Noeud(stagiaire31, -1, -1));
//		annuaire.ajouterStagiaire(new Noeud(stagiaire4, -1, -1));
//		annuaire.ajouterStagiaire(new Noeud(stagiaire5, -1, -1));

		annuaire.modifierStagiaire(new Stagiaire ("POTIN", "Thomas", "75","ATOD 21", 2014), new Stagiaire ("POLINO", "TOMA", "75", "ATOD 21", 2023));
		
		List<Stagiaire> stagiaires = annuaire.afficherListeOrdreAlphabetique();

		for(Stagiaire stag : stagiaires) {
			System.out.println(stag);
		}
//		annuaire.creerPDF("src/main/resources/mesFichiers/ListeStagiaires.pdf");
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
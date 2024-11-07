package fr.isika.cda28.projet1.Annuaire;

import java.io.File;
import java.io.IOException;
// Sert à faire la transition entre front et fichier binaire
import java.io.RandomAccessFile;
import java.util.List;

public class Annuaire {
	// Attributs
	private RandomAccessFile raf;
	public Noeud noeud;

// Constructeur vide qui contient le raf initialisé
	public Annuaire() {
		try {
			File fichier = new File("src/mesFichiers/ListeStagiaires.bin");
			raf = new RandomAccessFile(fichier, "rw");
		} catch (IOException e) {
			System.err.println("Le fichier binaire n'a pas été ouvert");
			e.printStackTrace();
		}

	}

	// Getters et Setters

	public RandomAccessFile getRaf() {
		return raf;
	}

	// Méthodes

	public void ajouterStagiaire(Noeud stagiaire) throws IOException {

		if (raf.length() == 0) {
			raf.seek(0);
			stagiaire.ecrireNoeud(stagiaire,raf);
		} else {
			raf.seek(0);
			Noeud racine = stagiaire.lireNoeud(raf);
			racine.ajoutStagiaireRecursif(stagiaire, raf);
		}

	}

	public void close() {
		try {
			if (raf != null) {
				raf.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
//	public void ajouterStagiaire(Stagiaire nouveauStagiaire) {
//		if (nouveauStagiaire.getNom().compareTo(noeudActuel.getStagiaire().getNom()) < 0) {
//			// On va à droite
//			if (this.noeudDroit == null) {
//				// Il n'y a pas de noeud droit donc on écrit le nouveauStagiaire
//				this.noeudDroit = new Noeud(nouveauStagiaire, null, null);
//			} else {
//				this.noeudDroit.ajouterStagiaire(nouveauStagiaire, noeudActuel);
//			}
//
//		} else {
//			if (this.noeudGauche == null) {
//				// Il n'y a pas de noeud droit donc on écrit le nouveauStagiaire
//				this.noeudGauche = new Noeud(nouveauStagiaire, null, null);
//			} else {
//				this.noeudGauche.ajouterStagiaire(nouveauStagiaire, noeudActuel);
//			}
//		}
//	}

	
}

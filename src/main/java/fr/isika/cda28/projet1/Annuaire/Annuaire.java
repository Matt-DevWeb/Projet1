package fr.isika.cda28.projet1.Annuaire;

import java.io.File;
import java.io.IOException;
// Sert à faire la transition entre front et fichier binaire
import java.io.RandomAccessFile;

public class Annuaire {
	// Attributs
	private RandomAccessFile raf;

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
	public void positionCurseur() throws IOException {
		System.out.println(raf.length());

		long positionCurseur = raf.length() / 124;
		System.out.println(positionCurseur);
	}

	public void ajouterStagiaire(Noeud stagiaire) throws IOException {

		if (raf.length() == 0) {
			ecrireNoeud(stagiaire, -1, -1);
		} else {
			stagiaire.ajoutStagiaireRecursif(stagiaire, raf);
		}
		return;
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

	public void ecrireNoeud(Noeud stagiaire, int filsGauche, int filsDroit) throws IOException {
		raf.writeChars(stagiaire.getStagiaire().getNomLong());
		raf.writeChars(stagiaire.getStagiaire().getPrenomLong());
		raf.writeChars(stagiaire.getStagiaire().getDepartementLong());
		raf.writeChars(stagiaire.getStagiaire().getCursusLong());
		raf.writeInt(stagiaire.getStagiaire().getAnneePromo());
		raf.writeInt(filsGauche); // Indice du noeud gauche
		raf.writeInt(filsDroit); // Indice du noeud droit
		System.out.println("ecrireNoeoud renvoit : " + raf.getFilePointer());
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

	public Noeud lireNoeud(Noeud stagiaire, int filsGauche, int filsDroit) throws IOException {// Check si cest Noeud ou Void

		// Nom
		for (int i = 0; i < Stagiaire.TAILLE_MAX_NOM; i++) {
			stagiaire.getStagiaire().setNom(stagiaire.getStagiaire().getNom() + raf.readChar());
		}
		stagiaire.getStagiaire().getNom().trim();
		System.out.println("Nom : " + stagiaire.getStagiaire().getNom());

		// Prenom
		for (int i = 0; i < Stagiaire.TAILLE_MAX_PRENOM; i++) {
			stagiaire.getStagiaire().setPrenom(stagiaire.getStagiaire().getPrenom() + raf.readChar());

		}
		stagiaire.getStagiaire().getPrenom().trim();
		System.out.println("Prenom  : " + stagiaire.getStagiaire().getPrenom());

		// Departement
		for (int i = 0; i < Stagiaire.TAILLE_MAX_DEPARTEMENT; i++) {
			stagiaire.getStagiaire().setDepartement(stagiaire.getStagiaire().getDepartement() + raf.readChar());

		}
		stagiaire.getStagiaire().getDepartement().trim();
		System.out.println("Le departement est  : " + stagiaire.getStagiaire().getDepartement());

		// Cursus
		for (int i = 0; i < Stagiaire.TAILLE_MAX_CURSUS; i++) {
			stagiaire.getStagiaire().setCursus(stagiaire.getStagiaire().getCursus() + raf.readChar());

		}
		stagiaire.getStagiaire().getCursus().trim();
		System.out.println("Le cursus est  : " + stagiaire.getStagiaire().getCursus());

		// AnneePromo
		stagiaire.getStagiaire().setAnneePromo(raf.readInt());
		stagiaire.getStagiaire().getAnneePromo();
		System.out.println("L'annee de promo est  : " + stagiaire.getStagiaire().getAnneePromo());

		// FilsGauche
		filsGauche = (raf.readInt());
		System.out.println("Le fils gauche est  : " + filsGauche);

		// FilsDroit
		filsDroit = (raf.readInt());
		System.out.println("Le fils droit est  : " + filsDroit);
		
		return stagiaire;// voir pour le return
	}
}

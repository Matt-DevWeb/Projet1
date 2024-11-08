package fr.isika.cda28.projet1.Annuaire.Fonctionnalités;

import java.io.File;
import java.io.IOException;
// Sert à faire la transition entre front et fichier binaire
import java.io.RandomAccessFile;

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
			stagiaire.ecrireNoeud(stagiaire, raf);
		} else {
			raf.seek(0);
			Noeud racine = stagiaire.lireNoeud(raf);
			racine.ajoutStagiaireRecursif(stagiaire, raf);
		}

	}
	public Noeud rechercherStagiaire (String stagiaireARechercher) throws IOException {
		Noeud resultatRecherche = new Noeud();
		if (raf.length() == 0) {
			System.out.println(" Nous ne pouvons pas trouver de stagiaire, car celui-ci est vide");
		} else {
			raf.seek(0);
			Noeud racine = new Noeud();
			racine = racine.lireNoeud(raf);
			resultatRecherche= racine.rechercheNoeud(stagiaireARechercher, raf, 0);
		} return resultatRecherche;
	}

	public void supprimerStagiaire (Noeud stagiaireASupprimer) throws IOException {
		if (raf.length() == 0 ) {
			System.out.println("L'annuaire est vide ");
		} else {
			raf.seek(0);
			Noeud racine = stagiaireASupprimer.lireNoeud(raf);
			racine.supprimerNoeud(stagiaireASupprimer,raf, 0);
		}
	}
	
	public void afficherListeOrdreAlphabetique() throws IOException {
        if (raf.length() > 0) {
            raf.seek(0);  // Revenir au début du fichier pour lire la racine
            Noeud racine = new Noeud(new Stagiaire(), -1, -1);  // Initialiser un noeud pour la racine
            racine = racine.lireNoeud(raf);  // Lire la racine
            racine.listeOrdreAlphabetique(raf);  // Lancer la traversée In-Order
        } else {
            System.out.println("L'annuaire est vide.");
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

	@Override
	public String toString() {
		return "Annuaire [raf=" + raf + ", noeud=" + noeud + "]";
	}

}

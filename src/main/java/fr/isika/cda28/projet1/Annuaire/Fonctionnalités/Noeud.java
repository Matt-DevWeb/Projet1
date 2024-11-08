package fr.isika.cda28.projet1.Annuaire.Fonctionnalités;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Noeud {

	public final static int TAILLE_NOEUD_OCTET = Stagiaire.TAILLE_STAGIAIRE_OCTET + 4 + 4;

	// ATTRIBUTS
	private Stagiaire stagiaire;
	private int filsGauche;
	private int filsDroit;

	// CONSTRUCTEUR

	public Noeud(Stagiaire stagiaire, int filsGauche, int filsDroit) {
		super();
		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}

	public Noeud getRacine() {
		return this;
	}

	// GETTERS ET SETTERS
	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public int getNoeudGauche() {
		return filsGauche;
	}

	public void setNoeudGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public int getNoeudDroit() {
		return filsDroit;
	}

	public void setNoeudDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}

	@Override
	public String toString() {
		return "Noeud [stagiaire=" + stagiaire + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit + "]";
	}

	public void ecrireNoeud(Noeud stagiaire, RandomAccessFile raf) throws IOException {

		raf.writeChars(stagiaire.getStagiaire().getNomLong());
		raf.writeChars(stagiaire.getStagiaire().getPrenomLong());
		raf.writeChars(stagiaire.getStagiaire().getDepartementLong());
		raf.writeChars(stagiaire.getStagiaire().getCursusLong());
		raf.writeInt(stagiaire.getStagiaire().getAnneePromo());
		raf.writeInt(stagiaire.getNoeudGauche()); // Indice du noeud gauche
		raf.writeInt(stagiaire.getNoeudDroit()); // Indice du noeud droit

	}

	public static Noeud lireNoeud(RandomAccessFile raf) throws IOException {// Check si cest Noeud ou
		// Void
		Noeud noeudLu = new Noeud(new Stagiaire(), -1, -1);
// Nom
		for (int i = 0; i < Stagiaire.TAILLE_MAX_NOM; i++) {
			noeudLu.getStagiaire().setNom(noeudLu.getStagiaire().getNom() + raf.readChar());
		}
		noeudLu.getStagiaire().setNom(noeudLu.getStagiaire().getNom().trim());
//System.out.println("Nom : " + stagiaire.getStagiaire().getNom());

// Prenom
		for (int i = 0; i < Stagiaire.TAILLE_MAX_PRENOM; i++) {
			noeudLu.getStagiaire().setPrenom(noeudLu.getStagiaire().getPrenom() + raf.readChar());
			;
		}
		noeudLu.getStagiaire().setPrenom(noeudLu.getStagiaire().getPrenom().trim());

// Departement
		for (int i = 0; i < Stagiaire.TAILLE_MAX_DEPARTEMENT; i++) {
			noeudLu.getStagiaire().setDepartement(noeudLu.getStagiaire().getDepartement() + raf.readChar());

		}
		noeudLu.getStagiaire().setDepartement(noeudLu.getStagiaire().getDepartement().trim());

// Cursus
		for (int i = 0; i < Stagiaire.TAILLE_MAX_CURSUS; i++) {
			noeudLu.getStagiaire().setCursus(noeudLu.getStagiaire().getCursus() + raf.readChar());
		}
		noeudLu.getStagiaire().setCursus(noeudLu.getStagiaire().getCursus().trim());

// AnneePromo
		noeudLu.getStagiaire().setAnneePromo(raf.readInt());
//System.out.println("L'annee de promo est  : " + stagiaire.getStagiaire().getAnneePromo());

// FilsGauche
		noeudLu.setNoeudGauche(raf.readInt());
//System.out.println("Le fils gauche est  : " + filsGauche);

// FilsDroit
		noeudLu.setNoeudDroit(raf.readInt());

		return noeudLu;// voir pour le return

	}

	public void ajoutStagiaireRecursif(Noeud nouveauNoeud, RandomAccessFile raf) throws IOException {
		if (this.stagiaire.getNomLong().compareTo(nouveauNoeud.getStagiaire().getNomLong()) > 0) {

			if (filsGauche == -1) {
				// raf.seek(raf.length());
				// System.out.println("1 FG : " + raf.getFilePointer());
				raf.seek(raf.getFilePointer() - 8);// on repositionne pour ecrire l'index
				// System.out.println("après le -8: " +raf.getFilePointer());
				filsGauche = (int) raf.length() / TAILLE_NOEUD_OCTET;
				raf.writeInt(filsGauche); // on ecrit l'index dans le parent
				raf.seek(raf.length());// retour a la fin
				// System.out.println("2 FG: " + raf.getFilePointer());
				ecrireNoeud(nouveauNoeud, raf);// ecrire le nouveau noeud (fils gauche)
				// System.out.println("3 FG: " + raf.getFilePointer());
			} else {
				raf.seek(filsGauche * TAILLE_NOEUD_OCTET);
				// System.out.println("4 FG: " + raf.getFilePointer());
//				Noeud noeudFilsGauche = new Noeud(new Stagiaire(), -1, -1);
				Noeud noeudFilsGauche = lireNoeud(raf);
				noeudFilsGauche.ajoutStagiaireRecursif(nouveauNoeud, raf);
			}

		} else {
			if (filsDroit == -1) {
//				raf.seek(raf.length());
//				System.out.println("1 FD: " + raf.getFilePointer());
				raf.seek(raf.getFilePointer() - 4);
				// System.out.println("après le -4: " +raf.getFilePointer());
				filsDroit = (int) raf.length() / TAILLE_NOEUD_OCTET;
				raf.writeInt(filsDroit);
				raf.seek(raf.length());
				// System.out.println("2 FD: " + raf.getFilePointer());
				ecrireNoeud(nouveauNoeud, raf);
				// System.out.println("3 FD: " + raf.getFilePointer());
			} else {
				raf.seek(filsDroit * TAILLE_NOEUD_OCTET);
				// System.out.println("4 FD: " + raf.getFilePointer());
//				Noeud noeudFilsDroit = new Noeud(new Stagiaire(), -1, -1);
				Noeud noeudFilsDroit = lireNoeud(raf);
				noeudFilsDroit.ajoutStagiaireRecursif(nouveauNoeud, raf);
			}
		}

	}

	public void listeOrdreAlphabetique(RandomAccessFile raf) throws IOException {
		if (filsGauche != -1) { // Si un fils gauche existe
			raf.seek(filsGauche * TAILLE_NOEUD_OCTET); // Accède à la position du fils gauche
			Noeud noeudFilsGauche = lireNoeud(raf); // Lit le noeud gauche
			noeudFilsGauche.listeOrdreAlphabetique(raf); // Parcours récursif du sous-arbre gauche
		}

		// Affiche le noeud courant (la racine dans ce contexte)
		System.out.println(this.getStagiaire());

		if (filsDroit != -1) { // Si un fils droit existe
			raf.seek(filsDroit * TAILLE_NOEUD_OCTET); // Accède à la position du fils droit
			Noeud noeudFilsDroit = lireNoeud(raf); // Lit le noeud droit
			noeudFilsDroit.listeOrdreAlphabetique(raf); // Parcours récursif du sous-arbre droit
		}
	}

	public static boolean rechercheNoeud(String valeurRechercher, RandomAccessFile raf, long position)
			throws IOException {
		raf.seek(position);
		Noeud nouveauNoeud = lireNoeud(raf);

		if (nouveauNoeud.getStagiaire().getNomLong().trim().equalsIgnoreCase(valeurRechercher)) {
			return true;
		}

		// Vérifie Fils Gauche

		if (nouveauNoeud.getNoeudGauche() != -1) {
			if (rechercheNoeud(valeurRechercher, raf, nouveauNoeud.getNoeudGauche() * TAILLE_NOEUD_OCTET)) {
				return true;
			}
		}

		// Vérifie Fils Droit

		if (nouveauNoeud.getNoeudDroit() != -1) {
			return rechercheNoeud(valeurRechercher, raf, nouveauNoeud.getNoeudDroit() * TAILLE_NOEUD_OCTET);
		}

		return false;
	}

	public void supprimerStagiaire(Noeud noeudASupprimer, RandomAccessFile raf) throws IOException {
		// Cas 1 : Pas d'enfant (feuille)
		if (noeudASupprimer.getNoeudGauche() == -1 && noeudASupprimer.getNoeudDroit() == -1) {
			// Marquer le noeud comme supprimé ou gérer la suppression
			noeudASupprimer.setStagiaire(null); // Marquer comme supprimé
		}
		// Cas 2 : Un seul enfant
		else if (noeudASupprimer.getNoeudGauche() == -1 || noeudASupprimer.getNoeudDroit() == -1) {
			int enfant;
			if (noeudASupprimer.getNoeudGauche() != -1) {
			    enfant = noeudASupprimer.getNoeudGauche();
			} else {
			    enfant = noeudASupprimer.getNoeudDroit();
			}

		raf.seek(enfant * TAILLE_NOEUD_OCTET);
		Noeud enfantNoeud = lireNoeud(raf);
		noeudASupprimer.setStagiaire(enfantNoeud.getStagiaire());
		noeudASupprimer.setNoeudGauche(enfantNoeud.getNoeudGauche());
		noeudASupprimer.setNoeudDroit(enfantNoeud.getNoeudDroit());
	}
	// Cas 3 : Deux enfants
	else

	{
		Noeud successeur = noeudASupprimer.noeudSuccesseur(raf);
		if (successeur != null) {
			noeudASupprimer.setStagiaire(successeur.getStagiaire());
			// Supprimer le successeur dans le sous-arbre droit
			raf.seek(filsDroit * TAILLE_NOEUD_OCTET);
			Noeud noeudDroit = lireNoeud(raf);
			noeudDroit.supprimerStagiaire(successeur, raf);
		}
	}
	}

	public Noeud noeudSuccesseur(RandomAccessFile raf) throws IOException {
		if (filsDroit != -1) {
			raf.seek(filsDroit * TAILLE_NOEUD_OCTET);
			Noeud noeudCourant = lireNoeud(raf);

			while (noeudCourant.getNoeudGauche() != -1) {
				raf.seek(noeudCourant.getNoeudGauche() * TAILLE_NOEUD_OCTET);
				noeudCourant = lireNoeud(raf);
			}
			return noeudCourant;
		}
		return null;
	}

}

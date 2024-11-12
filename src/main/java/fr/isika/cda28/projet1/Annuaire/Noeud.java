package fr.isika.cda28.projet1.Annuaire;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

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

	public Noeud() {
		this.stagiaire = new Stagiaire();
		this.filsGauche = -1;
		this.filsDroit = -1;
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

	public int getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public int getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}

	@Override
	public String toString() {
		return "Stagiaire= " + stagiaire + ", filsGauche= " + filsGauche + ", filsDroit= " + filsDroit;
	}

	public void ecrireNoeud(Noeud stagiaire, RandomAccessFile raf) throws IOException {

		raf.writeChars(stagiaire.getStagiaire().getNomLong());
		raf.writeChars(stagiaire.getStagiaire().getPrenomLong());
		raf.writeChars(stagiaire.getStagiaire().getDepartementLong());
		raf.writeChars(stagiaire.getStagiaire().getCursusLong());
		raf.writeInt(stagiaire.getStagiaire().getAnneePromo());
		raf.writeInt(stagiaire.getFilsGauche()); // Indice du noeud gauche
		raf.writeInt(stagiaire.getFilsDroit()); // Indice du noeud droit

	}

	public Noeud lireNoeud(RandomAccessFile raf) throws IOException {// Check si cest Noeud ou
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
		noeudLu.setFilsGauche(raf.readInt());
//System.out.println("Le fils gauche est  : " + filsGauche);

// FilsDroit
		noeudLu.setFilsDroit(raf.readInt());

		return noeudLu;// voir pour le return

	}

	public void ajoutStagiaireRecursif(Noeud nouveauNoeud, RandomAccessFile raf) throws IOException {
		if (this.stagiaire.compareTo(nouveauNoeud.getStagiaire()) > 0) {

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

	public void listeOrdreAlphabetique(RandomAccessFile raf, List<Stagiaire> listeTriee) throws IOException {
		if (filsGauche != -1) { // Si un fils gauche existe
			raf.seek(filsGauche * TAILLE_NOEUD_OCTET); // Accède à la position du fils gauche
			Noeud noeudFilsGauche = lireNoeud(raf); // Lit le noeud gauche
			noeudFilsGauche.listeOrdreAlphabetique(raf, listeTriee); // Parcours récursif du sous-arbre gauche
		}

		// Affiche le noeud courant (la racine dans ce contexte)
		listeTriee.add(this.getStagiaire());

		if (filsDroit != -1) { // Si un fils droit existe
			raf.seek(filsDroit * TAILLE_NOEUD_OCTET); // Accède à la position du fils droit
			Noeud noeudFilsDroit = lireNoeud(raf); // Lit le noeud droit
			noeudFilsDroit.listeOrdreAlphabetique(raf, listeTriee); // Parcours récursif du sous-arbre droit
		}
	}

//	public Noeud rechercheNoeud(String valeurRechercher, RandomAccessFile raf, long position)
//			throws IOException {
//		raf.seek(position);
//		Noeud nouveauNoeud = lireNoeud(raf);
//
//		if (nouveauNoeud.getStagiaire().getNomLong().trim().equalsIgnoreCase(valeurRechercher)) {
//			return nouveauNoeud;
//		}
//
//		// Vérifie Fils Gauche
//
//		if (nouveauNoeud.getFilsGauche() != -1) {
//			if (rechercheNoeud(valeurRechercher, raf, nouveauNoeud.getFilsGauche() * TAILLE_NOEUD_OCTET) != null) {
//				return nouveauNoeud;
//			}
//		}
//
//		// Vérifie Fils Droit
//
//		if (nouveauNoeud.getFilsDroit() != -1) {
//			return rechercheNoeud(valeurRechercher, raf, nouveauNoeud.getFilsDroit() * TAILLE_NOEUD_OCTET);
//		}
//
//		return null;
//	}

	public Noeud rechercheNoeud(Stagiaire stagiaireRecherche, RandomAccessFile raf, long position) throws IOException {
		raf.seek(position);
		Noeud nouveauNoeud = lireNoeud(raf);

		// Utilisation de compareTo pour vérifier l'égalité avec l'objet recherché
		if (nouveauNoeud.getStagiaire().compareTo(stagiaireRecherche) == 0) {
			return nouveauNoeud;
		}

		// Recherche dans le sous-arbre gauche si le stagiaire recherché est "plus
		// petit"
		if (nouveauNoeud.getStagiaire().compareTo(stagiaireRecherche) > 0 && nouveauNoeud.getFilsGauche() != -1) {
			return rechercheNoeud(stagiaireRecherche, raf, nouveauNoeud.getFilsGauche() * TAILLE_NOEUD_OCTET);
		}

		// Recherche dans le sous-arbre droit si le stagiaire recherché est "plus grand"
		if (nouveauNoeud.getStagiaire().compareTo(stagiaireRecherche) < 0 && nouveauNoeud.getFilsDroit() != -1) {
			return rechercheNoeud(stagiaireRecherche, raf, nouveauNoeud.getFilsDroit() * TAILLE_NOEUD_OCTET);
		}

		return null;
	}

	public void supprimerRacine(RandomAccessFile raf, int indexCourant) throws IOException {
		// Recherche du successeur du noeud courant
		Noeud noeudSuccesseur = this.noeudSuccesseur(raf);

		// Positionnement du curseur sur la racine dans le fichier
		raf.seek(indexCourant * TAILLE_NOEUD_OCTET);

		// On remplace les fils du successeur par ceux du noeud courant (racine)
		noeudSuccesseur.setFilsGauche(filsGauche);
		noeudSuccesseur.setFilsDroit(filsDroit);

		// On écrit le successeur modifié dans le fichier
		ecrireNoeud(noeudSuccesseur, raf);

		// On cherche à supprimer le successeur dans le sous-arbre droit de la racine
		raf.seek(filsDroit * TAILLE_NOEUD_OCTET);
		Noeud noeudDroit = lireNoeud(raf);

		// Suppression récursive du successeur dans le sous-arbre droit
		noeudDroit.supprimerNoeud(noeudSuccesseur, raf, indexCourant);

		// Affichage du successeur qui remplace la racine
		System.out.println(this.noeudSuccesseur(raf) + " est le successeur de " + this);
	}

	public void supprimerNoeud(Noeud noeudASupprimer, RandomAccessFile raf, int indexParent) throws IOException {
		// Calcul de l'index courant du noeud dans le fichier
		int indexCourant = (int) (raf.getFilePointer() - TAILLE_NOEUD_OCTET) / TAILLE_NOEUD_OCTET;

		// Si le stagiaire du noeud courant correspond à celui à supprimer
		if (this.getStagiaire().compareTo(noeudASupprimer.getStagiaire()) == 0) {
			// Cas 1 : Le noeud à supprimer est une feuille (sans enfants)
			if (this.filsDroit == -1 && this.filsGauche == -1) {
				raf.seek(indexParent * TAILLE_NOEUD_OCTET);
				Noeud noeudParent = lireNoeud(raf);

				// Mise à jour de l'index du parent pour supprimer le noeud
				if (noeudParent.getStagiaire().compareTo(noeudASupprimer.getStagiaire()) < 0) {
					raf.seek(raf.getFilePointer() - 4);
					raf.writeInt(-1); // Mettre à jour le fils droit du parent à -1
				} else {
					raf.seek(raf.getFilePointer() - 8);
					raf.writeInt(-1); // Mettre à jour le fils gauche du parent à -1
				}
			}
			// Cas 2 : Le noeud à supprimer a un seul enfant
			else if (this.filsDroit == -1 || this.filsGauche == -1) {
				raf.seek(indexParent * TAILLE_NOEUD_OCTET);
				Noeud noeudParent = lireNoeud(raf);

				// Si le noeud a un fils à gauche ou à droite, on l'affecte au parent
				if (noeudParent.getStagiaire().compareTo(noeudASupprimer.getStagiaire()) < 0) {
					raf.seek(raf.getFilePointer() - 4);
					if (this.filsDroit == -1) {
						raf.writeInt(this.filsGauche); // Remplacer par le fils gauche
					} else {
						raf.writeInt(this.filsDroit); // Remplacer par le fils droit
					}
				} else {
					raf.seek(raf.getFilePointer() - 8);
					if (this.filsDroit == -1) {
						raf.writeInt(this.filsGauche); // Remplacer par le fils gauche
					} else {
						raf.writeInt(this.filsDroit); // Remplacer par le fils droit
					}
				}
			}
			// Cas 3 : Le noeud à supprimer a deux enfants
			else {
				this.supprimerRacine(raf, indexCourant); // Appel à la méthode supprimerRacine pour gérer ce cas
			}
		}
		// Cas où on cherche à supprimer le noeud dans le sous-arbre droit
		else if (this.getStagiaire().compareTo(noeudASupprimer.getStagiaire()) < 0) {
			raf.seek(filsDroit * TAILLE_NOEUD_OCTET);
			Noeud noeudDroit = lireNoeud(raf);
			noeudDroit.supprimerNoeud(noeudASupprimer, raf, indexCourant);
		}
		// Cas où on cherche à supprimer le noeud dans le sous-arbre gauche
		else {
			raf.seek(filsGauche * TAILLE_NOEUD_OCTET);
			Noeud noeudGauche = lireNoeud(raf);
			noeudGauche.supprimerNoeud(noeudASupprimer, raf, indexCourant);
		}
	}

	public Noeud noeudSuccesseur(RandomAccessFile raf) throws IOException {
		if (filsDroit != -1) {
			raf.seek(filsDroit * TAILLE_NOEUD_OCTET);
			Noeud noeudCourant = lireNoeud(raf);

			// Recherche du plus petit noeud dans le sous-arbre droit
			while (noeudCourant.getFilsGauche() != -1) {
				raf.seek(noeudCourant.getFilsGauche() * TAILLE_NOEUD_OCTET);
				noeudCourant = lireNoeud(raf);
			}
			return noeudCourant;
		}
		return null; // Si le noeud n'a pas de fils droit, il n'a pas de successeur
	}

}

package fr.isika.cda28.projet1.Annuaire.BackEnd;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * La classe Noeud représente un nœud dans un arbre binaire pour stocker les
 * informations d'un stagiaire et ses liens vers les nœuds enfants gauche et
 * droit. Cette classe permet de manipuler ces nœuds dans un fichier binaire.
 * 
 * <p>
 * Chaque nœud contient un objet Stagiaire et les indices des fils gauche et
 * droit dans le fichier binaire. Elle fournit des méthodes pour ajouter,
 * rechercher, lire, écrire et supprimer des nœuds, ainsi que pour organiser les
 * stagiaires en ordre alphabétique.
 */

public class Noeud {

	/**
	 * Constants
	 * 
	 * @constant TAILLE_NOEUD_OCTET Description: Taille en octets d'un noeud.
	 */
	public final static int TAILLE_NOEUD_OCTET = Stagiaire.TAILLE_STAGIAIRE_OCTET + 4 + 4;

	// Attributs
	private Stagiaire stagiaire;
	private int filsGauche;
	private int filsDroit;

	/**
	 * Constructors
	 * 
	 * @constructor Noeud(Stagiaire stagiaire, int filsGauche, int filsDroit)
	 *              Description: Constructeur de la classe Noeud avec des
	 *              paramètres.
	 * @param stagiaire  Le stagiaire du noeud.
	 * @param filsGauche L'index du fils gauche du noeud.
	 * @param filsDroit  L'index du fils droit du noeud.
	 */
	public Noeud(Stagiaire stagiaire, int filsGauche, int filsDroit) {
		super();
		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}

	/**
	 * @constructor Noeud() Description: Constructeur de la classe Noeud par défaut.
	 */
	public Noeud() {
		this.stagiaire = new Stagiaire();
		this.filsGauche = -1;
		this.filsDroit = -1;
	}

	/**
	 * Methods
	 * 
	 * @method getRacine() Description: Obtient la racine du noeud.
	 * @return La racine du noeud.
	 */
	public Noeud getRacine() {
		return this;
	}

	/**
	 * @method getStagiaire() Description: Obtient le stagiaire contenu dans le
	 *         noeud.
	 * @return Le stagiaire du noeud.
	 */
	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	/**
	 * @method setStagiaire(Stagiaire stagiaire) Description: Définit le stagiaire
	 *         du noeud.
	 * @param stagiaire Le nouveau stagiaire.
	 */
	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	/**
	 * @method getFilsGauche() Description: Obtient l'index du fils gauche du noeud.
	 * @return L'index du fils gauche.
	 */
	public int getFilsGauche() {
		return filsGauche;
	}

	/**
	 * @method setFilsGauche(int filsGauche) Description: Définit l'index du fils
	 *         gauche du noeud.
	 * @param filsGauche Le nouvel index du fils gauche.
	 */
	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	/**
	 * @method getFilsDroit() Description: Obtient l'index du fils droit du noeud.
	 * @return L'index du fils droit.
	 */
	public int getFilsDroit() {
		return filsDroit;
	}

	/**
	 * @method setFilsDroit(int filsDroit) Description: Définit l'index du fils
	 *         droit du noeud.
	 * @param filsDroit Le nouvel index du fils droit.
	 */
	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}

	// METHODES*************************************************************************

	/**
	 * @method toString() Description: Retourne une représentation textuelle du
	 *         noeud.
	 * @return La représentation textuelle du noeud.
	 */
	@Override
	public String toString() {
		return "Stagiaire= " + stagiaire + ", filsGauche= " + filsGauche + ", filsDroit= " + filsDroit;
	}

	/**
	 * @method ecrireNoeud(Noeud stagiaire, RandomAccessFile raf) Description: Écrit
	 *         un noeud dans le fichier binaire.
	 * @param stagiaire Le noeud à écrire.
	 * @param raf       Le RandomAccessFile où écrire le noeud.
	 * @throws IOException
	 */
	public void ecrireNoeud(Noeud stagiaire, RandomAccessFile raf) throws IOException {
		raf.writeChars(stagiaire.getStagiaire().getNomLong().toUpperCase());
		raf.writeChars(stagiaire.getStagiaire().getPrenomLong());
		raf.writeChars(stagiaire.getStagiaire().getDepartementLong());
		raf.writeChars(stagiaire.getStagiaire().getCursusLong());
		raf.writeInt(stagiaire.getStagiaire().getAnneePromo());
		raf.writeInt(stagiaire.getFilsGauche()); // Indice du noeud gauche
		raf.writeInt(stagiaire.getFilsDroit()); // Indice du noeud droit
	}

	/**
	 * @method lireNoeud(RandomAccessFile raf) Description: Lit un noeud depuis le
	 *         fichier binaire.
	 * @param raf Le RandomAccessFile où lire le noeud.
	 * @return Le noeud lu.
	 * @throws IOException
	 */
	public Noeud lireNoeud(RandomAccessFile raf) throws IOException {

		// Check si cest Noeud ou Void
		Noeud noeudLu = new Noeud(new Stagiaire(), -1, -1);

		// Nom
		for (int i = 0; i < Stagiaire.TAILLE_MAX_NOM; i++) {
			noeudLu.getStagiaire().setNom(noeudLu.getStagiaire().getNom() + raf.readChar());
		}
		noeudLu.getStagiaire().setNom(noeudLu.getStagiaire().getNom().trim());

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

		// FilsGauche
		noeudLu.setFilsGauche(raf.readInt());

		// FilsDroit
		noeudLu.setFilsDroit(raf.readInt());

		return noeudLu;
	}

	/**
	 * @method ajoutStagiaireRecursif(Noeud nouveauNoeud, RandomAccessFile raf)
	 *         Description: Ajoute un stagiaire de manière récursive dans l'arbre.
	 * @param nouveauNoeud Le nouveau noeud à ajouter.
	 * @param raf          Le RandomAccessFile utilisé.
	 * @throws IOException
	 */
	public void ajoutStagiaireRecursif(Noeud nouveauNoeud, RandomAccessFile raf) throws IOException {
		if (this.stagiaire.compareTo(nouveauNoeud.getStagiaire()) > 0) {

			if (filsGauche == -1) {

				raf.seek(raf.getFilePointer() - 8);// on repositionne pour ecrire l'index

				filsGauche = (int) raf.length() / TAILLE_NOEUD_OCTET;
				raf.writeInt(filsGauche); // on ecrit l'index dans le parent
				raf.seek(raf.length());// retour a la fin

				ecrireNoeud(nouveauNoeud, raf);// ecrire le nouveau noeud (fils gauche)

			} else {
				raf.seek(filsGauche * TAILLE_NOEUD_OCTET);

				Noeud noeudFilsGauche = lireNoeud(raf);
				noeudFilsGauche.ajoutStagiaireRecursif(nouveauNoeud, raf);
			}

		} else {
			if (filsDroit == -1) {

				raf.seek(raf.getFilePointer() - 4);
				filsDroit = (int) raf.length() / TAILLE_NOEUD_OCTET;
				raf.writeInt(filsDroit);
				raf.seek(raf.length());
				ecrireNoeud(nouveauNoeud, raf);

			} else {
				raf.seek(filsDroit * TAILLE_NOEUD_OCTET);
				Noeud noeudFilsDroit = lireNoeud(raf);
				noeudFilsDroit.ajoutStagiaireRecursif(nouveauNoeud, raf);
			}
		}
	}

	/**
	 * Remplit une liste des stagiaires en ordre alphabétique.
	 * 
	 * @param raf        le fichier binaire contenant les stagiaires
	 * @param listeTriee la liste dans laquelle ajouter les stagiaires triés
	 * @throws IOException si une erreur d'entrée/sortie se produit
	 */
	public void listeOrdreAlphabetique(RandomAccessFile raf, List<Stagiaire> listeTriee) throws IOException {

		// Si un fils gauche existe
		if (filsGauche != -1) {
			raf.seek(filsGauche * TAILLE_NOEUD_OCTET); // Accède à la position du fils gauche
			Noeud noeudFilsGauche = lireNoeud(raf); // Lit le noeud gauche
			noeudFilsGauche.listeOrdreAlphabetique(raf, listeTriee); // Parcours récursif du sous-arbre gauche
		}

		// Affiche le noeud courant (la racine dans ce contexte)
		listeTriee.add(this.getStagiaire());

		// Si un fils droit existe
		if (filsDroit != -1) {
			raf.seek(filsDroit * TAILLE_NOEUD_OCTET); // Accède à la position du fils droit
			Noeud noeudFilsDroit = lireNoeud(raf); // Lit le noeud droit
			noeudFilsDroit.listeOrdreAlphabetique(raf, listeTriee); // Parcours récursif du sous-arbre droit
		}
	}

	/**
	 * Recherche un nœud dans l'arbre à partir d'un stagiaire donné.
	 * 
	 * @param stagiaireRecherche le stagiaire à rechercher
	 * @param raf                le fichier binaire où rechercher
	 * @param position           la position dans le fichier à partir de laquelle
	 *                           commencer
	 * @return le nœud correspondant au stagiaire recherché, ou null si non trouvé
	 * @throws IOException si une erreur d'entrée/sortie se produit
	 */
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

	/**
	 * Supprime la racine de l'arbre et réorganise l'arbre pour préserver sa
	 * structure.
	 * 
	 * @param raf          le fichier binaire où supprimer la racine
	 * @param indexCourant l'index courant dans le fichier
	 * @throws IOException si une erreur d'entrée/sortie se produit
	 */
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

	}

	/**
	 * Supprime un nœud spécifique de l'arbre et réorganise l'arbre pour préserver
	 * sa structure.
	 * 
	 * @param noeudASupprimer le nœud à supprimer
	 * @param raf             le fichier binaire où effectuer la suppression
	 * @param indexParent     l'index du parent du nœud
	 * @throws IOException si une erreur d'entrée/sortie se produit
	 */
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
				// Appel à la méthode supprimerRacine pour gérer ce cas
				this.supprimerRacine(raf, indexCourant);
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

	/**
	 * Recherche et retourne le nœud successeur dans l'arbre, basé sur l'ordre
	 * alphabétique.
	 * 
	 * @param raf le fichier binaire où rechercher le successeur
	 * @return le nœud successeur, ou null s'il n'y a pas de successeur
	 * @throws IOException si une erreur d'entrée/sortie se produit
	 */
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
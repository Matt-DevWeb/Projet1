package fr.isika.cda28.projet1.Annuaire.BackEnd;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * Class Noeud
 * javadoc de la classe Noeud
 */

public class Noeud {

	/**
     * Constants
     * @constant TAILLE_NOEUD_OCTET
     * Description: Taille en octets d'un noeud.
     */
	public final static int TAILLE_NOEUD_OCTET = Stagiaire.TAILLE_STAGIAIRE_OCTET + 4 + 4;
	
	//Attributs
	private Stagiaire stagiaire;
	private int filsGauche;
	private int filsDroit;

	/**
     * Constructors
     * @constructor Noeud(Stagiaire stagiaire, int filsGauche, int filsDroit)
     * Description: Constructeur de la classe Noeud avec des paramètres.
     * @param stagiaire Le stagiaire du noeud.
     * @param filsGauche L'index du fils gauche du noeud.
     * @param filsDroit L'index du fils droit du noeud.
     */
	public Noeud(Stagiaire stagiaire, int filsGauche, int filsDroit) {
		super();
		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}

	/**
     * @constructor Noeud()
     * Description: Constructeur de la classe Noeud par défaut.
     */
	public Noeud() {
		this.stagiaire = new Stagiaire();
		this.filsGauche = -1;
		this.filsDroit = -1;
	}

    /**
     * Methods
     * @method getRacine()
     * Description: Obtient la racine du noeud.
     * @return La racine du noeud.
     */
	public Noeud getRacine() {
		return this;
	}
	
	/**
     * @method getStagiaire()
     * Description: Obtient le stagiaire contenu dans le noeud.
     * @return Le stagiaire du noeud.
     */
	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	/**
     * @method setStagiaire(Stagiaire stagiaire)
     * Description: Définit le stagiaire du noeud.
     * @param stagiaire Le nouveau stagiaire.
     */
	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	 /**
     * @method getFilsGauche()
     * Description: Obtient l'index du fils gauche du noeud.
     * @return L'index du fils gauche.
     */
	public int getFilsGauche() {
		return filsGauche;
	}

	 /**
     * @method setFilsGauche(int filsGauche)
     * Description: Définit l'index du fils gauche du noeud.
     * @param filsGauche Le nouvel index du fils gauche.
     */
	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	/**
     * @method getFilsDroit()
     * Description: Obtient l'index du fils droit du noeud.
     * @return L'index du fils droit.
     */
	public int getFilsDroit() {
		return filsDroit;
	}

	 /**
     * @method setFilsDroit(int filsDroit)
     * Description: Définit l'index du fils droit du noeud.
     * @param filsDroit Le nouvel index du fils droit.
     */
	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}
	
	//METHODES*************************************************************************
	
	/**
     * @method toString()
     * Description: Retourne une représentation textuelle du noeud.
     * @return La représentation textuelle du noeud.
     */
	@Override
	public String toString() {
		return "Stagiaire= " + stagiaire + ", filsGauche= " + filsGauche + ", filsDroit= " + filsDroit;
	}

	/**
     * @method ecrireNoeud(Noeud stagiaire, RandomAccessFile raf)
     * Description: Écrit un noeud dans le fichier binaire.
     * @param stagiaire Le noeud à écrire.
     * @param raf Le RandomAccessFile où écrire le noeud.
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
     * @method lireNoeud(RandomAccessFile raf)
     * Description: Lit un noeud depuis le fichier binaire.
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
     * Description: Ajoute un stagiaire de manière récursive dans l'arbre.
     * @param nouveauNoeud Le nouveau noeud à ajouter.
     * @param raf Le RandomAccessFile utilisé.
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

	// Méthode pour lire par ordre alphabétique
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


	// Méthode pour rechercher un noeud
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

	// Méthode pour supprimer une racine
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

	// Méthode pour supprimer un noeud
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

	// Méthode pour rechercher le noeud Successeur
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
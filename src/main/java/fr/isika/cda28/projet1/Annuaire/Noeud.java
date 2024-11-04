package fr.isika.cda28.projet1.Annuaire;

public class Noeud {

	// ATTRIBUTS
	private Stagiaire stagiaire;
	private Noeud noeudGauche;
	private Noeud noeudDroit;

	private Noeud racine;

	// CONSTRUCTEUR
	public Noeud(Stagiaire stagiaire) {

		this.stagiaire = stagiaire;
		this.noeudGauche = null;
		this.noeudDroit = null;
	}

	public Noeud() {
		this.racine = null;
	}

// GETTERS ET SETTERS
	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Noeud getNoeudGauche() {
		return noeudGauche;
	}

	public void setNoeudGauche(Noeud noeudGauche) {
		this.noeudGauche = noeudGauche;
	}

	public Noeud getNoeudDroit() {
		return noeudDroit;
	}

	public void setNoeudDroit(Noeud noeudDroit) {
		this.noeudDroit = noeudDroit;
	}

	// METHODES AJOUTER/ SUPPRIMER/ METTRE A JOUR/ ....

	public void ajouterRacine(Stagiaire stagiaire) {
		if (racine == null) {
			racine = new Noeud(stagiaire);
		} else {
			racine.ajouterStagiaire(stagiaire);
		}
	}

//	public Noeud supprimerRacine(Stagiaire stagiaire) {
//		if (racine != null) {
//
//			racine.supprimerStagiaire(stagiaire);
//		}
//	}
//
//	public void ajouterStagiaire(Stagiaire nouveauStagiaire) {
//		if (nouveauStagiaire) {
//			nouvelleValeur = new Noeud();
//
//		}
//		return racine;
//	}
//
//}

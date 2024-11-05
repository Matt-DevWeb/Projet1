package fr.isika.cda28.projet1.Annuaire;

public class Noeud {

	// ATTRIBUTS
	private Stagiaire stagiaire;
	private Noeud noeudGauche;
	private Noeud noeudDroit;

	// CONSTRUCTEUR
	public Noeud(Stagiaire stagiaire) {
		super();
		this.stagiaire = stagiaire;
		this.noeudGauche = null;
		this.noeudDroit = null;
	}

	public Noeud(Stagiaire stagiaire, Noeud noeudGauche, Noeud noeudDroit) {
		super();
		this.stagiaire = stagiaire;
		this.noeudGauche = noeudGauche;
		this.noeudDroit = noeudDroit;
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

	// Méthodes

	public void ajouterStagiaire(Stagiaire nouveauStagiaire, Noeud noeudActuel) {
		if (nouveauStagiaire.getNom().compareTo(noeudActuel.getStagiaire().getNom()) < 0) {
			// On va à droite
			if (this.noeudDroit == null) {
				// Il n'y a pas de noeud droit donc on écrit le nouveauStagiaire
				this.noeudDroit = new Noeud(nouveauStagiaire, null, null);
			} else {
				this.noeudDroit.ajouterStagiaire(nouveauStagiaire, noeudActuel);
			}

		} else {
			if (this.noeudGauche == null) {
				// Il n'y a pas de noeud droit donc on écrit le nouveauStagiaire
				this.noeudGauche = new Noeud(nouveauStagiaire, null, null);
			} else {
				this.noeudGauche.ajouterStagiaire(nouveauStagiaire, noeudActuel);
			}
		}
	}
}

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

	
	

}


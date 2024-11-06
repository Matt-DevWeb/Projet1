package fr.isika.cda28.projet1.Annuaire;

public class Noeud {

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
// Appeller des méthodes recursives 
}

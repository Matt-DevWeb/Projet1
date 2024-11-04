package fr.isika.cda28.projet1.Annuaire;

public class Stagiaire {

	// Attributs de la classe
	String nom;
	String prenom;
	String departement;
	String cursus;
	int anneePromo;

	// Constructor

	public Stagiaire() {
		super();
	}

	public Stagiaire(String nom, String prenom, String departement, String cursus, int anneePromo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cursus = cursus;
		this.anneePromo = anneePromo;
		this.departement = departement;
	}

	// GETTERS ET SETTERS
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCursus() {
		return cursus;
	}

	public void setCursus(String cursus) {
		this.cursus = cursus;
	}

	public int getAnneePromo() {
		return anneePromo;
	}

	public void setAnneePromo(int anneePromo) {
		this.anneePromo = anneePromo;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	// Methodes
	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", cursus=" + cursus + ", anneePromo=" + anneePromo
				+ ", departement=" + departement + "]";
	}

}

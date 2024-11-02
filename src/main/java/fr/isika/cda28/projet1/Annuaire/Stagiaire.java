package fr.isika.cda28.projet1.Annuaire;

import java.time.LocalDate;

public class Stagiaire {
	
	// Attributs de la classe
	String nom;
	String prenom;
	String cursus;
	LocalDate anneePromo;
	int departement;
	
	// Constructor
	public Stagiaire(String nom, String prenom, String cursus, LocalDate anneePromo, int departement) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cursus = cursus;
		this.anneePromo = anneePromo;
		this.departement = departement;
	}

	
	//GETTERS ET SETTERS
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

	public LocalDate getAnneePromo() {
		return anneePromo;
	}

	public void setAnneePromo(LocalDate anneePromo) {
		this.anneePromo = anneePromo;
	}

	public int getDepartement() {
		return departement;
	}

	public void setDepartement(int departement) {
		this.departement = departement;
	}

	// Methodes
	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", cursus=" + cursus + ", anneePromo=" + anneePromo
				+ ", departement=" + departement + "]";
	}
	
	
	

}

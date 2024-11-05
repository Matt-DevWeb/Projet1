package fr.isika.cda28.projet1.Annuaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Noeud extends Stagiaire {

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

	
	

}


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

	
	public void lectureStagiaire(){
	
	try

	{

		// Ouverture d'un fichier binaire pour écrire les informations des stagiaires
		RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/ListeStagiaires.bin", "rw");

		// lire le 1er stagiaire
		raf.seek(0);
		Stagiaire stagiaireRacine = new Stagiaire();

		// ou est mon curseur
		System.out.println(raf.getFilePointer());

		char[] nomChar = new char[Stagiaire.TAILLE_MAX_NOM];

		for (int i = 0; i < Stagiaire.TAILLE_MAX_NOM; i++) {
			//stagiaireRacine.setNom(stagiaireRacine.getNom() + raf.readChar());
//			stagiaireRacine.setNom(raf.readChar());
			nomChar[i] = raf.readChar();
		}
		
		String nom = new String(nomChar);

		System.out.println("Stagiaire Racine : " + nom);

		// Fermeture des flux de lecture et d'écriture
		raf.close();

	}catch(
	IOException e)
	{
		// Gestion des exceptions : affichage de la trace d'erreur
		e.printStackTrace();
	}

}}


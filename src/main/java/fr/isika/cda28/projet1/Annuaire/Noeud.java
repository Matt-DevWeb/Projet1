package fr.isika.cda28.projet1.Annuaire;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Noeud extends Annuaire {

	public final static int TAILLE_NOEUD_OCTET = Stagiaire.TAILLE_STAGIAIRE_OCTET + 4 + 4 ;   
	
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

//	// méthode d'ajout version Vincent
//	public void ajout(String nouvelleValeur) {
//		if (this.valeur.compareTo(nouvelleValeur) < 0) { // valeur < nouvelleValeur => on va à droite
//			if (this.filsDroit == null) { // il n'y a pas de fils gauche
//				this.filsDroit = new Noeud(nouvelleValeur, null, null);
//			} else { // s'il y a déjà un fils droit
//				this.filsDroit.ajout(nouvelleValeur);
//			}
//		} else { // valeur > nouvelleValeur => on va à gauche
//			if (this.filsGauche == null) { // il n'y a pas de fils gauche
//				this.filsGauche = new Noeud(nouvelleValeur, null, null);
//			} else { // s'il y a déjà un fils gauche
//				this.filsGauche.ajout(nouvelleValeur);
//			}
//		}
//	}
//	
	public void ajoutStagiaireRecursif(Noeud nouveauNoeud, RandomAccessFile raf) throws IOException {
		if (this.stagiaire.getNomLong().compareTo(nouveauNoeud.getStagiaire().getNomLong()) < 0) {
			if (this.filsGauche == -1) {
				raf.seek(raf.getFilePointer()-8);
				raf.writeInt((int)raf.length()/TAILLE_NOEUD_OCTET);
				raf.seek(raf.length());
				ecrireNoeud(nouveauNoeud, filsGauche, filsDroit);
			} else {
				raf.seek(this.filsGauche*TAILLE_NOEUD_OCTET);
				Noeud noeudFilsGauche = lireNoeud(raf, -1, -1);
				noeudFilsGauche.ajoutStagiaireRecursif(nouveauNoeud, raf);
			}

		} else {
			if (this.filsDroit == -1) {
				raf.seek(raf.getFilePointer()-4);
				raf.writeInt((int)raf.length()/TAILLE_NOEUD_OCTET);
				raf.seek(raf.length());
				ecrireNoeud(nouveauNoeud, filsGauche, filsDroit);
			} else {
				nouveauNoeud.ajoutStagiaireRecursif(nouveauNoeud, raf);
			}
		}

	}

}

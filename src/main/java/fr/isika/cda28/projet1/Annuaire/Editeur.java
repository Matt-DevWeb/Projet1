package fr.isika.cda28.projet1.Annuaire;

public class Editeur extends Utilisateurs {
	String nom;
	String prenom;

	public Editeur(String userID, String password, String nom, String prenom) {

		super(userID, password, _EDITEUR);
		this.nom= nom;
		this.prenom = nom;

	}

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

	@Override
	public String toString() {
		return "Editeur [nom=" + nom + ", prenom=" + prenom + "]";
	}

	// Attribus
	// Constructeur

}
package fr.isika.cda28.projet1.Annuaire;

public class Stagiaire {
	public final static int TAILLE_MAX_NOM = 20;
	public final static int TAILLE_MAX_PRENOM = 20;
	public final static int TAILLE_MAX_DEPARTEMENT = 4;
	public final static int TAILLE_MAX_CURSUS = 12;

	public final static int TAILLE_STAGIAIRE_OCTET = TAILLE_MAX_NOM * 2 + TAILLE_MAX_PRENOM * 2
			+ TAILLE_MAX_DEPARTEMENT * 2 + TAILLE_MAX_CURSUS * 2 + 4;

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

	

	public String getNomLong() {
		String nomLong = nom;
		if (nomLong.length() < TAILLE_MAX_NOM) {
			for (int i = nom.length(); i < TAILLE_MAX_NOM; i++) {
				nomLong += " ";
			}

		} else {
			nomLong = nomLong.substring(0, TAILLE_MAX_NOM);

		}
		return nomLong;
	}

	public String getPrenomLong() {
		String prenomLong = prenom;
		if (prenomLong.length() < TAILLE_MAX_PRENOM) {
			for (int i = prenom.length(); i < TAILLE_MAX_PRENOM; i++) {
				prenomLong += " ";
			}

		} else {
			prenomLong = prenomLong.substring(0, TAILLE_MAX_PRENOM);

		}
		return prenomLong;
	}
	public String getDepartementLong() {
		String departementLong = departement;
		if (departementLong.length() < TAILLE_MAX_DEPARTEMENT) {
			for (int i = departement.length(); i < TAILLE_MAX_DEPARTEMENT; i++) {
				departementLong += " ";
			}

		} else {
			departementLong = departementLong.substring(0, TAILLE_MAX_DEPARTEMENT);

		}
		return departementLong;
	}
	public String getCursusLong() {
		String cursusLong = cursus;
		if (cursusLong.length() < TAILLE_MAX_CURSUS) {
			for (int i = cursus.length(); i < TAILLE_MAX_CURSUS; i++) {
				cursusLong += " ";
			}

		} else {
			cursusLong = cursusLong.substring(0, TAILLE_MAX_CURSUS);

		}
		return cursusLong;
	}
}

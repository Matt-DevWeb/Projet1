package fr.isika.cda28.projet1.Annuaire.BackEnd;

/**
 * La classe Stagiaire représente un stagiaire avec des attributs de base tels
 * que le nom, prénom, département, cursus, et l'année de promotion. Elle
 * implémente Comparable pour permettre la comparaison de stagiaires selon leurs
 * attributs.
 */
public class Stagiaire implements Comparable<Stagiaire> {

	// Constantes pour la taille maximale des chaînes de caractères
	public final static int TAILLE_MAX_NOM = 20;
	public final static int TAILLE_MAX_PRENOM = 20;
	public final static int TAILLE_MAX_DEPARTEMENT = 4;
	public final static int TAILLE_MAX_CURSUS = 12;

	// Taille totale d'un objet Stagiaire en octets (pour le fichier binaire)
	public final static int TAILLE_STAGIAIRE_OCTET = TAILLE_MAX_NOM * 2 + TAILLE_MAX_PRENOM * 2
			+ TAILLE_MAX_DEPARTEMENT * 2 + TAILLE_MAX_CURSUS * 2 + 4;

	// Attributs de la classe Stagiaire
	String nom;
	String prenom;
	String departement;
	String cursus;
	int anneePromo;

	/**
	 * Constructeur par défaut. Initialise les attributs de base avec des valeurs
	 * par défaut.
	 */

	public Stagiaire() {
		super();
		nom = "";
		prenom = "";
		departement = "";
		cursus = "";
	}

	/**
	 * Constructeur avec paramètres.
	 * 
	 * @param nom         le nom du stagiaire
	 * @param prenom      le prénom du stagiaire
	 * @param departement le département du stagiaire
	 * @param cursus      le cursus du stagiaire
	 * @param anneePromo  l'année de promotion du stagiaire
	 */
	public Stagiaire(String nom, String prenom, String departement, String cursus, int anneePromo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cursus = cursus;
		this.anneePromo = anneePromo;
		this.departement = departement;
	}

	// METHODES*************************************************************************

	/**
	 * Représentation en chaîne de caractères d'un Stagiaire.
	 * 
	 * @return une chaîne de caractères décrivant le stagiaire.
	 */
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

	/**
	 * Génère une chaîne de longueur fixe pour un attribut du stagiaire. Si la
	 * chaîne est plus courte que la taille maximale, elle est complétée par des
	 * espaces. Si elle est plus longue, elle est tronquée.
	 * 
	 * @param value  la valeur de l'attribut
	 * @param length la longueur fixe désirée
	 * @return une chaîne de longueur fixe
	 */
	@Override
	public String toString() {
		return "Nom= " + nom + ", prenom= " + prenom + ", cursus= " + cursus + ", anneePromo= " + anneePromo
				+ ", departement= " + departement;
	}

	// Méthodes pour obtenir des chaînes de longueur fixe (pour le fichier binaire)
	public String getNomLong() {
		// Rendre le nom de longueur fixe
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
		// Rendre le prénom de longueur fixe
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
		// Rendre le département de longueur fixe
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
		// Rendre le cursus de longueur fixe
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

	/**
	 * Compare deux objets Stagiaire d'abord par nom, puis par prénom, et enfin par
	 * cursus.
	 * 
	 * @param stagiaireAComparer le stagiaire à comparer
	 * @return un entier négatif, zéro ou positif si ce stagiaire est respectivement
	 *         inférieur, égal ou supérieur au stagiaire comparé
	 */
	@Override
	public int compareTo(Stagiaire stagiaireAComparer) {
		int i = this.nom.trim().compareTo(stagiaireAComparer.getNom().trim());
		if (i == 0) {
			i = this.prenom.trim().compareTo(stagiaireAComparer.getPrenom().trim());
			if (i == 0) {
				i = this.cursus.trim().compareTo(stagiaireAComparer.getCursus().trim());
			}
		}
		return i;
	}
}

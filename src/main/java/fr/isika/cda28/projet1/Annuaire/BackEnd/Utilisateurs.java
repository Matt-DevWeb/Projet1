package fr.isika.cda28.projet1.Annuaire.BackEnd;

/**
 * La classe Utilisateurs représente un utilisateur avec des informations
 * d'identification telles que l'ID utilisateur, le mot de passe et le rôle.
 * Elle fournit des méthodes pour vérifier si l'utilisateur est un
 * administrateur ou un éditeur.
 */
public class Utilisateurs {

	// Attributs
	private String userID;
	private String password;
	private String role;
	public final static String _EDITEUR = "editeur";
	public final static String _ADMIN = "admin";

	/**
	 * Constructeur avec paramètres.
	 * 
	 * @param userID   l'identifiant de l'utilisateur
	 * @param password le mot de passe de l'utilisateur
	 * @param role     le rôle de l'utilisateur (admin ou éditeur)
	 */
	public Utilisateurs(String userID, String password, String role) {
		this.userID = userID;
		this.password = password;
		this.role = role;
	}

	// getters et setters

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// METHODES*************************************************************************

	/**
	 * Vérifie si l'utilisateur a un rôle d'administrateur.
	 * 
	 * @return true si l'utilisateur est un administrateur, false sinon
	 */

	public boolean isAdmin() {
		return role.equals(_ADMIN);
	}

	/**
	 * Vérifie si l'utilisateur a un rôle d'éditeur.
	 * 
	 * @return true si l'utilisateur est un éditeur, false sinon
	 */
	public boolean isEditeur() {
		return role.equals(_EDITEUR);
	}
}
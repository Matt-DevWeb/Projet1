package fr.isika.cda28.projet1.Annuaire;



public class Utilisateurs {
	
	//Attributs
	private String userID;
	private String password;
	private String role;
	public final static String _EDITEUR="editeur";
	public final static String _ADMIN="admin";
	
	//Constructeurs
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
	
	//Methode pour renvoyer true si utilisateur est un admin
	public boolean isAdmin() {
		return role.equals(_ADMIN);
	}
	
	//Methode pour renvoyer true si utilisateur est un editeur
	public boolean isEditeur() {
		return role.equals(_EDITEUR);
		}
	
	
}
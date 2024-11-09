package fr.isika.cda28.projet1.Annuaire.Fonctionnalités;



public class Utilisateurs {
	
	//Attributs
	private String userID;
	private String password;
	
	//Constructeurs
	public Utilisateurs(String userID, String password) {
		
		this.userID = userID;
		this.password = password;

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

}
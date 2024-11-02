package fr.isika.cda28.projet1.Annuaire;

import java.util.List;

public class Utilisateurs {
	
	//Attributs
	private String utilisateurs;
	private String password;
	private String role; // Admin, editeur ou consultant
	private List<String> permissions;// Liste des permissions spécifiques
	
	//Constructeurs
	public Utilisateurs(String utilisateurs, String password, String role, List<String> permissions) {
		
		this.utilisateurs = utilisateurs;
		this.password = password;
		this.role = role;
		this.permissions = List.of();// INITIALISER UNE LIST VIDE// A VERIFIER A L'USAGE PARCE QU'INITIALEMENT TIRE DE JS/ 
	}
	// getters et setters

	public String getUtilisateurs() {
		return utilisateurs;
	}

	public String getRole() {
		return role;
	}

	public List<String> getPermissions() {
		return permissions;
	}
	
	
	//Methodes
	// boolean droitDacces 
	
	
}

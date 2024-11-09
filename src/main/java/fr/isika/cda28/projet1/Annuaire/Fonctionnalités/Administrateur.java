package fr.isika.cda28.projet1.Annuaire.Fonctionnalités;

public class Administrateur extends Utilisateurs {

	// Attribus
	private String role;
	private String permission;

	public Administrateur(String userID, String password, String role, String permission) {
		super(userID, password);
		this.role = role;
		this.permission = permission;

	}

	public Administrateur(String userID, String password) {
		super(userID, password);

	}
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}

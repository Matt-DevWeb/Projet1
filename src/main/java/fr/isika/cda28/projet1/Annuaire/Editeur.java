package fr.isika.cda28.projet1.Annuaire;

public class Editeur extends Utilisateurs {
	
//attribus
	private String role;
	private String permission ;
	public Editeur(String userID, String password) {
		super(userID, password);
			

}
	
	public Editeur(String userID, String password, String role, String permission) {
		super(userID, password);
		this.role = role;
		this.permission = permission;
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

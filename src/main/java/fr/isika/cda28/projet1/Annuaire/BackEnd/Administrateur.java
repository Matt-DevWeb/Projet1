package fr.isika.cda28.projet1.Annuaire.BackEnd;

public class Administrateur extends Utilisateurs {

	// Constructeur
	public Administrateur(String userID, String password) {
		super(userID, password, _ADMIN);
	}
}

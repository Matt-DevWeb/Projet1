package fr.isika.cda28.projet1.Annuaire.BackEnd;

import java.util.ArrayList;
import java.util.List;

public class Authentification {

	private List<Utilisateurs> utilisateurs = new ArrayList<>();



	public Authentification() {

		// Ajouter des utilisateurs pour l'exemple
		utilisateurs.add(new Editeur("editeur1", "1234", "John", "Doe"));
		utilisateurs.add(new Editeur("editeur2", "1234", "Kevin", "Hart"));
		utilisateurs.add(new Administrateur("admin", "1234"));

	}

	public Utilisateurs authenticate(String userID, String password) {
		for (Utilisateurs utilisateur : utilisateurs) {
			if (utilisateur.getUserID().equals(userID) && utilisateur.getPassword().equals(password)) {
				System.out.println("Connexion réussie");

				return utilisateur;
			}
		}
		System.out.println("Connexion échec");
		return null;
	}


}
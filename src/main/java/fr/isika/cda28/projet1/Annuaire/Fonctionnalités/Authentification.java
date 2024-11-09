package fr.isika.cda28.projet1.Annuaire.Fonctionnalités;

import java.util.ArrayList;
import java.util.List;

public class Authentification {
	
	private List<Utilisateurs> utilisateurs = new ArrayList<>();

    public Authentification() {
    	
        // Ajouter des utilisateurs pour l'exemple
        utilisateurs.add(new Utilisateurs("admin@gmail.com", "1234"));
        utilisateurs.add(new Utilisateurs("editeur@gmail.com", "password"));
        
    }

    public boolean authenticate(String userID, String password) {
        for (Utilisateurs utilisateur : utilisateurs) {
            if (utilisateur.getUserID().equals(userID) && utilisateur.getPassword().equals(password)) {
            	System.out.println("Connexion réussie");
                return true;
            }
        }
        System.out.println("Connexion échec");
        return false;
    }
}
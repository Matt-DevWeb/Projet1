package fr.isika.cda28.projet1.Annuaire;

import java.util.ArrayList;
import java.util.List;

public class Authentification {
	
	private List<Utilisateurs> utilisateurs = new ArrayList<>();

    public Authentification() {
    	
        // Ajouter des utilisateurs pour l'exemple
    	utilisateurs.add(new Editeur("editeur1@gmail.com", "12345","editeur","partiel"));
    	utilisateurs.add(new Editeur("editeur2@gmail.com", "56789","editeur","partiel"));
        utilisateurs.add(new Administrateur("admin@gmail.com", "1234","Admin","ALL"));
    
        
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
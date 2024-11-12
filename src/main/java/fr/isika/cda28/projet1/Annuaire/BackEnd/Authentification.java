package fr.isika.cda28.projet1.Annuaire.BackEnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Authentification {
	private RandomAccessFile raf;
	private List<Utilisateurs> utilisateurs = new ArrayList<>();



	public Authentification() {
			
		try {
			FileReader fr1 = new FileReader("src/main/resources/mesFichiers/listeEditeurs.txt");
			BufferedReader br1 = new BufferedReader(fr1);
			while (br1.ready()) {
				String user = br1.readLine();
				String motDePasse = br1.readLine();
				Editeur editeur1 = new Editeur(user,motDePasse);
				br1.readLine();
				utilisateurs.add(editeur1);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Ajouter des utilisateurs pour l'exemple
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
	
	public void supprimerEditeur (String userID, Annuaire annuaire) {
		// on vide le fichier des editeurs pour le recréer
		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter("src/main/resources/mesFichiers/listeEditeurs.txt", false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// on cherche l'editeur a supprimé
		for (Utilisateurs utilisateur : utilisateurs) {
			if (utilisateur.getUserID().equals(userID) && utilisateur.isEditeur()) {
				utilisateurs.remove(utilisateur) ;
				System.out.println("Editeur Supprimé");
				break;
			}
		}
		
		miseAjourFichierEditeur(annuaire);
		
		
	}

	public void miseAjourFichierEditeur (Annuaire annuaire) {
		// on cherche les editeurs restants et on les recris dans le fichier

		for (Utilisateurs utilisateur : utilisateurs) {
			if(utilisateur.isEditeur()) {
			annuaire.ajouterEditeur((Editeur)utilisateur, true);
			}
		}
		}


}
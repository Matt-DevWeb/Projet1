package fr.isika.cda28.projet1.Annuaire.BackEnd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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


}
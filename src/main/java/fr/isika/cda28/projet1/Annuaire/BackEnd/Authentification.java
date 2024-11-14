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

/**
 * La classe Authentification permet de gérer l'authentification des
 * utilisateurs, ainsi que la gestion des éditeurs. Elle lit les informations
 * des éditeurs depuis un fichier et fournit des méthodes pour
 * l'authentification et la suppression d'éditeurs.
 */

public class Authentification {

	// Attributs
	private RandomAccessFile raf;
	private List<Utilisateurs> utilisateurs = new ArrayList<>();

	/**
	 * Constructors
	 * 
	 * @constructor Authentification() * Initialise la liste des utilisateurs en
	 *              chargeant les éditeurs depuis un fichier. Ajoute également un
	 *              administrateur par défaut pour l'authentification.
	 */
	public Authentification() {

		try {
			FileReader fr1 = new FileReader("src/main/resources/mesFichiers/listeEditeurs.txt");
			BufferedReader br1 = new BufferedReader(fr1);
			while (br1.ready()) {
				String user = br1.readLine();
				String motDePasse = br1.readLine();
				Editeur editeur1 = new Editeur(user, motDePasse);
				br1.readLine();
				utilisateurs.add(editeur1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Ajouter des utilisateurs pour l'exemple
		utilisateurs.add(new Administrateur("admin", "1234"));

	} // *************** Ici se termine le constructeur de la page Authentification
		// ***************

	// METHODES*************************************************************************

	/**
	 * Methods
	 * 
	 * @method authenticate(String userID, String password) Description: Authentifie
	 *         un utilisateur en vérifiant son identifiant et son mot de passe.
	 * @param userID   L'identifiant de l'utilisateur.
	 * @param password Le mot de passe de l'utilisateur.
	 * @return L'utilisateur authentifié s'il existe, sinon `null`.
	 */
	public Utilisateurs authenticate(String userID, String password) {
		for (Utilisateurs utilisateur : utilisateurs) {
			if (utilisateur.getUserID().equals(userID) && utilisateur.getPassword().equals(password)) {
				return utilisateur;
			}
		}
		return null;
	}

	/**
	 * @method supprimerEditeur(String userID, Annuaire annuaire) Description:
	 *         Supprime un éditeur de la liste des éditeurs.
	 * @param userID   L'identifiant de l'éditeur à supprimer.
	 * @param annuaire L'annuaire utilisé.
	 */
	public void supprimerEditeur(String userID, Annuaire annuaire) {
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
				utilisateurs.remove(utilisateur);
				break;
			}
		}
		miseAjourFichierEditeur(annuaire);
	}

	/**
	 * @method miseAjourFichierEditeur(Annuaire annuaire) Description: Met à jour le
	 *         fichier des éditeurs.
	 * @param annuaire L'annuaire utilisé.
	 */
	public void miseAjourFichierEditeur(Annuaire annuaire) {
		// on cherche les editeurs restants et on les recris dans le fichier
		for (Utilisateurs utilisateur : utilisateurs) {
			if (utilisateur.isEditeur()) {
				annuaire.ajouterEditeur((Editeur) utilisateur, true);
			}
		}
	}
}
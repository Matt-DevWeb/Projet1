package fr.isika.cda28.projet1.Annuaire.BackEnd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * La classe GestionDesFichiers gère le chargement des données de stagiaires
 * depuis un fichier texte et vérifie si le fichier binaire contenant les
 * stagiaires est déjà rempli.
 */

public class GestionDesFichiers {

	// Attributs
	private Annuaire annuaire;

	/**
	 * Constructors
	 * 
	 * @constructor GestionDesFichiers() Description: Constructeur de la classe
	 *              GestionDesFichiers.
	 */
	public GestionDesFichiers() {
		this.annuaire = new Annuaire();
	}

	// METHODES*************************************************************************

	/**
	 * Methods
	 * 
	 * @method chargerStagiairesDepuisFichier() Description: Charge les stagiaires
	 *         depuis un fichier texte.
	 */
	public void chargerStagiairesDepuisFichier() {
		try {
			// Ouverture du fichier texte contenant les informations des stagiaires
			FileReader fr = new FileReader("src/main/resources/mesFichiers/STAGIAIRES.DON");
			BufferedReader br = new BufferedReader(fr);

			// Lire chaque ligne du fichier texte et créer les objets Stagiaire
			while (br.ready()) {

				// Création d'un objet Stagiaire vide avec des valeurs par défaut
				Stagiaire stagiaire = new Stagiaire();

				// Lire et définir le nom, prénom, département, cursus, et l'année de promo
				stagiaire.setNom(br.readLine());
				stagiaire.setPrenom(br.readLine());
				stagiaire.setDepartement(br.readLine());
				stagiaire.setCursus(br.readLine());
				stagiaire.setAnneePromo(Integer.parseInt(br.readLine()));

				// Lire la ligne séparatrice "*" (et l'ignorer)
				br.readLine();

				annuaire.ajouterStagiaire(new Noeud(stagiaire, -1, -1));
			}

			// Fermeture des flux de lecture
			br.close();
			fr.close();
		} catch (IOException e) {
			// Gestion des exceptions : affichage de la trace d'erreur
			e.printStackTrace();
		}
	}

	/**
	 * @method fichierBinaireRempli() Description: Vérifie si le fichier binaire des
	 *         stagiaires est rempli.
	 * @return `true` si le fichier binaire est rempli, `false` sinon.
	 */
	public boolean fichierBinaireRempli() {
		File fichierBinaire = new File("src/main/resources/mesFichiers/ListeStagiaires.bin");
		return fichierBinaire.exists() && fichierBinaire.length() > 0;
	}
}

package fr.isika.cda28.projet1.Annuaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class GestionFichiers {

	public static void main(String[] args) {

		// Création d'un objet Stagiaire vide avec des valeurs par défaut
		Stagiaire stagiaire = new Stagiaire(null, null, null, null, 0);
		// Création d'une liste pour stocker les objets Stagiaire
		ArrayList<Stagiaire> listeStagiaire = new ArrayList<>();

		// ********** Lire le fichier texte **********
		try {
			// Ouverture du fichier texte contenant les informations des stagiaires
			FileReader fr = new FileReader("src/mesFichiers/STAGIAIRES.DON");
			BufferedReader br = new BufferedReader(fr);

			// Ouverture d'un fichier binaire pour écrire les informations des stagiaires
			RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/ListeStagiaires.bin", "rw");

			// Lire chaque ligne du fichier texte et créer les objets Stagiaire
			while (br.ready()) {
				// Lire et définir le nom, prénom, département, cursus, et l'année de promo
				stagiaire.setNom(br.readLine());
				stagiaire.setPrenom(br.readLine());
				stagiaire.setDepartement(br.readLine());
				stagiaire.setCursus(br.readLine());
				stagiaire.setAnneePromo(Integer.parseInt(br.readLine()));
				// Lire la ligne séparatrice "*" (et l'ignorer)
				br.readLine();
				// Ajouter l'objet Stagiaire à la liste
				listeStagiaire.add(stagiaire);
				// Créer un nouvel objet Stagiaire pour la prochaine entrée
				stagiaire = new Stagiaire();
			}

			// Écrire chaque objet Stagiaire dans le fichier binaire
			for (Stagiaire stagiaireEcriture : listeStagiaire) {
				// Écriture des champs de l'objet Stagiaire dans le fichier
				raf.writeChars(stagiaireEcriture.getNomLong());
				raf.writeChars(stagiaireEcriture.getPrenomLong());
				raf.writeChars(stagiaireEcriture.getDepartementLong());
				raf.writeChars(stagiaireEcriture.getCursusLong());
				raf.writeInt(stagiaireEcriture.getAnneePromo());
			}

			// Fermeture des flux de lecture et d'écriture
			br.close();
			fr.close();
			raf.close();

		} catch (IOException e) {
			// Gestion des exceptions : affichage de la trace d'erreur
			e.printStackTrace();
		}
	}
}

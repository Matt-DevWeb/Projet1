package fr.isika.cda28.projet1.Annuaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class GestionFichiers {

	public static void main(String[] args) {

		// Création d'un objet Stagiaire vide avec des valeurs par défaut
		Stagiaire stagiaire = new Stagiaire();
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

		try

		{
			RandomAccessFile raf2 = new RandomAccessFile("src/mesFichiers/ListeStagiaires.bin", "r");
			// lire le 1er stagiaire
//			raf2.seek(232 * Stagiaire.TAILLE_STAGIAIRE_OCTET);

			stagiaire.setNom("");
			stagiaire.setPrenom("");
			stagiaire.setDepartement("");
			stagiaire.setAnneePromo(0);
			stagiaire.setCursus("");

			// ou est mon curseur
			System.out.println(raf2.getFilePointer());

			while (raf2.getFilePointer() < raf2.length()) {
				// Nom
//				for (Stagiaire stagiaireBinaire : raf2 )
				for (int i = 0; i < Stagiaire.TAILLE_MAX_NOM; i++) {
					stagiaire.setNom(stagiaire.getNom() + raf2.readChar());

				}
				stagiaire.getNom().trim();
				System.out.println("Nom : " + stagiaire.getNom());
				// Prenom

				for (int i = 0; i < Stagiaire.TAILLE_MAX_PRENOM; i++) {
					stagiaire.setPrenom(stagiaire.getPrenom() + raf2.readChar());

				}
				stagiaire.getPrenom().trim();
				System.out.println("Prenom  : " + stagiaire.getPrenom());
				// Departement

				for (int i = 0; i < Stagiaire.TAILLE_MAX_DEPARTEMENT; i++) {
					stagiaire.setDepartement(stagiaire.getDepartement() + raf2.readChar());

				}
				stagiaire.getDepartement().trim();
				System.out.println("Le departement est  : " + stagiaire.getDepartement());
				// Cursus

				for (int i = 0; i < Stagiaire.TAILLE_MAX_CURSUS; i++) {
					stagiaire.setCursus(stagiaire.getCursus() + raf2.readChar());

				}
				stagiaire.getCursus().trim();
				System.out.println("Le cursus est  : " + stagiaire.getCursus());

				// AnneePromo

				stagiaire.setAnneePromo(raf2.readInt());

				stagiaire.getAnneePromo();
				System.out.println("L'annee de promo est  : " + stagiaire.getAnneePromo());

//			Stagiaire stagiaire = new Stagiaire();
			}

			// Fermeture des flux de lecture et d'écriture
			raf2.close();

		} catch (IOException e) {
			// Gestion des exceptions : affichage de la trace d'erreur
			e.printStackTrace();
		}
	}
}

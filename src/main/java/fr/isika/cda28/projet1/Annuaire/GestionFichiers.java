package fr.isika.cda28.projet1.Annuaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GestionFichiers {

	public static void main(String[] args) {

		// **********objet pour lire un fichier**********

		try {
			FileReader fr = new FileReader("src/mesFichiers/STAGIAIRES.DON");
			BufferedReader br = new BufferedReader(fr);

			Stagiaire stagiaire = new Stagiaire(null, null, null, null, 0);

			// tant que le buffer a quelque chose a lire il construit le stagiaire
			// jusqu'à ce qu'il rencontre "*"

			while (br.ready()) {
				stagiaire.setNom(br.readLine());
				stagiaire.setPrenom(br.readLine());
				stagiaire.setDepartement(br.readLine());
				stagiaire.setCursus(br.readLine());
				stagiaire.setAnneePromo(Integer.parseInt(br.readLine()));
				br.readLine();
				System.out.println(stagiaire);
			}

			br.close();
			fr.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

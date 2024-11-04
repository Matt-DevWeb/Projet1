package fr.isika.cda28.projet1.Annuaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class GestionFichiers {

	public static void main(String[] args) {

		Stagiaire stagiaire = new Stagiaire(null, null, null, null, 0);
		ArrayList<Stagiaire> listeStagiaire = new ArrayList<>();

		// **********objet pour lire un fichier**********

		try {
			FileReader fr = new FileReader("src/mesFichiers/STAGIAIRES.DON");
			BufferedReader br = new BufferedReader(fr);

			RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/ListeStagiaires.bin", "rw");

			// tant que le buffer a quelque chose a lire il construit le stagiaire
			// jusqu'à ce qu'il rencontre "*"

			while (br.ready()) {
				stagiaire.setNom(br.readLine());
				stagiaire.setPrenom(br.readLine());
				stagiaire.setDepartement(br.readLine());
				stagiaire.setCursus(br.readLine());
				stagiaire.setAnneePromo(Integer.parseInt(br.readLine()));
				br.readLine();
				listeStagiaire.add(stagiaire);
				stagiaire = new Stagiaire(); 
				
				

			}

			for (Stagiaire  stagiaireEcriture : listeStagiaire) {

				raf.writeChars(stagiaireEcriture.getNom());
				raf.writeChars(stagiaireEcriture.getPrenom());
				raf.writeChars(stagiaireEcriture.getDepartement());
				raf.writeChars(stagiaireEcriture.getCursus());
				raf.writeInt(stagiaireEcriture.getAnneePromo());
			}
			
			br.close();
			fr.close();
			raf.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

package fr.isika.cda28.projet1.Annuaire;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
// Sert à faire la transition entre front et fichier binaire
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Annuaire {
	// Attributs
	private RandomAccessFile raf;
	public Noeud noeud;

// Constructeur vide qui contient le raf initialisé
	public Annuaire() {
		try {
			File fichier = new File("src/main/resources/mesFichiers/ListeStagiaires.bin");
			raf = new RandomAccessFile(fichier, "rw");
		} catch (IOException e) {
			System.err.println("Le fichier binaire n'a pas été ouvert");
			e.printStackTrace();
		}

	}

	// Getters et Setters

	public RandomAccessFile getRaf() {
		return raf;
	}

	// Méthodes

	public ObservableList<Stagiaire> lireFichierObservable() throws IOException {
		ObservableList<Stagiaire> stagiaires = FXCollections.observableArrayList();
		if (raf == null || !raf.getChannel().isOpen()) {
			System.err.println("Le fichier binaire raf n'est pas initialisé.");
			return stagiaires; // Retourne une liste vide
		}
		if (raf.length() == 0) {
			System.out.println("Le fichier binaire est vide.");
			return stagiaires; // Retourne une liste vide
		}

		Noeud tmp = new Noeud();
		for (int position = 0; position < raf.length(); position += tmp.TAILLE_NOEUD_OCTET) {
			raf.seek(position);
			Noeud nouveauNoeud = tmp.lireNoeud(this.raf);
			stagiaires.add(nouveauNoeud.getStagiaire());
		}
		return stagiaires;
	}

	public void ajouterStagiaire(Noeud stagiaire) throws IOException {

		if (raf.length() == 0) {
			raf.seek(0);
			stagiaire.ecrireNoeud(stagiaire, raf);
		} else {
			raf.seek(0);
			Noeud racine = stagiaire.lireNoeud(raf);
			racine.ajoutStagiaireRecursif(stagiaire, raf);
		}

	}

	public Noeud rechercherStagiaire(Stagiaire stagiaireARechercher) throws IOException {
		Noeud resultatRecherche = new Noeud();
		if (raf.length() == 0) {
			System.out.println(" Nous ne pouvons pas trouver de stagiaire, car celui-ci est vide");
		} else {
			raf.seek(0);
			Noeud racine = new Noeud();
			racine = racine.lireNoeud(raf);
			resultatRecherche = racine.rechercheNoeud(stagiaireARechercher, raf, 0);
		}
		return resultatRecherche;
	}

	public void supprimerStagiaire(Noeud stagiaireASupprimer) throws IOException {
		if (raf.length() == 0) {
			System.out.println("L'annuaire est vide ");
		} else {
			raf.seek(0);
			Noeud racine = stagiaireASupprimer.lireNoeud(raf);
			racine.supprimerNoeud(stagiaireASupprimer, raf, 0);
		}
	}
	

	public List<Stagiaire> afficherListeOrdreAlphabetique() throws IOException {
		List<Stagiaire> listeTriee = new ArrayList<>();
		if (raf.length() > 0) {
			raf.seek(0); // Revenir au début du fichier pour lire la racine
			Noeud racine = new Noeud(new Stagiaire(), -1, -1); // Initialiser un noeud pour la racine
			racine = racine.lireNoeud(raf); // Lire la racine
			racine.listeOrdreAlphabetique(raf, listeTriee); // Lancer la traversée In-Order et remplir la liste
		} else {
			System.out.println("L'annuaire est vide.");
		}
		return listeTriee;
	}

	public List<Stagiaire> imprimerListeOrdreAlphabetique() throws IOException {
		return afficherListeOrdreAlphabetique(); // Retourner directement la liste triée
	}

	public void creerPDF(String cheminFichierPDF) {
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(cheminFichierPDF));
			document.open();

			// Titre du document
			document.add(new Paragraph("Annuaire des Stagiaires", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
			document.add(new Paragraph("\n")); // Espace après le titre

			// Création d'une table avec 4 colonnes pour chaque attribut du stagiaire
			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100); // Ajuste la largeur de la table à 100% du document

			// Ajout des en-têtes de colonnes
			table.addCell("Nom");
			table.addCell("Prénom");
			table.addCell("Departement");
			table.addCell("Cursus");
			table.addCell("Année de la promo");

			// Récupération de la liste triée
			List<Stagiaire> listeImpression = imprimerListeOrdreAlphabetique();
			if (listeImpression == null || listeImpression.isEmpty()) {
				System.out.println("La Liste est vide ou null, ou il n'y aucun stagiaire à afficher");
				document.add(new Paragraph("Aucun stagiaire à afficher"));
			} else {
				// Remplissage de la table avec les informations des stagiaires
				for (Stagiaire stagiaire : listeImpression) {
					table.addCell(stagiaire.getNom());
					table.addCell(stagiaire.getPrenom());
					table.addCell(stagiaire.getDepartement());
					table.addCell(stagiaire.getCursus());
					table.addCell(String.valueOf(stagiaire.getAnneePromo()));
				}
			}
			// Ajout de la table au document
			document.add(table);

		} catch (DocumentException | IOException e) {
			System.err.println("Erreur lors de la création du document PDF : " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (document.isOpen()) {
				document.close();
			}
		}
	}

	public void close() {
		try {
			if (raf != null) {
				raf.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Annuaire [raf=" + raf + ", noeud=" + noeud + "]";
	}

}

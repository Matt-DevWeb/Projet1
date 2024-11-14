package fr.isika.cda28.projet1.Annuaire.BackEnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import javafx.scene.control.TableView;

/**
 * Class Annuaire
 * javadoc de la classe Annuaire
 */

public class Annuaire {

	// Attributs
	private RandomAccessFile raf;
	public Noeud noeud;

	 /**
     * Constructors
     * @constructor Annuaire()
     * Description: Constructeur de la classe Annuaire.
     */
	
	public Annuaire() {
		try {
			File fichier = new File("src/main/resources/mesFichiers/ListeStagiaires.bin");
			raf = new RandomAccessFile(fichier, "rw");
		} catch (IOException e) {
			System.err.println("Le fichier binaire n'a pas été ouvert");
			e.printStackTrace();
		}
		try {
			FileReader fr1 = new FileReader("src/main/resources/mesFichiers/listeEditeurs.txt");
			BufferedReader br1 = new BufferedReader(fr1);
			while (br1.ready()) {
				String email = br1.readLine();
				String motDePasse = br1.readLine();
				Editeur editeur = new Editeur(email, motDePasse);
				br1.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // *************** Ici se termine le constructeur de la page Annuaire **************
	

	// METHODES*************************************************************************

	/**
     * Methods
     * @method getRaf()
     * Description: Obtient le RandomAccessFile utilisé par l'annuaire.
     * @return Le RandomAccessFile.
     */
	
	public RandomAccessFile getRaf() {
		return raf;
	}


	/**
     * @method ajouterStagiaire(Noeud stagiaire)
     * Description: Ajoute un stagiaire à l'annuaire.
     * @param stagiaire Le noeud contenant les informations du stagiaire à ajouter.
     * @throws IOException
     */
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

	 /**
     * @method modifierStagiaire(Stagiaire stagiaireAModifier, Stagiaire stagiaireModifie)
     * Description: Modifie les informations d'un stagiaire dans l'annuaire.
     * @param stagiaireAModifier Le stagiaire à modifier.
     * @param stagiaireModifie Les nouvelles informations du stagiaire.
     * @return Le nouveau noeud avec les informations modifiées.
     * @throws Exception
     */
	public Noeud modifierStagiaire(Stagiaire stagiaireAModifier, Stagiaire stagiaireModifie) throws Exception {
		if (stagiaireAModifier != null && stagiaireModifie != null) {
			raf.seek(0); // on se positionne au début du fichier
			Noeud noeudASupprimer = new Noeud(stagiaireAModifier, -1, -1);
			noeudASupprimer.rechercheNoeud(stagiaireAModifier, raf, raf.getFilePointer()); // On vient rechercher le

			// noeud à modifier
			noeudASupprimer.lireNoeud(raf); // On lit le noeud
			Noeud nouveauNoeud = new Noeud(stagiaireModifie, -1, -1); // On vient créer un nouveau noeud avec le

			// stagiaire modifie
			
			ajouterStagiaire(nouveauNoeud);
			
			supprimerStagiaire(noeudASupprimer);
			return nouveauNoeud;
		}
		return null;
	}

	 /**
     * @method rechercherStagiaire(Stagiaire stagiaireARechercher)
     * Description: Recherche un stagiaire dans l'annuaire.
     * @param stagiaireARechercher Le stagiaire à rechercher.
     * @return Le noeud contenant les informations du stagiaire recherché.
     * @throws IOException
     */
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

	 /**
     * @method supprimerStagiaire(Noeud stagiaireASupprimer)
     * Description: Supprime un stagiaire de l'annuaire.
     * @param stagiaireASupprimer Le noeud contenant les informations du stagiaire à supprimer.
     * @throws IOException
     */
	public void supprimerStagiaire(Noeud stagiaireASupprimer) throws IOException {
		if (raf.length() == 0) {
			System.out.println("L'annuaire est vide ");
		} else {
			raf.seek(0);
			Noeud racine = stagiaireASupprimer.lireNoeud(raf);
			racine.supprimerNoeud(stagiaireASupprimer, raf, 0);
		}
	}

	/**
     * @method lireFichierObservable()
     * Description: Lit le fichier binaire et retourne une liste observable de stagiaires.
     * @return La liste observable de stagiaires.
     * @throws IOException
     */
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

	 /**
     * @method afficherListeOrdreAlphabetique()
     * Description: Affiche la liste des stagiaires par ordre alphabétique.
     * @return La liste des stagiaires triée par ordre alphabétique.
     * @throws IOException
     */
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

	/**
     * @method imprimerListeOrdreAlphabetique()
     * Description: Imprime la liste des stagiaires par ordre alphabétique.
     * @return La liste des stagiaires triée par ordre alphabétique.
     * @throws IOException
     */
	public List<Stagiaire> imprimerListeOrdreAlphabetique() throws IOException {
		return afficherListeOrdreAlphabetique(); // Retourner directement la liste triée
	}

	/**
     * @method creerPDF(TableView<Stagiaire> tableView, String cheminFichierPDF)
     * Description: Crée un fichier PDF contenant la liste des stagiaires.
     * @param tableView La TableView contenant les informations des stagiaires.
     * @param cheminFichierPDF Le chemin du fichier PDF à créer.
     */
	public void creerPDF(TableView<Stagiaire> tableView, String cheminFichierPDF) {
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
			List<Stagiaire> listeImpression = tableView.getItems();
			if (listeImpression == null || listeImpression.isEmpty()) {
				
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

	/**
     * @method ajouterEditeur(Editeur editeur, boolean append)
     * Description: Ajoute un éditeur dans le fichier des éditeurs.
     * @param editeur L'éditeur à ajouter.
     * @param append Indique s'il faut ajouter l'éditeur à la fin du fichier.
     */
	public void ajouterEditeur(Editeur editeur, boolean append) {
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("src/main/resources/mesFichiers/listeEditeurs.txt", append))) {
			writer.write(editeur.getUserID());
			writer.newLine();
			writer.write(editeur.getPassword());
			writer.newLine();
			writer.write("*");
			writer.newLine();
			
		} catch (IOException e) {
			System.err.println("Une erreur s'est produite lors de l'écriture dans le fichier : " + e.getMessage());
		}
	}

	/**
     * @method close()
     * Description: Ferme le RandomAccessFile utilisé par l'annuaire.
     */
	public void close() {
		try {
			if (raf != null) {
				raf.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 /**
     * @method toString()
     * Description: Retourne une représentation textuelle de l'annuaire.
     * @return La représentation textuelle de l'annuaire.
     */
	@Override
	public String toString() {
		return "Annuaire [raf=" + raf + ", noeud=" + noeud + "]";
	}
}

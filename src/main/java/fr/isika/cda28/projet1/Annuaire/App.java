package fr.isika.cda28.projet1.Annuaire;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App extends Application {
	private Annuaire annuaire;

	@Override
	public void init() throws Exception {
		GestionDesFichiers fichier = new GestionDesFichiers();
		annuaire = new Annuaire();
		if (!fichier.fichierBinaireRempli()) {
			fichier.chargerStagiairesDepuisFichier();
		} else {
			annuaire.afficherListeOrdreAlphabetique();
		}

		System.out.println("le nombre de stagiaires est de " + annuaire.lireFichierObservable().size());

		super.init();
	}

	@Override
	public void start(Stage stage) throws Exception {

		PageAccueil root = new PageAccueil(annuaire);
		root.setPromotion(annuaire.lireFichierObservable());
//		annuaire.afficherListeOrdreAlphabetique();
		Image icon = new Image(getClass().getResourceAsStream("/mesFichiers/icon.png"), 40, 40, true, true);
//		annuaire.creerPDF("src/main/resources/mesFichiers/ListeStagiaires.pdf");
		stage.getIcons().add(icon);
//		 On instancie la scène avec ses dimensions.
		Scene scene = new Scene(root, 1280, 720);

		// On donne un titre à la scène
		stage.setTitle("DevUp Academy");
		stage.setResizable(false);
		// On donne notre scene à notre stage
		stage.setScene(scene);
		stage.sizeToScene();

		// On affiche notre stage
		stage.show();
	}

	public static void main(String[] args) throws IOException {

		// Annuaire annuaireTest = new Annuaire();
		// Stagiaire stagiaireTest = new Stagiaire("DAUBERMANN", "Maxime", "62","AL
		// 18",2022 );
		// Noeud stagiaireTestNoeud = new Noeud(stagiaireTest, -1, -1);

		// annuaireTest.ajouterStagiaire(stagiaireTestNoeud);

		launch(args);
	}

}
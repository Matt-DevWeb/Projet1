package fr.isika.cda28.projet1.Annuaire;

import java.io.IOException;

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
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void init() throws Exception {
		GestionDesFichiers fichier = new GestionDesFichiers();
		fichier.chargerStagiairesDepuisFichier();
		super.init();
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {

		Annuaire annuaire = new Annuaire();

		PageAccueil root = new PageAccueil();
		root.setPromotion(annuaire.lireFichier());	 

		Image icon = new Image(getClass().getResourceAsStream("/mesFichiers/icon.png"), 40, 40, true, true);

		stage.getIcons().add(icon);
//		 On instancie la scène avec ses dimensions.
		Scene scene = new Scene(root, 1024, 576);

		// On donne un titre à la scène
		stage.setTitle("DevUp Academy");
		stage.setResizable(false);
		// On donne notre scene à notre stage
		stage.setScene(scene);
		stage.sizeToScene();

		// On affiche notre stage
		stage.show();
	}

	public static void main(String[] args) throws IOException{
		
		//Annuaire annuaireTest = new Annuaire();
		//Stagiaire stagiaireTest = new Stagiaire("DAUBERMANN", "Maxime", "62","AL 18",2022 );
		//Noeud stagiaireTestNoeud = new Noeud(stagiaireTest, -1, -1);
		
		//annuaireTest.ajouterStagiaire(stagiaireTestNoeud);
		
		launch(args);
	}

}
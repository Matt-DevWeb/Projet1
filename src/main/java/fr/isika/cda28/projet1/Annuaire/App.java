package fr.isika.cda28.projet1.Annuaire;

import fr.isika.cda28.projet1.Annuaire.Design.PageAccueil;
import fr.isika.cda28.projet1.Annuaire.Design.PageVisiteurs;
import fr.isika.cda28.projet1.Annuaire.Fonctionnalités.Annuaire;
import fr.isika.cda28.projet1.Annuaire.Fonctionnalités.GestionDesFichiers;
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
	public void start(Stage stage) throws Exception {

		Annuaire annuaire = new Annuaire();

		PageAccueil root = new PageAccueil(annuaire);

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

	public static void main(String[] args) {
		GestionDesFichiers fichier = new GestionDesFichiers();
	fichier.chargerStagiairesDepuisFichier();
		
		
		launch(args);
	}

}
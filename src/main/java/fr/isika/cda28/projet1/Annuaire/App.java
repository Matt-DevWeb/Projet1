package fr.isika.cda28.projet1.Annuaire;

import fr.isika.cda28.projet1.Annuaire.Design.PageAccueil;
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
		PageAccueil root = new PageAccueil();
		
		Image icon = new Image(getClass().getResourceAsStream("favicon.jpg"), 32, 32 , true, true);
		System.out.println(getClass().getResource("favicon.jpg"));
		stage.getIcons().add(icon);
		// On instancie la scène avec ses dimensions.
		Scene scene = new Scene(root, 1000, 800);

		// On donne un titre à la scène
		stage.setTitle("DevUp Academy");

		// On donne notre scene à notre stage
		stage.setScene(scene);
		stage.sizeToScene();

		// On affiche notre stage
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
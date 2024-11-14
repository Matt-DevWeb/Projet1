/**
 * Package fr.isika.cda28.projet1.Launcher
 */

package fr.isika.cda28.projet1.Launcher;

import java.io.IOException;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Annuaire;
import fr.isika.cda28.projet1.Annuaire.BackEnd.GestionDesFichiers;
import fr.isika.cda28.projet1.Annuaire.FrontEnd.PageAccueil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class App
 * javadoc de la classe App
 */

public class App extends Application {
	
	// Attributs
	private Annuaire annuaire;
	
	/**
     * Methods
     * @method init()
     * Description: Méthode appelée pour initialiser l'application.
     * @throws Exception
     */
	@Override
	public void init() throws Exception {
		GestionDesFichiers fichier = new GestionDesFichiers();
		annuaire = new Annuaire();
		if (!fichier.fichierBinaireRempli()) {
			fichier.chargerStagiairesDepuisFichier();
		} else {
			annuaire.afficherListeOrdreAlphabetique();
		}
		
		super.init();
	}

	/**
     * @method start(Stage stage)
     * Description: Méthode appelée pour démarrer l'application.
     * @param stage Le stage principal de l'application.
     * @throws Exception
     */
	@Override
	public void start(Stage stage) throws Exception {
		PageAccueil root = new PageAccueil(annuaire);
		root.setPromotion(annuaire.lireFichierObservable());
		Image icon = new Image(getClass().getResourceAsStream("/mesFichiers/icon.png"), 40, 40, true, true);
		stage.getIcons().add(icon);
		
		//On instancie la scène avec ses dimensions.
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
	
	/**
     * @method main(String[] args)
     * Description: Point d'entrée de l'application.
     * @param args Les arguments de la ligne de commande.
     * @throws IOException
     */
	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
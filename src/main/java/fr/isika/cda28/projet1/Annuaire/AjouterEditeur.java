
package fr.isika.cda28.projet1.Annuaire;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AjouterEditeur extends BorderPane {
	// On instancie un label "Ajouter un éditeur"
	private Label ajouterEditeur = new Label("Ajouter un éditeur");

	// Déclaration des labels et textfields pour le gridPane
	private Label nomlabel = new Label("Nom");
	private TextField champNom = new TextField();
	private Label prenomlabel = new Label("Prénom");
	private TextField champPrenom = new TextField();
	private Label emaillabel = new Label("Email");
	private TextField champEmail = new TextField();
	private Label motDePasselabel = new Label("Mot de passe");
	private PasswordField champMotdepasse = new PasswordField();

	// Déclaration des boutons
	private Button cheminVersListeStagiaire = new Button("Annuaire");
	private Button accueil = new Button("Accueil");

	// Bouton valider editeur
	private Button valider = new Button("Valider");

	// Logo
	private Image logo = new Image(getClass().getResourceAsStream("/mesFichiers/logo_blanc_ligne.png"));
	private ImageView logoImageView = new ImageView(logo);

	// Declaration VBox et GridPane
	VBox leftside = new VBox(220); // Conteneur à gauche pour le logo et les boutons
	VBox leftSideButtons = new VBox(20); // Conteneur des boutons à gauche
	GridPane rightSide = new GridPane(); // Conteneur droite pour le GridPane

	// Constructeur
	public AjouterEditeur(Annuaire annuaire, ObservableList<Stagiaire> stagiaires) {
		super();

	

		// dimensions logo
		logoImageView.setFitWidth(200);
		logoImageView.setFitHeight(81);

		// mettre en gras
		ajouterEditeur.setStyle("-fx-text-fill:white ;-fx-font-size:30px ;");

		// fond blanc
		setStyle("-fx-background-color:#172428");
		cheminVersListeStagiaire.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		accueil.setStyle("-fx-background-color: #4C5A6B ; -fx-text-fill: white; -fx-font-size: 16px;");
		// Definir taille de la VBox + ajout composants
		leftside.setPrefSize(220, 450);
		leftside.getChildren().addAll(logoImageView, leftSideButtons);
		leftSideButtons.getChildren().addAll(cheminVersListeStagiaire, accueil);

		// Modifier la couleur de fond gauche
		leftside.setStyle("-fx-background-color:#172428");

		// alignement dans la VBox
		leftside.setAlignment(Pos.TOP_CENTER);
		leftside.setPadding(new Insets(20));
		leftSideButtons.setAlignment(Pos.CENTER);
//

		nomlabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		champNom.setPromptText("Entrez votre nom");
		prenomlabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		champPrenom.setPromptText("Entrez votre prénom");
		emaillabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		champEmail.setPromptText("Entrez votre email");
		motDePasselabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		champMotdepasse.setPromptText("Entrez votre mot de passe");
		valider.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		// la taille GridPane droit
		rightSide.setPrefSize(580, 450);

		// Ajouter les éléments au GridPane (formulaire)
		rightSide.setAlignment(Pos.CENTER);
		rightSide.add(ajouterEditeur, 0, 0, 2, 1);
		rightSide.add(nomlabel, 0, 2, 1, 1);
		rightSide.add(champNom, 0, 3, 1, 1);
		rightSide.add(prenomlabel, 2, 2, 1, 1);
		rightSide.add(champPrenom, 2, 3, 1, 1);
		rightSide.add(emaillabel, 0, 5, 1, 1);
		rightSide.add(champEmail, 0, 6, 1, 1);
		rightSide.add(motDePasselabel, 2, 5, 1, 1);
		rightSide.add(champMotdepasse, 2, 6, 1, 1);
		rightSide.add(valider, 1, 8);

		// margin du bouton valider
		GridPane.setMargin(valider, new Insets(20, 0, 0, 0));

		// on donne du comportement au bouton valider

				valider.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Annuaire annuaire = new Annuaire();

						String nom = champNom.getText();
						String prenom = champPrenom.getText();
						String email = champEmail.getText();
						String motDepasse= champMotdepasse.getText();
						
						Editeur nouveauEditeur = new Editeur(email, motDepasse, nom, prenom);

						annuaire.ajouterEditeur(nouveauEditeur);
		                
		                			Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Ajout réalisé");
						alert.setHeaderText(null);
						alert.setContentText("Félicitations vous avez ajouter un nouveau stagiaire");
						alert.showAndWait();

					}
				});
		
		rightSide.setVgap(10);
		rightSide.setHgap(5);

		// Ajout VBox gauche et la GridPane dans borderPane
		this.setLeft(leftside);
		this.setRight(rightSide);
	}
}
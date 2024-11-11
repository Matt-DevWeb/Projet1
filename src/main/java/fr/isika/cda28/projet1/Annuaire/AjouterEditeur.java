package fr.isika.cda28.projet1.Annuaire;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	private TextField nom = new TextField();
	private Label prenomlabel = new Label("Prénom");
	private TextField prenom = new TextField();
	private Label emaillabel = new Label("Email");
	private TextField email = new TextField();
	private Label motDePasselabel = new Label("Mot de passe");
	private PasswordField motdepasse = new PasswordField();

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
		nom.setPromptText("Entrez votre nom");
		prenomlabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		prenom.setPromptText("Entrez votre prénom");
		emaillabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		email.setPromptText("Entrez votre email");
		motDePasselabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		motdepasse.setPromptText("Entrez votre mot de passe");
		valider.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		// la taille GridPane droit
		rightSide.setPrefSize(580, 450);

		// Ajouter les éléments au GridPane (formulaire)
		rightSide.setAlignment(Pos.CENTER);
		rightSide.add(ajouterEditeur, 0, 0, 2, 1);
		rightSide.add(nomlabel, 0, 2, 1, 1);
		rightSide.add(nom, 0, 3, 1, 1);
		rightSide.add(prenomlabel, 2, 2, 1, 1);
		rightSide.add(prenom, 2, 3, 1, 1);
		rightSide.add(emaillabel, 0, 5, 1, 1);
		rightSide.add(email, 0, 6, 1, 1);
		rightSide.add(motDePasselabel, 2, 5, 1, 1);
		rightSide.add(motdepasse, 2, 6, 1, 1);
		rightSide.add(valider, 1, 8);

		// margin du bouton valider
		GridPane.setMargin(valider, new Insets(20, 0, 0, 0));

		rightSide.setVgap(10);
		rightSide.setHgap(5);

		// Ajout VBox gauche et la GridPane dans borderPane
		this.setLeft(leftside);
		this.setRight(rightSide);
	}
}

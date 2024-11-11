package fr.isika.cda28.projet1.Annuaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AjouterStagiaire extends BorderPane {
	// On instancie un label "Ajouter un éditeur"
	private Label ajouterEditeur = new Label("Ajouter un stagiaire");

	// Déclaration des labels et textfields pour le gridPane
	private Label nomLabel = new Label("Nom");
	private TextField nom = new TextField();
	private Label prenomLabel = new Label("Prénom");
	private TextField prenom = new TextField();
	private Label cursusLabel = new Label("Cursus");
	private TextField email = new TextField();
	private Label promotionLabel = new Label("Promotion");
	private PasswordField motdepasse = new PasswordField();
	private Label departementLabel = new Label("Departement");
	private ChoiceBox<String> departement = new ChoiceBox<>();

	// Déclaration des boutons
	private Button cheminVersListeStagiaire = new Button("Annuaire");
	private Button boutonAccueil = new Button("Accueil");
	private Button rechercher = new Button("Rechercher");

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
	public AjouterStagiaire(Annuaire annuaire, ObservableList<Stagiaire> stagiaires) {
		super();

		// dimensions logo
		logoImageView.setFitWidth(200);
		logoImageView.setFitHeight(81);

		// mettre en gras
		ajouterEditeur.setStyle("-fx-text-fill:white ;-fx-font-size:30px ;");

		// fond blanc
		setStyle("-fx-background-color:#172428");
		cheminVersListeStagiaire.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonAccueil.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		// Definir taille de la VBox + ajout composants
		leftside.setPrefSize(220, 450);
		leftside.getChildren().addAll(logoImageView, leftSideButtons);
		leftSideButtons.getChildren().addAll(cheminVersListeStagiaire, boutonAccueil);

		// Modifier la couleur de fond gauche
		leftside.setStyle("-fx-background-color:#172428");

		// alignement dans la VBox
		leftside.setAlignment(Pos.TOP_CENTER);
		leftside.setPadding(new Insets(20));
		leftSideButtons.setAlignment(Pos.CENTER);
//

		nomLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		nom.setPromptText("Entrez votre nom");
		prenomLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		prenom.setPromptText("Entrez votre prénom");
		cursusLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		email.setPromptText("Entrez votre email");
		promotionLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		motdepasse.setPromptText("Entrez votre mot de passe");
		departementLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		valider.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		// la taille GridPane droit
		rightSide.setPrefSize(580, 450);

		// Ajouter les éléments au GridPane (formulaire)
		rightSide.setAlignment(Pos.CENTER);
		rightSide.add(ajouterEditeur, 0, 0, 2, 1);
		rightSide.add(nomLabel, 0, 2, 1, 1);
		rightSide.add(nom, 0, 3, 1, 1);
		rightSide.add(prenomLabel, 2, 2, 1, 1);
		rightSide.add(prenom, 2, 3, 1, 1);
		rightSide.add(cursusLabel, 0, 5, 1, 1);
		rightSide.add(email, 0, 6, 1, 1);
		rightSide.add(promotionLabel, 2, 5, 1, 1);
		rightSide.add(motdepasse, 2, 6, 1, 1);
		rightSide.add(departementLabel, 0, 7, 1, 1);
		rightSide.add(departement, 0, 8, 1, 1);
		rightSide.add(valider, 1, 10);

		// margin du bouton valider
		GridPane.setMargin(valider, new Insets(20, 0, 0, 0));

		rightSide.setVgap(10);
		rightSide.setHgap(5);

		// Ajout VBox gauche et la GridPane dans borderPane
		this.setLeft(leftside);
		this.setRight(rightSide);

		// liste pour la choiceBox

		List<String> dept = new ArrayList<String>();
		for (int i = 1; i < 96; i++) {
			dept.add(String.valueOf(i));
		}
		dept.add("971");
		dept.add("972");
		dept.add("973");
		dept.add("974");
		dept.add("976");
		departement.getItems().addAll(dept);
		departement.getSelectionModel().select(0);

		// on ajoute du comportement au bouton accueil
		boutonAccueil.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Annuaire annuaire = new Annuaire();
				PageAccueil pageAccueil = new PageAccueil(annuaire);
				try {
					pageAccueil.setPromotion(annuaire.lireFichierObservable());
				} catch (IOException e) {
					e.printStackTrace();
				}
				boutonAccueil.getScene().setRoot(pageAccueil);
			}

		});

		
		
		
		


	}
}
package fr.isika.cda28.projet1.Annuaire.FrontEnd;

import java.io.IOException;
import java.util.List;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Annuaire;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Noeud;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Stagiaire;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Utilisateurs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class PageAjouterStagiaire extends BorderPane {
	
	// Attributs
	private Annuaire annuaire;
	private List<Stagiaire> stagiaires;
	private Utilisateurs utilisateurs;
	
	// On instancie un label "Ajouter un éditeur"
	private Label labelAjouterStagiaire = new Label("Ajouter un stagiaire");

	// Déclaration des labels et textfields pour le gridPane
	private Label nomLabel = new Label("Nom");
	private TextField champNom = new TextField();

	private Label prenomLabel = new Label("Prénom");
	private TextField champPrenom = new TextField();

	private Label cursusLabel = new Label("Cursus");
	private TextField champCursus = new TextField();

	private Label promotionLabel = new Label("Année de la promotion");
	private TextField champPromotion = new TextField();

	private Label departementLabel = new Label("Departement");
	private TextField champDepartement = new TextField();

	// Déclaration des boutons
	private Button boutonAnnuaire = new Button("Annuaire");
	private Button boutonAccueil = new Button("Accueil");

	// Bouton valider editeur
	private Button boutonValider = new Button("Valider");

	// Logo
	private Image logo = new Image(getClass().getResourceAsStream("/mesFichiers/logo_Projet1.png"));
	private ImageView logoImageView = new ImageView(logo);

	// Declaration VBox et GridPane
	VBox coteGauche = new VBox(450); // Conteneur à gauche pour le logo et les boutons
	VBox coteGaucheBoutons = new VBox(20); // Conteneur des boutons à gauche
	GridPane coteDroit = new GridPane(); // Conteneur droite pour le GridPane

	// Constructeur de la page AjouterStagiaire
	public PageAjouterStagiaire(Annuaire annuaire, Utilisateurs utilisateurs) {
		super();
		this.utilisateurs = utilisateurs;
		// dimensions logo
		logoImageView.setFitWidth(140);
		logoImageView.setFitHeight(140);

		// mettre en gras
		labelAjouterStagiaire.setStyle("-fx-text-fill:white ;-fx-font-size:20px ;");

		// fond blanc
		setStyle("-fx-background-color:#172428");
		boutonAnnuaire.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonAnnuaire.setPrefSize(200, 20);
		boutonAccueil.setStyle("-fx-background-color: #4C5A6B ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonAccueil.setPrefSize(200, 20);

		// Definir taille de la VBox + ajout composants
		coteGauche.setPrefSize(220, 450);
		coteGauche.setStyle("-fx-background-color:#25333F");
		coteGauche.getChildren().addAll(logoImageView, coteGaucheBoutons);
		coteGaucheBoutons.getChildren().addAll(boutonAnnuaire, boutonAccueil);

		// alignement dans la VBox
		coteGauche.setAlignment(Pos.CENTER);
		coteGaucheBoutons.setAlignment(Pos.CENTER);

		nomLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		champNom.setPadding(new Insets(10, 15, 10, 15));
		champNom.setMaxWidth(700);
		champNom.setPromptText("Entrez le nom du stagiaire");

		prenomLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		champPrenom.setPadding(new Insets(10, 15, 10, 15));
		champPrenom.setMaxWidth(700);
		champPrenom.setPromptText("Entrez le prénom du stagiaire");

		cursusLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		champCursus.setPadding(new Insets(10, 15, 10, 15));
		champCursus.setMaxWidth(700);
		champCursus.setPromptText("Entrez le cursus du stagiaire");

		promotionLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		champPromotion.setPadding(new Insets(10, 15, 10, 15));
		champPromotion.setMaxWidth(700);
		champPromotion.setPromptText("Entrez l'année de promotion du stagiaire");

		departementLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		champDepartement.setPadding(new Insets(10, 15, 10, 15));
		champDepartement.setMaxWidth(700);
		champDepartement.setPromptText("Entrez le département (en chiffre)");

		boutonValider.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");

		// Ajouter les éléments au GridPane (formulaire)
		for (int i = 0; i < 2; i++) {
			coteDroit.getColumnConstraints().add(new ColumnConstraints(200));
		}
		for (int i = 0; i < 11; i++) {
			coteDroit.getRowConstraints().add(new RowConstraints(40));
		}
		coteDroit.setAlignment(Pos.CENTER);
		coteDroit.add(labelAjouterStagiaire, 0, 0, 2, 1);
		coteDroit.add(nomLabel, 0, 2, 1, 1);
		coteDroit.add(champNom, 0, 3, 1, 1);
		coteDroit.add(prenomLabel, 1, 2, 1, 1);
		coteDroit.add(champPrenom, 1, 3, 1, 1);
		coteDroit.add(cursusLabel, 0, 5, 1, 1);
		coteDroit.add(champCursus, 0, 6, 1, 1);
		coteDroit.add(promotionLabel, 1, 5, 1, 1);
		coteDroit.add(champPromotion, 1, 6, 1, 1);
		coteDroit.add(departementLabel, 0, 8, 1, 1);
		coteDroit.add(champDepartement, 0, 9, 1, 1);
		coteDroit.add(boutonValider, 0, 11, 2, 1);

		GridPane.setHalignment(boutonValider, javafx.geometry.HPos.CENTER);

		coteDroit.setVgap(5);
		coteDroit.setHgap(50);

		// Ajout VBox gauche et la GridPane dans borderPane
		this.setLeft(coteGauche);
		this.setCenter(coteDroit);

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
		
		// on ajoute du comportement au bouton valider
		boutonValider.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Annuaire annuaire = new Annuaire();

				String nom = champNom.getText();
				String prenom = champPrenom.getText();
				String cursus = champCursus.getText();
				int promotion = Integer.parseInt(champPromotion.getText());
				String departement = champDepartement.getText();
				Stagiaire nouveauStagiaire = new Stagiaire(nom, prenom, departement, cursus, promotion);
				Noeud nouveauNoeud = new Noeud(nouveauStagiaire, -1, -1);
				try {
					annuaire.ajouterStagiaire(nouveauNoeud);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ajout réalisé");
				alert.setHeaderText(null);
				alert.setContentText("Félicitations vous avez ajouté un nouveau stagiaire");
				alert.showAndWait();
				champNom.clear();
				champPrenom.clear();
				champCursus.clear();
				champPromotion.clear();
				champDepartement.clear();
			}
		});
		
		// on ajoute du comportement au bouton annuaire
		boutonAnnuaire.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Annuaire annuaire = new Annuaire();
				PageAdminEdit pageAdminEdit = new PageAdminEdit(annuaire, utilisateurs);
				boutonAnnuaire.getScene().setRoot(pageAdminEdit);
			}
		});
	}// *************** Ici se termine le constructeur de la page AjouterStagiaire ***************
}
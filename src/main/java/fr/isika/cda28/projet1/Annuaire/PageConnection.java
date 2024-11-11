package fr.isika.cda28.projet1.Annuaire;

import java.io.IOException;
import java.util.ArrayList;

import fr.isika.cda28.projet1.Annuaire.Administrateur;
import fr.isika.cda28.projet1.Annuaire.Annuaire;
import fr.isika.cda28.projet1.Annuaire.Authentification;
import fr.isika.cda28.projet1.Annuaire.Utilisateurs;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PageConnection extends BorderPane {
	private ObservableList<Stagiaire> stagiaires;
	
	// Initialisation des éléments de la page "connection"
	private HBox header = new HBox(20);
	private VBox mainContent = new VBox(30);

	// Initialisation des label invitant à se connecter
	private Label labelTitre = new Label("Connectez-vous");
	private Label labelEmail = new Label("Email");
	private Label labelMotDePasse = new Label("Mot de passe");

	// Initialisation de l'image
	private Image logo = new Image(getClass().getResourceAsStream("/mesFichiers/logo_blanc_ligne.png"));
	private ImageView logoImageView = new ImageView(logo);

	// Initialisation des boutons
	private Button boutonAccueil = new Button("Accueil");
	private Button boutonValider = new Button("Valider");

	// Initialisation des champs à remplir
	TextField champEmail = new TextField();
	PasswordField champMotDePasse = new PasswordField();

	// Constructeur
	public PageConnection(Annuaire annuaire, ObservableList<Stagiaire> stagiaires) {
		super();
		// Initialisation de la liste des stagiaires (peut être une liste vide ou chargée d'une source de données)

		// ajout du style au graphe + logos
		logoImageView.setFitWidth(200);
		logoImageView.setFitHeight(81);

		setStyle("-fx-background-color:#172428");
		labelTitre.setStyle("-fx-text-fill:white ;-fx-font-size:40px ;");
		labelEmail.setStyle("-fx-text-fill:white ;-fx-font-size:15px ;");
		labelMotDePasse.setStyle("-fx-text-fill:white ;-fx-font-size:15px ;");
		champEmail.setMaxWidth(200);
		champEmail.setPrefHeight(30);
		champMotDePasse.setMaxWidth(200);
		champMotDePasse.setPrefHeight(30);
		boutonValider.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonAccueil.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");

		champEmail.setPromptText("Entrez votre Email");
		champMotDePasse.setPromptText("Entrez votre Mot de Passe");

		// on définit les espaces et positions
		header.setPadding(new Insets(20, 20, 20, 20));
		mainContent.setAlignment(Pos.CENTER);
		header.setPrefSize(800, 80); // Taille fixe pour le header
		HBox.setHgrow(logoImageView, Priority.ALWAYS); // L'image peut s'étirer
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		VBox.setMargin(labelTitre, new Insets(0, 0, 50, 0));

		// j'ajoute mon logo et mon bouton accueil au header
		header.getChildren().addAll(logoImageView, spacer, boutonAccueil);

		// j'ajoute mes labels, mes champs et mon bouton valider au mainContent
		mainContent.getChildren().addAll(labelTitre, labelEmail, champEmail, labelMotDePasse, champMotDePasse,
				boutonValider);

		// j'ajoute mon header et mon mainContent au BorderPane
		this.setTop(header);
		this.setCenter(mainContent);

		// Ajout du comportement au bouton "accueil"
		boutonAccueil.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Annuaire annuaire = new Annuaire();
				PageAccueil pageAccueil = new PageAccueil(annuaire);
				boutonAccueil.getScene().setRoot(pageAccueil);
			}

		});
		// Ajout du comportement au bouton "valider"
		boutonValider.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Authentification authentifcation = new Authentification();
				String userID = champEmail.getText();
				String password = champMotDePasse.getText();

				Utilisateurs utilisateurConnecte = authentifcation.authenticate(userID, password);

				if (utilisateurConnecte!=null) {
					Annuaire annuaire = new Annuaire();
					PageAdminEdit pageAdminEdit  = new PageAdminEdit(annuaire, stagiaires, utilisateurConnecte);
					boutonValider.getScene().setRoot(pageAdminEdit);
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Connexion échouée");
					alert.setHeaderText(null);
					alert.setContentText("Identifiant ou mot de passe incorrect");
					alert.showAndWait();
				}
			}

		});
	}

	// Getters et Setters

	public Label getLabelTitre() {
		return labelTitre;
	}

	public void setLabelTitre(Label labelTitre) {
		this.labelTitre = labelTitre;
	}

	public Label getLabelEmail() {
		return labelEmail;
	}

	public void setLabelEmail(Label labelEmail) {
		this.labelEmail = labelEmail;
	}

	public Label getLabelMotDePasse() {
		return labelMotDePasse;
	}

	public void setLabelMotDePasse(Label labelMotDePasse) {
		this.labelMotDePasse = labelMotDePasse;
	}

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}

	public ImageView getLogoImageView() {
		return logoImageView;
	}

	public void setLogoImageView(ImageView logoImageView) {
		this.logoImageView = logoImageView;
	}

	public Button getBoutonAccueil() {
		return boutonAccueil;
	}

	public void setBoutonAccueil(Button boutonAccueil) {
		this.boutonAccueil = boutonAccueil;
	}

	public Button getBoutonValider() {
		return boutonValider;
	}

	public void setBoutonValider(Button boutonValider) {
		this.boutonValider = boutonValider;
	}

	public TextField getChampEmail() {
		return champEmail;
	}

	public void setChampEmail(TextField champEmail) {
		this.champEmail = champEmail;
	}

	public TextField getChampMotDePasse() {
		return champMotDePasse;
	}

	public void setChampMotDePasse(PasswordField champMotDePasse) {
		this.champMotDePasse = champMotDePasse;
	}

	public HBox getHeader() {
		return header;
	}

	public void setHeader(HBox header) {
		this.header = header;
	}

	public VBox getMainContent() {
		return mainContent;
	}

	public void setMainContent(VBox mainContent) {
		this.mainContent = mainContent;
	}
	public void setPromotion(ObservableList<Stagiaire> observableList) {
		this.stagiaires = observableList;
	}

}
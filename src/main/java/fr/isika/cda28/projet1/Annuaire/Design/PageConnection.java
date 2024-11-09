package fr.isika.cda28.projet1.Annuaire.Design;

import fr.isika.cda28.projet1.Annuaire.Fonctionnalités.Annuaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PageConnection extends BorderPane {
	private Annuaire annuaire;
	// Initialisation des éléments de la page "connection"
	private HBox header = new HBox(20);
	private VBox mainContent = new VBox(30);

	// Initialisation des label invitant à se connecter
	private Label labelTitre = new Label("Connectez-vous");
	private Label labelEmail = new Label("Email                                         ");
	private Label labelMotDePasse = new Label("Mot de passe                          ");

	// Initialisation de l'image
	private Image logo = new Image(getClass().getResourceAsStream("/mesFichiers/logo_blanc_ligne.png"));
	private ImageView logoImageView = new ImageView(logo);

	// Initialisation des boutons
	private Button boutonAccueil = new Button("Accueil");
	private Button boutonValider = new Button("Valider");

	// Initialisation des champs à remplir
	TextField champEmail = new TextField();
	TextField champMotDePasse = new TextField();

	// Constructeur
	public PageConnection() {
		super();

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
				PageAccueil pageAccueil = new PageAccueil(annuaire);
				boutonAccueil.getScene().setRoot(pageAccueil);
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

	public void setChampMotDePasse(TextField champMotDePasse) {
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

}
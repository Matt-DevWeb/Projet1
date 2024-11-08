package fr.isika.cda28.projet1.Annuaire.Design;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PageAccueil extends BorderPane {

	// On instancie notre messsage de bienvenue
	private Label bienvenue = new Label("Bienvenue sur l'annuaire de l'entreprise");

	// On instancie l'image
	private Image logo = new Image(getClass().getResourceAsStream("/mesFichiers/logo_blanc_ligne.png"));
	private ImageView logoImageView = new ImageView(logo);

	// On instancie nos boutons
	private Button connexion = new Button("Se connecter");
	private Button recherche = new Button("Rechercher");
	private Button consulter = new Button("Consulter l'annuaire");

	// On instancie les HBox et VBox qui sont contenu dans le BorderPane
	private HBox header = new HBox(20);
	private VBox mainContent = new VBox(20);
	private VBox buttonContent = new VBox(20);

	public PageAccueil() {
		super();

		setPrefSize(800, 450);
		logoImageView.setFitWidth(200);
		logoImageView.setFitHeight(81);

		setStyle("-fx-background-color:#172428");
		bienvenue.setStyle("-fx-text-fill:white ;-fx-font-size:30px ;");

		// on initialise les espaces et positions
		header.setPadding(new Insets(20, 20, 20, 20));
		mainContent.setAlignment(Pos.CENTER);
		buttonContent.setAlignment(Pos.CENTER);

		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		connexion.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		recherche.setStyle("-fx-background-color: #334255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		consulter.setStyle("-fx-background-color: #334255 ; -fx-text-fill: white; -fx-font-size: 16px;");

		// on initialise la taille du header

		header.setPrefSize(800, 80); // Taille fixe pour le header
		header.setHgrow(logoImageView, Priority.ALWAYS); // L'image peut s'étirer
		// On ajoute les images et bouton au Header
		header.getChildren().addAll(logoImageView, spacer, connexion);
		// on ajoute nos boutons a la VBo buttonContent
		buttonContent.getChildren().addAll(recherche, consulter);

		// On ajoute VBox et label au maincontent

		mainContent.getChildren().addAll(bienvenue, buttonContent);
		VBox.setVgrow(bienvenue, Priority.ALWAYS); // Le label peut se redimensionner verticalement
		// On instancie les HBox et VBox dans le BorderPane
		this.setTop(header);
		this.setCenter(mainContent);
		// Ajout du comportement au bouton "connexion"
		connexion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				PageConnection pageConnection = new PageConnection();
				connexion.getScene().setRoot(pageConnection);
			}

		});
	}

	public Label getBienvenue() {
		return bienvenue;
	}

	public void setBienvenue(Label bienvenue) {
		this.bienvenue = bienvenue;
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

	public Button getConnexion() {
		return connexion;
	}

	public void setConnexion(Button connexion) {
		this.connexion = connexion;
	}

	public Button getRecherche() {
		return recherche;
	}

	public void setRecherche(Button recherche) {
		this.recherche = recherche;
	}

	public Button getConsulter() {
		return consulter;
	}

	public void setConsulter(Button consulter) {
		this.consulter = consulter;
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

	public VBox getButtonContent() {
		return buttonContent;
	}

	public void setButtonContent(VBox buttonContent) {
		this.buttonContent = buttonContent;
	}

}

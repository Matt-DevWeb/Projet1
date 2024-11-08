package fr.isika.cda28.projet1.Annuaire.Design;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PageAccueil extends BorderPane {
	// On instancie notre messsage de bienvenue
	private Label bienvenue = new Label("Bienvenue sur l'annuaire de l'entreprise");

	// On instancie l'image
	private Image logo = new Image(getClass().getResourceAsStream("/mesFichiers/LogoProjet1.png"));
	private ImageView logoImageView = new ImageView(logo);

	// On instancie nos boutons
	private Button connexion = new Button("Se connecter");
	private Button recherche = new Button("Rechercher");
	private Button consulter = new Button("Consulter l'annuaire");

	// On instancie les HBox et VBox qui sont contenu dans le BorderPane
	private HBox header = new HBox(590);
	private VBox mainContent = new VBox(50);
	private VBox buttonContent = new VBox(20);

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

	public PageAccueil() {
		super();

		setPrefSize(800, 450);
		logoImageView.setFitWidth(80);
		logoImageView.setFitHeight(80);

		setStyle("-fx-background-color:white");

		// on initialise les espaces et positions
		header.setPadding(new Insets(20, 20, 20, 20));
		mainContent.setAlignment(Pos.CENTER);
		buttonContent.setAlignment(Pos.CENTER);

		// on initialise la taille du header
		header.setPrefSize(800, 80);

		// On ajoute les images et bouton au Header
		header.getChildren().addAll(logoImageView, connexion);
		// on ajoute nos boutons a la VBo buttonContent
		buttonContent.getChildren().addAll(recherche, consulter);

		// On ajoute VBox et label au maincontent
		mainContent.getChildren().addAll(bienvenue, buttonContent);

		// On instancie les HBox et VBox dans le BorderPane
		this.setTop(header);
		this.setCenter(mainContent);

	}

}

package fr.isika.cda28.projet1.Annuaire.FrontEnd;

import fr.isika.cda28.projet1.Annuaire.BackEnd.Annuaire;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Stagiaire;
import javafx.collections.ObservableList;
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
	
	// Attributs
	private ObservableList<Stagiaire> stagiaires;
	private Annuaire annuaire;

	// Label pour afficher un message de bienvenue
	private Label labelBienvenue = new Label("Bienvenue sur l'annuaire \nde Dev'Up academy");

	// ImageView pour afficher le logo de l'entreprise
	private Image logo = new Image(getClass().getResourceAsStream("/mesFichiers/logo_blanc_ligne.png"));
	private ImageView logoImageView = new ImageView(logo);

	// Boutons pour les différentes actions disponibles sur la page d'accueil
	private Button boutonConnexion = new Button("Se connecter");
	private Button boutonConsulter = new Button("Consulter l'annuaire");

	// Conteneurs HBox et VBox utilisés pour organiser les éléments sur la page
	private HBox hBoxHeader = new HBox(20);
	private VBox vBoxMainContent = new VBox(20);
	private VBox vBoxButtonContent = new VBox(20);

	// Constructeur de la page d'accueil
	public PageAccueil(Annuaire annuaire) {
		super();
		this.annuaire = annuaire;

		// Définir la taille du logo
		logoImageView.setFitWidth(200);
		logoImageView.setFitHeight(81);

		// Définir le style général de la page
		setStyle("-fx-background-color:#172428");
		labelBienvenue.setStyle("-fx-text-fill:white ;-fx-font-size:40px; -fx-text-alignment : center;");

		// Configurer les espaces et l'alignement des conteneurs
		hBoxHeader.setPadding(new Insets(20, 20, 20, 20));
		vBoxMainContent.setAlignment(Pos.CENTER);
		vBoxButtonContent.setAlignment(Pos.CENTER);

		// Créer un espaceur pour pousser les éléments à gauche ou à droite
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS); // Permet au spacer de prendre tout l'espace disponible

		// Style des boutons
		boutonConnexion.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonConsulter.setStyle("-fx-background-color: #334255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonConsulter.setPrefSize(200, 20);

		// Configurer la taille du header (barre supérieure)
		hBoxHeader.setPrefSize(800, 80); // Taille fixe pour le header
		HBox.setHgrow(logoImageView, Priority.ALWAYS); // Permet au logo de s'étendre si nécessaire

		// Ajouter le logo et le bouton de connexion au header
		hBoxHeader.getChildren().addAll(logoImageView, spacer, boutonConnexion);

		// Espaces pour ajuster la position verticale du label de bienvenue
		Region topSpacer = new Region(); // Espace en haut
		Region bottomSpacer = new Region(); // Espace en bas
		VBox.setVgrow(topSpacer, Priority.ALWAYS);
		VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

		// Définir une marge pour remonter le label de bienvenue
		VBox.setMargin(labelBienvenue, new Insets(0, 0, 100, 0));

		// Conteneur pour centrer le label de bienvenue verticalement
		VBox labelContainer = new VBox(20);
		labelContainer.setAlignment(Pos.CENTER);
		labelContainer.getChildren().addAll(topSpacer, labelBienvenue, bottomSpacer);

		// Ajouter les boutons à la VBox buttonContent
		vBoxButtonContent.getChildren().addAll(boutonConsulter);

		// Ajouter le label de bienvenue et les boutons au conteneur principal
		vBoxMainContent.getChildren().addAll(labelBienvenue, vBoxButtonContent);
		VBox.setVgrow(labelBienvenue, Priority.ALWAYS); // Le label peut s'étendre verticalement si nécessaire

		// Ajouter le header (haut de page) et le contenu principal (centre de la page)
		this.setTop(hBoxHeader);
		this.setCenter(vBoxMainContent);

		// Ajouter le comportement au bouton "connexion"
		boutonConnexion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Annuaire annuaire = new Annuaire();
				// Lorsque le bouton "Se connecter" est cliqué, passer à la page de connexion
				PageConnexion pageConnection = new PageConnexion(annuaire);
				boutonConnexion.getScene().setRoot(pageConnection);
			}
		});

		// Ajouter le comportement au bouton "consulter"
		boutonConsulter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Annuaire annuaire = new Annuaire();
				PageVisiteurs pageVisiteurs = new PageVisiteurs(annuaire);
				boutonConsulter.getScene().setRoot(pageVisiteurs);
			}
		});

	} // *************** Ici se termine le constructeur de la page d'accueil ***************

	// Getters et Setters pour les éléments de la page
	public Label getBienvenue() {
		return labelBienvenue;
	}

	public void setBienvenue(Label bienvenue) {
		this.labelBienvenue = bienvenue;
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
		return boutonConnexion;
	}

	public void setConnexion(Button connexion) {
		this.boutonConnexion = connexion;
	}

	public Button getConsulter() {
		return boutonConsulter;
	}

	public void setConsulter(Button consulter) {
		this.boutonConsulter = consulter;
	}

	public HBox getHeader() {
		return hBoxHeader;
	}

	public void setHeader(HBox header) {
		this.hBoxHeader = header;
	}

	public VBox getMainContent() {
		return vBoxMainContent;
	}

	public void setMainContent(VBox mainContent) {
		this.vBoxMainContent = mainContent;
	}

	public VBox getButtonContent() {
		return vBoxButtonContent;
	}

	public void setButtonContent(VBox buttonContent) {
		this.vBoxButtonContent = buttonContent;
	}

	public void setPromotion(ObservableList<Stagiaire> observableList) {
		this.stagiaires = observableList;
	}
}

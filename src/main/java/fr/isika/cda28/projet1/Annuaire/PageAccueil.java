package fr.isika.cda28.projet1.Annuaire;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

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
	// on instancie une liste de stagiaires;
	private ArrayList<Stagiaire> promotion;

	// Label pour afficher un message de bienvenue
	private Label bienvenue = new Label("Bienvenue sur l'annuaire de la Dev'Up academy");

	// ImageView pour afficher le logo de l'entreprise
	private Image logo = new Image(getClass().getResourceAsStream("/mesFichiers/logo_blanc_ligne.png"));
	private ImageView logoImageView = new ImageView(logo);

	// Boutons pour les différentes actions disponibles sur la page d'accueil
	private Button connexion = new Button("Se connecter");
	private Button recherche = new Button("Rechercher");
	private Button consulter = new Button("Consulter l'annuaire");

	// Conteneurs HBox et VBox utilisés pour organiser les éléments sur la page
	private HBox header = new HBox(20);
	private VBox mainContent = new VBox(20);
	private VBox buttonContent = new VBox(20);

	
	// Constructeur de la page d'accueil
	public PageAccueil() {
		super();
		
		this.promotion = new ArrayList<Stagiaire>();
		// Définir la taille de la fenêtre principale
//		setPrefSize(1370, 1080);

		// Définir la taille du logo
		logoImageView.setFitWidth(200);
		logoImageView.setFitHeight(81);

		// Définir le style général de la page
		setStyle("-fx-background-color:#172428");
		bienvenue.setStyle("-fx-text-fill:white ;-fx-font-size:40px;");

		// Configurer les espaces et l'alignement des conteneurs
		header.setPadding(new Insets(20, 20, 20, 20));
		mainContent.setAlignment(Pos.CENTER);
		buttonContent.setAlignment(Pos.CENTER);

		// Créer un espaceur pour pousser les éléments à gauche ou à droite
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS); // Permet au spacer de prendre tout l'espace disponible

		// Style des boutons
		connexion.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		recherche.setStyle("-fx-background-color: #334255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		consulter.setStyle("-fx-background-color: #334255 ; -fx-text-fill: white; -fx-font-size: 16px;");

		// Configurer la taille du header (barre supérieure)
		header.setPrefSize(800, 80); // Taille fixe pour le header
		HBox.setHgrow(logoImageView, Priority.ALWAYS); // Permet au logo de s'étendre si nécessaire

		// Ajouter le logo et le bouton de connexion au header
		header.getChildren().addAll(logoImageView, spacer, connexion);

		// Espaces pour ajuster la position verticale du label de bienvenue
		Region topSpacer = new Region(); // Espace en haut
		Region bottomSpacer = new Region(); // Espace en bas
		VBox.setVgrow(topSpacer, Priority.ALWAYS);
		VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

		// Définir une marge pour remonter le label de bienvenue
		VBox.setMargin(bienvenue, new Insets(0, 0, 100, 0));

		// Conteneur pour centrer le label de bienvenue verticalement
		VBox labelContainer = new VBox(20);
		labelContainer.setAlignment(Pos.CENTER);
		labelContainer.getChildren().addAll(topSpacer, bienvenue, bottomSpacer);

		// Ajouter les boutons à la VBox buttonContent
		buttonContent.getChildren().addAll(recherche, consulter);

		// Ajouter le label de bienvenue et les boutons au conteneur principal
		mainContent.getChildren().addAll(bienvenue, buttonContent);
		VBox.setVgrow(bienvenue, Priority.ALWAYS); // Le label peut s'étendre verticalement si nécessaire

		// Ajouter le header (haut de page) et le contenu principal (centre de la page)
		this.setTop(header);
		this.setCenter(mainContent);

		// Ajouter le comportement au bouton "connexion" pour changer de page
		connexion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Lorsque le bouton "Se connecter" est cliqué, passer à la page de connexion
				PageConnection pageConnection = new PageConnection();
				connexion.getScene().setRoot(pageConnection);
			}
		});
		consulter.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				PageVisiteurs pageVisiteurs = new PageVisiteurs(promotion);
				consulter.getScene().setRoot(pageVisiteurs);

			}

		});
	}

	// Getters et Setters pour les éléments de la page
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
	public void setPromotion(ArrayList<Stagiaire> promotion) {
		this.promotion = promotion;
	}
}

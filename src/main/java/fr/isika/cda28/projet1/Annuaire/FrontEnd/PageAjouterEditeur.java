package fr.isika.cda28.projet1.Annuaire.FrontEnd;

import java.io.IOException;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Annuaire;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Editeur;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Utilisateurs;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class PageAjouterEditeur extends BorderPane {
	
	// Attributs
	private Utilisateurs utilisateurs;

	// On instancie un label "Ajouter un éditeur"
	private Label labelAjouterEditeur = new Label("Ajouter un éditeur");

	// Déclaration des labels et textfields pour le gridPane
	private Label labelUtilisateur = new Label("Nom d'utilisateur");
	private TextField champUtilisateur = new TextField();
	private Label labelMotDePasse = new Label("Mot de passe");
	private PasswordField champMotDePasse = new PasswordField();

	// Déclaration des boutons
	private Button boutonAnnuaire = new Button("Annuaire");
	private Button boutonAccueil = new Button("Accueil");

	// Bouton valider editeur
	private Button boutonValider = new Button("Valider");

	// Logo
	private Image logo = new Image(getClass().getResourceAsStream("/mesFichiers/logo_Projet1.png"));
	private ImageView logoImageView = new ImageView(logo);

	// Declaration VBox et GridPane
	VBox vBoxCoteGauche = new VBox(450); // Conteneur à gauche pour le logo et les boutons
	VBox vBoxLeftSideButtons = new VBox(20); // Conteneur des boutons à gauche
	GridPane coteDroit = new GridPane(); // Conteneur droite pour le GridPane

	// Constructeur de la page AjouterEditeur
	public PageAjouterEditeur(Annuaire annuaire, Utilisateurs utilisateurs) {
		super();
		this.utilisateurs = utilisateurs;

		// dimensions logo
		logoImageView.setFitWidth(140);
		logoImageView.setFitHeight(140);

		// mettre en gras
		labelAjouterEditeur.setStyle("-fx-text-fill:white ;-fx-font-size:30px ;");

		// fond blanc
		setStyle("-fx-background-color:#172428");
		boutonAnnuaire.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonAnnuaire.setPrefSize(200, 20);
		
		//Stylisation du bouton d'accueil
		boutonAccueil.setStyle("-fx-background-color: #4C5A6B ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonAccueil.setPrefSize(200, 20);
		
		// Definir taille de la VBox + ajout composants
		vBoxCoteGauche.setPrefSize(220, 450);
		vBoxCoteGauche.getChildren().addAll(logoImageView, vBoxLeftSideButtons);
		vBoxLeftSideButtons.getChildren().addAll(boutonAnnuaire, boutonAccueil);

		// Modifier la couleur de fond gauche
		vBoxCoteGauche.setStyle("-fx-background-color:#25333F");

		// alignement dans la VBox
		vBoxCoteGauche.setAlignment(Pos.CENTER);
		vBoxLeftSideButtons.setAlignment(Pos.CENTER);

		labelUtilisateur.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		champUtilisateur.setPromptText("Créez le nom d'utilisateur de l'éditeur");
		champUtilisateur.setPadding(new Insets(10, 15, 10, 15));
		champUtilisateur.setMaxWidth(700);
		
		labelMotDePasse.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
		champMotDePasse.setPromptText("Créez le mot de passe de l'éditeur");
		champMotDePasse.setPadding(new Insets(10, 15, 10, 15));
		champMotDePasse.setMaxWidth(700);
		
		boutonValider.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");

		// Ajouter les éléments au GridPane (formulaire)
				for (int i = 0; i < 2; i++) {
					coteDroit.getColumnConstraints().add(new ColumnConstraints(200));
				}
				for (int i = 0; i < 5; i++) {
					coteDroit.getRowConstraints().add(new RowConstraints(40));
				}
				
		// Ajouter les éléments au GridPane (formulaire)
		coteDroit.setAlignment(Pos.CENTER);
		coteDroit.add(labelAjouterEditeur, 0, 0, 2, 1);
		coteDroit.add(labelUtilisateur, 0, 2, 1, 1);
		coteDroit.add(champUtilisateur, 0, 3, 1, 1);
		coteDroit.add(labelMotDePasse, 1, 2, 1, 1);
		coteDroit.add(champMotDePasse, 1, 3, 1, 1);
		coteDroit.add(boutonValider, 0, 5, 2, 1);

		// Alignement du bouton valider
		GridPane.setHalignment(boutonValider, javafx.geometry.HPos.CENTER);
		coteDroit.setVgap(5);
		coteDroit.setHgap(50);

		// Ajout VBox gauche et la GridPane dans borderPane
		this.setLeft(vBoxCoteGauche);
		this.setCenter(coteDroit);

		// on ajoute du comportement au bouton acceuil
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
				String email = champUtilisateur.getText();
				String motDepasse = champMotDePasse.getText();
				Editeur nouveauEditeur = new Editeur(email, motDepasse);
				
				annuaire.ajouterEditeur(nouveauEditeur,true);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ajout réalisé");
				alert.setHeaderText(null);
				alert.setContentText("L'éditeur a été ajouté avec succès");
				alert.showAndWait();
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
	} // *************** Ici se termine le constructeur de la page AjouterEditeur ***************
}

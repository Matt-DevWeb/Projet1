package fr.isika.cda28.projet1.Annuaire;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PageAdminEdit extends BorderPane {

	private Annuaire annuaire;
	public TableView<Annuaire> tableViewStagiaire;

	// On instancie les labels
	private Label bienvenue = new Label("Bienvenue !");
	private Label listeStagiaire = new Label("Liste des stagiaires de l'entreprise.");

	// On instancie l'image
	private Image logo = new Image(getClass().getResourceAsStream("/mesFichiers/logo_Projet1.png"));
	private ImageView logoImageView = new ImageView(logo);

	// On instancie nos boutons
	private Button connexion = new Button("Se connecter");
	private Button imprimer = new Button("Imprimer la liste");
	private Button accueil = new Button("Accueil");
	private Button recherche = new Button("Rechercher");
	private Button trier = new Button("Trier");
	private Button mettreAjour = new Button ("Mettre a jour un stagiaire");
	private Button ajoutStagiaire = new Button("Ajouter un stagiaire");
	private Button suppStagiaire = new Button("Supprimer un stagiaire");
	private Button ajoutEditeur  = new Button("Ajouter un editeur");
	private Button suppEditeur = new Button("Supprimer un editeur ");
	

	// On instancie le TextField
	private TextField zoneRecherche = new TextField();

	// On instancie les HBox et VBox qui sont contenu dans le BorderPane
	private VBox coteGauche = new VBox(50);
	private VBox coteGaucheBoutons = new VBox(20);
	private VBox contenuPrincipal = new VBox();
	private HBox bienvenueContenu = new HBox(350);
	private HBox rechercheContenu = new HBox(5);
	private HBox listeTriContenu = new HBox(300);

	public PageAdminEdit(Annuaire annuaire) {
		super();
		this.annuaire = annuaire;
		this.tableViewStagiaire = new TableView<Annuaire>();
		// taille de la page
//		setPrefSize(1366, 768);
		setStyle("-fx-background-color:#172428");
		// logo
		logoImageView.setFitWidth(140);
		logoImageView.setFitHeight(140);

		// COTE GAUCHE

		// on initialise la taille de la VBox coteGauche
		coteGauche.setPrefSize(220, 450);

		// On ajoute les images et la VBox du coteGauche
		coteGauche.getChildren().addAll(logoImageView, coteGaucheBoutons);

		// on ajoute nos boutons a la VBox coteGauche
		coteGaucheBoutons.getChildren().addAll(mettreAjour,ajoutStagiaire,suppStagiaire,ajoutEditeur,suppEditeur,imprimer,accueil);

		// On chane la change la couleur de fond de la partie gauche
		coteGauche.setStyle("-fx-background-color:#25333F");
		imprimer.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		accueil.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");

		// CENTRE DE PAGE
		recherche.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		connexion.setStyle("-fx-background-color:#324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		trier.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");

		// On change la couleur du texte des labels
		bienvenue.setStyle("-fx-text-fill: white; -fx-font-size:30px;");
		listeStagiaire.setStyle("-fx-text-fill: white; -fx-font-size:16px;");

		// On stylise le textField zoneRecherche
		zoneRecherche.setMaxWidth(300);
		zoneRecherche.setPrefHeight(30);
		zoneRecherche.setPromptText("Rechercher un stagiaire");

		// On ajoute le label bienvenue et le bouton connexion à la VBox
		// bienvenueContenu
		bienvenueContenu.getChildren().addAll(bienvenue, connexion);

		// On ajoute du padding à la HBox bienvenueContenu
		bienvenueContenu.setPadding(new Insets(30, 30, 30, 30));

		// On ajoute le TextField et le bouton à la HBox rechercheContenu
		rechercheContenu.getChildren().addAll(zoneRecherche, recherche);

		// On ajoute le label listeStagiaire et le bouton trier à la HBox
		// listeTriContenu
		listeTriContenu.getChildren().addAll(listeStagiaire, trier);

		// On ajoute du padding à la HBox listeTriContenu
		listeTriContenu.setPadding(new Insets(30, 30, 30, 30));

		// table VIEW
		tableViewStagiaire.setEditable(false);

		TableColumn<Annuaire, String> colonneNom = new TableColumn<>(" Nom ");
		colonneNom.setMinWidth(200);
		colonneNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

		TableColumn<Annuaire, String> colonnePrenom = new TableColumn<>(" Prenom ");
		colonnePrenom.setMinWidth(200);
		colonnePrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

		TableColumn<Annuaire, String> colonneDepartement = new TableColumn<>(" Departement ");
		colonneDepartement.setMinWidth(50);
		colonneDepartement.setCellValueFactory(new PropertyValueFactory<>("departement"));

		TableColumn<Annuaire, String> colonneCursus = new TableColumn<>(" Cursus ");
		colonneCursus.setMinWidth(150);
		colonneCursus.setCellValueFactory(new PropertyValueFactory<>("cursus"));

		TableColumn<Annuaire, Integer> colonnePromo = new TableColumn<>(" Année de la promo ");
		colonnePromo.setMinWidth(100);
		colonnePromo.setCellValueFactory(new PropertyValueFactory<>("anneePromo"));

		tableViewStagiaire.getColumns().addAll(colonneNom, colonnePrenom, colonneDepartement, colonneCursus,
				colonnePromo);

		tableViewStagiaire.setPlaceholder(new Label("Aucun stagiaire trouvé."));
		// permet au colonne d'utiliser tout l'espace disponible
		tableViewStagiaire.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		// permet de selectionner de multiples éléments
		tableViewStagiaire.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableViewStagiaire.setStyle("-fx-background-color: #324255");
		// Changer la couleur du texte dans la colonne nom
		colonneNom.setCellFactory(col -> {
			return new TableCell<Annuaire, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						setText(item);
						setStyle("-fx-text-fill: darkblue;"); // Changer la couleur du texte
					}
				}
			};
		});

//		tableViewStagiaire.setRowFactory(tv -> {
//		    TableRow<Annuaire> row = new TableRow<>();
//		    row.setStyle("-fx-background-color: #324255;");
//		    row.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
//		        if (isSelected) {
//		            row.setStyle("-fx-background-color: #BFD7EA; -fx-text-fill:#172428" );
//		        } else {
//		            row.setStyle("-fx-background-color: #172428");
//		        }
//		    });
//		    return row;
//		});

		tableViewStagiaire.getColumns().forEach(column -> {
			column.setStyle("-fx-background-color: #324255; -fx-text-fill: white;");
		});

		tableViewStagiaire.setItems(FXCollections.observableArrayList(this.annuaire));

		// On ajoute les HBox bienvenueContenu et rechercheContenu à la VBox
		// contenuPrincipal
		contenuPrincipal.getChildren().addAll(bienvenueContenu, rechercheContenu, listeTriContenu, tableViewStagiaire);

		// on initialise les espaces et positions des VBox et HBox
		coteGauche.setAlignment(Pos.CENTER);
		coteGaucheBoutons.setAlignment(Pos.CENTER);
		contenuPrincipal.setAlignment(Pos.CENTER);
		bienvenueContenu.setAlignment(Pos.CENTER);
		rechercheContenu.setAlignment(Pos.CENTER);
		listeTriContenu.setAlignment(Pos.CENTER);

		//on affiche et on cache les boutons en fontion du profil

		
		
		// on ajoute du comportement au bouton accueil
		accueil.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				PageAccueil pageAccueil = new PageAccueil();
				accueil.getScene().setRoot(pageAccueil);
			}

		});

		// On instancie les HBox et VBox dans le BorderPane
		this.setLeft(coteGauche);
		this.setCenter(contenuPrincipal);

	}

	public Label getBienvenue() {
		return bienvenue;
	}

	public void setBienvenue(Label bienvenue) {
		this.bienvenue = bienvenue;
	}

	public Label getListeStagiaire() {
		return listeStagiaire;
	}

	public void setListeStagiaire(Label listeStagiaire) {
		this.listeStagiaire = listeStagiaire;
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

	public Button getImprimer() {
		return imprimer;
	}

	public void setImprimer(Button imprimer) {
		this.imprimer = imprimer;
	}

	public Button getAccueil() {
		return accueil;
	}

	public void setAccueil(Button accueil) {
		this.accueil = accueil;
	}

	public Button getRecherche() {
		return recherche;
	}

	public void setRecherche(Button recherche) {
		this.recherche = recherche;
	}

	public Button getTrier() {
		return trier;
	}

	public void setTrier(Button trier) {
		this.trier = trier;
	}

	public TextField getZoneRecherche() {
		return zoneRecherche;
	}

	public void setZoneRecherche(TextField zoneRecherche) {
		this.zoneRecherche = zoneRecherche;
	}

	public VBox getCoteGauche() {
		return coteGauche;
	}

	public void setCoteGauche(VBox coteGauche) {
		this.coteGauche = coteGauche;
	}

	public VBox getCoteGaucheBoutons() {
		return coteGaucheBoutons;
	}

	public void setCoteGaucheBoutons(VBox coteGaucheBoutons) {
		this.coteGaucheBoutons = coteGaucheBoutons;
	}

	public VBox getContenuPrincipal() {
		return contenuPrincipal;
	}

	public void setContenuPrincipal(VBox contenuPrincipal) {
		this.contenuPrincipal = contenuPrincipal;
	}

	public HBox getBienvenueContenu() {
		return bienvenueContenu;
	}

	public void setBienvenueContenu(HBox bienvenueContenu) {
		this.bienvenueContenu = bienvenueContenu;
	}

	public HBox getRechercheContenu() {
		return rechercheContenu;
	}

	public void setRechercheContenu(HBox rechercheContenu) {
		this.rechercheContenu = rechercheContenu;
	}

	public HBox getListeTriContenu() {
		return listeTriContenu;
	}

	public void setListeTriContenu(HBox listeTriContenu) {
		this.listeTriContenu = listeTriContenu;
	}

}

package fr.isika.cda28.projet1.Annuaire;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

public class PageAdminEdit extends BorderPane {

	private TableView<Stagiaire> tableViewStagiaire;
	private ObservableList<Stagiaire> datas = FXCollections.observableArrayList();

	// On instancie les labels
	private Label labelBienvenue = new Label("Annuaire des stagiaires de la Dev'Up Academy !");
	private Label labelListeStagiaires = new Label("Liste des stagiaires de l'entreprise.");

	// On instancie l'image
	private Image logo = new Image(getClass().getResourceAsStream("/mesFichiers/logo_Projet1.png"));
	private ImageView logoImageView = new ImageView(logo);

	// On instancie nos boutons
	private Button boutonImprimer = new Button("Imprimer la liste");
	private Button boutonAccueil = new Button("Accueil");
	private Button boutonRecherche = new Button("Lancer la recherche");
	private Button boutonDeconnexion = new Button("Se déconnecter");
	private Button boutonMettreAjour = new Button("Modifier un stagiaire");
	private Button boutonAjoutStagiaire = new Button("Ajouter un stagiaire");
	private Button boutonSuppStagiaire = new Button("Supprimer un stagiaire");
	private Button boutonAjoutEditeur = new Button("Ajouter un editeur");
	private Button boutonSuppEditeur = new Button("Supprimer un editeur ");

	// On instancie le TextField
	private TextField zoneRecherche = new TextField();

	// On instancie les HBox et VBox qui sont contenu dans le BorderPane
	private VBox coteGauche = new VBox(180);

	private VBox coteGaucheBoutons = new VBox(20);
	private VBox contenuPrincipal = new VBox();
	private HBox bienvenueContenu = new HBox(350);
	private HBox rechercheContenu = new HBox(5);
	private HBox listeTriContenu = new HBox(300);
	// On instancie la ChoiceBox
	private ComboBox<String> criteres = new ComboBox();

	private Annuaire annuaire;

	public PageAdminEdit(Annuaire annuaire, ObservableList<Stagiaire> stagiaires, Utilisateurs utilisateurConnecte) {
		super();
		this.annuaire = annuaire;

		tableViewStagiaire = new TableView<>(FXCollections.observableArrayList(stagiaires));

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

		if (utilisateurConnecte.isAdmin()) {
			coteGaucheBoutons.getChildren().addAll(boutonMettreAjour, boutonAjoutStagiaire, boutonSuppStagiaire,
					boutonAjoutEditeur, boutonSuppEditeur, boutonImprimer, boutonAccueil);
		} else {
			coteGaucheBoutons.getChildren().addAll(boutonAjoutStagiaire, boutonImprimer, boutonAccueil);

		}
		// On change la couleur de fond de la partie gauche
		coteGauche.setStyle("-fx-background-color:#25333F");

		boutonImprimer.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonImprimer.setPrefSize(200, 20);

		boutonAccueil.setStyle("-fx-background-color: #4C5A6B ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonAccueil.setPrefSize(200, 20);

		boutonMettreAjour.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonMettreAjour.setPrefSize(200, 20);

		boutonAjoutStagiaire.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonAjoutStagiaire.setPrefSize(200, 20);

		boutonSuppStagiaire.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonSuppStagiaire.setPrefSize(200, 20);

		boutonAjoutEditeur.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonAjoutEditeur.setPrefSize(200, 20);

		boutonSuppEditeur.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonSuppEditeur.setPrefSize(200, 20);

		boutonDeconnexion.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonDeconnexion.setPrefSize(200, 20);
		// CENTRE DE PAGE
		boutonRecherche.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		criteres.setStyle("-fx-background-color:#324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		criteres.setPrefWidth(180); // Limite la largeur du ComboBox
		criteres.setMaxHeight(300); // Limite la hauteur du ComboBox, si nécessaire
		// On change la couleur du texte des labels
		labelBienvenue.setStyle("-fx-text-fill: white; -fx-font-size:20px;");
		// labelListeStagiaires.setStyle("-fx-text-fill: white; -fx-font-size:16px;");

		// On stylise le textField zoneRecherche
		zoneRecherche.setPadding(new Insets(10, 40, 10, 15));
		zoneRecherche.setMaxWidth(300);
		zoneRecherche.setPrefHeight(30);
		zoneRecherche.setPromptText("Rechercher un stagiaire");

		// On ajoute le label bienvenue et le bouton connexion à la VBox
		// bienvenueContenu
		bienvenueContenu.getChildren().addAll(labelBienvenue, boutonDeconnexion);

		// On ajoute du padding à la HBox bienvenueContenu
		bienvenueContenu.setPadding(new Insets(30, 30, 130, 30));
		// On ajoute le TextField et le bouton à la HBox rechercheContenu
		rechercheContenu.getChildren().addAll(zoneRecherche, criteres, boutonRecherche);
		rechercheContenu.setPadding(new Insets(10, 0, 40, 0));

		// On ajoute le label listeStagiaire et le bouton trier à la HBox
		// listeTriContenu
		// listeTriContenu.getChildren().addAll(boutonTrier);

		// On ajoute du padding à la HBox listeTriContenu
		// listeTriContenu.setPadding(new Insets(30, 30, 30, 30));

		// table VIEW
		tableViewStagiaire.setEditable(true);

		TableColumn<Stagiaire, String> colonneNom = new TableColumn<>();
		colonneNom.setMinWidth(200);
		colonneNom.setGraphic(titreTableView("Nom", Color.WHITE, 14));
		colonneNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

		TableColumn<Stagiaire, String> colonnePrenom = new TableColumn<>();
		colonnePrenom.setMinWidth(200);
		colonnePrenom.setGraphic(titreTableView("Prenom", Color.WHITE, 14));
		colonnePrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

		TableColumn<Stagiaire, String> colonneDepartement = new TableColumn<>();
		colonneDepartement.setMinWidth(50);
		colonneDepartement.setGraphic(titreTableView("Dpt", Color.WHITE, 14));
		colonneDepartement.setCellValueFactory(new PropertyValueFactory<>("departement"));

		TableColumn<Stagiaire, String> colonneCursus = new TableColumn<>();
		colonneCursus.setMinWidth(150);
		colonneCursus.setGraphic(titreTableView("Cursus", Color.WHITE, 14));
		colonneCursus.setCellValueFactory(new PropertyValueFactory<>("cursus"));

		TableColumn<Stagiaire, Integer> colonnePromo = new TableColumn<>();
		colonnePromo.setMinWidth(100);
		colonnePromo.setGraphic(titreTableView("Promo", Color.WHITE, 14));
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
			return new TableCell<Stagiaire, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						setText(item);
						setStyle("-fx-text-fill: white;"); // Changer la couleur du texte
					}
				}
			};
		});
		colonnePrenom.setCellFactory(col -> {
			return new TableCell<Stagiaire, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						setText(item);
						setStyle("-fx-text-fill: white;"); // Changer la couleur du texte
					}
				}
			};
		});
		colonneDepartement.setCellFactory(col -> {
			return new TableCell<Stagiaire, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						setText(item);
						setStyle("-fx-text-fill: white;"); // Changer la couleur du texte
					}
				}
			};
		});
		colonneCursus.setCellFactory(col -> {
			return new TableCell<Stagiaire, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						setText(item);
						setStyle("-fx-text-fill: white;"); // Changer la couleur du texte
					}
				}
			};
		});
		colonnePromo.setCellFactory(col -> {
			return new TableCell<Stagiaire, Integer>() {
				@Override
				protected void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						setText(item.toString());
						setStyle("-fx-text-fill: white;"); // Changer la couleur du texte
					}
				}
			};
		});

		tableViewStagiaire.setRowFactory(tv -> {
			TableRow<Stagiaire> row = new TableRow<>();
			row.setStyle("-fx-background-color: #324255;");
			row.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
				if (isSelected) {
					row.setStyle("-fx-background-color: #172428; -fx-text-fill:#172428");
				} else {
					row.setStyle("-fx-background-color: #324255");
				}
			});
			return row;
		});

		tableViewStagiaire.getColumns().forEach(column -> {
			column.setStyle("-fx-background-color: #324255; -fx-text-fill: white;");
		});

		tableViewStagiaire.setItems(stagiaires);

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

		// on affiche et on cache les boutons en fontion du profil

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

		boutonImprimer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					proposerTelechargementPDF();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		});

		boutonAjoutStagiaire.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				AjouterStagiaire ajouterStagiaire = new AjouterStagiaire(annuaire, stagiaires);
				boutonAjoutStagiaire.getScene().setRoot(ajouterStagiaire);

			}

		});
		boutonAjoutEditeur.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				AjouterEditeur ajouterEditeur = new AjouterEditeur(annuaire, stagiaires);
				boutonAjoutEditeur.getScene().setRoot(ajouterEditeur);

			}

		});

		boutonDeconnexion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Annuaire annuaire = new Annuaire();
				PageVisiteurs pageVisiteurs = new PageVisiteurs(annuaire, stagiaires);
				boutonDeconnexion.getScene().setRoot(pageVisiteurs);

			}

		});
		boutonRecherche.setOnAction(event -> filterStagiaires());
		// On instancie les HBox et VBox dans le BorderPane
		this.setLeft(coteGauche);
		this.setCenter(contenuPrincipal);
		datas.addAll(stagiaires);
		// Rempliere la ChoiceBox
		List<String> criters = new ArrayList<String>();
		
		criters.add("Rechercher par :");
		criters.add("Nom");
		criters.add("Prenom");
		criters.add("Cursus");
		criters.add("Departement");
		criters.add("Promotion");
		criteres.getItems().addAll(criters);
		criteres.getSelectionModel().select(0);
		
		criteres.setCellFactory(param -> {
			ListCell<String> cell = new ListCell<String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null) {
						setText(item);
						setTextFill(Color.WHITE); // Changer la couleur du texte
						setStyle("-fx-background-color: #324255;");
					} else {
						setText(null);
					}
				}
			};
			cell.setOnMouseEntered(event -> {
				criteres.setPrefWidth(179); // Limite la largeur du ComboBox
				criteres.setMaxHeight(299);
				cell.setStyle("-fx-background-color: #324255; -fx-border-color: white; -fx-border-width: 1px;"); // Contour
																													// Blanc
																													// au
																													// survol

			});

			cell.setOnMouseExited(event -> {
				cell.setStyle("-fx-background-color: #324255;"); // Enlever le contour au survol
			});
			return cell;
		});

		criteres.setButtonCell(new ListCell<String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null) {
					setText(item);
					setTextFill(Color.WHITE); // Changer la couleur du texte
				} else {
					setText(null);
				}
			}
		});

	}

	private void filterStagiaires() {
		String critere = criteres.getValue().toLowerCase();
		String value = zoneRecherche.getText().toLowerCase();

		FilteredList<Stagiaire> listFiltre = new FilteredList<>(datas, stagiaire -> {
			switch (critere) {
			case "nom":
				return stagiaire.getNom().equalsIgnoreCase(value);
			case "prenom":
				return stagiaire.getPrenom().equalsIgnoreCase(value);
			case "departement":
				return stagiaire.getDepartement().equalsIgnoreCase(value);
			case "cursus":
				return stagiaire.getCursus().equalsIgnoreCase(value);
			case "promotion":
				return String.valueOf(stagiaire.getAnneePromo()).equalsIgnoreCase(value);
			default:
				return true;
			}
		});
		tableViewStagiaire.setItems(listFiltre);
	}

	public void proposerTelechargementPDF() throws IOException {
		Annuaire annuaire = new Annuaire();
		// Créer une instance de FileChooser
		FileChooser fileChooser = new FileChooser();

		// Définir un filtre pour n'accepter que les fichiers PDF
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PDF Files", "*.pdf");
		fileChooser.getExtensionFilters().add(filter);

		// Ouvrir une boîte de dialogue pour choisir le fichier
		File fichier = fileChooser.showSaveDialog(null);

		// Vérifier si l'utilisateur a sélectionné un fichier
		if (fichier != null) {
			if (!fichier.getName().endsWith(".pdf")) {
				// Ajouter l'extension .pdf si nécessaire
				fichier = new File(fichier.getAbsolutePath() + ".pdf");
			}
			System.out.println("Fichier sélectionné : " + fichier.getAbsolutePath());
			// Créer le PDF à l'emplacement sélectionné
			annuaire.creerPDF(fichier.getAbsolutePath());
			System.out.println("Le fichier PDF a été créé avec succès à : " + fichier.getAbsolutePath());
			try {
				File pdfFile = new File(fichier.getPath());
				if (pdfFile.exists()) {
					if (Desktop.isDesktopSupported()) {

						// Ouvrir le fichier
						Desktop desktop = Desktop.getDesktop();
						desktop.open(pdfFile);
					} else {
						System.out.println("Le système ne supporte pas l'ouverture de fichiers par défaut.");
					}

				} else {
					System.out.println("Le fichier PDF n'existe pas.");
				}
			} catch (IOException e) {
				System.out.println("Erreur lors de l'ouverture du fichier PDF : " + e.getMessage());
			}
		} else {
			System.out.println("L'utilisateur a annulé la selection du fichier");
		}
	}

	// Getters et Setters
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

	public Button getImprimer() {
		return boutonImprimer;
	}

	public void setImprimer(Button imprimer) {
		this.boutonImprimer = imprimer;
	}

	public Button getAccueil() {
		return boutonAccueil;
	}

	public void setAccueil(Button accueil) {
		this.boutonAccueil = accueil;
	}

	public Button getRecherche() {
		return boutonRecherche;
	}

	public void setRecherche(Button recherche) {
		this.boutonRecherche = recherche;
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

	public TableView<Stagiaire> getTableViewStagiaire() {
		return tableViewStagiaire;
	}

	public void setTableViewStagiaire(TableView<Stagiaire> tableViewStagiaire) {
		this.tableViewStagiaire = tableViewStagiaire;
	}

	// public void setPromotion(ObservableList<Stagiaire> observableList) {
//		this.tableViewStagiaire = observableList;
//	}
	// Méthode pour créer des Labels avec style pour les titres des colonnes de
	// TableView
	private Label titreTableView(String title, Color color, int fontSize) {
		Label titreColonne = new Label(title);
		titreColonne.setTextFill(color); // Couleur de la police
		titreColonne.setFont(new Font("Arial", fontSize)); // Police et taille
		return titreColonne;
	}

}

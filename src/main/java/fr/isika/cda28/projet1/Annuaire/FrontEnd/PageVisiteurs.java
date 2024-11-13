package fr.isika.cda28.projet1.Annuaire.FrontEnd;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Annuaire;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

public class PageVisiteurs extends BorderPane {

	// Attributs
	private Annuaire annuaire;
	private List<Stagiaire> stagiaires;
	public TableView<Stagiaire> tableViewStagiaire;
	private ObservableList<Stagiaire> datas = FXCollections.observableArrayList();

	// On instancie les labels
	private Label labelBienvenue = new Label("Annuaire des stagiaires de la Dev'Up Academy !");

	// On instancie l'image
	private Image logo = new Image(getClass().getResourceAsStream("/mesFichiers/logo_Projet1.png"));
	private ImageView logoImageView = new ImageView(logo);

	// On instancie nos boutons
	private Button boutonConnexion = new Button("Se connecter");
	private Button boutonImprimer = new Button("Imprimer la liste");
	private Button boutonAccueil = new Button("Accueil");
	private Button boutonRecherche = new Button("Lancer la recherche");
	private Button boutonTrier = new Button("Trier");
	private Button boutonAfficherListe = new Button("Afficher la liste de stagiaires");

	// On instancie le TextField
	private TextField champZoneRecherche = new TextField();

	// On instancie les HBox et VBox qui sont contenu dans le BorderPane
	private VBox vBoxCoteGauche = new VBox(450);
	private VBox vBoxCoteGaucheBoutons = new VBox(20);
	private VBox vBoxContenuPrincipal = new VBox();
	private HBox hBoxBienvenueContenu = new HBox(350);
	private HBox hBoxRechercheContenu = new HBox(20);
	private HBox hBoxListeTriContenu = new HBox(300);
	private ComboBox<String> comboBoxCriteres = new ComboBox();

	// Constructeur de la page Visiteurs
	public PageVisiteurs(Annuaire annuaire) {
		super();
		this.annuaire = annuaire;
		try {
			this.stagiaires = FXCollections.observableArrayList(annuaire.afficherListeOrdreAlphabetique());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// initialisation de la liste de stagiaire avec la liste ordonnée de l'annuaire
		tableViewStagiaire = new TableView<>(FXCollections.observableArrayList(this.stagiaires));
		
		// taille de la page
		setStyle("-fx-background-color:#172428");
		
		// logo
		logoImageView.setFitWidth(140);
		logoImageView.setFitHeight(140);

		// COTE GAUCHE
		
		// on initialise la taille de la VBox coteGauche
		vBoxCoteGauche.setPrefSize(220, 450);

		// On ajoute les images et la VBox du coteGauche
		vBoxCoteGauche.getChildren().addAll(logoImageView, vBoxCoteGaucheBoutons);

		// on ajoute nos boutons a la VBox coteGauche
		vBoxCoteGaucheBoutons.getChildren().addAll(boutonImprimer, boutonAccueil);

		// On chane la change la couleur de fond de la partie gauche
		vBoxCoteGauche.setStyle("-fx-background-color:#25333F");
		boutonImprimer.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonImprimer.setPrefSize(200, 20);
		boutonAccueil.setStyle("-fx-background-color: #4C5A6B ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonAccueil.setPrefSize(200, 20);
		
		// CENTRE DE PAGE
		
		boutonRecherche.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		boutonConnexion.setStyle("-fx-background-color:#324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		comboBoxCriteres.setStyle("-fx-background-color:#324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		comboBoxCriteres.setPrefWidth(180); // Limite la largeur du ComboBox
		comboBoxCriteres.setMaxHeight(300); // Limite la hauteur du ComboBox, si nécessaire

		// On change la couleur du texte des labels
		labelBienvenue.setStyle("-fx-text-fill: white; -fx-font-size:20px;");
		
		// On stylise le textField zoneRecherche
		champZoneRecherche.setMaxWidth(300);
		champZoneRecherche.setPrefHeight(30);
		champZoneRecherche.setPromptText("Rechercher un stagiaire");
		champZoneRecherche.setPadding(new Insets(10, 40, 10, 15));
		
		// On ajoute le label bienvenue et le bouton connexion à la VBox
		// bienvenueContenu
		hBoxBienvenueContenu.getChildren().addAll(labelBienvenue, boutonConnexion);
		
		// On ajoute du padding à la HBox bienvenueContenu
		hBoxBienvenueContenu.setPadding(new Insets(30, 30, 130, 30));
		
		// On stylise le bouton AfficherListe
		boutonAfficherListe.setStyle("-fx-background-color: #324255 ; -fx-text-fill: white; -fx-font-size: 16px;");

		// On ajoute le TextField et le bouton à la HBox rechercheContenu
		hBoxRechercheContenu.getChildren().addAll(comboBoxCriteres,champZoneRecherche,  boutonRecherche, boutonAfficherListe);
		hBoxRechercheContenu.setPadding(new Insets(10, 0, 40, 0));
		
		// On ajoute le label listeStagiaire et le bouton trier à la HBox

		// table VIEW
		tableViewStagiaire.setEditable(false);

		TableColumn<Stagiaire, String> colonneNom = new TableColumn<>();
		colonneNom.setMinWidth(200);
		colonneNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));

		TableColumn<Stagiaire, String> colonnePrenom = new TableColumn<>();
		colonnePrenom.setMinWidth(200);
		colonnePrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));

		TableColumn<Stagiaire, String> colonneDepartement = new TableColumn<>();
		colonneDepartement.setMinWidth(50);
		colonneDepartement.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));

		TableColumn<Stagiaire, String> colonneCursus = new TableColumn<>();
		colonneCursus.setMinWidth(150);
		colonneCursus.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("cursus"));

		TableColumn<Stagiaire, Integer> colonnePromo = new TableColumn<>();
		colonnePromo.setMinWidth(100);
		colonnePromo.setCellValueFactory(new PropertyValueFactory<Stagiaire, Integer>("anneePromo"));

		tableViewStagiaire.getColumns().addAll(colonneNom, colonnePrenom, colonneDepartement, colonneCursus, colonnePromo);

		tableViewStagiaire.setPlaceholder(new Label("Aucun stagiaire trouvé."));
		
		// permet au colonne d'utiliser tout l'espace disponible
		tableViewStagiaire.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		// permet de selectionner de multiples éléments
		tableViewStagiaire.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableViewStagiaire.setStyle("-fx-background-color: #324255");
		
		// Définition des en-têtes avec couleurs personnalisées
		colonneNom.setGraphic(titreTableView("Nom", Color.WHITE, 14));
		colonnePrenom.setGraphic(titreTableView("Prenom", Color.WHITE, 14));
		colonneDepartement.setGraphic(titreTableView("Dpt", Color.WHITE, 14));
		colonneCursus.setGraphic(titreTableView("Cursus", Color.WHITE, 14));
		colonnePromo.setGraphic(titreTableView("Promo", Color.WHITE, 14));

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
		
		// Changer la couleur du texte dans la colonne prenom
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
		
		// Changer la couleur du texte dans la colonne departement
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
		
		// Changer la couleur du texte dans la colonne cursus
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
		
		// Changer la couleur du texte dans la colonne promo
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
		
		// Ajout de style pour les lignes lorsqu'elles sont sélectionnées par l'utilisateur
		tableViewStagiaire.setRowFactory(tv -> {
			TableRow<Stagiaire> row = new TableRow<>();
			row.setStyle("-fx-background-color: #324255;");
			row.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
				if (isSelected) {
					row.setStyle("-fx-background-color: #172428; -fx-text-fill:#172428");
				} else {
					row.setStyle("-fx-background-color: #324255;");
				}
			});
			return row;
		});

		// Ajout de style pour les colonnes
		tableViewStagiaire.getColumns().forEach(column -> {
			column.setStyle("-fx-background-color: #324255; -fx-text-fill: white;");
		});
		
		// On charge la liste dans la tableview
		tableViewStagiaire.setItems((ObservableList  <Stagiaire>)this.stagiaires);
		datas.addAll((ObservableList <Stagiaire>) this.stagiaires);
		// Remplire la ChoiceBox
		List<String> criters = new ArrayList<String>();

		criters.add("Rechercher par :");
		criters.add("Nom");
		criters.add("Prenom");
		criters.add("Cursus");
		criters.add("Departement");
		criters.add("Promotion");
		comboBoxCriteres.getItems().addAll(criters);
		comboBoxCriteres.getSelectionModel().select(0);
		comboBoxCriteres.setEditable(false);

		comboBoxCriteres.setCellFactory(param -> {
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
				comboBoxCriteres.setPrefWidth(179); // Limite la largeur du ComboBox
				comboBoxCriteres.setMaxHeight(299);
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

		comboBoxCriteres.setButtonCell(new ListCell<String>() {
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

		// On ajoute les HBox bienvenueContenu et rechercheContenu à la VBox
		// contenuPrincipal
		vBoxContenuPrincipal.getChildren().addAll(hBoxBienvenueContenu, hBoxRechercheContenu, hBoxListeTriContenu, tableViewStagiaire);

		// on initialise les espaces et positions des VBox et HBox
		vBoxCoteGauche.setAlignment(Pos.CENTER);
		vBoxCoteGaucheBoutons.setAlignment(Pos.CENTER);
		vBoxContenuPrincipal.setAlignment(Pos.CENTER);
		hBoxBienvenueContenu.setAlignment(Pos.CENTER);
		hBoxRechercheContenu.setAlignment(Pos.CENTER);
		hBoxListeTriContenu.setAlignment(Pos.CENTER);

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

		// on ajoute du comportement au bouton recherche
		boutonRecherche.setOnAction(event -> filterStagiaires());

		// on ajoute du comportement au bouton imprimer
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

		// on ajoute du comportement au bouton afficherListeStagiaire
		boutonAfficherListe.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tableViewStagiaire.setItems((ObservableList<Stagiaire>) stagiaires);
			}
		});

		// On instancie les HBox et VBox dans le BorderPane
		this.setLeft(vBoxCoteGauche);
		this.setCenter(vBoxContenuPrincipal);

		// Ajouter le comportement au bouton "connexion" pour changer de page
		boutonConnexion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Annuaire annuaire = new Annuaire();
				// Lorsque le bouton "Se connecter" est cliqué, passer à la page de connexion
				PageConnexion pageConnection = new PageConnexion(annuaire);
				boutonConnexion.getScene().setRoot(pageConnection);
			}
		});
		
		// réinitialisation de la liste de stagiaires pour prendre en compte les modifications
		
		
		
		
	} // *************** Ici se termine le constructeur de la page Visiteurs ***************

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

	public Button getConnexion() {
		return boutonConnexion;
	}

	public void setConnexion(Button connexion) {
		this.boutonConnexion = connexion;
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

	public Button getTrier() {
		return boutonTrier;
	}

	public void setTrier(Button trier) {
		this.boutonTrier = trier;
	}

	public TextField getZoneRecherche() {
		return champZoneRecherche;
	}

	public void setZoneRecherche(TextField zoneRecherche) {
		this.champZoneRecherche = zoneRecherche;
	}

	public VBox getCoteGauche() {
		return vBoxCoteGauche;
	}

	public void setCoteGauche(VBox coteGauche) {
		this.vBoxCoteGauche = coteGauche;
	}

	public VBox getCoteGaucheBoutons() {
		return vBoxCoteGaucheBoutons;
	}

	public void setCoteGaucheBoutons(VBox coteGaucheBoutons) {
		this.vBoxCoteGaucheBoutons = coteGaucheBoutons;
	}

	public VBox getContenuPrincipal() {
		return vBoxContenuPrincipal;
	}

	public void setContenuPrincipal(VBox contenuPrincipal) {
		this.vBoxContenuPrincipal = contenuPrincipal;
	}

	public HBox getBienvenueContenu() {
		return hBoxBienvenueContenu;
	}

	public void setBienvenueContenu(HBox bienvenueContenu) {
		this.hBoxBienvenueContenu = bienvenueContenu;
	}

	public HBox getRechercheContenu() {
		return hBoxRechercheContenu;
	}

	public void setRechercheContenu(HBox rechercheContenu) {
		this.hBoxRechercheContenu = rechercheContenu;
	}

	public HBox getListeTriContenu() {
		return hBoxListeTriContenu;
	}

	public void setListeTriContenu(HBox listeTriContenu) {
		this.hBoxListeTriContenu = listeTriContenu;
	}
	
	//METHODES********************************************************************

	// Méthode pour FILTRER
	private void filterStagiaires() {
		String critere = comboBoxCriteres.getValue().toLowerCase();
		String value = champZoneRecherche.getText().toLowerCase();

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
		tableViewStagiaire.refresh();
	}

	// Méthode pour IMPRIMER
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
			annuaire.creerPDF(tableViewStagiaire, fichier.getAbsolutePath());
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

	// Méthode pour créer des Labels avec style pour les titres des colonnes de
	// TableView
	private Label titreTableView(String title, Color color, int fontSize) {
		Label titreColonne = new Label(title);
		titreColonne.setTextFill(color); // Couleur de la police
		titreColonne.setFont(new Font("Arial", fontSize)); // Police et taille
		return titreColonne;
	}
}

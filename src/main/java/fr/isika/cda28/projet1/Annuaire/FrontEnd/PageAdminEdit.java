package fr.isika.cda28.projet1.Annuaire.FrontEnd;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Annuaire;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Authentification;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Noeud;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Stagiaire;
import fr.isika.cda28.projet1.Annuaire.BackEnd.Utilisateurs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

public class PageAdminEdit extends BorderPane {

	// Attributs
	private Annuaire annuaire;
	private List<Stagiaire> stagiaires;
	private Utilisateurs utilisateurs;
	private TableView<Stagiaire> tableViewStagiaire;
	private ObservableList<Stagiaire> datas = FXCollections.observableArrayList();

	// On instancie les labels
	private Label labelBienvenue = new Label("Annuaire des stagiaires de la Dev'Up Academy !");

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
	private Button boutonSuppEditeur = new Button("Supprimer un editeur");
	private Button boutonAfficherListe = new Button("Afficher la liste de stagiaires");

	// On instancie le TextField
	private TextField champZoneRecherche = new TextField();

	// On instancie les HBox et VBox qui sont contenu dans le BorderPane
	private VBox vBoxCoteGauche = new VBox(180);
	private VBox vBoxCoteGaucheBoutons = new VBox(20);
	private VBox vBoxContenuPrincipal = new VBox();
	private HBox hBoxBienvenueContenu = new HBox(350);
	private HBox hBoxRechercheContenu = new HBox(5);
	private HBox hBoxListeTriContenu = new HBox(300);

	// On instancie la ChoiceBox
	private ComboBox<String> choiceBoxCriteres = new ComboBox();

	// Constructeur de la page AdminEdit
	public PageAdminEdit(Annuaire annuaire, Utilisateurs utilisateurConnecte) {
		super();
		this.utilisateurs = utilisateurConnecte;
		this.annuaire = annuaire;
		try {
			this.stagiaires = FXCollections.observableArrayList(annuaire.afficherListeOrdreAlphabetique());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// *****A COMPLETER*****
		tableViewStagiaire = new TableView<>(FXCollections.observableArrayList(this.stagiaires));

		// Définir le style général de la page
		setStyle("-fx-background-color:#172428");
		
		// Définir la taille du logo
		logoImageView.setFitWidth(140);
		logoImageView.setFitHeight(140);

		// On initialise la taille de la VBox coteGauche
		vBoxCoteGauche.setPrefSize(220, 450);

		// On ajoute les images et la VBox du coteGauche
		vBoxCoteGauche.getChildren().addAll(logoImageView, vBoxCoteGaucheBoutons);

		// on ajoute nos boutons a la VBox coteGauche en fonction du profil qui est connecté
		if (utilisateurConnecte.isAdmin()) {
			vBoxCoteGaucheBoutons.getChildren().addAll(boutonMettreAjour, boutonAjoutStagiaire, boutonSuppStagiaire,
					boutonAjoutEditeur, boutonSuppEditeur, boutonImprimer, boutonAccueil);
		} else {
			vBoxCoteGaucheBoutons.getChildren().addAll(boutonAjoutStagiaire, boutonImprimer, boutonAccueil);
		}
		
		// On change la couleur de fond de la partie gauche
		vBoxCoteGauche.setStyle("-fx-background-color:#25333F");

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
		
		//On ajoute du style à notre choiceBox
		choiceBoxCriteres.setStyle("-fx-background-color:#324255 ; -fx-text-fill: white; -fx-font-size: 16px;");
		choiceBoxCriteres.setPrefWidth(180); // Limite la largeur du ComboBox
		choiceBoxCriteres.setMaxHeight(300); // Limite la hauteur du ComboBox, si nécessaire
		
		// On change la couleur du texte des labels
		labelBienvenue.setStyle("-fx-text-fill: white; -fx-font-size:20px;");

		// On stylise le textField zoneRecherche
		champZoneRecherche.setPadding(new Insets(10, 40, 10, 15));
		champZoneRecherche.setMaxWidth(300);
		champZoneRecherche.setPrefHeight(30);
		champZoneRecherche.setPromptText("Rechercher un stagiaire");

		// On ajoute le label bienvenue et le bouton connexion à la VBox
		hBoxBienvenueContenu.getChildren().addAll(labelBienvenue, boutonDeconnexion);

		// On ajoute du padding à la HBox bienvenueContenu
		hBoxBienvenueContenu.setPadding(new Insets(30, 30, 130, 30));
		
		// On ajoute le TextField et le bouton à la HBox rechercheContenu
		hBoxRechercheContenu.getChildren().addAll(choiceBoxCriteres, champZoneRecherche, boutonRecherche, boutonAfficherListe);
		hBoxRechercheContenu.setPadding(new Insets(10, 0, 40, 0));
		boutonAfficherListe.setStyle("-fx-background-color: #324255; -fx-text-fill: white; -fx-font-size: 16px;");

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

		tableViewStagiaire.getColumns().addAll(colonneNom, colonnePrenom, colonneDepartement, colonneCursus, colonnePromo);

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
		
		// Changer la couleur du texte dans la colonne prénom
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
					row.setStyle("-fx-background-color: #324255");
				}
			});
			return row;
		});

		// Ajout de style pour les colonnes
		tableViewStagiaire.getColumns().forEach(column -> {
			column.setStyle("-fx-background-color: #324255; -fx-text-fill: white;");
		});

		// On charge la liste dans la tableview
		tableViewStagiaire.setItems((ObservableList <Stagiaire>) this.stagiaires);

		// On ajoute les HBox bienvenueContenu et rechercheContenu à la VBox
		vBoxContenuPrincipal.getChildren().addAll(hBoxBienvenueContenu, hBoxRechercheContenu, hBoxListeTriContenu, tableViewStagiaire);

		// on initialise les espaces et positions des VBox et HBox
		vBoxCoteGauche.setAlignment(Pos.CENTER);
		vBoxCoteGaucheBoutons.setAlignment(Pos.CENTER);
		vBoxContenuPrincipal.setAlignment(Pos.CENTER);
		hBoxBienvenueContenu.setAlignment(Pos.CENTER);
		hBoxRechercheContenu.setAlignment(Pos.CENTER);
		hBoxListeTriContenu.setAlignment(Pos.CENTER);

		// on récupère la sélection souris du tableView dans une variable pour SUPPRIMER
		tableViewStagiaire.getSelectionModel().selectedItemProperty()
				.addListener((obs, ancienElement, nouvelElement) -> {
					if (nouvelElement != null) {
						Stagiaire stagiaireSelectionne = nouvelElement;

						boutonSuppStagiaire.setOnAction(event -> {
							// Afficher une alerte de confirmation avant la suppression
							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							alert.setTitle("Confirmation de suppression");
							alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce stagiaire ?");
							alert.setContentText("Cette action est irréversible.");

							// Attendre la réponse de l'utilisateur
							alert.showAndWait().ifPresent(reponse -> {
								if (reponse == ButtonType.OK) {
									// L'utilisateur a confirmé, procéder à la suppression
									Noeud noeudASupprimer = new Noeud(stagiaireSelectionne, -1, -1);

									try {
										annuaire.supprimerStagiaire(noeudASupprimer);
										tableViewStagiaire.getItems().remove(stagiaireSelectionne); // Retirer l'élément de la liste
										
									} catch (IOException e) {
										e.printStackTrace();
									}

									// Rafraîchir la TableView pour mettre à jour l'affichage
									tableViewStagiaire.refresh();
								} else {
									// L'utilisateur a annulé la suppression
									System.out.println("Suppression annulée.");
								}
							});
						});
					}
				});

		// on récupère la sélection souris du tableView dans une variable pour MODIFIER
		tableViewStagiaire.getSelectionModel().selectedItemProperty()
				.addListener((obs, ancienElement, nouvelElement) -> {
					if (nouvelElement != null) {
						Stagiaire stagiaireSelectionne = nouvelElement;

						boutonMettreAjour.setOnAction(event -> {
							Optional<Stagiaire> result = afficherDialogueModification(stagiaireSelectionne);

							result.ifPresent(stagiaireModifie -> {
								try {
									// Mise à jour dans l'annuaire et dans la TableView
									annuaire.modifierStagiaire(stagiaireSelectionne, stagiaireModifie);
									tableViewStagiaire.getItems().remove(stagiaireSelectionne);
									

									tableViewStagiaire.getItems().add(stagiaireModifie); // Ajout du stagiaire à la
									
									tableViewStagiaire.getItems().sort(Comparator.comparing(Stagiaire::getNom)); 
									tableViewStagiaire.refresh(); // Rafraîchit l'affichage de la TableView
									tableViewStagiaire.getSelectionModel().select(stagiaireModifie);
									tableViewStagiaire.scrollTo(stagiaireModifie);
								} catch (Exception e) {
									e.printStackTrace();
								}
							});
						});
					}
				});

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

		// on ajoute du comportement au bouton ajouterStagiare
		boutonAjoutStagiaire.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				PageAjouterStagiaire ajouterStagiaire = new PageAjouterStagiaire(annuaire, utilisateurs);
				boutonAjoutStagiaire.getScene().setRoot(ajouterStagiaire);
			}
		});

		// on ajoute du comportement au bouton ajoutEditeur
		boutonAjoutEditeur.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				PageAjouterEditeur ajouterEditeur = new PageAjouterEditeur(annuaire, utilisateurs);
				boutonAjoutEditeur.getScene().setRoot(ajouterEditeur);
			}
		});

		// on ajoute du comportement au bouton Deconnexion
		boutonDeconnexion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Annuaire annuaire = new Annuaire();
				PageConnexion pageConnection = new PageConnexion(annuaire);
				boutonDeconnexion.getScene().setRoot(pageConnection);
			}
		});

		// on ajoute du comportement au bouton recherche
		boutonRecherche.setOnAction(event -> filterStagiaires());

		// on ajoute du comportement au bouton afficherListeStagiaire
		boutonAfficherListe.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tableViewStagiaire.setItems((ObservableList<Stagiaire>) stagiaires);
			}
		});
		
		boutonSuppEditeur.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TextInputDialog dialog = new TextInputDialog();
		        dialog.setTitle("Suppression Editeur");
		        dialog.setHeaderText("Entrez le userID");
		        dialog.setContentText("User ID de l'editeur:");

		        // Traditional way to get the response value.
		        Optional<String> result = dialog.showAndWait();
		        result.ifPresent(name -> {
		        	Authentification authentification = new Authentification();
		        	authentification.supprimerEditeur(name, annuaire);
		        	});
			}
		});

		// On instancie les HBox et VBox dans le BorderPane
		this.setLeft(vBoxCoteGauche);
		this.setCenter(vBoxContenuPrincipal);
		
		
		// Remplire la ChoiceBox
		List<String> criters = new ArrayList<String>();

		criters.add("Rechercher par :");
		criters.add("Nom");
		criters.add("Prenom");
		criters.add("Cursus");
		criters.add("Departement");
		criters.add("Promotion");
		choiceBoxCriteres.getItems().addAll(criters);
		choiceBoxCriteres.getSelectionModel().select(0);

		choiceBoxCriteres.setCellFactory(param -> {
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
				choiceBoxCriteres.setPrefWidth(179); // Limite la largeur du ComboBox
				choiceBoxCriteres.setMaxHeight(299);
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

		choiceBoxCriteres.setButtonCell(new ListCell<String>() {
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
		datas.addAll(this.stagiaires);
		
	} // *************** Ici se termine le constructeur de la page AdminEdit ***************
	
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

	public TableView<Stagiaire> getTableViewStagiaire() {
		return tableViewStagiaire;
	}

	public void setTableViewStagiaire(TableView<Stagiaire> tableViewStagiaire) {
		this.tableViewStagiaire = tableViewStagiaire;
	}
	
	//METHODES********************************************************************
	
	// Méthode pour Imprimer en PDF
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
	
	// Méthode pour FILTRER
	private void filterStagiaires() {
		String critere = choiceBoxCriteres.getValue().toLowerCase();
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

	// Méthode pour créer des Labels avec style pour les titres des colonnes de TableView
	private Label titreTableView(String title, Color color, int fontSize) {
		Label titreColonne = new Label(title);
		titreColonne.setTextFill(color); // Couleur de la police
		titreColonne.setFont(new Font("Arial", fontSize)); // Police et taille
		return titreColonne;
	}

	// Méthode pour MODIFIER dans une fenetre 
	public Optional<Stagiaire> afficherDialogueModification(Stagiaire stagiaire) {
		Dialog<Stagiaire> dialog = new Dialog<>();
		dialog.setTitle("Modifier Stagiaire");

		// Configurer les champs de saisie
		TextField nomField = new TextField(stagiaire.getNom());
		TextField prenomField = new TextField(stagiaire.getPrenom());
		TextField cursusField = new TextField(stagiaire.getCursus());
		TextField departementField = new TextField(stagiaire.getDepartement());
		TextField promoField = new TextField(String.valueOf(stagiaire.getAnneePromo()));

		// Configurer le layout de la boîte de dialogue
		GridPane grid = new GridPane();
		grid.add(new Label("Nom:"), 0, 0);
		grid.add(nomField, 1, 0);
		grid.add(new Label("Prenom:"), 0, 1);
		grid.add(prenomField, 1, 1);
		grid.add(new Label("Cursus:"), 0, 2);
		grid.add(cursusField, 1, 2);
		grid.add(new Label("Departement (en chiffres) :"), 0, 3);
		grid.add(departementField, 1, 3);
		grid.add(new Label("Année de la Promo:"), 0, 4);
		grid.add(promoField, 1, 4);
		
		// Ajouter d'autres champs de manière similaire
		grid.setVgap(15); // Exemple de configuration des espaces
		grid.setHgap(15);
		dialog.getDialogPane().setContent(grid);
		
		// Boutons de validation
		ButtonType modifierButtonType = new ButtonType("Modifier");
		dialog.getDialogPane().getButtonTypes().addAll(modifierButtonType, ButtonType.CANCEL);

		// Créer l'action de validation
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == modifierButtonType) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Modification réalisée");
				alert.setHeaderText(null);
				alert.setContentText("Vous avez modifié correctement le stagiaire");
				alert.showAndWait();
				stagiaire.setNom(nomField.getText().toUpperCase());
				stagiaire.setPrenom(prenomField.getText());
				stagiaire.setCursus(cursusField.getText());
				stagiaire.setDepartement(departementField.getText());
				stagiaire.setAnneePromo(Integer.parseInt(promoField.getText()));
				// Mettre à jour d'autres champs si nécessaire
				return stagiaire;
			}
			return null;
		});
		return dialog.showAndWait();
	}
}

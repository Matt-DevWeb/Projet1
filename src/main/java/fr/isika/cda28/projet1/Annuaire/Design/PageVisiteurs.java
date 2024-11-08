
package fr.isika.cda28.projet1.Annuaire.Design;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PageVisiteurs extends BorderPane {

	// On instancie les labels
	private Label bienvenue = new Label("Bienvenue !");
	private Label listeStagiaire = new Label("Liste des stagiaires de l'entreprise.");

	// On instancie l'image
	private Image logo = new Image(
			"file:///C:/Users/coleen/Documents/ISIKA/projet1/Code/Projet1/src/mesFichiers/LogoProjet1.png");
	private ImageView logoImageView = new ImageView(logo);

	// On instancie nos boutons
	private Button connexion = new Button("Se connecter");
	private Button imprimer = new Button("Imprimer la liste");
	private Button accueil = new Button("Accueil");
	private Button recherche = new Button("Rechercher");
	private Button trier = new Button("Trier");

	// On instancie le TextField
	private TextField zoneRecherche = new TextField("Rechercher un stagiaire");

	// On instancie les HBox et VBox qui sont contenu dans le BorderPane
	private VBox coteGauche = new VBox(220);
	private VBox coteGaucheBoutons = new VBox(20);
	private VBox contenuPrincipal = new VBox();
	private HBox bienvenueContenu = new HBox(350);
	private HBox rechercheContenu = new HBox(5);
	private HBox listeTriContenu = new HBox(300);

	public PageVisiteurs() {
		super();

		setPrefSize(800, 450);
		logoImageView.setFitWidth(80);
		logoImageView.setFitHeight(80);

		setStyle("-fx-background-color:white");

		// on initialise la taille de la VBox coteGauche
		coteGauche.setPrefSize(220, 450);

		// On ajoute les images et la VBox du coteGauche
		coteGauche.getChildren().addAll(logoImageView, coteGaucheBoutons);

		// on ajoute nos boutons a la VBox coteGauche
		coteGaucheBoutons.getChildren().addAll(imprimer, accueil);

		// On chane la change la couleur de fond de la partie gauche
		coteGauche.setStyle("-fx-background-color:#D9D9D9");

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

		// On ajoute les HBox bienvenueContenu et rechercheContenu à la VBox
		// contenuPrincipal
		contenuPrincipal.getChildren().addAll(bienvenueContenu, rechercheContenu, listeTriContenu);

		// on initialise les espaces et positions des VBox et HBox
		coteGauche.setAlignment(Pos.CENTER);
		coteGaucheBoutons.setAlignment(Pos.CENTER);
		contenuPrincipal.setAlignment(Pos.CENTER);
		bienvenueContenu.setAlignment(Pos.CENTER);
		rechercheContenu.setAlignment(Pos.CENTER);
		listeTriContenu.setAlignment(Pos.CENTER);

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

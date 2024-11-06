package fr.isika.cda28.projet1.Annuaire;

public class Annuaire  {
	// Attributs
	private Noeud racine;
	
	//Getteurs
	public Noeud getRacine() {
		return racine;
	}
	
	//Méthodes
	public void ajouterRacine(Stagiaire stagiaire) {
		if (racine == null) {
			racine = new Noeud(stagiaire);
		}else {
			System.out.println("La racine est déjà remplie");
		}return;
	}
	
	
	
	
}

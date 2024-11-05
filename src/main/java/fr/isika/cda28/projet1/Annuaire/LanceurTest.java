package fr.isika.cda28.projet1.Annuaire;

public class LanceurTest {
	public static void main(String[] args) {
		
		Stagiaire stagiaire1 = new Stagiaire("Lacroix","Pascale","91","BOBI 5", 2008);
		Stagiaire stagiaire2 = new Stagiaire("Dupond","Jaques","77","CDA 28", 2024);
		Noeud stagiaireRacine = new Noeud(stagiaire1,null, null);
	
		
		stagiaireRacine.ajouterStagiaire(stagiaire2,stagiaireRacine);
		
//		System.out.println(stagiaire1.toString());
		System.out.println(stagiaireRacine);
		
	}
}
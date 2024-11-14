package fr.isika.cda28.projet1.Annuaire.BackEnd;

/**
 * La classe Editeur représente un utilisateur ayant un rôle d'éditeur. Elle
 * hérite de la classe Utilisateurs et définit automatiquement le rôle de
 * l'utilisateur comme "éditeur".
 */
public class Editeur extends Utilisateurs {

	/**
	 * Constructors
	 * 
	 * @constructor Editeur(String userID, String password) Description:
	 *              Constructeur de la classe Editeur.
	 * @param userID   L'identifiant de l'éditeur.
	 * @param password Le mot de passe de l'éditeur.
	 */
	public Editeur(String userID, String password) {
		super(userID, password, _EDITEUR);
	}
}
/**
 * Package fr.isika.cda28.projet1.Annuaire.BackEnd
 */

package fr.isika.cda28.projet1.Annuaire.BackEnd;

/**
 * Class Administrateur
 * javadoc de la classe Administrateur
 */

public class Administrateur extends Utilisateurs {

	/**
     * Constructors
     * @constructor Administrateur(String userID, String password)
     * Description: Constructeur de la classe Administrateur.
     * @param userID L'identifiant de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     */
	public Administrateur(String userID, String password) {
		super(userID, password, _ADMIN);
	}
}

/**
 * Package fr.isika.cda28.projet1.Annuaire.BackEnd
 */

package fr.isika.cda28.projet1.Annuaire.BackEnd;

/**
 * Class Administrateur Représente un administrateur dans le système, héritant
 * des propriétés de la classe Utilisateurs. Cette classe permet de créer des
 * utilisateurs de type Administrateur avec un identifiant et un mot de passe.
 */

public class Administrateur extends Utilisateurs {

	/**
	 * Constructors
	 * 
	 * @constructor Administrateur(String userID, String password) Description:
	 *              Permet de créer un administrateur en lui attribuant un
	 *              identifiant utilisateur et un mot de passe. Le rôle de
	 *              l'utilisateur est défini comme étant un administrateur via la
	 *              constante `_ADMIN`.
	 *
	 * @param userID   L'identifiant de l'utilisateur.
	 * @param password Le mot de passe de l'utilisateur.
	 */
	public Administrateur(String userID, String password) {
		super(userID, password, _ADMIN);
	}
}

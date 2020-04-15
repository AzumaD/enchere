/**
 * 
 */
package fr.eni.encheres.bo;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class Categorie {

	private int idCategorie;
	private String libelle;

	/**
	 *
	 * @return idCategorie
	 */
	public int getIdCategorie() {
		return idCategorie;
	}

	/**
	 *
	 * @param idCategorie
	 */
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	/**
	 *
	 * @return libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 *
	 * @param libelle
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", libelle=" + libelle + "]";
	}


}

/**
 * 
 */
package fr.eni.encheres.dal;

import java.sql.*;
import java.util.ArrayList;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.outils.BuisnessException;
import fr.eni.encheres.outils.Log;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class ArticleDAOImpl implements DAO<Article>{

	private ConnectionProvider connectionProvider = new ConnectionProvider();

	private static final String INSERT_INTO =
			"INSERT INTO `ARTICLES` (`nomArticle`, `description`, `dateDebutEnchere`," +
			"`dateFinEnchere`, `prixInitial`, `prixVente`, `idUtilisateur`, `idCategorie`) " +
			"VALUES (?, ?, ?, ?, ?, 0, ?, ?)";

	@Override
	public void create(Article objet) throws BuisnessException {
	}

	/**
	 * Méthode de création d'un article
	 *
	 * @param article
	 * @return Article
	 * @throws BuisnessException
	 */
	public Article createArticle(Article article) throws BuisnessException {


		try {
			PreparedStatement ps = connectionProvider.getInstance().prepareStatement(
					INSERT_INTO,
					Statement.RETURN_GENERATED_KEYS
			);


			ps.setString (1, article.getNomArticle().trim());
			ps.setString (2, article.getDescription().trim());
			ps.setDate   (3, article.getDateDebutEnchere());
			ps.setDate   (4, article.getDateFinEnchere());
			ps.setInt    (5, article.getPrixInitial());
			ps.setInt    (6, article.getUtilisateur().getIdUtilisateur());
			ps.setInt    (7, article.getCategorie().getIdCategorie());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			while (rs.next()){
				article.setIdArticle(rs.getInt(1));
			}

			ps.setInt    (1, article.getIdArticle());

		} catch (SQLException e){
			new Log(e.getMessage());
			throw new BuisnessException(e.getMessage(),e);
		}

		return article;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(Article article) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Article article) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#find(int)
	 */
	@Override
	public Article find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return ArrayList<Article>
	 */
	@Override
	public ArrayList<Article> findAll() {
		// TODO Auto-generated method stub
		return null;
	}




}

/**
 * 
 */
package fr.eni.encheres.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.outils.BuisnessException;
import fr.eni.encheres.outils.Log;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class RetraitDAOImpl implements DAO<Retrait>{
private static final String ADD = "INSERT INTO RETRAITS (idArticle,rue,codePostal,ville) " 
			+ "VALUES	(?,?,?,?)";
private static final String SELECTBYID = "SELECT rue, codePostal, ville " +
										"FROM RETRAITS " +
										"WHERE idArticle=?";

private static ConnectionProvider connectionProvider = new ConnectionProvider();
private Log log;

	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @throws SQLException 
	 * @see fr.eni.encheres.dal.DAO#create(java.lang.Object)
	 */
	@Override
	public void create(Retrait retrait) throws BuisnessException {
		try {
			PreparedStatement pstmt = connectionProvider.getInstance().prepareStatement(ADD);
			pstmt.setInt(1, retrait.getArticle().getIdArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log = new Log(e.getMessage());
			BuisnessException buisnessException = new BuisnessException(e.getMessage(), e);
			throw buisnessException;
		}
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(Retrait retrait) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Retrait retrait) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.dal.DAO#find(int)
	 */
	@Override
	public Retrait find(int id) throws BuisnessException {

		Retrait retrait = new Retrait();
		try{
			PreparedStatement ps = connectionProvider.getInstance().prepareStatement(SELECTBYID);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("codePostal"));
				retrait.setVille(rs.getString("ville"));
			}

		} catch (SQLException e){
			new Log(e.getMessage());
			throw new BuisnessException(e.getMessage(),e);
		}
		return retrait;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#findAll(java.lang.Object)
	 */
	@Override
	public ArrayList<Retrait> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

}

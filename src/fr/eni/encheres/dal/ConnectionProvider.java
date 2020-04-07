package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Classe de connexion à la jdbc
 */
public class ConnectionProvider {

	/**
	 * Instance de la classe Connection
	 */
	private static Connection connection;


	//Constructeur privé
	public ConnectionProvider(){
		ConnectionProvider.getConnection();
	}


	/**
	 * Récupère l'instance de la connection à la bdd
	 * @return ConnectionProvider
	 */
	public Connection getInstance(){
		return connection;
	}

	/**
	 * Récupère la connexion à la bdd
	 */
	private static void getConnection(){

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
			connection = dataSource.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossible d'accéder à la base de données");
		}
	}

}

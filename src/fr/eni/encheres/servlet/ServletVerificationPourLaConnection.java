package fr.eni.encheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDAOImpl;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Servlet Home
 */

 @WebServlet( name="ServletVerificationPourLaConnection", urlPatterns = {"/seConnecter"} )
public class ServletVerificationPourLaConnection extends javax.servlet.http.HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

	protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	String identifiant = request.getParameter("identifiant");
    	String motdepasse =  request.getParameter("motdepasse");
    	boolean existeEnBase = false;
    	Utilisateur utilisateur = new Utilisateur();
    	
    	request.setAttribute("identifiant", identifiant);
    	request.setAttribute("motdepasse", motdepasse);
    	
    	if(identifiant.contains("@")) {
    		//true = correspond à un email
    		utilisateur = new Utilisateur(identifiant,motdepasse,true);
        	try {
				existeEnBase = utilisateurManager.verifier(utilisateur);
			} catch (BuisnessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else {
    		utilisateur = new Utilisateur(identifiant,motdepasse,false); 
    		try {
				existeEnBase = utilisateurManager.verifier(utilisateur);
			} catch (BuisnessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   	
    	}
    	
    	if(existeEnBase == true) {
    		HttpSession session = request.getSession();
    		session.setAttribute("idUtilisateur", utilisateur.getIdUtilisateur());
    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/listeEnchereConnecte.jsp");
        	rd.forward(request, response);
    	}else {
    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/erreurSeConnecter.jsp");
        	rd.forward(request, response);
    	}
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/formulaireSeConnecter.jsp");
    	rd.forward(request, response);
    }
}
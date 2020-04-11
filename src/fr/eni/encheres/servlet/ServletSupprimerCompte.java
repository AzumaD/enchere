package fr.eni.encheres.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Servlet implementation class ServletSupprimerCompte
 */
@WebServlet(name="ServletSupprimerCompte", urlPatterns = {"/SupprimerCompte"})
public class ServletSupprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSupprimerCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
    	if(session.getAttribute("idUltilisateur")!=null) {
    		if(request.getParameter("id")!=null) {
				try {
					utilisateurManager.delete(utilisateurManager.find(Integer.valueOf(request.getParameter("id"))));
					session.setAttribute("idUtilisateur", null);
					this.getServletContext().getRequestDispatcher("/Home").forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (BuisnessException e) {
					e.printStackTrace();
				}
	    	}else {
	    		this.getServletContext().getRequestDispatcher("/WEB-INF//erreur404.jsp").forward(request, response);
	    	}
    	}
	}
}

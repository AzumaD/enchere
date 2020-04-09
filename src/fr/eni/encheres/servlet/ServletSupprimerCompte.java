package fr.eni.encheres.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Servlet implementation class ServletSupprimerCompte
 */
@WebServlet(name="ServletSupprimerCompte", urlPatterns = {"/SupprimerCompte"})
public class ServletSupprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		if(request.getParameter("id")!=null) {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			try {
				utilisateurManager.delete(utilisateurManager.find(Integer.valueOf(request.getParameter("id"))));
				Cookie[] cookies = request.getCookies();
				for(Cookie unCookie:cookies)
    			{
    				if(unCookie.getName().equals("idUtilisateur")) {
    					unCookie.setMaxAge(0);
    				}
    			}
				this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurInconnu.jsp").forward(request, response);
			} catch (BuisnessException e) {
				e.printStackTrace();
			}
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		}
	}
}

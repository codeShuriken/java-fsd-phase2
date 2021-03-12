import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Query;

import com.example.HibernateUtil;
import com.example.User;

@WebServlet("/login")
public class UserAuthenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserAuthenticate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        try {
           SessionFactory factory = HibernateUtil.getSessionFactory();
           
           Session session = factory.openSession();
           // using HQL
           String email = request.getParameter("email");
           String password = request.getParameter("password");
           
           Query query = session.createQuery("from User u where u.email=:email and u.password=:password", User.class);
           query.setParameter("email", email);
           query.setParameter("password", password);
           List<User> res = query.getResultList();
           
           session.close();
           //System.out.println(res.size());
           if (res.size() == 1) {
        	   HttpSession httpSession = request.getSession();
        	   httpSession.setAttribute("user", email);
        	   response.sendRedirect("welcome.jsp");
           }else {
        	   response.sendRedirect("wrongDetails.jsp");
           }
        } catch (Exception ex) {
	    	 PrintWriter out = response.getWriter();
	         out.println("<html><body><b>Error!!! Please try logging in again!!!</b>");
	         out.println("<a href = \"index.jsp\">Try again</a>");
	         out.println("</body></html>");
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
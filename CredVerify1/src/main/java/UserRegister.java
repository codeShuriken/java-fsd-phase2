import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Query;

import com.example.HibernateUtil;
import com.example.User;

@WebServlet("/userRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 SessionFactory factory = HibernateUtil.getSessionFactory();
         
         Session session = factory.openSession();
         PrintWriter out = response.getWriter();
         
         User user = new User(request.getParameter("email"), request.getParameter("password"));
     
         out.println("<html><body>");
         out.println("<b>Registering " + user + "</b><br>");
         
        try {
        	String email = request.getParameter("email");
        	
        	session.beginTransaction();
        	
        	Query query = session.createQuery("from User u where u.email=:email", User.class);
        	query.setParameter("email", email);
    		List<User> res = query.getResultList();
    		if (res.size() != 0){
                 out.println("<b>User with the given email already exists!!!</b><br>");
            }else {
                session.save(user);
                out.println("<b>Successfully registered the user</b><br>");
            }
			 out.println("<a href = \"index.jsp\">Login now </a>");    
			 out.println("</body></html>");
			 session.getTransaction().commit();
        } catch (Exception ex) {
        	 out.println("<b>Couldn't register the user</b><br>");
             out.println("<a href = \"register.jsp\">Try again</a>");    
             out.println("</body></html>");
        }finally {
        	session.close();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
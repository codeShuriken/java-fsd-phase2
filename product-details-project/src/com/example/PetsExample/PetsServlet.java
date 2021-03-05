package com.example.PetsExample;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.PetsExample.model.DBConnection;


public class PetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public PetsServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {      
            InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
            Properties props = new Properties();
            props.load(in);
            
            int prodID = Integer.parseInt(request.getParameter("prodID"));
                
            DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("userid"), props.getProperty("password"));
        	Statement stmt = conn.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rst = stmt.executeQuery("select * from products p where p.id = " + prodID);
            
            out.println("<html><body>");
            if (!rst.next()) {
    			out.println("<p style=\"color:red\">Product with the Product ID "+ prodID + " does not exist in the database!!</p>");
    			out.println("<a href = \"index.html\"> Go Back </a>");
            }else {
            	out.println("<table><tr><th>ID</th><th>Name</th><th>Color</th><th>Price</th></tr>");
                out.println("<tr><td>" + rst.getString("id") + "</td><td>" + rst.getString("name") + "</td>" + "<td>" +
                		rst.getString("color") + "</td><td>" + rst.getBigDecimal("price") + "</td></tr>");
                out.println("</table>");
                out.println("<a href = \"index.html\"> Go Back </a>");
            }
            out.println("</body></html>");
            
          
            
            stmt.close();        
            conn.closeConnection();
		} catch(NumberFormatException e) {
			out.println("<html><body style=\"color:red\">");
			out.println("ERROR! Product ID not valid!!!!<br>");
			out.println("<a href = \"index.html\"> Go Back </a>");
			out.println("</body></html>");
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

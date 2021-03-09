package com.example;

import java.io.IOException;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.Product;


/**
 * Servlet implementation class PetsServlet
 */
public class PetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
        try {
               SessionFactory factory = HibernateUtil.getSessionFactory();
               
               Session session = factory.openSession();
               // using HQL
               List<Product> list = session.createQuery("from Product", Product.class).list();
               
               session.close();
               
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<b>Product Listing</b><br>");
                for(Product p: list) {
                        out.println("ID: " + String.valueOf(p.getID()) + ", Name: " + p.getName() +
                                        ", Price: " + String.valueOf(p.getPrice()) + ", Color: " + p.getColor().toString() + "<br>");
                }
    	        out.println("<a href = \"index.jsp\"> Go Back </a>");
                out.println("</body></html>");
            
            
        } catch (Exception ex) {
                throw ex;
        }

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<p>Adding Pet " + request.getParameter("name") + "</p>");
      
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        
        Session session = sessionFactory.getCurrentSession();
        
        try {
	        Product product = new Product(request.getParameter("name"), new BigDecimal(request.getParameter("price")), 
	        		request.getParameter("color"));
	        
	        
	        session.beginTransaction();
	        
	        Query query = session.createQuery("from Product p where p.name=:name and p.price=:price and p.color=:color", 
	        		Product.class);
	        query.setParameter("name", product.getName());
	        query.setParameter("price", product.getPrice());
	        query.setParameter("color", product.getColor());
	        
	        List<Product> list = query.getResultList();
	        
	        if (list.size() == 0) {
	        	session.save(product);
	        	out.println("<p>Successfully added to the database!!!</p>");
	        }else {
	        	out.println("<p>Pet already present in the database!!!</p>");
	        }
	        
	        session.getTransaction().commit();
	        
	        out.println("<a href = \"index.jsp\"> Go Back </a>");
	        out.println("</body></html>");
        }catch(Exception e) {
        	out.println("<p>Couldn't add to the database: " + e.getMessage() + " </p>");
	        out.println("<a href = \"index.jsp\"> Go Back </a>");
	        out.println("</body></html>");
        }
        finally {
        	session.close();
        }
	}
}
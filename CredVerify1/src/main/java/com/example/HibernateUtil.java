package com.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			Configuration con = new Configuration().configure().addAnnotatedClass(User.class);

			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(con.getProperties()).build();
            
            sessionFactory = con.buildSessionFactory(standardRegistry);
		} catch (Throwable th) {
			throw new ExceptionInInitializerError(th);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
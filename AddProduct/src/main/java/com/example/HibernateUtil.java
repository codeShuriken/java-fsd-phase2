package com.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {

			sessionFactory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Product.class)
					.buildSessionFactory();

		} catch (Throwable th) {
			throw new ExceptionInInitializerError(th);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
package io.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	private static SessionFactory buildSessionFactory () {
		
		SessionFactory factory = null;
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (HibernateException e) {
			System.err.println("Initial SessionFactory creation failed " + e);
			throw new ExceptionInInitializerError(e);
		}
		
		return factory;
	}

	public static SessionFactory getSessionFactory () {
		
		if (sessionFactory==null) {
			sessionFactory = buildSessionFactory ();
		}
		
		return sessionFactory;
	}
}

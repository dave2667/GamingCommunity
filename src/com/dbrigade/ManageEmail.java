package com.dbrigade;
import java.util.List;

import java.time.LocalDate;
import java.util.Iterator; 

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageEmail {
	private static SessionFactory factory; 

	public ManageEmail() {}

	public static void main(String[] args) {

		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}

		ManageEmail MM = new ManageEmail();

		/* Add few message board records in database */
		Integer emailID1 = MM.addEmail("j.sharpshooter@test.net", true); 
		Integer emailID2 = MM.addEmail("williemakeit@outhouse.com", true);
		Integer emailID3 = MM.addEmail("yoyo@email.org", true); 

		/* List down all the message boards */
		MM.listEmails();

		/* Update message board records */
		MM.updateEmail(emailID3, "thecaptain@dbrigade.com");

		/* Delete a message board from the database */
		MM.deleteEmail(emailID2);

		/* List down new list of the message boards */
		MM.listEmails();
	}

	/* Method to CREATE a message board in the database */
	public Integer addEmail(String emailName, boolean active){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer emailID = null;

		try {
			tx = session.beginTransaction();
			Email email = new Email(emailName, active); 
			emailID = (Integer) session.save(email);   
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return emailID;
	}

	/* Method to  READ all the emails */
	@SuppressWarnings("unchecked")
	public void listEmails( ){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<Email> emails = session.createQuery("FROM Email").list(); 
			for (Iterator<Email> iterator = emails.iterator(); iterator.hasNext();){
				Email email = iterator.next(); 
				System.out.println("Email Name: " + email.getEmailName()); 
				System.out.println("    Active: " + email.isActive()); 
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	/* Method to UPDATE board name of a email */
	public void updateEmail(Integer emailID, String emailName ){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Email email = session.get(Email.class, emailID); 
			email.setEmailName( emailName );
			session.update(emailName); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	/* Method to DELETE a message board from the records */
	public void deleteEmail(Integer emailID){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Email email = session.get(Email.class, emailID); 
			session.delete(email); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
}

package com.dbrigade;
import java.util.List;

import java.time.LocalDate;
import java.util.Iterator; 

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageCompany {
	private static SessionFactory factory; 

	public ManageCompany() {}

	public static void main(String[] args) {

		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}

		ManageCompany MM = new ManageCompany();

		/* Add few message board records in database */
		Integer companyID1 = MM.addCompany("Brigade HQ", 1, 3, true); 
		Integer companyID2 = MM.addCompany("Charlie Company", 3, 1, true);
		Integer companyID3 = MM.addCompany("Alpha Company", 3, 1, true); 

		/* List down all the message boards */
		MM.listCompanys();

		/* Update message board records */
		MM.updateCompany(companyID3, "Alpha Company");

		/* Delete a message board from the database */
		MM.deleteCompany(companyID2);

		/* List down new list of the message boards */
		MM.listCompanys();
	}

	/* Method to CREATE a message board in the database */
	public Integer addCompany(String companyName, Integer captainID, Integer lieutenantID, boolean active){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer companyID = null;

		try {
			tx = session.beginTransaction();
			Company company = new Company(companyName, captainID, lieutenantID, active); 
			companyID = (Integer) session.save(company);   
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return companyID;
	}

	/* Method to  READ all the companys */
	@SuppressWarnings("unchecked")
	public void listCompanys( ){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<Company> companys = session.createQuery("FROM Company").list(); 
			for (Iterator<Company> iterator = companys.iterator(); iterator.hasNext();){
				Company company = iterator.next(); 
				System.out.println(" Company Name: " + company.getCompanyName()); 
				System.out.println("   Captain ID: " + company.getCaptainID()); 
				System.out.println("Lieutenant ID: " + company.getLieutenantID()); 
				System.out.println("       Active: " + company.isActive()); 
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	/* Method to UPDATE board name of a company */
	public void updateCompany(Integer companyID, String companyName ){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Company company = session.get(Company.class, companyID); 
			company.setCompanyName( companyName );
			session.update(companyName); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	/* Method to DELETE a message board from the records */
	public void deleteCompany(Integer companyID){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Company company = session.get(Company.class, companyID); 
			session.delete(company); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
}

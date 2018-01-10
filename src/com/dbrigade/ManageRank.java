package com.dbrigade;
import java.util.List;

import java.time.LocalDate;
import java.util.Iterator; 

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageRank {
	private static SessionFactory factory; 

	public ManageRank() {}

	public static void main(String[] args) {

		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}

		ManageRank MM = new ManageRank();

		/* Add few message board records in database */
		Integer rankID1 = MM.addRank("Recruit", true); 
		Integer rankID2 = MM.addRank("Private", true);
		Integer rankID3 = MM.addRank("PFC", true); 
		Integer rankID4 = MM.addRank("Corp", true); 
		Integer rankID5 = MM.addRank("Sargeant", true); 
		Integer rankID6 = MM.addRank("Lieutenant", true); 
		Integer rankID7 = MM.addRank("Captain", true); 
		Integer rankID8 = MM.addRank("Major", true); 
		Integer rankID9 = MM.addRank("Colonel", true); 
		Integer rankID10 = MM.addRank("Command Staff", true); 
		

		/* List down all the message boards */
		MM.listRanks();

		/* Update message board records */
		MM.updateRank(rankID4, "Corporal");

		/* Delete a message board from the database */
		MM.deleteRank(rankID3);

		/* List down new list of the message boards */
		MM.listRanks();
	}

	/* Method to CREATE a message board in the database */
	public Integer addRank(String rankName, boolean active){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer rankID = null;

		try {
			tx = session.beginTransaction();
			Rank rank = new Rank(rankName, active); 
			rankID = (Integer) session.save(rank);   
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return rankID;
	}

	/* Method to  READ all the ranks */
	@SuppressWarnings("unchecked")
	public void listRanks( ){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<Rank> ranks = session.createQuery("FROM Rank").list(); 
			for (Iterator<Rank> iterator = ranks.iterator(); iterator.hasNext();){
				Rank rank = iterator.next(); 
				System.out.println("Rank Name: " + rank.getRankName()); 
				System.out.println("    Active: " + rank.isActive()); 
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	/* Method to UPDATE board name of a rank */
	public void updateRank(Integer rankID, String rankName ){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Rank rank = session.get(Rank.class, rankID); 
			rank.setRankName( rankName );
			session.update(rankName); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	/* Method to DELETE a message board from the records */
	public void deleteRank(Integer rankID){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Rank rank = session.get(Rank.class, rankID); 
			session.delete(rank); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
}

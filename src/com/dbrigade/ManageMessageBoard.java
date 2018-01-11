package com.dbrigade;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.Iterator; 

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageMessageBoard {
	private static SessionFactory factory; 

	public ManageMessageBoard() {}

	public static void main(String[] args) {

		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}

		ManageMessageBoard MM = new ManageMessageBoard();

		/* Add few message board records in database */
		Integer boardID1 = MM.addMessageBoard("Community News", true, true); 
		Integer boardID2 = MM.addMessageBoard("Brigade Commanders", true, true);
		Integer boardID3 = MM.addMessageBoard("Captain's Lounge", true, true); 
		Integer boardID4 = MM.addMessageBoard("Charlie Company", true, false); 
		Integer boardID5 = MM.addMessageBoard("Alpha Compnay", true, false); 

		/* List down all the message boards */
		MM.listMessageBoards();

		/* Update message board records */
		MM.updateMessageBoard(boardID5, "Alpha Company");

		/* Delete a message board from the database */
		MM.deleteMessageBoard(boardID4);

		/* List down new list of the message boards */
		MM.listMessageBoards();
	}

	/* Method to CREATE a message board in the database */
	public Integer addMessageBoard(String messageboardName, boolean active, boolean rankAccess){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer messageboardID = null;

		try {
			tx = session.beginTransaction();
			MessageBoard messageBoard = new MessageBoard(messageboardName, active, rankAccess); 
			messageboardID = (Integer) session.save(messageBoard);   
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return messageboardID;
	}

	/* Method to  READ all the messageboards */
	@SuppressWarnings("unchecked")
	public void listMessageBoards( ){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<MessageBoard> messageBoards = session.createQuery("FROM MessageBoard").list(); 
			for (Iterator<MessageBoard> iterator = messageBoards.iterator(); iterator.hasNext();){
				MessageBoard messageBoard = iterator.next(); 
				System.out.print("Messageboard Name: " + messageBoard.getMessageboardName()); 
				System.out.print("           Active: " + messageBoard.isActive()); 
				System.out.print(" Rank Access Only: " + messageBoard.isRankAccess()); 
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	/* Method to UPDATE board name of a messageboard */
	public void updateMessageBoard(Integer messageBoardID, String messageBoardName ){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			MessageBoard messageBoard = session.get(MessageBoard.class, messageBoardID); 
			messageBoard.setMessageboardName( messageBoardName );
			session.update(messageBoardName); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	/* Method to DELETE a message board from the records */
	public void deleteMessageBoard(Integer messageBoardID){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			MessageBoard messageBoard = session.get(MessageBoard.class, messageBoardID); 
			session.delete(messageBoard); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
	
	public Integer getNamedNewsBoard(String messageBoardName){
		EntityManager em = DBUtil.emf.createEntityManager();
		String sqlStatement = "SELECT messageboard_id FROM MESSAGEBOARD m WHERE messageboard_name = '"
				+ messageBoardName + "' AND messageboard_active = 1 ORDER BY messageboard_id DESC";
		
		TypedQuery<MessageBoard> q2 =
			      em.createQuery(sqlStatement, MessageBoard.class);
		return q2.getFirstResult();
	}
}

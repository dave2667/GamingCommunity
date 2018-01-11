package com.dbrigade;
import java.util.List;

import javax.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator; 

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageMessage {
	private static SessionFactory factory; 

	public ManageMessage() {}

	public static void main(String[] args) {

		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}


		
		ManageMessage MM = new ManageMessage();

		/* Add few message records in database */
		Integer messageID1 = MM.addMessage(1, true, 0, LocalDateTime.now(), 1, "Code of conduct has been revised", "All members please check the for a revised code of conduct.  The code of conduct can be found at the top of every page by clicking the 'Conduct' button in the top navigation bar."); 
		Integer messageID2 = MM.addMessage(1, true, 0, LocalDateTime.now(), 3, "Alpha Company meeting for 1/18 has been cancelled", "Due to the widespread flu epidemic, the online meeting will be cancelled for this week.  We will meet regular time next week.");
		Integer messageID3 = MM.addMessage(3, true, 0, LocalDateTime.now(), 3, "Welcome our newest member", "Alpha Coompany has its newest recruit!  Please welcome IM_Newbie to the team.  Show him theropes and show him why Alpha is 'A+'!");

		/* List down all the messages */
		MM.listMessages();

		/* Update message records */
		MM.updateMessage(messageID3, "Welcome our newest member", "Alpha Company has its newest recruit!  Please welcome IM_Newbie to the team.  Show him the ropes and show him why Alpha is 'A+'!");

		/* Delete a message from the database */
		//MM.deleteMessage(messageID2);

		/* List down new list of the messages */
		MM.listMessages();
	}

	/* Method to CREATE a message in the database */
	public Integer addMessage(int messageBoardID, boolean mainMessage, int mainID, LocalDateTime postTime,
			int memberID, String messageTitle, String messageBody){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer messageID = null;

		try {
			tx = session.beginTransaction();
			Message message = new Message(messageBoardID, mainMessage, mainID, postTime,
					memberID, messageTitle, messageBody); 
			messageID = (Integer) session.save(message);   
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return messageID;
	}

	/* Method to  READ all the messages */
	@SuppressWarnings("unchecked")
	public void listMessages( ){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<Message> messages = session.createQuery("FROM Message").list(); 
			for (Iterator<Message> iterator = messages.iterator(); iterator.hasNext();){
				Message message = iterator.next(); 
		System.out.println("messageboard_id = " + message.getMessageBoardID());
		System.out.println("   main_message = " + message.isMainMessage());
		System.out.println("        main_id = " + message.getMainID());
		System.out.println("      post_time = " + message.getPostTime());
		System.out.println("      member_id = " + message.getMemberID());
		System.out.println("  message_title = " + message.getMessageTitle());
		System.out.println("   message_body = " + message.getMessageBody());
}
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	/* Method to UPDATE name of a message */
	public void updateMessage(Integer messageID, String messageTitle, String messageBody ){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Message message = session.get(Message.class, messageID); 
			message.setMessageTitle( messageTitle );
			message.setMessageBody(messageBody);
			session.update(message); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	/* Method to DELETE a message from the records */
	public void deleteMessage(Integer messageID){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Message message = session.get(Message.class, messageID); 
			session.delete(message); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
}

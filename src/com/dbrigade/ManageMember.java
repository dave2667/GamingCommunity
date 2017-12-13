package com.dbrigade;
import java.util.List;

import java.time.LocalDate;
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageMember {
   private static SessionFactory factory; 
   
   public ManageMember() {}
   
   public static void main(String[] args) {

      try {
         factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      
      ManageMember MM = new ManageMember();

      /* Add few member records in database */
      Integer mbrID1 = MM.addMember("IMDaBoss", "Johnnie", "Johnson", 1, 1,
		 LocalDate.of(2017,01,01), 1, 1, LocalDate.of(2017,01,01), true); 
      Integer mbrID2 = MM.addMember("SecondDog", "Able", "Baker", 2, 2,
    	 LocalDate.of(2017,02,01), 1, 1, LocalDate.of(2017,01,01), false);
      Integer mbrID3 = MM.addMember("MajorMajor", "Roger", "Oveur", 3, 3,
    	 LocalDate.of(2017,06,01), 2, 2, LocalDate.of(2017,05,17), true); 

      /* List down all the members */
      MM.listMembers();

      /* Update member's records */
      MM.updateMember(mbrID1, "Sharpshooter");

      /* Delete a member from the database */
      MM.deleteMember(mbrID2);

      /* List down new list of the members */
      MM.listMembers();
   }
   
   /* Method to CREATE a member in the database */
   public Integer addMember(String gamerTag, String firstName, String lastName, int emailID, int rankID,
			LocalDate rankDate, int companyID, int recommendID, LocalDate joinDate, boolean active){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer memberID = null;
      
      try {
         tx = session.beginTransaction();
         Member member = new Member(gamerTag, firstName, lastName, emailID, rankID,
        		 rankDate, companyID, recommendID, joinDate, active);
         memberID = (Integer) session.save(member);   
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close();
      }
      return memberID;
   }
   
   /* Method to  READ all the members */
   @SuppressWarnings("unchecked")
public void listMembers( ){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List<Member> members = session.createQuery("FROM Member").list(); 
         for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();){
            Member member = iterator.next(); 
            System.out.print("Gamertag: " + member.getGamerTag()); 
            System.out.print("First Name: " + member.getFirstName()); 
            System.out.print("  Last Name: " + member.getLastName()); 
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
   
   /* Method to UPDATE gamertag for a member */
   public void updateMember(Integer MemberID, String gamerTag ){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Member member = session.get(Member.class, MemberID); 
         member.setGamerTag( gamerTag );
		 session.update(member); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
   
   /* Method to DELETE a member from the records */
   public void deleteMember(Integer MemberID){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Member member = session.get(Member.class, MemberID); 
         session.delete(member); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
}
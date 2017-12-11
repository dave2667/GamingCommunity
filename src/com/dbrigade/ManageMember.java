package com.dbrigade;
import java.util.List;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageMember {
   private static SessionFactory factory; 
   public static void main(String[] args) {
      
      try {
         factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      
      ManageMember MM = new ManageMember();

      /* Add few member records in database */
      //Integer empID1 = MM.addMember("Zara", "Ali", 1000);
      //Integer empID2 = MM.addMember("Daisy", "Das", 5000);
      //Integer empID3 = MM.addMember("John", "Paul", 10000);

      /* List down all the members */
      MM.listMembers();

      /* Update member's records */
      //MM.updateMember(empID1, 5000);

      /* Delete a member from the database */
      //MM.deleteMember(empID2);

      /* List down new list of the members */
      MM.listMembers();
   }
   
   /* Method to CREATE a member in the database */
   public Integer addMember(String gamerTag, String firstName, String lastName, String emailID, int rankID,
			LocalDate rankDate, int companyID, int recommendID, LocalDate joinDate, Boolean active){
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
   public void listMembers( ){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List members = session.createQuery("FROM Member").list(); 
         for (Iterator iterator = members.iterator(); iterator.hasNext();){
            Member member = (Member) iterator.next(); 
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
         Member member = (Member)session.get(Member.class, MemberID); 
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
         Member member = (Member)session.get(Member.class, MemberID); 
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
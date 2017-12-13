package com.dbrigade;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
public class Member {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private int memberID;
	
	@Column(name = "gamertag")
	private String gamerTag;
	
	@Column(name = "first_name")	
	private String firstName;
	
	@Column(name = "last_name")	
	private String lastName;
	
	@Column(name = "email_id")	
	private int emailID;
	
	@Column(name = "rank_id")
	private int rankID;
	
	@Column(name = "rank_date")	
	private LocalDate rankDate;
	
	@Column(name = "company_id")	
	private int companyID;
	
	@Column(name = "recommend_id")	
	private int recommendID;
	
	@Column(name = "join_date")	
	private LocalDate joinDate;
	
	@Column(name = "member_active")	
	private boolean active;
	
	public Member() {	}
	
	public Member(String gamerTag, String firstName, String lastName, int emailID, int rankID,
			LocalDate rankDate, int companyID, int recommendID, LocalDate joinDate, boolean active) {
		super();
		this.gamerTag = gamerTag;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.rankID = rankID;
		this.rankDate = rankDate;
		this.companyID = companyID;
		this.recommendID = recommendID;
		this.joinDate = joinDate;
		this.active = active;
	}

	public int getMemberID() {
		return memberID;
	}

	public String getGamerTag() {
		return gamerTag;
	}
	
	public void setGamerTag(String gamerTag) {
		this.gamerTag = gamerTag;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getEmailID() {
		return emailID;
	}
	
	public void setEmailID(int emailID) {
		this.emailID = emailID;
	}
	
	public int getRankID() {
		return rankID;
	}
	
	public void setRankID(int rankID) {
		this.rankID = rankID;
	}
	
	public LocalDate getRankDate() {
		return rankDate;
	}
	
	public void setRankDate(LocalDate rankDate) {
		this.rankDate = rankDate;
	}
	
	public int getCompanyID() {
		return companyID;
	}
	
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	
	public int getRecommendID() {
		return recommendID;
	}
	
	public void setRecommendID(int recommendID) {
		this.recommendID = recommendID;
	}
	
	public LocalDate getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}	 	

}

package com.dbrigade;
import javax.persistence.*;

@Entity
@Table(name = "COMPANY")
public class Company {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private int companyID;
	
	@Column(name = "company_name")	
	private String companyName;
	
	@Column(name = "captain_id")
	private int captainID;
	
	@Column(name = "lieutenant_id")	
	private int lieutenantID;
	
	@Column(name = "company_active")	
	private boolean active;
	
	public Company() {	}
	
	public Company(String companyName, int captain_id, int lieutenantID, boolean active) {
		super();
		this.companyName = companyName;
		this.captainID = captain_id;
		this.lieutenantID = lieutenantID;
		this.active = active;
	}

	public int getCompanyID() {
		return companyID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCaptainID() {
		return captainID;
	}

	public void setCaptainID(int captainID) {
		this.captainID = captainID;
	}

	public int getLieutenantID() {
		return lieutenantID;
	}

	public void setLieutenantID(int lieutenantID) {
		this.lieutenantID = lieutenantID;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}	
}

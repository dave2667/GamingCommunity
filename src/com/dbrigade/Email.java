package com.dbrigade;
import javax.persistence.*;

@Entity
@Table(name = "EMAIL")
public class Email {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "email_id")
	private int emailID;
	
	@Column(name = "email_name")	
	private String emailName;
	
	@Column(name = "email_active")	
	private boolean active;
	
	public Email() {	}
	
	public Email(String emailName, boolean active) {
		super();
		this.emailName = emailName;
		this.active = active;
	}

	public int getEmailID() {
		return emailID;
	}

	public String getEmailName() {
		return emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
